package controllers;

import play.*;
import play.data.Upload;
import play.libs.Files;
import play.mvc.*;
import utils.AllFrontOperators;
import utils.RunExperiment;

import java.io.File;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import models.*;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void getAllOperators() {
    	List<OperatorFather> listOperatorFather = AllFrontOperators.getLists();
    	renderJSON(listOperatorFather);
    }
    
    public static void uploadFile(File mydatafile) {
    	if (mydatafile!=null) {
    		long currentTime = System.currentTimeMillis();
	    	String fileName = currentTime + mydatafile.getName();
	    	Files.copy(mydatafile, Play.getFile("public/uploaddata/" + fileName));
	    	renderText(fileName);
    	} else {
    		renderText("nofile");
    	}
    }
    
    public static void acceptExperiment(String rows) {
    	Object obj=JSONValue.parse(rows);
    	JSONArray arrayrow=(JSONArray)obj;
    	int n = arrayrow.size();
    	String key = RunExperiment.Run(n, arrayrow);
    	renderText(key);
    }
    
    public static void showResult(String key) {
    	Map<String, String> resultMaps = ResultObj.maps;
    	String result = resultMaps.get(key);
//    	resultMaps.remove(key);
    	String[] str = key.split(":");
    	int casenum = Integer.parseInt(str[1]);
    	
    	if (casenum==1) {
    		//导入数据
    		String[] tmp = result.substring(1,result.length()-1).split("}, ");
    		for (int i=0; i<tmp.length; i++) {
    			tmp[i] = tmp[i].substring(1);
    			if (i==tmp.length-1) tmp[i] =  tmp[i].substring(0, tmp[i].length()-1);
    		}
    		render("Application/result001.html",tmp);
    	} 
    	else if (casenum==3) {
    		//各种聚类算法
    		String[] temp1 = result.split("类");
    		int count = Integer.parseInt(temp1[0]);
    		ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();
    		int length=0;
    		String[] temp2 = temp1[1].split(";;");
    		for (int i=0; i<temp2.length; i++) {
    			ArrayList<String> alist = new ArrayList<String>();
    			String[] tmp = temp2[i].substring(1,temp2[i].length()-1).split("}, ");
    			length = Math.max(length, tmp.length);
    			for (int j=0; j<tmp.length; j++) {
        			tmp[j] = tmp[j].substring(1);
        			if (j==tmp.length-1) tmp[j] =  tmp[j].substring(0, tmp[j].length()-1);
        			alist.add(tmp[j]);
        		}
    			lists.add(alist);
    		}
    		render("Application/result003.html",count,length,lists);
    	} 
    	else if (casenum==6) {
    		//对所有列进行评分
    		String[] temp1 = result.split(",");
    		List<Double> list = new ArrayList<>();
    		for (int i=0; i<temp1.length; i++) {
    			list.add(Double.parseDouble(temp1[i]));
    		}
    		render("Application/result006.html",list);
    	} 
    	else if (casenum==7) {
    		//对所有列进行ranking排名
    		String[] temp1 = result.split(",");
    		List<Integer> list = new ArrayList<>();
    		for (int i=0; i<temp1.length; i++) {
    			list.add(Integer.parseInt(temp1[i]));
    		}
    		render("Application/result007.html",list);
    	} 
    	else if (casenum==8) {
    		//各种分类算法
    		String[] A = result.split("错误预测");
    		int wrong = Integer.parseInt(A[1]);
    		String[] B = A[0].split("正确预测");
    		int correct = Integer.parseInt(B[1]);
    		int total = wrong+correct;
    		String[] C = B[0].split(";;;");
    		List<String> listA = new ArrayList<>();
    		List<String> listB = new ArrayList<>();
    		List<String> listC = new ArrayList<>();
    		for (int i=0; i<C.length; i++) {
    			String[] D = C[i].split(";;");
    			listA.add(D[0]);
    			listB.add(D[1]);
    			listC.add(D[2]);
    		}
    		double correctLV = 1.0 * correct / total;
    		double wrongLV = 1.0 * wrong / total;
    		render("Application/result008.html",listA,listB,listC,correct,wrong,total,correctLV,wrongLV);
    	}
    	else {
    		renderText(result);
    	}
    }
    
    
    
    
    
    
    
    
    
    
    
    //文件上传测试页面
    public static void testUpload() {
    	render();
    }
    
    
    //多文件上传测试
    /*
    public static void uploadPhotoFile(String abc) {
    	List<Upload> files = (List<Upload>) request.args.get("__UPLOADS");
    	String fileName = null;
        for (Upload upload : files) {
            if (upload.getSize() > 0) {
                File f = upload.asFile();
                long currentTime = System.currentTimeMillis();
                fileName = currentTime + f.getName();
                File storeFile = new File("./public/uploaddata/" + fileName);
                Files.copy(f, storeFile);
            }
        }
        renderText(fileName);
    }
    */
    
    
}