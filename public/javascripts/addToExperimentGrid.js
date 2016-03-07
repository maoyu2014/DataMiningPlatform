function addToExperimentGrid01(name,operator) {
	var dg = $('#experdgid01');
    dg.datagrid('appendRow',{
		name: name,
		operator: operator,
	});
    
    /*
    alert(operator);
    alert(operator.operatorClass);
    alert(operator.operatorMethod);
    alert(operator.description);
    alert(operator.methodArgumentValue);
    alert(operator.methodArgumentName);
    alert(operator.methodFrontStyle);
    */
}

function addToExperimentGrid02(name,operator) {
	var dg = $('#experdgid02');
	dg.datagrid('appendRow',{
		name: name,
		operator: operator,
	});
    
}