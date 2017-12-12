package cn.e3.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.JedisCluster;

public class JedisSpring {
	/**
	 * 需求:测试spring整合jedis
	 */
	@Test
	public void JedisSpringTest(){
		//加载spring的配置文件
		ApplicationContext ap = new ClassPathXmlApplicationContext("classpath*:spring/jedis.xml");
		JedisCluster jc = ap.getBean(JedisCluster.class);
		//设置值
		jc.set("address", "武当山");
	}
	
}
