# restaurant
### 相关说明

1.本项目使用`maven`构建，相关依赖需要`maven`来管理  
2.jdk版本 1.8，其他版本没有测试  
3.启动类为 `cn.edu.bjtu.yb.restaurant.App`， `Spring Boot` 内嵌`Tomcat`，直接以普通java项目运行即可  

### rest接口:
1.[ip]:8080/user,  
*请求类型*：GET  
*参数*：  
username 用户名，必须  
password 密码，必须  
*返回*：  
1.认证成功，用户个人资料，eg:{"gender":"无","name":"测试学生","age":18,"account":100,"token":"test"}  	
gender 性别,name 昵称, age 年龄, account 余额, token 用户身份标识  
2.认证失败，""  

2.[ip]:8080/user,  
*请求类型*：POST  	
*参数*：  
username 用户名，必须  
password 密码，必须  		
name 名称/昵称，必须  	
age 年龄，可选  
gender 性别，可选，0表示不详，1表示男，2表示女  
*返回*：  
1.注册成功，用户个人资料  
2.注册失败，""  

3.[ip]:8080/restaurants,  
*请求类型*：GET  
*参数*：
*cookie*：token 用户身份标识  
*返回*：  
1.token认证成功：餐厅列表，eg:[{"name":"测试餐厅","id":1,"pic":"/img/restaurant/1.jpg"},{"name":"测试餐厅2","id":2,"pic":"/img/restaurant/2.jpg"}]  
name 餐厅名称，id 餐厅id，pic 餐厅图片路径(静态资源)  
2.token认证失败：""  

4.[ip]:8080/restaurants/{restaurant} or [ip]:8080/restaurants/{restaurant}/windows  
*请求类型*：GET  
*参数*：路径变量，将{restaurant}处替换为餐厅id  
*cooki*e：token 用户身份标识  
*返回*：  
1.token认证成功：指定餐厅的窗口列表，eg：  
2.token认证失败：""  

5.[ip]:8080/restaurants/{restaurant}/windows/{window}  
*请求类型*：GET  
*参数*：路径变量restaurant，将{restaurant}处替换为餐厅id  
路径变量window，将{window}处替换为窗口id  
*cookie*：token 用户身份标识  
*返回*：  
1.token认证成功：指定餐厅的指定窗口的菜列表，eg：["{\"price\":1000,\"restaurant\":1,\"name\":\"测试菜\",\"description\":\"测试菜，不可食用\",\"id\":16,\"pic\":\"/img/dish/1.png\",\"window\":1}","{\"price\":1000,\"restaurant\":1,\"name\":\"测试菜2\",\"description\":\"测试菜2，不可食用\",\"id\":17,\"pic\":\"/img/dish/2.png\",\"window\":1}"]  
price 价格，restaurant 餐厅id，name 菜名，description 菜的描述，id，菜的id，pic 菜样图的路径(静态资源)，window 窗口id  
2.token认证失败：""  
	
