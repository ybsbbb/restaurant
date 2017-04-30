package cn.edu.bjtu.yb.restaurant.dao.sqlprovider;

import org.apache.ibatis.jdbc.SQL;

import cn.edu.bjtu.yb.restaurant.bean.StudentBean;

public class StudentProvider {

	public String insertProvider(final StudentBean stu) {
		String sql = new SQL(){
			{
				INSERT_INTO("stuuser");
				VALUES("username","#{username}");
				VALUES("password","#{password}");
				VALUES("name","#{name}");
				VALUES("age","#{age}");
				if(stu.getGender() != null) {
					VALUES("gender","#{gender}");
				}				
			}
		}.toString();
		return sql;
	}
	
	public String updateProvider(final StudentBean stu) {
		String sql = new SQL(){
			{
				UPDATE("stuuser");
				if(stu.getUsername() != null && !"".equals(stu.getUsername())){
					SET("username","#{username}");
				}
				if(stu.getPassword() != null && !"".equals(stu.getPassword())){
					SET("password","#{password}");
				}
				if(stu.getName() != null && !"".equals(stu.getName())){
					SET("name","#{name}");
				}
				if(stu.getGender() != null && !"".equals(stu.getGender())){
					SET("gender","#{gender}");
				}
			}
		}.toString();
		
		return sql;
	}
	
}
