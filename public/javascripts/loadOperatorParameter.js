function loadOperatorParameter01(){
	
	var row = $('#experdgid01').datagrid('getSelected');
	/*
	alert(row.name);
	alert(row.operator.operatorClass+row.operator.operatorMethod);
	*/
	if (row){
		var name = row.name;
		var operator = row.operator;
		var description = operator.description;
		$("#XiangGuanShuoMing").text(description);
		var methodArgumentName = operator.methodArgumentName;
//		alert(methodArgumentName);
		var methodArgumentValue = operator.methodArgumentValue;
//		alert(methodArgumentValue);
		var methodFrontStyle = operator.methodFrontStyle;
//		alert(methodFrontStyle);
		var length = methodArgumentName.length;
		var element = $("");
		var str="";
		for (var i=0; i<length; i++) {
			alert(methodFrontStyle[i]);
		}
	} else {
		alert("loadOperatorParameter.js函数中row没有发现");
	}
	
}

function loadOperatorParameter02(){
	
	
	
}