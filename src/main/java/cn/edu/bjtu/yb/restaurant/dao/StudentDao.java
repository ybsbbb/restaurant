package cn.edu.bjtu.yb.restaurant.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.edu.bjtu.yb.restaurant.bean.StudentBean;

public interface StudentDao {
	
	@Insert("INSERT INTO stuuser")
	public int insert();
	
	@Select("SELECT * FROM stuuser WHERE username=#{username} and password=#{password}")
	public StudentBean queryOne(@Param("username")String username,@Param("password")String password);
}