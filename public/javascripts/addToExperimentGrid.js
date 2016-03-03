function addToExperimentGrid01(text,opclass22) {
	var dg = $('#experdgid01');
    dg.datagrid('appendRow',{
		opname: text,
		opclass: opclass22,
	});
    
}

function addToExperimentGrid02(text,opclass22) {
	var dg = $('#experdgid02');
    dg.datagrid('appendRow',{
		opname: text,
		opclass: opclass22,
	});
    
}