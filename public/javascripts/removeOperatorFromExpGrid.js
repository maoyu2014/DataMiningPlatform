function removeOperatorFromExpGrid01(){
	
	var row = $('#experdgid01').datagrid('getSelected');
	if (row){
		var index = $('#experdgid01').datagrid('getRowIndex', row);
		$('#experdgid01').datagrid('deleteRow', index);
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
		var index = $('#experdgid02').datagrid('getRowIndex', row);
		$('#experdgid02').datagrid('deleteRow', index);
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