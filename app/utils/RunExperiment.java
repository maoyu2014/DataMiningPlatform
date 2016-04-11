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
import models.ResultObj;
import net.sf.javaml.core.Dataset;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RunExperiment {

	public static long Run(int n, JSONArray arrayrow) {
		
		List<Operator> lists = new ArrayList<>();
		Map<String, Operator> maps = AllServerOperators.getMaps();
		
		for (int i=0; i<n; i++) {
			JSONObject row=(JSONObject)arrayrow.get(i);
	    	JSONObject operator = (JSONObject) row.get("operator");
	    	String operatorClass = (String) operator.get("operatorClass");
	    	String operatorMethod = (String) operator.get("operatorMethod");
	    	JSONArray classArgumentValue = (JSONArray) operator.get("classArgumentValue");
	    	JSONArray methodArgumentValue = (JSONArray) operator.get("methodArgumentValue");
	    	
//	    	System.out.println("~~~~~:	"+methodArgumentValue);
	    	
	    	Operator FactOperator = maps.get(operatorClass+"."+operatorMethod);
	    	
    		FactOperator.classArgumentValue = classArgumentValue.toArray();
    		FactOperator.methodArgumentValue = methodArgumentValue.toArray();
	    	lists.add(FactOperator);
		}
		
		/*
		System.out.println("----------------------------------------------------------");
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
		System.out.println("----------------------------------------------------------");
		*/
		
		return coreRun(lists);
		
	}

	/*
	 * 利用反射机制进行运行
	 */
	private static long coreRun(List<Operator> lists) {
		String result = null;
		Object tempdata = null;
		Class returnType = null;
		for (int i=0; i<lists.size(); i++) {
			Operator ooo = lists.get(i);
			String s1 = ooo.operatorClass;
			Class<?> c1 = null;
			Method m1 = null;
			Object o1 = null;
			returnType = ooo.returnType;
			
			try {
				c1 = Class.forName(s1);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.err.println("没找到这个类");
			}
			
			try {
				m1 = c1.getMethod(ooo.operatorMethod, ooo.methodArgument);
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
				System.err.println("没找到这个方法");
			}
			
			if (!Modifier.isStatic(m1.getModifiers())) {
				Constructor<?> co = null;
				try {
					co = c1.getConstructor(ooo.classArgument);
				} catch (NoSuchMethodException | SecurityException e1) {
					e1.printStackTrace();
					System.err.println("没找到这个构造方法");
				}
	//			co.setAccessible(true);   
				try {
					Class[] classArgument = ooo.classArgument;
					Object[] classArgumentValue = ooo.classArgumentValue;
					for (int j=0; j<classArgument.length; j++) {
						if (classArgument[j]==File.class) {
							String fileName = (String) classArgumentValue[j];
							classArgumentValue[j] = new File("./public/uploaddata/" + fileName);
						} else if (classArgument[j]==int.class) {
							classArgumentValue[j] = (int)(long) classArgumentValue[j];
						} else if (classArgument[j]==String.class) {
							classArgumentValue[j] = (String) classArgumentValue[j];
						} else if (classArgument[j]==Dataset.class) {
							classArgumentValue[j] = (Dataset) tempdata;
						}
					}
					o1 = co.newInstance(classArgumentValue);
				} catch (InstantiationException | IllegalAccessException  | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
					System.err.println("没找到这个对象");
				}
			}
			
			try {
				Class[] methodArgument = ooo.methodArgument;
				Object[] methodArgumentValue = ooo.methodArgumentValue;
				for (int j=0; j<methodArgument.length; j++) {
					if (methodArgument[j]==File.class) {
						String fileName = (String) methodArgumentValue[j];
						methodArgumentValue[j] = new File("./public/uploaddata/" + fileName);
					} else if (methodArgument[j]==int.class) {
						methodArgumentValue[j] = (int)(long) methodArgumentValue[j];
					} else if (methodArgument[j]==String.class) {
						methodArgumentValue[j] = (String) methodArgumentValue[j];
					} else if (methodArgument[j]==Dataset.class) {
						methodArgumentValue[j] = (Dataset) tempdata;
					}
				}
				tempdata = m1.invoke(o1, methodArgumentValue);
			} catch (IllegalAccessException | IllegalArgumentException	| InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		if (returnType==Dataset.class) {
			Dataset dataset = (Dataset) tempdata;
//			System.out.println(dataset);
			result = dataset.toString();
		} else if (returnType==Dataset[].class) {
			Dataset[] clusters = (Dataset[]) tempdata;
//			System.out.println("============="+clusters.length);
			result += "聚为"+clusters.length+"类:\n";
			for (Dataset clu : clusters) {
//	        	System.out.println(clu);
//	        	System.out.println("-------------------------------------------");
				result += clu.toString() + "\n";
				result += "-------------------------------------------\n";
	        }
		}

		long key =System.currentTimeMillis();
		Map<Long, String> resultMaps = ResultObj.maps;
		resultMaps.put(key, result);
        return key;
	}
	
}
