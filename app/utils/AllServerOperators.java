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
 * 后台所有完整算子的集合
 */
public class AllServerOperators {
	
	private AllServerOperators() {}
	
	public static List<Operator> lists= null;
	public static Map<String, Operator> maps = null;
	
	public static List<Operator> getLists() {
		if (lists==null) setAllServerOperators();
		return lists;
	}
	
	public static Map<String, Operator> getMaps() {
		if (maps==null) setAllServerOperators();
		return maps;
	}
	
	private static void setAllServerOperators() {
		lists = new ArrayList<>();
		maps = new HashMap<>();

		Operator operator = null;

		/*
		 * 第0类：DataManipulation--读取文件
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
		operator.methodArgumentValue = new Object[] {null, -1, ","};
		operator.methodFrontStyle = new String[] {"filebox", "numberbox", "textbox"};
		operator.returnType = Dataset.class;
		
		operator.name = "读取文件";
		operator.description = "<p>DataManipulation-读取文件</p>" +
								"<p>文件的格式为csv类格式，其中参数classIndex表示第几列是类型，列数从0开始，参数separator表示数据的分隔符，一般csv文件多以（逗号、分号、tab）作为分割符。输入文件中只有class label可以是非数字的</p>" +
								"<p>输入:文件、index、Seperator，输出:Dataset</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		/*
		 * 第0类：DataManipulation--导出文件
		 */
		operator = new Operator();
		operator.category = Category.categorys[0];
		operator.operatorClass = "net.sf.javaml.tools.data.FileHandler";
		operator.operatorMethod = "exportDataset";
		
		operator.classArgument = new Class[] {};
		operator.classArgumentName = new String[] {};
		operator.classArgumentValue = new Object[] {};
		operator.classFrontStyle = new String[] {};
		
		operator.methodArgument = new Class[] {Dataset.class, File.class, boolean.class, String.class};
		operator.methodArgumentName = new String[] {"data", "outFileName", "compress", "separator"};
		operator.methodArgumentValue = new Object[] {null, null, false, ","};
		operator.methodFrontStyle = new String[] {"inner-data", "textbox", "textbox", "textbox"};
		operator.returnType = void.class;
		
		operator.name = "导出文件";
		operator.description = "<p>DataManipulation-导出文件</p>" +
				"<p>文件的格式为csv-like格式，其中参数data表示要输出的数据，outFile表示输出的文件名，compress表示是否压缩（默认false不压缩,或者true压缩），参数separator表示数据的分隔符，一般csv文件多以（逗号、分号、tab）作为分割符。输出文件中class label默认出现在第0列</p>" +
				"<p>输入:Dataset，输出:File</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		/*
		 * 第0类：DataManipulation--NormalizeMidrange归一化训练
		 */
		operator = new Operator();
		operator.category = Category.categorys[0];
		operator.operatorClass = "net.sf.javaml.filter.normalize.NormalizeMidrange";
		operator.operatorMethod = "build";
		
		operator.classArgument = new Class[] {double.class, double.class};
		operator.classArgumentName = new String[] {"middle", "range"};
		operator.classArgumentValue = new Object[] { 0, 2 };
		operator.classFrontStyle = new String[] {"numberbox","numberbox"};
		
		operator.methodArgument = new Class[] {Dataset.class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.returnType = void.class;
		
		operator.name = "Midrange归一化训练";
		operator.description = "<p>DataManipulation-NormalizeMidrange归一化训练</p>" +
				"<p>对输入的Dataset数据进行标准化训练，在应用它到具体数据上之前，需要使用本算子到一个Dataset来确定每一列合适的mid-ranges。比如，对于 mid-range 0和range 2，将产生range[-1,1]</p>" +
				"<p>输入:Dataset，输出:void</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		/*
		 * 第0类：DataManipulation--NormalizeMidrange归一化应用
		 */
		operator = new Operator();
		operator.category = Category.categorys[0];
		operator.operatorClass = "net.sf.javaml.filter.normalize.NormalizeMidrange";
		operator.operatorMethod = "filter";
		
		operator.classArgument = new Class[] {};
		operator.classArgumentName = new String[] {};
		operator.classArgumentValue = new Object[] {};
		operator.classFrontStyle = new String[] {};
		
		operator.methodArgument = new Class[] {Dataset.class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.returnType = void.class;
		
		operator.name = "Midrange归一化使用";
		operator.description = "<p>DataManipulation-NormalizeMidrange归一化使用</p>" +
				"<p>对输入的Dataset数据进行标准化，标准化结束之后，被标准化的数据Dataset将会改变</p>" +
				"<p>输入:Dataset，输出:void</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		
		
		/*
		 * 第1类：Clustering-KMeans算法
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
		operator.returnType = Dataset[].class;
				
		operator.name = "KMeans算法";
		operator.description = "<p>Clustering-KMeans算法</p>"+
								"<p>经典的聚类算法啊，可以对一组数据进行聚类操作。参数clusters表示需要聚合成几类，参数iterations表示需要进行迭代的次数，觉得不需要修改可以不用调整</p>" +
								"<p>输入:Dataset，输出:Dataset[]数组</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		
		/*
		 * 第1类：Clustering-AICScore评估算法
		 */
		operator = new Operator();
		operator.category = Category.categorys[1];
		operator.operatorClass = "net.sf.javaml.clustering.evaluation.AICScore";
		operator.operatorMethod = "score";
		
		operator.classArgument = new Class[] {};
		operator.classArgumentName = new String[] {};
		operator.classArgumentValue = new Object[] {};
		operator.classFrontStyle = new String[] {};
		
		operator.methodArgument = new Class[] {Dataset[].class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.returnType = double.class;
				
		operator.name = "聚类AICScore评估";
		operator.description = "<p>聚类AICScore评估</p>"+
								"<p>对聚类的算法的聚类结果进行评估，得到一个double类型的score值表示结果</p>" + 
								"<p>输入:Dataset[]数组，输出:double</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		
		/*
		 * 第1类：Clustering-BICScore评估算法
		 */
		operator = new Operator();
		operator.category = Category.categorys[1];
		operator.operatorClass = "net.sf.javaml.clustering.evaluation.BICScore";
		operator.operatorMethod = "score";
		
		operator.classArgument = new Class[] {};
		operator.classArgumentName = new String[] {};
		operator.classArgumentValue = new Object[] {};
		operator.classFrontStyle = new String[] {};
		
		operator.methodArgument = new Class[] {Dataset[].class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.returnType = double.class;
				
		operator.name = "聚类BICScore评估";
		operator.description = "<p>聚类BICScore评估</p>"+
								"<p>对聚类的算法的聚类结果进行评估，得到一个double类型的score值表示结果</p>" +
								"<p>输入:Dataset[]数组，输出:double</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		
		/*
		 * 第1类：Clustering-SumOfSquaredErrors评估算法
		 */
		operator = new Operator();
		operator.category = Category.categorys[1];
		operator.operatorClass = "net.sf.javaml.clustering.evaluation.SumOfSquaredErrors";
		operator.operatorMethod = "score";
		
		operator.classArgument = new Class[] {};
		operator.classArgumentName = new String[] {};
		operator.classArgumentValue = new Object[] {};
		operator.classFrontStyle = new String[] {};
		
		operator.methodArgument = new Class[] {Dataset[].class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.returnType = double.class;
				
		operator.name = "聚类SumOfSquaredErrors评估";
		operator.description = "<p>聚类SumOfSquaredErrors评估</p>"+
								"<p>对聚类的算法的聚类结果进行评估，得到一个double类型的score值表示结果</p>" +
								"<p>输入:Dataset[]数组，输出:double</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		
		
		
		
		
		
	}

}
