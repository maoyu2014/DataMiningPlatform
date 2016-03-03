package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;

import java.util.*;

public class OperatorChild {
	public int id;
	public String text;
	public String opclass;
	
	public OperatorChild(int id, String text, String opclass) {
		this.id = id;
		this.text = text;
		this.opclass = opclass;
	}
	
	
}
