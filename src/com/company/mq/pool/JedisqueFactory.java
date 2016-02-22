package com.company.mq.pool;

import java.net.URI;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import com.github.xetorthio.jedisque.Jedisque;

public class JedisqueFactory  implements PooledObjectFactory<Jedisque> {

	URI[] uris;
	public JedisqueFactory(URI... uris) {
		super();
		this.uris = uris;
	}


	@Override
	public void activateObject(PooledObject<Jedisque> pooledJedis) throws Exception {
		//final Jedisque jedis = pooledJedis.getObject();
//		jedis.isConnected()
	}


	  @Override
	  public void destroyObject(PooledObject<Jedisque> pooledJedis) throws Exception {
	    final Jedisque jedis = pooledJedis.getObject();
	    if (jedis.isConnected()) {
	      try {
	        try {
	          jedis.close();
	        } catch (Exception e) {
	        }
	        jedis.disconnect();
	      } catch (Exception e) {

	      }
	    }

	  }


	@Override
	public PooledObject<Jedisque> makeObject() throws Exception {
		Jedisque jedisque= new Jedisque(uris);
//		jedisque.connect();
		   return new DefaultPooledObject<Jedisque>(jedisque);
	}

	@Override
	public void passivateObject(PooledObject<Jedisque> arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateObject(PooledObject<Jedisque> pooledJedis) {
		  final Jedisque jedis = pooledJedis.getObject();
		 return jedis.isConnected();
	}

}
