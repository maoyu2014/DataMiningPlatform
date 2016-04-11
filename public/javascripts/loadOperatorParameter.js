/*
 * 从算子集中去动态的处理每个算子所具备的参数，在参数集中显示出来
 */
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
					var temp = $("<p>"+classArgumentName[i]+":"+
							"<form id='classff' method='post' enctype='multipart/form-data'>"+
							"<input id=" + classinputformid + " name='mydatafile' class='easyui-filebox' >"+
							"<a href='#' class='easyui-linkbutton' style='width:50px;margin-left:10px;' onclick='uploadClassFormFile("+i+")'>上传</a>"+
							"</form>"+
							"</p>");
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
					var temp = $("<p>"+methodArgumentName[i]+":"+
							"<form id='methodff' method='post' enctype='multipart/form-data'>"+
							"<input id=" + methodinputformid + " name='mydatafile' class='easyui-filebox' >"+
							"<a href='#' class='easyui-linkbutton' style='width:50px;margin-left:10px;' onclick='uploadMethodFormFile("+i+")'>上传</a>"+
							"</form>"+
							"</p>");
					var temp2 = $("<p>"+value+"</p>");
					$("#argumentSetID").append(temp, temp2);
				}
			}
		}
		
		var temp = $("<p>"+"<a href='#' class='easyui-linkbutton' style='width:80px;margin-top:5px;' onclick='saveValueBack01("+index+")'>保存</a>"+"</p>");
		$("#argumentSetID").append(temp);
		$.parser.parse("#argumentSetID");
		
		//显示操作到caozuoID中
		var myDate = new Date();
		var mytime = myDate.toLocaleTimeString();     //获取当前时间
		var temp = $("<p>" + mytime + ":算子调参:" + name + "</p>");
		$("#caozuoID").append(temp);
		
	} else {
		alert("loadOperatorParameter.js函数:row没有发现");
	}
	
}

/*
 * 处理类的构造函数中有文件上传的filebox，同时将后端生成的新文件名传递给前端算子保存起来
 */
function uploadClassFormFile(iii) {
	$("#classff").form({
	    url:"/uploadFile",
	    onSubmit: function() {
	        // do some check
	        // return false to prevent submit;
	    },
	    success:function(data) {
//	    	alert(data);
	    	if (data=='nofile') {
	    		alert("没有文件，上传失败");
	    		return;
	    	}
	    	var row = $('#experdgid01').datagrid('getSelected');
	    	var operator = row.operator;
	    	var classArgumentValue = operator.classArgumentValue;
	    	classArgumentValue[iii]=data;
	        alert("上传成功");
	    }
	});
	// submit the form
	$("#classff").submit();
}

/*
 * 处理方法中有文件上传的filebox，同时将后端生成的新文件名传递给前端算子保存起来
 */
function uploadMethodFormFile(iii) {
	$("#methodff").form({
	    url:"/uploadFile",
	    onSubmit: function(){
	        // do some check
	        // return false to prevent submit;
	    },
	    success:function(data) {
//	    	alert(data);
	    	if (data=='nofile') {
	    		alert("没有文件，上传失败");
	    		return;
	    	}
	    	var row = $('#experdgid01').datagrid('getSelected');
	    	var operator = row.operator;
	    	var methodArgumentValue = operator.methodArgumentValue;
	    	methodArgumentValue[iii]=data;
	        alert("上传成功");
	    }
	});
	// submit the form
	$("#methodff").submit();
}

/*
 * 将textbox和numberbox中的值保存回前端的算子中去
 */
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



