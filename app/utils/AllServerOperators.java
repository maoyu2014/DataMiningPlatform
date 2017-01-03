package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.featureselection.ranking.RecursiveFeatureEliminationSVM;
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

		
		//-----------------------第0类，DataManipulation-------------------------
		
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
		operator.methodFrontStyle = new String[] {"filebox", "intnumberbox", "textbox"};
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
		operator.classFrontStyle = new String[] {"doublenumberbox","doublenumberbox"};
		
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
		
		
		//-----------------------第1类，Clustering-------------------------
		
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
		operator.classFrontStyle = new String[] {"intnumberbox", "intnumberbox"};
		
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
		 * 第1类：Clustering-FarthestFirst算法
		 */
		operator = new Operator();
		operator.category = Category.categorys[1];
		operator.operatorClass = "www.clustering.myFarthestFirst";
		operator.operatorMethod = "cluster";
		
		operator.classArgument = new Class[] {int.class};
		operator.classArgumentName = new String[] {"numClusters"};
		operator.classArgumentValue = new Object[] {4};
		operator.classFrontStyle = new String[] {"intnumberbox"};
		
		operator.methodArgument = new Class[] {Dataset.class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.returnType = Dataset[].class;
				
		operator.name = "FarthestFirst算法";
		operator.description = "<p>Clustering-FarthestFirst算法</p>"+
								"<p>FarthestFirst聚类算法啊，可以对一组数据进行聚类操作。参数numClusters表示需要聚合成几类，觉得不需要修改可以不用调整</p>" +
								"<p>输入:Dataset，输出:Dataset[]数组</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		/*
		 * 第1类：Clustering-KMedoids算法
		 */
		operator = new Operator();
		operator.category = Category.categorys[1];
		operator.operatorClass = "www.clustering.myKMedoids";
		operator.operatorMethod = "cluster";
		
		operator.classArgument = new Class[] {int.class, int.class};
		operator.classArgumentName = new String[] {"numberOfClusters", "maxIterations"};
		operator.classArgumentValue = new Object[] {4, 100};
		operator.classFrontStyle = new String[] {"intnumberbox", "intnumberbox"};
		
		operator.methodArgument = new Class[] {Dataset.class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.returnType = Dataset[].class;
				
		operator.name = "KMedoids算法";
		operator.description = "<p>Clustering-KMedoids算法</p>"+
								"<p>KMedoids聚类算法啊，可以对一组数据进行聚类操作。参数numberOfClusters表示需要聚合成几类，参数maxIterations表示最大迭代次数，觉得不需要修改可以不用调整</p>" +
								"<p>输入:Dataset，输出:Dataset[]数组</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		/*
		 * 第1类：Clustering-Cobweb算法
		 */
		operator = new Operator();
		operator.category = Category.categorys[1];
		operator.operatorClass = "net.sf.javaml.clustering.Cobweb";
		operator.operatorMethod = "cluster";
		
		operator.classArgument = new Class[] {double.class, double.class};
		operator.classArgumentName = new String[] {"acuity", "cutoff"};
		operator.classArgumentValue = new Object[] {0.5, 0.01};
		operator.classFrontStyle = new String[] {"doublenumberbox", "doublenumberbox"};
		
		operator.methodArgument = new Class[] {Dataset.class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.returnType = Dataset[].class;
				
		operator.name = "Cobweb算法";
		operator.description = "<p>Clustering-Cobweb算法</p>"+
								"<p>Cobweb聚类算法啊，可以对一组数据进行聚类操作。参数acuity表示***，参数acuity表示***</p>" +
								"<p>输入:Dataset，输出:Dataset[]数组</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		/*
		 * 第1类：Clustering-DensityBasedSpatialClustering算法
		 */
		operator = new Operator();
		operator.category = Category.categorys[1];
		operator.operatorClass = "net.sf.javaml.clustering.DensityBasedSpatialClustering";
		operator.operatorMethod = "cluster";
		
		operator.classArgument = new Class[] {double.class, int.class};
		operator.classArgumentName = new String[] {"epsilon", "minPoints"};
		operator.classArgumentValue = new Object[] {0.1, 6};
		operator.classFrontStyle = new String[] {"doublenumberbox", "intnumberbox"};
		
		operator.methodArgument = new Class[] {Dataset.class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.returnType = Dataset[].class;
				
		operator.name = "DensityBasedSpatialClustering算法";
		operator.description = "<p>Clustering-DensityBasedSpatialClustering算法</p>"+
								"<p>DensityBasedSpatialClustering聚类算法啊，可以对一组数据进行聚类操作。参数epsilon表示***，参数minPoints表示***</p>" +
								"<p>输入:Dataset，输出:Dataset[]数组</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		/*
		 * 第1类：Clustering-IterativeFarthestFirst算法
		 */
		operator = new Operator();
		operator.category = Category.categorys[1];
		operator.operatorClass = "www.clustering.myIterativeFarthestFirst";
		operator.operatorMethod = "cluster";
		
		operator.classArgument = new Class[] {int.class, int.class};
		operator.classArgumentName = new String[] {"kMin", "kMax"};
		operator.classArgumentValue = new Object[] {2, 6};
		operator.classFrontStyle = new String[] {"intnumberbox", "intnumberbox"};
		
		operator.methodArgument = new Class[] {Dataset.class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.returnType = Dataset[].class;
				
		operator.name = "IterativeFarthestFirst算法";
		operator.description = "<p>Clustering-IterativeFarthestFirst算法</p>"+
								"<p>IterativeFarthestFirst聚类算法啊，可以对一组数据进行聚类操作。参数kMin表示***，参数kMax表示***</p>" +
								"<p>输入:Dataset，输出:Dataset[]数组</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		/*
		 * 第1类：Clustering-IterativeKMeans算法
		 */
		operator = new Operator();
		operator.category = Category.categorys[1];
		operator.operatorClass = "www.clustering.myIterativeKMeans";
		operator.operatorMethod = "cluster";
		
		operator.classArgument = new Class[] {int.class, int.class, int.class};
		operator.classArgumentName = new String[] {"kMin", "kMax", "iterations"};
		operator.classArgumentValue = new Object[] {2, 10, 100};
		operator.classFrontStyle = new String[] {"intnumberbox", "intnumberbox", "intnumberbox"};
		
		operator.methodArgument = new Class[] {Dataset.class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.returnType = Dataset[].class;
				
		operator.name = "IterativeKMeans算法";
		operator.description = "<p>Clustering-IterativeKMeans算法</p>"+
								"<p>IterativeKMeans聚类算法啊，可以对一组数据进行聚类操作。参数kMin表示最小聚类数，参数kMax表示最大聚类数, 参数iterations表示迭代次数</p>" +
								"<p>输入:Dataset，输出:Dataset[]数组</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		/*
		 * 第1类：Clustering-IterativeMultiKMeans算法
		 */
		operator = new Operator();
		operator.category = Category.categorys[1];
		operator.operatorClass = "www.clustering.myIterativeMultiKMeans";
		operator.operatorMethod = "cluster";
		
		operator.classArgument = new Class[] {int.class, int.class, int.class, int.class};
		operator.classArgumentName = new String[] {"kMin", "kMax", "iterations", "repeats"};
		operator.classArgumentValue = new Object[] {2, 6, 100, 10};
		operator.classFrontStyle = new String[] {"intnumberbox", "intnumberbox", "intnumberbox", "intnumberbox"};
		
		operator.methodArgument = new Class[] {Dataset.class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.returnType = Dataset[].class;
				
		operator.name = "IterativeMultiKMeans算法";
		operator.description = "<p>Clustering-IterativeMultiKMeans算法</p>"+
								"<p>IterativeMultiKMeans聚类算法啊，可以对一组数据进行聚类操作。参数kMin表示最小聚类数，参数kMax表示最大聚类数, 参数iterations表示迭代次数,repeats表示重复次数</p>" +
								"<p>输入:Dataset，输出:Dataset[]数组</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		/*
		 * 第1类：Clustering-MultiKMeans算法
		 */
		operator = new Operator();
		operator.category = Category.categorys[1];
		operator.operatorClass = "www.clustering.myMultiKMeans";
		operator.operatorMethod = "cluster";
		
		operator.classArgument = new Class[] {int.class, int.class, int.class};
		operator.classArgumentName = new String[] {"clusters", "iterations", "repeats"};
		operator.classArgumentValue = new Object[] {4, 100, 10};
		operator.classFrontStyle = new String[] {"intnumberbox", "intnumberbox", "intnumberbox"};
		
		operator.methodArgument = new Class[] {Dataset.class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.returnType = Dataset[].class;
				
		operator.name = "MultiKMeans算法";
		operator.description = "<p>Clustering-MultiKMeans算法</p>"+
								"<p>MultiKMeans聚类算法啊，可以对一组数据进行聚类操作。参数clusters表示聚类数, 参数iterations表示迭代次数,repeats表示重复次数</p>" +
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
		
		
		//-----------------------第2类，FeatureSelection-------------------------
		
		/*
		 * 第2类：FeatureSelection---Scoring---GainRatio特征得分训练
		 */
		operator = new Operator();
		operator.category = Category.categorys[2];
		operator.operatorClass = "www.featureselection.myGainRatio";
		operator.operatorMethod = "build";
		
		operator.classArgument = new Class[] {};
		operator.classArgumentName = new String[] {};
		operator.classArgumentValue = new Object[] {};
		operator.classFrontStyle = new String[] {};
		
		operator.methodArgument = new Class[] {Dataset.class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.returnType = void.class;
				
		operator.name = "GainRatio特征得分训练";
		operator.description = "<p>GainRatio特征得分训练</p>"+
								"<p>对一个Dataset的数据进行特征评估训练，训练完以后，用score对某一列（从0开始）评估，可以得到得分，score越高越好</p>" +
								"<p>输入:Dataset，输出:void</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		/*
		 * 第2类：FeatureSelection--Scoring--GainRatio特征得分使用
		 */
		operator = new Operator();
		operator.category = Category.categorys[2];
		operator.operatorClass = "www.featureselection.myGainRatio";
		operator.operatorMethod = "scoreAll";
		
		operator.classArgument = new Class[] {};
		operator.classArgumentName = new String[] {};
		operator.classArgumentValue = new Object[] {};
		operator.classFrontStyle = new String[] {};
		
		operator.methodArgument = new Class[] {};
		operator.methodArgumentName = new String[] {};
		operator.methodArgumentValue = new Object[] {};
		operator.methodFrontStyle = new String[] {};
		operator.returnType = double[].class;
		
		operator.name = "GainRatio特征得分使用";
		operator.description = "<p>GainRatio特征得分使用</p>"+
								"<p>对一个Dataset的数据的某一列进行评估，参数attributeIndex表示具体的某一列index，（从0开始），结果就是一个分值，得分越高越好</p>" +
								"<p>输入:无，输出:double[]数组</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		/*
		 * 第2类：FeatureSelection--Ranking--RecursiveFeatureEliminationSVM特征排名训练
		 */
		operator = new Operator();
		operator.category = Category.categorys[2];
		operator.operatorClass = "www.featureselection.myRecursiveFeatureEliminationSVM";
		operator.operatorMethod = "build";
		
		operator.classArgument = new Class[] {double.class, boolean.class, int.class};
		operator.classArgumentName = new String[] {"removePercentage", "optimize", "internalFolds"};
		operator.classArgumentValue = new Object[] {0.2, false, 4};
		operator.classFrontStyle = new String[] {"doublenumberbox", "textbox", "intnumberbox"};
		
		operator.methodArgument = new Class[] {Dataset.class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.returnType = void.class;
				
		operator.name = "RecursiveFeatureEliminationSVM特征排名训练";
		operator.description = "<p>RecursiveFeatureEliminationSVM特征评估训练</p>"+
								"<p>对一个Dataset的数据进行特征评估训练，训练完以后，用score对某一列（从0开始）评估，可以得到ranking排名，ranking越低越好</p>" +
								"<p>输入:Dataset，输出:void</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		/*
		 * 第2类：FeatureSelection--Ranking--RecursiveFeatureEliminationSVM特征排名使用
		 */
		operator = new Operator();
		operator.category = Category.categorys[2];
		operator.operatorClass = "www.featureselection.myRecursiveFeatureEliminationSVM";
		operator.operatorMethod = "rankAll";
		
		operator.classArgument = new Class[] {};
		operator.classArgumentName = new String[] {};
		operator.classArgumentValue = new Object[] {};
		operator.classFrontStyle = new String[] {};
		
		operator.methodArgument = new Class[] {};
		operator.methodArgumentName = new String[] {};
		operator.methodArgumentValue = new Object[] {};
		operator.methodFrontStyle = new String[] {};
		operator.returnType = int[].class;
		
		operator.name = "RecursiveFeatureEliminationSVM特征排名使用";
		operator.description = "<p>RecursiveFeatureEliminationSVM特征评估使用</p>"+
								"<p>对一个Dataset的数据的某一列进行评估，参数attributeIndex表示具体的某一列index，（从0开始），结果得到ranking排名，ranking越低越好</p>" +
								"<p>输入:无，输出:int[]数组</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		
		//-----------------------第3类，Classification-------------------------
		
		/*
		 * 第3类：Classification--KNN算法训练
		 */
		operator = new Operator();
		operator.category = Category.categorys[3];
		operator.operatorClass = "www.classification.myKNearestNeighbors";
		operator.operatorMethod = "buildClassifier";
		
		operator.classArgument = new Class[] {int.class};
		operator.classArgumentName = new String[] {"K"};
		operator.classArgumentValue = new Object[] {5};
		operator.classFrontStyle = new String[] {"intnumberbox"};
		
		operator.methodArgument = new Class[] {Dataset.class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.returnType = void.class;
		
		operator.name = "KNN算法训练";
		operator.description = "<p>KNN算法训练</p>"+
								"<p>对一个Dataset的数据的使用KNN算法进行训练，参数K表示K nearest neighbor中K的值，训练完可以得到一个分类器</p>" +
								"<p>输入:Dataset，输出:void</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		/*
		 * 第3类：Classification--KNN算法测试
		 */
		operator = new Operator();
		operator.category = Category.categorys[3];
		operator.operatorClass = "www.classification.myKNearestNeighbors";
		operator.operatorMethod = "classifyDataset";
		
		operator.classArgument = new Class[] {};
		operator.classArgumentName = new String[] {};
		operator.classArgumentValue = new Object[] {};
		operator.classFrontStyle = new String[] {};
		
		operator.methodArgument = new Class[] {Dataset.class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.returnType = String.class;
		
		operator.name = "KNN算法测试";
		operator.description = "<p>KNN算法测试</p>"+
								"<p>对一个Dataset的数据的使用KNN算法进行训练，训练完可以得到一个分类器，利用这个分类器可以对新的数据集进行分类操作</p>" +
								"<p>输入:Dataset，输出:String结果</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		/*
		 * 第3类：Classification--KDtreeKNN算法训练
		 */
		operator = new Operator();
		operator.category = Category.categorys[3];
		operator.operatorClass = "www.classification.myKDtreeKNN";
		operator.operatorMethod = "buildClassifier";
		
		operator.classArgument = new Class[] {int.class};
		operator.classArgumentName = new String[] {"K"};
		operator.classArgumentValue = new Object[] {5};
		operator.classFrontStyle = new String[] {"intnumberbox"};
		
		operator.methodArgument = new Class[] {Dataset.class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.returnType = void.class;
		
		operator.name = "KDtreeKNN算法训练";
		operator.description = "<p>KDtreeKNN算法训练</p>"+
								"<p>对一个Dataset的数据的使用KNN算法进行训练，参数K表示K nearest neighbor中K的值，训练完可以得到一个分类器</p>" +
								"<p>输入:Dataset，输出:void</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		/*
		 * 第3类：Classification--KDtreeKNN算法测试
		 */
		operator = new Operator();
		operator.category = Category.categorys[3];
		operator.operatorClass = "www.classification.myKDtreeKNN";
		operator.operatorMethod = "classifyDataset";
		
		operator.classArgument = new Class[] {};
		operator.classArgumentName = new String[] {};
		operator.classArgumentValue = new Object[] {};
		operator.classFrontStyle = new String[] {};
		
		operator.methodArgument = new Class[] {Dataset.class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.returnType = String.class;
		
		operator.name = "KDtreeKNN算法测试";
		operator.description = "<p>KDtreeKNN算法测试</p>"+
								"<p>对一个Dataset的数据的使用KNN算法进行训练，训练完可以得到一个分类器，利用这个分类器可以对新的数据集进行分类操作</p>" +
								"<p>输入:Dataset，输出:String结果</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		/*
		 * 第3类：Classification--MeanFeatureVotingClassifier算法训练
		 */
		operator = new Operator();
		operator.category = Category.categorys[3];
		operator.operatorClass = "www.classification.myMeanFeatureVotingClassifier";
		operator.operatorMethod = "buildClassifier";
		
		operator.classArgument = new Class[] {};
		operator.classArgumentName = new String[] {};
		operator.classArgumentValue = new Object[] {};
		operator.classFrontStyle = new String[] {};
		
		operator.methodArgument = new Class[] {Dataset.class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.returnType = void.class;
		
		operator.name = "MeanFeatureVotingClassifier算法训练";
		operator.description = "<p>MeanFeatureVotingClassifier算法训练</p>"+
								"<p>对一个Dataset的数据的使用KNN算法进行训练，训练完可以得到一个分类器</p>" +
								"<p>输入:Dataset，输出:void</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		/*
		 * 第3类：Classification--MeanFeatureVotingClassifier算法测试
		 */
		operator = new Operator();
		operator.category = Category.categorys[3];
		operator.operatorClass = "www.classification.myMeanFeatureVotingClassifier";
		operator.operatorMethod = "classifyDataset";
		
		operator.classArgument = new Class[] {};
		operator.classArgumentName = new String[] {};
		operator.classArgumentValue = new Object[] {};
		operator.classFrontStyle = new String[] {};
		
		operator.methodArgument = new Class[] {Dataset.class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.returnType = String.class;
		
		operator.name = "MeanFeatureVotingClassifier算法测试";
		operator.description = "<p>MeanFeatureVotingClassifier算法测试</p>"+
								"<p>对一个Dataset的数据的使用KNN算法进行训练，训练完可以得到一个分类器，利用这个分类器可以对新的数据集进行分类操作</p>" +
								"<p>输入:Dataset，输出:String结果</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		/*
		 * 第3类：Classification--NearestMeanClassifier算法训练
		 */
		operator = new Operator();
		operator.category = Category.categorys[3];
		operator.operatorClass = "www.classification.myNearestMeanClassifier";
		operator.operatorMethod = "buildClassifier";
		
		operator.classArgument = new Class[] {};
		operator.classArgumentName = new String[] {};
		operator.classArgumentValue = new Object[] {};
		operator.classFrontStyle = new String[] {};
		
		operator.methodArgument = new Class[] {Dataset.class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.returnType = void.class;
		
		operator.name = "NearestMeanClassifier算法训练";
		operator.description = "<p>NearestMeanClassifier算法训练</p>"+
								"<p>对一个Dataset的数据的使用KNN算法进行训练，训练完可以得到一个分类器</p>" +
								"<p>输入:Dataset，输出:void</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		/*
		 * 第3类：Classification--NearestMeanClassifier算法测试
		 */
		operator = new Operator();
		operator.category = Category.categorys[3];
		operator.operatorClass = "www.classification.myNearestMeanClassifier";
		operator.operatorMethod = "classifyDataset";
		
		operator.classArgument = new Class[] {};
		operator.classArgumentName = new String[] {};
		operator.classArgumentValue = new Object[] {};
		operator.classFrontStyle = new String[] {};
		
		operator.methodArgument = new Class[] {Dataset.class};
		operator.methodArgumentName = new String[] {"data"};
		operator.methodArgumentValue = new Object[] {null};
		operator.methodFrontStyle = new String[] {"inner-data"};
		operator.returnType = String.class;
		
		operator.name = "NearestMeanClassifier算法测试";
		operator.description = "<p>NearestMeanClassifier算法测试</p>"+
								"<p>对一个Dataset的数据的使用KNN算法进行训练，训练完可以得到一个分类器，利用这个分类器可以对新的数据集进行分类操作</p>" +
								"<p>输入:Dataset，输出:String结果</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		
		//-----------------------第4类，TCM-------------------------
		
		/*
		 * 第4类：中医药症状分词
		 */
		operator = new Operator();
		operator.category = Category.categorys[4];
		operator.operatorClass = "www.tcm.algorithm1";
		operator.operatorMethod = "method1";
		
		operator.classArgument = new Class[] {String.class, double.class,double.class,double.class,double.class,int.class,int.class};
		operator.classArgumentName = new String[] {"待切分汉字串", "a1","p1","a2","p2","rank1","rank2"};
		operator.classArgumentValue = new Object[] {"", 0.1, 0.001, 0.1, 0.001, 3, 3};
		operator.classFrontStyle = new String[] {"textbox", "doublenumberbox","doublenumberbox","doublenumberbox","doublenumberbox","intnumberbox","intnumberbox"};
		
		operator.methodArgument = new Class[] {};
		operator.methodArgumentName = new String[] {};
		operator.methodArgumentValue = new Object[] {};
		operator.methodFrontStyle = new String[] {};
		operator.returnType = String.class;
		
		operator.name = "双向条件概率和相对位置分词算法";
		operator.description = "<p>双向条件概率和绝对位置分词算法</p>"+
								"<p>对一个输入的症状句子的使用双向条件概率和绝对位置进行分词，参数a1和a2分别正向和逆向的成词概率，参数p1和p2分别表示正向和逆向的不成词概率，rank1和rank2分别表示两个相对位置</p>" +
								"<p>输入:String，输出:String</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		
		/*
		 * 第4类：中医药症状句子相似度
		 */
		operator = new Operator();
		operator.category = Category.categorys[4];
		operator.operatorClass = "www.tcm.algorithm2";
		operator.operatorMethod = "method2";
		
		operator.classArgument = new Class[] {};
		operator.classArgumentName = new String[] {};
		operator.classArgumentValue = new Object[] {};
		operator.classFrontStyle = new String[] {};
		
		operator.methodArgument = new Class[] {String.class};
		operator.methodArgumentName = new String[] {"待计算症状句子"};
		operator.methodArgumentValue = new Object[] {""};
		operator.methodFrontStyle = new String[] {"textbox"};
		operator.returnType = String.class;
		
		operator.name = "融合词语相似性和词语重要性的句子相似度计算";
		operator.description = "<p>双向条件概率和绝对位置分词算法</p>"+
								"<p>对一个输入的症状句子的使用融合词语相似性和词语重要性的句子相似度计算方法，在库中寻找出相似程度最高的前3条症状句子。</p>" +
								"<p>输入:String，输出:String[]数组</p>";
		lists.add(operator);
		maps.put(operator.operatorClass+"."+operator.operatorMethod, operator);
		
		
	}

}
