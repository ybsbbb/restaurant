		//“提交”功能函数
		function Subm(){
			var foodName=document.getElementById("FoodName").value;
			var price=document.getElementById("Price").value;
			var status=document.getElementById("Status").value;
			var describe=document.getElementById("Describe").value;
			
			alert(foodName);
			alert(price);
			alert(status);
			alert(describe);
			//插入数据
			
			window.location.href="Windows_1.html";
		}
	
		//“取消”功能函数
		function Quit(){
			window.location.href="Windows_1.html";
		}