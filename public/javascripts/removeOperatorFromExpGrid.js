function removeOperatorFromExpGrid01(){
	
	var row = $('#experdgid01').datagrid('getSelected');
	
	if (row){
		var name = row.name;
		var index = $('#experdgid01').datagrid('getRowIndex', row);
		$('#experdgid01').datagrid('deleteRow', index);
		
		//显示操作到caozuoID中
		var myDate = new Date();
		var mytime = myDate.toLocaleTimeString();     //获取当前时间
		var temp = $("<p>" + mytime + ":删除算子:" + name + "</p>");
		$("#caozuoID").append(temp);
	}
	
	/*
	var rows = $('#experdg').datagrid('getSelections');
	for (var i=0; i<rows.length; i++) {
		var row = rows[i];
		var index = $('#experdg').datagrid('getRowIndex', row);
		$('#experdg').datagrid('deleteRow', index);
	}
	*/
	
}

function removeOperatorFromExpGrid02(){
	
	var row = $('#experdgid02').datagrid('getSelected');
	
	if (row){
		var name = row.name;
		var index = $('#experdgid02').datagrid('getRowIndex', row);
		$('#experdgid02').datagrid('deleteRow', index);
		
		//显示操作到caozuoID中
		var myDate = new Date();
		var mytime = myDate.toLocaleTimeString();     //获取当前时间
		var temp = $("<p>" + mytime + ":删除算子:" + name + "</p>");
		$("#caozuoID").append(temp);
	}
	
	/*
	var rows = $('#experdg').datagrid('getSelections');
	for (var i=0; i<rows.length; i++) {
		var row = rows[i];
		var index = $('#experdg').datagrid('getRowIndex', row);
		$('#experdg').datagrid('deleteRow', index);
	}
	*/
	
}