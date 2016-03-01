package com.company.mq.pool;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.log4j.Logger;

import redis.clients.jedis.exceptions.JedisException;
import redis.clients.util.Pool;

import com.company.news.ProjectProperties;
import com.github.xetorthio.jedisque.Jedisque;

public class JedisquePool extends Pool<Jedisque> {

	URI[] uris;

	public JedisquePool() {
		try {
			String urisString = ProjectProperties.getProperty("jedisque.uris",
					"disque://120.25.212.44:7711");

			logger.info("jedisque.uris=" + urisString);
			String enable = ProjectProperties.getProperty("jedisque.enable",
					"true");
			if (!enable.equals("true")) {
				return;
			}
			String[] urisStringArr = urisString.split(",");
			uris = new URI[urisStringArr.length];

			for (int i = 0; i < urisStringArr.length; i++) {

				uris[i] = new URI(urisStringArr[i]);

			}
			this.internalPool = new GenericObjectPool<Jedisque>(
					new JedisqueFactory(uris), new GenericObjectPoolConfig());

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Logger logger = Logger.getLogger("PxJedisquePool");

	@Override
	public Jedisque getResource() {
		Jedisque jedis = super.getResource();

		return jedis;
	}

	/**
	 * @deprecated starting from Jedisque 3.0 this method will not be exposed.
	 *             Resource cleanup should be done using @see
	 *             {@link redis.clients.jedis.Jedisque#close()}
	 */
	@Override
	@Deprecated
	public void returnBrokenResource(final Jedisque resource) {
		if (resource != null) {
			returnBrokenResourceObject(resource);
		}
	}

	/**
	 * @deprecated starting from Jedisque 3.0 this method will not be exposed.
	 *             Resource cleanup should be done using @see
	 *             {@link redis.clients.jedis.Jedisque#close()}
	 */
	@Override
	@Deprecated
	public void returnResource(final Jedisque resource) {
		if (resource != null) {
			try {
				// resource.resetState();
				returnResourceObject(resource);
			} catch (Exception e) {
				returnBrokenResource(resource);
				throw new JedisException(
						"Could not return the resource to the pool", e);
			}
		}
	}
}
