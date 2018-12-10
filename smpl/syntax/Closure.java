package smpl.syntax;

import smpl.semantics.Environment;
import smpl.semantics.Visitor;
import smpl.sys.SmplException;
import java.util.ArrayList;
import smpl.values.SmplObj;

public class Closure extends SmplObj {

    private ArrayList<String> parameters;
    private StmtSequence body;
    private Environment env;

    public Closure(ArrayList<String> params, StmtSequence body, Environment env) {
    	super(Type.PROC);
	parameters = params;
	this.body = body;
	this.env = env;
    }

    // Add getters
    public ArrayList<String> getParameters() {
	return parameters;
    }

    public Environment getEnvironment() {
	return env;
    }	

    public StmtSequence getBody() {
	return body;
    }
}
