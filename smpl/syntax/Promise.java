package smpl.syntax;

import smpl.semantics.Environment;
import smpl.semantics.Visitor;
import smpl.sys.SmplException;
import java.util.ArrayList;
import smpl.values.SmplObj;

public class Promise extends SmplObj {

    private IRExp expression;
    private Environment env;
    private SmplObj val = SmplObj.DEFAULT;
    boolean eval;

    public Promise(IRExp exp, Environment env) {
    	super(Type.PROMISE);
	expression = exp;
	this.env = env;
    }

    // Add getters
    public IRExp getExp() {
	return expression;
    }

    public Environment getEnvironment() {
	return env;
    }	
    
    public void setVal(SmplObj val) {
	val = this.val;
    }
    
    public SmplObj getVal() {
	return val;
    }
    
    public void setEval(boolean eval){
    	eval = this.eval;
    }
    
    public boolean getEval(){
    	if (val != SmplObj.DEFAULT){
    		return true;
    	} else {
    		return false;
    	}
    }
}
