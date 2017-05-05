package cn.edu.bjtu.yb.restaurant.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 负责SqlSession的生成和关闭,单例
 * @author 杨博
 *
 */
public class SqlUtil {
	
	private static SqlUtil sqlutil;
	private SqlSessionFactory factory;

	private SqlUtil() throws IOException {
		String resources = "mybatis-conf.xml";
		InputStream in = Resources.getResourceAsStream(resources);
		factory = new SqlSessionFactoryBuilder().build(in);
	}
	
	/**
	 * 获取SqlSession实例
	 * @return
	 * @throws IOException
	 */
	public static SqlSession getSession() throws IOException {
		if(sqlutil == null) {
			sqlutil = new SqlUtil();
		}
		return sqlutil.factory.openSession();
	}
	
	/**
	 * 关闭SqlSession
	 * @param session
	 */
	public static void closeSession(SqlSession session) {
		session.close();
	}
}
