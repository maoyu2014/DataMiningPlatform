package utils;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Operator;
import models.ResultObj;
import net.sf.javaml.core.Dataset;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RunExperiment {

	public static String Run(int n, JSONArray arrayrow) {
		
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
	    	//本来打算用深拷贝，但是由于主要就是classArgumentValue和methodArgumentValue是关键，所以就偷懒这么搞了
	    	Operator FactOperatorToUse= new Operator();
	    	FactOperatorToUse.category = FactOperator.category;
	    	FactOperatorToUse.operatorClass = FactOperator.operatorClass;
	    	FactOperatorToUse.operatorMethod = FactOperator.operatorMethod;
	    	FactOperatorToUse.classArgument = FactOperator.classArgument;
	    	FactOperatorToUse.classArgumentName = FactOperator.classArgumentName;
	    	FactOperatorToUse.classFrontStyle = FactOperator.classFrontStyle;
	    	FactOperatorToUse.methodArgument = FactOperator.methodArgument;
	    	FactOperatorToUse.methodArgumentName = FactOperator.methodArgumentName;
	    	FactOperatorToUse.methodFrontStyle = FactOperator.methodFrontStyle;
	    	FactOperatorToUse.returnType = FactOperator.returnType;
	    	FactOperatorToUse.name = FactOperator.name;
	    	FactOperatorToUse.description = FactOperator.description;
	    	
	    	FactOperatorToUse.classArgumentValue = classArgumentValue.toArray();
	    	FactOperatorToUse.methodArgumentValue = methodArgumentValue.toArray();
	    	lists.add(FactOperatorToUse);
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
	private static String coreRun(List<Operator> lists) {
		String result = null;
		Object tempdata = null;
		Class returnType = null;
		Operator ooo = null;
		Map<String, Object> mapObj = new HashMap<>();
		for (int i=0; i<lists.size(); i++) {
			ooo = lists.get(i);
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
				if (mapObj.containsKey(s1)) {
					o1 = mapObj.get(s1);
				} else {
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
							} else if (classArgument[j]==Dataset[].class) {
								classArgumentValue[j] = (Dataset[]) tempdata;
							} else if (classArgument[j]==boolean.class) {
								if (!(classArgumentValue[j] instanceof Boolean))
									classArgumentValue[j] = Boolean.parseBoolean((String)classArgumentValue[j]);
							}
						}
						o1 = co.newInstance(classArgumentValue);
						mapObj.put(s1, o1);
					} catch (InstantiationException | IllegalAccessException  | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
						System.err.println("没找到这个对象");
					}
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
					} else if (methodArgument[j]==Dataset[].class) {
						methodArgumentValue[j] = (Dataset[]) tempdata;
					} else if (methodArgument[j]==boolean.class) {
						if (!(methodArgumentValue[j] instanceof Boolean))
							methodArgumentValue[j] = Boolean.parseBoolean((String)methodArgumentValue[j]);
					}
				}
				if (returnType==void.class) {
					Object obj = m1.invoke(o1, methodArgumentValue);
				} else {
					tempdata = m1.invoke(o1, methodArgumentValue);
				}
			} catch (IllegalAccessException | IllegalArgumentException	| InvocationTargetException e) {
				e.printStackTrace();
			}
			
			
		}
		
		/*
		if (returnType==Dataset.class) {
			Dataset dataset = (Dataset) tempdata;
//			System.out.println(dataset);
			result = dataset.toString();
		} else if (returnType==Dataset[].class) {
			Dataset[] clusters = (Dataset[]) tempdata;
//			System.out.println("============="+clusters.length);
			result = "聚为" + clusters.length + "类:\n";
			for (Dataset clu : clusters) {
//	        	System.out.println(clu);
//	        	System.out.println("-------------------------------------------");
				result += clu.toString() + "\n";
				result += "-------------------------------------------\n";
	        }
		} else if (returnType==double.class) {
			double scorevalue = (double) tempdata;
			result = String.valueOf(scorevalue);
		} else if (returnType==void.class) {
			//void目前只有导出数据是的
			result="导出数据执行完成";
		}
		*/
		
		int casenum = 0;
		if (ooo.operatorClass.equals("net.sf.javaml.tools.data.FileHandler") && ooo.operatorMethod.equals("loadDataset")) {
			//导入数据
			casenum=1;
			Dataset dataset = (Dataset) tempdata;
			result = dataset.toString();
		} else if (ooo.operatorClass.equals("net.sf.javaml.tools.data.FileHandler") && ooo.operatorMethod.equals("exportDataset")) {
			//导出数据
			casenum=2;
			result="导出数据执行完成";
		} else if ( (ooo.operatorClass.equals("net.sf.javaml.clustering.KMeans") || ooo.operatorClass.equals("www.clustering.myFarthestFirst") || ooo.operatorClass.equals("www.clustering.myKMedoids") ) && ooo.operatorMethod.equals("cluster")) {
			//各种聚类算法
			casenum=3;
			Dataset[] clusters = (Dataset[]) tempdata;
			result = clusters.length+"类";
			for (Dataset clu : clusters) {
				result += clu.toString() + ";;";
	        }
		} else if ( (ooo.operatorClass.equals("net.sf.javaml.clustering.evaluation.AICScore") || ooo.operatorClass.equals("net.sf.javaml.clustering.evaluation.BICScore") || ooo.operatorClass.equals("net.sf.javaml.clustering.evaluation.SumOfSquaredErrors") ) && ooo.operatorMethod.equals("score")) {
			//聚类评估
			casenum=4;
			double scorevalue = (double) tempdata;
			result = String.valueOf(scorevalue);
		} else if (ooo.operatorClass.equals("net.sf.javaml.filter.normalize.NormalizeMidrange") && ooo.operatorMethod.equals("filter")) {
			//归一化Midrange
			casenum=5;
			Dataset dataset = (Dataset) tempdata;
			result = dataset.toString();
		} else if (ooo.operatorClass.equals("www.featureselection.myGainRatio") && ooo.operatorMethod.equals("scoreAll")) {
			//对所有列进行评分
			casenum=6;
			double[] scorevalue = (double[]) tempdata;
			result = "";
			for (int i=0; i<scorevalue.length; i++) result+=scorevalue[i]+",";
		} else if (ooo.operatorClass.equals("www.featureselection.myRecursiveFeatureEliminationSVM") && ooo.operatorMethod.equals("rankAll")) {
			//对所有列进行ranking排名
			casenum=7;
			int[] rankvalue = (int[]) tempdata;
			result = "";
			for (int i=0; i<rankvalue.length; i++) result+=rankvalue[i]+",";
		} else if (ooo.operatorClass.equals("www.classification.myKNearestNeighbors") && ooo.operatorMethod.equals("classifyDataset")) {
			//各种分类算法
			casenum = 8;
			result = (String) tempdata;
		} else if (ooo.operatorClass.equals("") && ooo.operatorMethod.equals("")) {
			
		} else {
			casenum=-1;
			result="实验不完整，无结果!";
		}
		
		
		
		long keyvalue =System.currentTimeMillis();
		String key = keyvalue+":"+casenum;
		Map<String, String> resultMaps = ResultObj.maps;
		resultMaps.put(key, result);
        return key;
	}

	
}
