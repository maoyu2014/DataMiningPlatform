package controllers;

import play.*;
import play.mvc.*;
import utils.AllOperators;

import java.util.*;

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
    
    
}