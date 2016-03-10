package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RunExperiment {

	public RunExperiment(int n, JSONArray arrayrow) {
		for (int i=0; i<n; i++) {
			JSONObject row=(JSONObject)arrayrow.get(i);
	    	JSONObject operator = (JSONObject) row.get("operator");
	    	String operatorClass = (String) operator.get("operatorClass");
	    	String operatorMethod = (String) operator.get("operatorMethod");
	    	Object classArgumentValue = operator.get("classArgumentValue");
	    	Object methodArgumentValue = operator.get("methodArgumentValue");
	    	System.out.println(operatorClass);
	    	System.out.println(operatorMethod);
	    	System.out.println(classArgumentValue);
	    	System.out.println(methodArgumentValue);
	    	
		}
		
	}
}
