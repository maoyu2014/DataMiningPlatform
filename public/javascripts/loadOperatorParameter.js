function loadOperatorParameter01(){
	
	$("#argumentSetID").empty();
	
	var row = $('#experdgid01').datagrid('getSelected');
	var index = $('#experdgid01').datagrid('getRowIndex', row);
	
	/*
	alert(row.name);
	alert(row.operator.operatorClass+row.operator.operatorMethod);
	*/
	if (row){
		var name = row.name;
		var operator = row.operator;
		var description = operator.description;
		$("#XiangGuanShuoMing").html("<p>"+description+"</p>");
		
		var classArgumentName = operator.classArgumentName;
		var classArgumentValue = operator.classArgumentValue;
		var classFrontStyle = operator.classFrontStyle;
		if (classArgumentName!=undefined) { 
			var classLength = classArgumentName.length;
			for (var i=0; i<classLength; i++) {
				var value = classArgumentValue[i];
				var classinputformid="classinputformid"+i;
				if (classFrontStyle[i]=="textbox") {
					var temp = $("<p>"+classArgumentName[i]+":<br/><input id=" + classinputformid + " class='easyui-textbox' value="+value+"></p>");
					$("#argumentSetID").append(temp);
				} else if (classFrontStyle[i]=="numberbox") {
					var temp = $("<p>"+classArgumentName[i]+":<br/><input id=" + classinputformid + " class='easyui-numberbox' value="+value+"></p>");
					$("#argumentSetID").append(temp);
				} else if (classFrontStyle[i]=="filebox") {
					var temp = $("<p>"+classArgumentName[i]+":<br/><input id=" + classinputformid + " class='easyui-filebox' >"+
							"<a href='#' class='easyui-linkbutton' style='width:50px;margin-left:10px;'>上传</a>"+"</p>");
					var temp2 = $("<p>"+value+"</p>");
					$("#argumentSetID").append(temp, temp2);
				}
			}
		}
		
		var methodArgumentName = operator.methodArgumentName;
		var methodArgumentValue = operator.methodArgumentValue;
		var methodFrontStyle = operator.methodFrontStyle;
		if (methodArgumentName!=undefined) {
			var methodLength = methodArgumentName.length;
			for (var i=0; i<methodLength; i++) {
				var value = methodArgumentValue[i];
				/*
				if (value==undefined) {
					value=null;
				}
				*/
				var methodinputformid="methodinputformid"+i;
				if (methodFrontStyle[i]=="textbox") {
					var temp = $("<p>"+methodArgumentName[i]+":<br/><input id=" + methodinputformid + " class='easyui-textbox' value="+value+"></p>");
					$("#argumentSetID").append(temp);
				} else if (methodFrontStyle[i]=="numberbox") {
					var temp = $("<p>"+methodArgumentName[i]+":<br/><input id=" + methodinputformid + " class='easyui-numberbox' value="+value+"></p>");
					$("#argumentSetID").append(temp);
				} else if (methodFrontStyle[i]=="filebox") {
					var temp = $("<p>"+methodArgumentName[i]+":<br/><input id=" + methodinputformid + " class='easyui-filebox' >"+
							"<a href='#' class='easyui-linkbutton' style='width:50px;margin-left:10px;'>上传</a>"+"</p>");
					var temp2 = $("<p>"+value+"</p>");
					$("#argumentSetID").append(temp, temp2);
				}
			}
		}
		
		var temp = $("<p>"+"<a href='#' class='easyui-linkbutton' style='width:80px;margin-top:5px;' onclick='saveValueBack01("+index+")'>保存</a>"+"</p>");
		$("#argumentSetID").append(temp);
		$.parser.parse("#argumentSetID");
	} else {
		alert("loadOperatorParameter.js函数:row没有发现");
	}
	
}


function saveValueBack01(oldindex) {
	var row = $('#experdgid01').datagrid('getSelected');
	var newindex = $('#experdgid01').datagrid('getRowIndex', row);
	if (newindex != oldindex) {
		alert("请先保存该算子参数后再切换到其他算子调参");
		return;
	}
	if (row){
		var operator = row.operator;
		
		var classArgumentName = operator.classArgumentName;
		var classArgumentValue = operator.classArgumentValue;
		var classFrontStyle = operator.classFrontStyle;
		if (classArgumentName!=undefined) {
			var classLength = classArgumentName.length;
			for (var i=0; i<classLength; i++) {
				var classinputformid="classinputformid"+i;
				var value;
				if (classFrontStyle[i]=="textbox") {
					value = $("#"+classinputformid).textbox('getValue');
					classArgumentValue[i] = value;
				} else if (classFrontStyle[i]=="numberbox") {
					value = $("#"+classinputformid).numberbox('getValue');
					classArgumentValue[i] = parseInt(value);
				} else if (classFrontStyle[i]=="filebox") {
					value = $("#"+classinputformid).filebox('getValue');
					classArgumentValue[i] = value;
				}
			}
		}
		
		var methodArgumentName = operator.methodArgumentName;
		var methodArgumentValue = operator.methodArgumentValue;
		var methodFrontStyle = operator.methodFrontStyle;
		if (methodArgumentName!=undefined) {
			var methodLength = methodArgumentName.length;
			for (var i=0; i<methodLength; i++) {
				var methodinputformid="methodinputformid"+i;
				var value;
				if (methodFrontStyle[i]=="textbox") {
					value = $("#"+methodinputformid).textbox('getValue');
					methodArgumentValue[i] = value;
				} else if (methodFrontStyle[i]=="numberbox") {
					value = $("#"+methodinputformid).numberbox('getValue');
					methodArgumentValue[i] = parseInt(value);
				} else if (methodFrontStyle[i]=="filebox") {
					value = $("#"+methodinputformid).filebox('getValue');
					methodArgumentValue[i] = value;
				}
	//			var value = $("#"+inputformid).val();
			}
		}
		
		alert("保存成功");
	} else {
		alert("loadOperatorParameter.js函数:存回表单值时row没有发现");
		alert("保存失败");
	}
}


function loadOperatorParameter02(){
	
}



