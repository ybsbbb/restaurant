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
1.认证成功，用户个人资料，eg:{"gender":"无","name":"测试学生","age":18,"account":100,"username":"test"}  	
gender 性别,name 昵称, age 年龄, account 余额, username 用户身份标识  
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

6.[ip]:8080/orders/order  
*请求类型*：POST
*参数*：
username 用户名 必须  
order 订单 必须，格式为JSON字符串，eg:添加
{"menu":[{"window":"1","dish":"1","number":"1"},{"window":"1","dish":"1","number":"1"}],"restaurant":"1","price":"1000","taketime":"2017-05-05 11:11:11"}  
menu: 菜单，是用户选择的菜，是一个array，内容为JSON对象，该对象包括window 该菜所属窗口、dish 该菜的id、number 该菜的份数  
restaurant：餐厅id，表明该订单是在那个餐厅下的单  
price：总价格  
taketime：取餐时间，是一个时间格式的字符串 yyyy-mm-dd hh:MM:ss.sss  
*返回*：  
1.下单成功：返回订单号  
2.下单失败：返回""  
  
7.[ip]:8080/orders/user/{userid}  
*请求类型*：GET  
*参数*：  
userid 路径变量  用户id
*返回*：  
查询结果， JSONArray eg:[{"price":1,"id":"201705041713320000","ordertime":"2017-05-04 17:13:33.0"},{"price":1000,"id":"201705051040420000","ordertime":"2017-05-05 10:40:43.0"}]  
这是订单简要信息  

8.[ip]:8080/orders/restaurant/{restaurantid}  
*请求类型*：GET  
*参数*：  
restaurantid 路径变量  餐厅id
*返回*：  
查询结果， JSONArray eg:[{"price":1,"id":"201705041713320000","ordertime":"2017-05-04 17:13:33.0"},{"price":1000,"id":"201705051040420000","ordertime":"2017-05-05 10:40:43.0"}]  
这是订单简要信息


9.[ip]:8080/orders/order/{id}  
*请求类型*：GET  
*参数*：  
id 路径变量  订单id
*返回*：  
查询结果， JSONObject eg:{"taketime":"2017-05-05 11:11:11.0","price":1,"restaurant":1,"id":"201705041713320000","state":0,"ordertime":"2017-05-04 17:13:33.0","menu":"[{\"number\":1,\"dish\":1,\"window\":1},{\"number\":2,\"dish\":2,\"window\":2}]","customer":"test"}  
这是订单详细信息