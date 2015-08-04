package com.company.news.commons.util.upload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

import jcifs.smb.SmbFile;

import org.apache.log4j.Logger;
import org.imgscalr.Scalr;

import com.company.news.ProjectProperties;
import com.company.news.SystemConstants;
import com.company.news.commons.util.FileUtils;
import com.company.news.rest.util.SmbFileUtil;

public class DiskIUploadFile implements IUploadFile {
	// TODO Auto-generated method stub
	private static final String uploadPath = ProjectProperties.getProperty(
			"UploadFilePath", "c:/px_upload/");
	private static Logger logger = Logger.getLogger(DiskIUploadFile.class);
	@Override
	public void downloadFile() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteFile(String key) {
		logger.info("deleteFile:"+key);
		// TODO Auto-generated method stub
		if (SmbFileUtil.isSmbFileFormat(key)) {
			SmbFile file;
			try {
				file = new SmbFile(key);
				file.delete();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			File file = new File(uploadPath + key);
			file.delete();
		}
	}

	/**
	 * file 上传文件 key 上传路径和名字
	 */
	@Override
	public boolean uploadFile(InputStream input, String key, Integer type) {
		try {
			logger.info("uploadFile:"+key);
			FileUtils.createDirIfNoExists(uploadPath);

			// 生成缩略图
			BufferedImage thumbnail = Scalr.resize(ImageIO.read(input),
					Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH,
					this.getThumbSize(type)[0], this.getThumbSize(type)[1],
					Scalr.OP_ANTIALIAS);

			ImageIO.write(thumbnail, "png", new File(uploadPath + key));

			//保存原始图片
			String lastPath = "";
			String filename = "";
			// 业务数据，关联用户保存
			String[] keys = key.split("/");
			// 存在上级目录时
			if (keys.length > 1) {
				for (int i = 0; i < keys.length - 1; i++) {
					lastPath += (keys[i] + "/");
				}
			}
			filename = (keys[keys.length - 1] + "@old");

			if (!FileUtils.saveFile(input, uploadPath + lastPath, filename)) {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 
	 * @return
	 */
	private int[] getThumbSize(int type) {
		// 默认
		int[] size = { 80, 80 };
		if (SystemConstants.UploadFile_type_head.equals(type)) {
			size = new int[] { 100, 100 };
		} else if (SystemConstants.UploadFile_type_cook.equals(type)) {
			size = new int[] { 120, 120 };
		} else if (SystemConstants.UploadFile_type_xheditorimg.equals(type)) {
			size = new int[] { 160, 160 };
		}
		return size;
	}

	@Override
	public void getPath() {
		// TODO Auto-generated method stub

	}

}
