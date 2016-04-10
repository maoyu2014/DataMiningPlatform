package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import models.Operator;
import models.OperatorChild;
import models.OperatorFather;

/*
 * 传送到前端的用来展示的所有算子的集合
 * 由于数据量比较大，可能需要使用post方式
 */
public class AllFrontOperators {
	
	private AllFrontOperators() {}
	
	public static List<OperatorFather> lists= null;
	
	public static List<OperatorFather> getLists() {
		if (lists==null) setAllFrontOperators();
		return lists;
	}
	
	private static void setAllFrontOperators() {
		lists = new ArrayList<>();
		OperatorFather fa = null;
		OperatorChild ch = null;
		
		List<Operator> listO = AllServerOperators.getLists();

		fa = new OperatorFather(0, "Data manipulation", "open", null);
		fa.children = new ArrayList<>();
		for (Operator operator : listO) {
			if (operator.category.equals(Category.categorys[0])) {
				ch = new OperatorChild();
				ch.text = operator.name;
				ch.operatorClass=operator.operatorClass;
				ch.operatorMethod=operator.operatorMethod;
				ch.classArgumentName=operator.classArgumentName;
				ch.classArgumentValue=operator.classArgumentValue;
				ch.classFrontStyle=operator.classFrontStyle;
				ch.methodArgumentName=operator.methodArgumentName;
				ch.methodArgumentValue=operator.methodArgumentValue;
				ch.methodFrontStyle=operator.methodFrontStyle;
				ch.description=operator.description;
				fa.children.add(ch);
			}
		}
		lists.add(fa);

		fa = new OperatorFather(1, "Clustering", "open", null);
		fa.children = new ArrayList<>();
		for (Operator operator : listO) {
			if (operator.category.equals(Category.categorys[1])) {
				ch = new OperatorChild();
				ch.text = operator.name;
				ch.operatorClass=operator.operatorClass;
				ch.operatorMethod=operator.operatorMethod;
				ch.classArgumentName=operator.classArgumentName;
				ch.classArgumentValue=operator.classArgumentValue;
				ch.classFrontStyle=operator.classFrontStyle;
				ch.methodArgumentName=operator.methodArgumentName;
				ch.methodArgumentValue=operator.methodArgumentValue;
				ch.methodFrontStyle=operator.methodFrontStyle;
				ch.description=operator.description;
				fa.children.add(ch);
			}
		}
		lists.add(fa);

		fa = new OperatorFather(2, "Feature selection", "open", null);
		fa.children = new ArrayList<>();
		lists.add(fa);
		
		fa = new OperatorFather(3, "Classification", "open", null);
		fa.children = new ArrayList<>();
		lists.add(fa);
	}

}
