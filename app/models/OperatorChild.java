package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

/*
 * 传送到前端的json串中的子算法
 * 子算子也是具体的一个算法，也就是一个类+一个方法
 * 所以为了方便，我们会传送一些额外的东西到client端
 */
public class OperatorChild {
	
	public String text;
	public String operatorClass;
	public String operatorMethod;
	
	public String[] classArgumentName;
	public Object[] classArgumentValue;
	public String[] classFrontStyle;
	
	public String[] methodArgumentName;
	public Object[] methodArgumentValue;
	public String[] methodFrontStyle;
	
	public String description;
	
	
}
