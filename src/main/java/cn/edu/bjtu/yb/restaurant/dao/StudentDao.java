package cn.edu.bjtu.yb.restaurant.dao;

import java.io.IOException;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.SqlSession;

import cn.edu.bjtu.yb.restaurant.bean.StudentBean;
import cn.edu.bjtu.yb.restaurant.dao.sqlprovider.StudentProvider;
import cn.edu.bjtu.yb.restaurant.util.SqlUtil;

public interface StudentDao {
	
	@InsertProvider(method = "insertProvider", type = StudentProvider.class)
	public int insertOne(StudentBean stu);
	
	@UpdateProvider(method = "updateProvider", type = StudentProvider.class)
	public int updateOne(StudentBean stu);
	
	@Select("SELECT * FROM stuuser WHERE username=#{username} and password=#{password}")
	public StudentBean queryOne(@Param("username")String username,@Param("password")String password);

	public static void main(String[] args) {
		SqlSession session;
		try {
			session = SqlUtil.getSession();
			StudentDao dao = session.getMapper(StudentDao.class);
			StudentBean bean = new StudentBean();
			bean.setName("test2");
			bean.setPassword("test2");
			bean.setUsername("test2");
			dao.insertOne(bean);
			session.commit();
			session.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}