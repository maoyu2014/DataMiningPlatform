package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.javaml.core.Dataset;
import models.Operator;
import models.OperatorChild;
import models.OperatorFather;

/*
 * 后台所有算子的集合
 * 在构造函数中进行所有算子的初始化
 */
public class AllServerOperators {

	public List<Operator> lists= null;
	public Map<String, Operator> maps = null;
	
	public AllServerOperators() {
		lists = new ArrayList<>();
		maps = new HashMap<>();

		Operator operator = null;

		//DataManipulation-读取文件
		operator = new Operator();
		operator.category = Category.categorys[0];
		operator.operatorClass = "net.sf.javaml.tools.data.FileHandler";
		operator.operatorMethod = "loadDataset";
		operator.classArgument = null;
		operator.classArgumentName = null;
		operator.classArgumentValue = null;
		operator.classFrontStyle = null;
		operator.methodArgument = new Class[] {File.class, int.class, String.class};
		operator.methodArgumentName = new String[] {"file", "classIndex", "separator"};
		operator.methodArgumentValue = new Object[] {};
		operator.methodFrontStyle = new String[] {"file", "integer", "string"};
		operator.name = "读取文件";
		operator.description = "DataManipulation-读取文件";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		//DataManipulation-导出文件
		operator = new Operator();
		operator.category = Category.categorys[0];
		operator.operatorClass = "net.sf.javaml.tools.data.FileHandler";
		operator.operatorMethod = "exportDataset";
		operator.classArgument = null;
		operator.classArgumentName = null;
		operator.classArgumentValue = null;
		operator.classFrontStyle = null;
		operator.methodArgument = new Class[] {Dataset.class, File.class};
		operator.methodArgumentName = new String[] {"data", "file"};
		operator.methodArgumentValue = new Object[] {};
		operator.methodFrontStyle = new String[] {"inner-data", "file"};
		operator.name = "导出文件";
		operator.description = "DataManipulation-导出文件";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		//Clustering-KMeans算法
		operator = new Operator();
		operator.category = Category.categorys[1];
		operator.operatorClass = "net.sf.javaml.clustering.KMeans";
		operator.operatorMethod = "cluster";
		operator.classArgument = null;
		operator.classArgumentName = null;
		operator.classArgumentValue = null;
		operator.classFrontStyle = null;
		operator.methodArgument = new Class[] {Dataset.class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.name = "KMeans算法";
		operator.description = "Clustering-KMeans算法";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		
		
		
	}

}
