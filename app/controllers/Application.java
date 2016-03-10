package controllers;

import play.*;
import play.mvc.*;
import utils.AllOperators;
import utils.RunExperiment;

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
    	new RunExperiment(n, arrayrow);
    }
    
}