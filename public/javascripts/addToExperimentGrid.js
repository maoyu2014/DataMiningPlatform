function addToExperimentGrid01(name,operator) {
	var dg = $('#experdgid01');
    dg.datagrid('appendRow',{
		name: name,
		operator: operator,
	});
    
    //显示操作到caozuoID中
	var myDate = new Date();
	var mytime = myDate.toLocaleTimeString();     //获取当前时间
	//获取当前日期加时间
	//var mytime2 = myDate.toLocaleString();			
	var temp = $("<p>" + mytime + ":拖动算子:" + name + "</p>");
	$("#caozuoID").append(temp);
	
    /*
    alert(operator);
    alert(operator.operatorClass);
    alert(operator.operatorMethod);
    alert(operator.description);
    alert(operator.methodArgumentValue);
    alert(operator.methodArgumentName);
    alert(operator.methodFrontStyle);
    */
}

function addToExperimentGrid02(name,operator) {
	var dg = $('#experdgid02');
	dg.datagrid('appendRow',{
		name: name,
		operator: operator,
	});
    
	//显示操作到caozuoID中
	var myDate = new Date();
	var mytime = myDate.toLocaleTimeString();     //获取当前时间
	var temp = $("<p>" + mytime + ":拖动算子:" + name + "</p>");
	$("#caozuoID").append(temp);
	
}