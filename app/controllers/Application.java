package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void getAllOperators() {
    	List<OperatorFather> lists = new ArrayList<>();
    	OperatorFather fa = null;
    	OperatorChild ch = null;
    	
    	fa = new OperatorFather(1, "输入输出", "open", null);
    	fa.children = new ArrayList<>();
    	ch = new OperatorChild(11, "文件读取", "loadDataset");
    	fa.children.add(ch);
    	ch = new OperatorChild(12, "文件存储", "exportDataset");
    	fa.children.add(ch);
    	lists.add(fa);
    	
    	fa = new OperatorFather(2, "预处理", "open", null);
    	fa.children = new ArrayList<>();
    	ch = new OperatorChild(21, "归一化", "loadDataset");
    	fa.children.add(ch);
    	ch = new OperatorChild(22, "抽样", "exportDataset");
    	fa.children.add(ch);
    	lists.add(fa);
    	
    	fa = new OperatorFather(3, "分类算法", "open", null);
    	fa.children = new ArrayList<>();
    	ch = new OperatorChild(31, "KNN算法", "KNearestNeighbors");
    	fa.children.add(ch);
    	ch = new OperatorChild(32, "朴素贝叶斯算法", "NaiveBayesClassifier");
    	fa.children.add(ch);
    	lists.add(fa);
    	
    	renderJSON(lists);
    	
    }
}