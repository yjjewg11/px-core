package com.company.news.commons.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.company.news.SystemConstants;
import com.company.news.entity.Student;

public class ExcelUtil {

	private static WritableFont wf = new WritableFont(WritableFont.TIMES, 12,
			WritableFont.BOLD);
	private static WritableCellFormat cf = new WritableCellFormat(wf);
	static {
		try {
			cf.setAlignment(jxl.format.Alignment.CENTRE);
			cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格的内容垂直方向居中
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 单元格中的内容水平方向居中
	}

	public static void outputPrintWriterStream(HttpServletResponse response,
			String fname, List<Student> list) throws Exception {
		response.setHeader("Pragma", "no-cache");
		fname = java.net.URLEncoder.encode(fname,"UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(fname.getBytes("UTF-8"),"GBK") + ".xls");
		response.setContentType("application/msexcel");// 定义输出类型
		response.setCharacterEncoding(SystemConstants.Charset);

		createExcel(response.getOutputStream(), list);

		// out.close();
	}

	public static void createExcel(OutputStream os, List<Student> list) throws IOException, RowsExceededException,
			WriteException {
		// 创建工作区
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		// 创建新的一页，sheet只能在工作簿中使用
		WritableSheet sheet = workbook.createSheet("sheet1", 0);

		// 构造表头
		sheet.mergeCells(0, 0, 7, 0);// 添加合并单元格，第一个参数是起始列，第二个参数是起始行，第三个参数是终止列，第四个参数是终止行
		WritableFont bold = new WritableFont(WritableFont.ARIAL, 16,
				WritableFont.BOLD);// 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体显示
		WritableCellFormat titleFormate = new WritableCellFormat(bold);// 生成一个单元格样式控制对象
		titleFormate.setAlignment(jxl.format.Alignment.CENTRE);// 单元格中的内容水平方向居中
		titleFormate.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格的内容垂直方向居中

		sheet.setColumnView(0, 25);
		sheet.setColumnView(1, 25);
		sheet.setColumnView(2, 10);
		sheet.setColumnView(3, 25);
		sheet.setColumnView(4, 30);
		sheet.setColumnView(5, 30);
		sheet.setColumnView(6, 30);
		sheet.setColumnView(7, 10);

		Label title = new Label(0, 0, "幼儿园基本情况登记表", titleFormate);
		sheet.setRowView(0, 600, false);// 设置第一行的高度
		sheet.addCell(title);

		sheet.mergeCells(0, 1, 2, 1);
		Label youerxinxi = new Label(0, 1, "幼儿信息", cf);
		sheet.addCell(youerxinxi);

		sheet.mergeCells(3, 1, 6, 1);
		Label jiatingxinxi = new Label(3, 1, "家庭信息", cf);
		sheet.addCell(jiatingxinxi);

		sheet.mergeCells(7, 1, 7, 2);
		Label beizhu = new Label(7, 1, "备注", cf);
		sheet.addCell(beizhu);

		Label youerxingming = new Label(0, 2, "幼儿姓名", cf);
		sheet.addCell(youerxingming);

		Label idcard = new Label(1, 2, "身份证号", cf);
		sheet.addCell(idcard);

		Label xingbie = new Label(2, 2, "性别", cf);
		sheet.addCell(xingbie);

		Label xingming = new Label(3, 2, "姓名", cf);
		sheet.addCell(xingming);

		Label gongzuodanwei = new Label(4, 2, "工作单位", cf);
		sheet.addCell(gongzuodanwei);

		Label lianxidianhua = new Label(5, 2, "联系电话", cf);
		sheet.addCell(lianxidianhua);

		Label zhuzhi = new Label(6, 2, "住址", cf);
		sheet.addCell(zhuzhi);

		createStudentCell(sheet, list);

		// 将内容写到输出流中，然后关闭工作区，最后关闭输出流
		workbook.write();
		workbook.close();
		os.close();
	}

	/**
	 * 
	 * @param sheet
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	private static void createStudentCell(WritableSheet sheet,
			List<Student> list) throws RowsExceededException, WriteException {
		int i = 3;
		for (Student s : list) {
			sheet.mergeCells(0, i, 0, i + 1);
			Label youerxingming = new Label(0, i, s.getName());
			sheet.addCell(youerxingming);

			sheet.mergeCells(1, i, 1, i + 1);
			Label idcard = new Label(1, i, s.getIdcard());
			sheet.addCell(idcard);

			sheet.mergeCells(2, i, 2, i + 1);
			Label xingbie = new Label(2, i, s.getSex() != 1 ? "男" : "女");
			sheet.addCell(xingbie);

			Label fu_xingming = new Label(3, i, "父亲姓名:" + (s.getBa_name()!=null?s.getBa_name():""));
			sheet.addCell(fu_xingming);
			Label ma_xingming = new Label(3, i + 1, "母亲姓名:" + (s.getMa_name()!=null?s.getMa_name():""));
			sheet.addCell(ma_xingming);

			Label fu_gongzuodanwei = new Label(4, i, s.getBa_work());
			sheet.addCell(fu_gongzuodanwei);

			Label ma_gongzuodanwei = new Label(4, i + 1, s.getMa_work());
			sheet.addCell(ma_gongzuodanwei);

			Label fu_lianxidianhua = new Label(5, i, s.getBa_tel());
			sheet.addCell(fu_lianxidianhua);

			Label ma_lianxidianhua = new Label(5, i + 1, s.getMa_tel());
			sheet.addCell(ma_lianxidianhua);

			sheet.mergeCells(6, i, 6, i + 1);
			Label zhuzhi = new Label(6, i, s.getAddress());
			sheet.addCell(zhuzhi);

			sheet.mergeCells(7, i, 7, i + 1);
			Label beizhu = new Label(7, i, "");
			sheet.addCell(beizhu);
			
			i=i+2;

		}

	}
}
