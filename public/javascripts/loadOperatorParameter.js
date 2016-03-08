function loadOperatorParameter01(){
	
	$("#argumentSetID").empty();
	
	var row = $('#experdgid01').datagrid('getSelected');
	
	/*
	alert(row.name);
	alert(row.operator.operatorClass+row.operator.operatorMethod);
	*/
	if (row){
		var name = row.name;
		var operator = row.operator;
		var description = operator.description;
		$("#XiangGuanShuoMing").html("<p>"+description+"</p>");
		
		var methodArgumentName = operator.methodArgumentName;
		var methodArgumentValue = operator.methodArgumentValue;
		var methodFrontStyle = operator.methodFrontStyle;
		
		var methodLength = methodArgumentName.length;
		for (var i=0; i<methodLength; i++) {
			var inputformid="inputformid"+i;
			if (methodFrontStyle[i]=="textbox") {
				var temp = $("<p>"+methodArgumentName[i]+":<br/><input id=" + inputformid + " class='easyui-textbox' value="+methodArgumentValue[i]+"></p>");
				$("#argumentSetID").append(temp);
			} else if (methodFrontStyle[i]=="numberbox") {
				var temp = $("<p>"+methodArgumentName[i]+":<br/><input id=" + inputformid + " class='easyui-numberbox' value="+methodArgumentValue[i]+"></p>");
				$("#argumentSetID").append(temp);
			} else if (methodFrontStyle[i]=="filebox") {
				var temp = $("<p>"+methodArgumentName[i]+":<br/><input id=" + inputformid + " class='easyui-filebox' value="+methodArgumentValue[i]+">"+
						"<a href='#' class='easyui-linkbutton' style='width:50px;margin-left:10px;'>上传</a>"+"</p>");
				$("#argumentSetID").append(temp);
			}
		}
		var temp = $("<p>"+"<a href='#' class='easyui-linkbutton' style='width:80px;margin-top:5px;' onclick='saveValueBack01()'>保存</a>"+"</p>");
		$("#argumentSetID").append(temp);
		$.parser.parse("#argumentSetID");
	} else {
		alert("loadOperatorParameter.js函数中row没有发现");
	}
	
}



function loadOperatorParameter02(){
	
}



function saveValueBack01() {
	var row = $('#experdgid01').datagrid('getSelected');
	if (row){
		var operator = row.operator;
		var methodArgumentName = operator.methodArgumentName;
		var methodArgumentValue = operator.methodArgumentValue;
		var methodFrontStyle = operator.methodFrontStyle;
//		alert(methodArgumentName);
//		alert(methodArgumentValue);
//		alert(methodFrontStyle);
		
		var methodLength = methodArgumentName.length;
		for (var i=0; i<methodLength; i++) {
			var inputformid="inputformid"+i;
			var value = $("#"+inputformid).val();
			methodArgumentValue[i] = value;
		}
		alert("保存成功");
	} else {
		alert("loadOperatorParameter.js函数存回表单值时row没有发现");
		alert("保存失败");
	}
}
