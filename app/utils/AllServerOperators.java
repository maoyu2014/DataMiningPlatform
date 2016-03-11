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

		/*
		 * DataManipulation-读取文件
		 */
		operator = new Operator();
		operator.category = Category.categorys[0];
		operator.operatorClass = "net.sf.javaml.tools.data.FileHandler";
		operator.operatorMethod = "loadDataset";
		
		operator.classArgument = new Class[] {};
		operator.classArgumentName = new String[] {};
		operator.classArgumentValue = new Object[] {};
		operator.classFrontStyle = new String[] {};
		
		operator.methodArgument = new Class[] {File.class, int.class, String.class};
		operator.methodArgumentName = new String[] {"inputfile", "classIndex", "separator"};
		operator.methodArgumentValue = new Object[] {null, -1, "\\t"};
		operator.methodFrontStyle = new String[] {"filebox", "numberbox", "textbox"};
		
		operator.name = "读取文件";
		operator.description = "<p>DataManipulation-读取文件</p>" +
								"<p>文件的格式为csv类格式，其中参数classIndex表示第几列是类型，参数separator表示数据的分隔符</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		/*
		 * DataManipulation-导出文件
		 */
		operator = new Operator();
		operator.category = Category.categorys[0];
		operator.operatorClass = "net.sf.javaml.tools.data.FileHandler";
		operator.operatorMethod = "exportDataset";
		
		operator.classArgument = new Class[] {};
		operator.classArgumentName = new String[] {};
		operator.classArgumentValue = new Object[] {};
		operator.classFrontStyle = new String[] {};
		
		operator.methodArgument = new Class[] {Dataset.class, File.class};
		operator.methodArgumentName = new String[] {"data", "file"};
		operator.methodArgumentValue = new Object[] {};
		operator.methodFrontStyle = new String[] {"inner-data", "filebox"};
		
		operator.name = "导出文件";
		operator.description = "DataManipulation-导出文件";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		/*
		 * Clustering-KMeans算法
		 */
		operator = new Operator();
		operator.category = Category.categorys[1];
		operator.operatorClass = "net.sf.javaml.clustering.KMeans";
		operator.operatorMethod = "cluster";
		
		operator.classArgument = new Class[] {int.class, int.class};
		operator.classArgumentName = new String[] {"clusters", "iterations"};
		operator.classArgumentValue = new Object[] {4,100};
		operator.classFrontStyle = new String[] {"numberbox", "numberbox"};
		
		operator.methodArgument = new Class[] {Dataset.class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		
		operator.name = "KMeans算法";
		operator.description = "<p>Clustering-KMeans算法</p>"+
								"<p>经典的聚类算法啊，可以对一组数据进行聚类操作。参数clusters表示需要聚合成几类，参数iterations表示需要进行迭代的次数</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		
		
		
	}

}
