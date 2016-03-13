package controllers;

import play.*;
import play.data.Upload;
import play.libs.Files;
import play.mvc.*;
import utils.AllOperators;
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
    	AllOperators allOperators = new AllOperators();
    	List<OperatorFather> lists = allOperators.lists;    	
    	renderJSON(lists);
    }
    
    public static void acceptExperiment(String rows) {
    	Object obj=JSONValue.parse(rows);
    	JSONArray arrayrow=(JSONArray)obj;
    	int n = arrayrow.size();
    	long key = RunExperiment.Run(n, arrayrow);
    	renderText(key);
    }
    
    public static void showResult(long key) {
    	String result = ResultObj.results.get(key);
    	renderText(result);
    }
    
    public static void testFile() {
    	render();
    }
    
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
}