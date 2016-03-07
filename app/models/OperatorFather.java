package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

/*
 * 传送到前端的json串中的父算子
 */
public class OperatorFather {
	public int id;
	public String text;
	public String state;
	public List<OperatorChild> children;
	
	public OperatorFather(int id, String text, String state, List<OperatorChild> children) {
		this.id = id;
		this.text = text;
		this.state = state;
		this.children = children;
	}
	
	
	
}
