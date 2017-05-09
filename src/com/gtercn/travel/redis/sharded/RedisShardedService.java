package com.gtercn.travel.redis.sharded;

import redis.clients.jedis.ShardedJedis;

public interface RedisShardedService {
	/**
	 * 获取redis的客户端
	 * @return
	 * 2017-4-17 下午04:45:04
	 */
	public ShardedJedis getRedisClient();
	
    /**
     * 出现异常后，将资源返还给pool 
     * @param shardedJedis
     * @param broken
     * 2017-4-17 下午04:45:31
     */
    public void returnResource(ShardedJedis shardedJedis,boolean broken);
}
