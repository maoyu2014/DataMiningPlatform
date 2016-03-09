/**
 * 
 */
function runExperiment01() {
	var rows = $('#experdgid01').datagrid('getRows');
	height = rows.length;
	if (height==0) {
		alert("没有任何算子！");
		return;
	}
	/*
	for (var i=0; i<height; i++) {
		var row = rows[i];
		console.log(row);
	}
	*/
	
}