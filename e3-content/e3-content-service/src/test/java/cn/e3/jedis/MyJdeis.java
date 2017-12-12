package cn.e3.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class MyJdeis {
	/**
	 * 需求:连接单机版的redis服务器
	 * 
	 */
	@Test
	public void linkSingleRedisWithOutPool(){
		//穿件jedis对象,连接单机版的服务器
		Jedis jedis = new  Jedis("192.168.65.150",6379);
		//添加值
		jedis.set("name", "张三丰天下无敌");
		String name = jedis.get("name");
		System.out.println(name);
	}
	
	
	/**
	 * 需求:连接单机版的redis服务器
	 * 
	 */
	@SuppressWarnings("resource")
	@Test
	public void linkSingleRedisWithPool(){
		//创建连接池配置对象
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		//设置最大的连接数
		poolConfig.setMaxTotal(2000);
		poolConfig.setMaxIdle(20);
		//创建jedis连接池对象
		JedisPool jedisPool = new JedisPool(poolConfig,"192.168.65.150",6379);
		//创建jedis连接对象,连接单机版的redis服务器
		Jedis jedis = jedisPool.getResource();
		//添加值
		jedis.set("username", "张无忌张三丰天下无敌");
		
	}
	
	
	/**
	 * 连接集群版redis服务器
	 */
	@SuppressWarnings("resource")
	@Test
	public void linkSingleRedisWithClusterRedis(){
		//创建连接池配置对象
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		//设置最大的连接数
		poolConfig.setMaxTotal(2000);
		poolConfig.setMaxIdle(20);
		
		//创建set集合对象,封装集群节点
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("192.168.65.150", 7001));
		nodes.add(new HostAndPort("192.168.65.150", 7002));
		nodes.add(new HostAndPort("192.168.65.150", 7003));
		nodes.add(new HostAndPort("192.168.65.150", 7004));
		nodes.add(new HostAndPort("192.168.65.150", 7005));
		nodes.add(new HostAndPort("192.168.65.150", 7006));
		nodes.add(new HostAndPort("192.168.65.150", 7007));
		nodes.add(new HostAndPort("192.168.65.150", 7008));
		
		//创建集群对象
		JedisCluster cluster = new JedisCluster(nodes,poolConfig);
		cluster.set("name", "redis集群测试");
		
	}
}	
