	//“全选”功能函数
	function SelectAll(){
		var select=document.getElementsByName("check_Food");
		var judge=false;
		
		for(var i=0;i<select.length;i++){
			if(select[i].checked==false){
				judge=true;
				break;
			}
		}
		
		if(judge==true){
			for(var i=0;i<select.length;i++){
				select[i].checked=true;
			}
		}else{
			for(var i=0;i<select.length;i++){
				select[i].checked=false;
			}
		}
	}
	
	//“删除”功能函数
	function Delete(){
		if(confirm("确定要删除已选择的菜品信息吗？")){
			alert("Yes");
			//传递参数，删除数据
			windows.location.reload();
		}
	}
	
	//“新增”功能函数
	function Add(){
		window.location.href="#";
	}
	
	//“编辑”功能函数
	function Edit(dishid,windowid,restaurantid){
		window.location.href="/editdish?dishid="+dishid
		+"&windowid="+windowid+"&restaurantid="+restaurantid;
		//函数需要参数传递，接收参数，建议用菜品id
		//window.location.href="Edit.html"//+"?"+菜品id
	}