package cn.edu.bjtu.yb.restaurant.dao;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.edu.bjtu.yb.restaurant.bean.StudentBean;
import cn.edu.bjtu.yb.restaurant.dao.sqlprovider.StudentProvider;

public interface StudentDao {
	
	@InsertProvider(method = "insertProvider", type = StudentProvider.class)
	public int insertOne(StudentBean stu);
	
	@UpdateProvider(method = "updateProvider", type = StudentProvider.class)
	public int updateOne(StudentBean stu);
	
	@Select("SELECT * FROM stuuser WHERE username=#{username} and password=#{password}")
	public StudentBean queryOne(@Param("username")String username,@Param("password")String password);

}