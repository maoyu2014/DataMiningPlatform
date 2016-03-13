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
	$.get(
			'/acceptExperiment',
			{
				rows:JSON.stringify(rows)
			},
			function(data, status) {
				alert(status);
				var parameters = encodeURIComponent("key=" + data);
				window.open("/showResult?key="+data,"xxoo","menubar=no, toolbar=no, location=no,directories=no, status=no, scrollbars=no, titlebar=yes, resizable=no, height=768 ,width=1024,top=0,left=0");  
//				alert(data);
			}); 
	
	/*
	for (var i=0; i<height; i++) {
		var row = rows[i];
		console.log(row);
	}
	*/
	
}