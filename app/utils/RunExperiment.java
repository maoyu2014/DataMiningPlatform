package utils;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.Operator;
import net.sf.javaml.core.Dataset;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RunExperiment {

	public static void Run(int n, JSONArray arrayrow) {
		List<Operator> lists = new ArrayList<>();
		AllServerOperators allServerOperators = new AllServerOperators();
		Map<String, Operator> maps = allServerOperators.maps;
		
		for (int i=0; i<n; i++) {
			JSONObject row=(JSONObject)arrayrow.get(i);
	    	JSONObject operator = (JSONObject) row.get("operator");
	    	String operatorClass = (String) operator.get("operatorClass");
	    	String operatorMethod = (String) operator.get("operatorMethod");
	    	JSONArray classArgumentValue = (JSONArray) operator.get("classArgumentValue");
	    	JSONArray methodArgumentValue = (JSONArray) operator.get("methodArgumentValue");
	    	
	    	Operator FactOperator = maps.get(operatorClass+"."+operatorMethod);
	    	
    		FactOperator.classArgumentValue = classArgumentValue.toArray();
    		FactOperator.methodArgumentValue = methodArgumentValue.toArray();
	    	lists.add(FactOperator);
		}
		
		for (int i=0; i<n; i++) {
			Operator o = lists.get(i);
			System.out.println(o.operatorClass);
	    	System.out.println(o.operatorMethod);
			System.out.println(o.classArgumentValue+"	length="+o.classArgumentValue.length);
			for (int j=0; j<o.classArgumentValue.length; j++)
				System.out.println(o.classArgumentValue[j]);
			System.out.println(o.methodArgumentValue+"	length="+o.methodArgumentValue.length);
			for (int j=0; j<o.methodArgumentValue.length; j++)
				System.out.println(o.methodArgumentValue[j]);
		}
		
		
//		coreRun(lists);
		
	}

	/*
	 * 利用反射机制进行运行
	 */
	private static void coreRun(List<Operator> lists) {
		Object tempdata = null;
		for (int i=0; i<lists.size(); i++) {
			Operator ooo = lists.get(i);
			String s1 = ooo.operatorClass;
			Class<?> c1 = null;
			Method m1 = null;
			Object o1 = null;
			
			try {
				c1 = Class.forName(s1);
			} catch (ClassNotFoundException e) {
				System.err.println("没找到这个类");
			}
			
			try {
				m1 = c1.getMethod(ooo.operatorMethod, ooo.methodArgument);
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			
			if (!Modifier.isStatic(m1.getModifiers())) {
				Constructor<?> co = null;
				try {
						co = c1.getConstructor(ooo.classArgument);
				} catch (NoSuchMethodException | SecurityException e1) {
					e1.printStackTrace();
				}
	//			co.setAccessible(true);   
				try {
						o1 = co.newInstance(ooo.classArgumentValue);
				} catch (InstantiationException | IllegalAccessException  | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			
			try {
				tempdata = m1.invoke(o1, ooo.methodArgumentValue);
			} catch (IllegalAccessException | IllegalArgumentException	| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		Dataset[] clusters = (Dataset[]) tempdata;
		for (Dataset clu : clusters) {
        	System.out.println(clu);
        }
	}
	
}
