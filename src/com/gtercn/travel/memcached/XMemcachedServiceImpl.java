package com.gtercn.travel.memcached;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.Counter;
import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.auth.AuthInfo;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.travel.utils.CachedKeys;

@Service(value = "xMemcachedService")
public class XMemcachedServiceImpl implements XMemcachedService {
	@Autowired
	private MemcachedClient memcachedClient;

	@Override
	public <T> T get(String key, long timeout) {
		try {
			return memcachedClient.get(key, timeout);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void delete(String key) {
		try {
			memcachedClient.deleteWithNoReply(key);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 计数器取值，支持分布式系统
	 */
	@Override
	public long getCount(long min, long max, String key) {
		long count = min;
		Counter counter = memcachedClient.getCounter(key);
		try {
			count = counter.incrementAndGet();
			if (count > max) {
				counter.set(min);
				count = min;
			}
		} catch (MemcachedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public <T> Map<String, GetsResponse<T>> gets(Collection<String> coll) {
		try {
			return memcachedClient.gets(coll);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean replace(String value, int index, Object obj) {
		try {
			return memcachedClient.replace(value, index, obj);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean set(final String key, final int exp, final Object value) {
		try {
			return memcachedClient.set(key, exp, value);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean add(String key, int exp, Object value) {
		try {
			return memcachedClient.add(key, exp, value);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void addWithNoReply(String key, int exp, Object value) {
		try {
			memcachedClient.addWithNoReply(key, exp, value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean cas(String key, int exp, Object value, long cas) {
		try {
			return memcachedClient.cas(key, exp, value, cas);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void deleteWithNoReply(String key) {
		try {
			memcachedClient.deleteWithNoReply(key);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}

	

	@Override
	public void replaceWithNoReply(String key, int exp, Object value) {
		try {
			memcachedClient.replace(key, exp, value);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Store key-value item to memcached,doesn't wait for reply
	 * 
	 * @param <T>
	 * @param key
	 *            stored key
	 * @param exp
	 *            An expiration time, in seconds. Can be up to 30 days. After 30
	 *            days, is treated as a unix timestamp of an exact date.
	 * @param value
	 *            stored data
	 */
	@Override
	public void setWithNoReply(String key, int exp, Object value) {
		try {
			memcachedClient.setWithNoReply(key, exp, value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public <T> void setStoreList(Collection<T> list) {
		try {
			memcachedClient.setWithNoReply(CachedKeys.SHOP_KEYS_LIST,
					CachedKeys.EXPIRE_TIME, list);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public <T> Map<String,T> getStoreList(Collection<String> keys) {
		Map<String,T> resultMap = new HashMap<String,T>();
		Map<String, GetsResponse<Object>> map = gets(keys);
		if (null != map && map.size() > 0) {
			Collection<GetsResponse<Object>> result = new ArrayList<GetsResponse<Object>>(
					map.values());
			for (GetsResponse<Object> temp : result) {
				T t = (T) temp.getValue();
				resultMap.put(CachedKeys.SHOP_KEYS_LIST, t);
			}
		}
		return resultMap;
	}

	/**
	 * Memcached 1.4.3开始支持SASL验证客户端，在服务器配置启用SASL之后，
	 * 客户端需要通过授权验证才可以跟memcached继续交互，否则将被拒绝请求。
	 * XMemcached1.2.5开始支持这个特性。假设memcached设置了SASL验证，
	 * 典型地使用CRAM-MD5或者PLAIN的文本用户名和密码的验证机制， 假设用户名为cacheuser，密码为123456，
	 * 
	 * @throws IOException
	 */
	public void authority() {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil
				.getAddresses("127.0.0.1:11211"));
		builder.addAuthInfo(AddrUtil.getOneAddress("127.0.0.1:11211"), AuthInfo
				.typical("cacheuser", "123456"));
		// Must use binary protocol
		builder.setCommandFactory(new BinaryCommandFactory());
		try {
			MemcachedClient client = builder.build();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public <T> boolean isKeyExist(String key) {
		 try {
			T  t =get(key,CachedKeys.EXPIRE_TIME);
			 if(t!=null)
				 return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
