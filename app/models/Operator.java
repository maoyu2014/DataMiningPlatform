package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

/*
 * 表示后台的一个算子
 */
public class Operator {
	
	public String category;
	public String operatorClass;
	public String operatorMethod;
	
	public Class[] classArgument;
	public String[] classArgumentName;
	public Object[] classArgumentValue;
	public String[] classFrontStyle;
	
	public Class[] methodArgument;
	public String[] methodArgumentName;
	public Object[] methodArgumentValue;
	public String[] methodFrontStyle;
	public Class returnType;
	
	public String name;
	public String description;

}
