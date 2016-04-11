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
	$.post(
			'/acceptExperiment',
			{
				rows:JSON.stringify(rows)
			},
			function(data, status) {
				if (status=='success') {
//					var parameters = encodeURIComponent("key=" + data);
					window.open("/showResult?key="+data, "", "");  
				} else {
					alert("运行失败");
				}
			}); 
	
	/*
	for (var i=0; i<height; i++) {
		var row = rows[i];
		console.log(row);
	}
	*/
	
}