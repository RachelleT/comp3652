package smpl.semantics;

import cs34q.gfx.GraphingPanel;
import java.util.*;
import smpl.syntax.IRExpAdd;
import smpl.syntax.IRExpDiv;
import smpl.syntax.IRExpLit;
import smpl.syntax.IRExpMod;
import smpl.syntax.IRExpMul;
import smpl.syntax.IRExpSub;
import smpl.syntax.IRExpVar;
import smpl.syntax.IRExpIf;
import smpl.syntax.IRExpLe;
import smpl.syntax.IRExpFunCall;
import smpl.syntax.IRExpGe;
import smpl.syntax.IRExpGt;
import smpl.syntax.IRExpLt;
import smpl.syntax.IRExpGt;
import smpl.syntax.IRExpLe;
import smpl.syntax.IRExpLt;
import smpl.syntax.IRExpEq;
import smpl.syntax.SmplProgram;
import smpl.syntax.Statement;
import smpl.syntax.StmtDefinition;
import smpl.syntax.StmtExpr;
import smpl.syntax.StmtSequence;
import smpl.syntax.StmtFunDefn;
import smpl.syntax.StmtLet;
import smpl.syntax.Binding;
import smpl.syntax.Closure;
import smpl.syntax.Promise;
import smpl.syntax.IRExp;
import smpl.sys.SmplException;
import smpl.values.SmplBool;
import smpl.values.SmplObj;

public class SmplEval implements Visitor<Environment<SmplObj>, SmplObj> {
    /* For this visitor, the argument passed to all visit
       methods will be the environment object that used to
       be passed to the eval method in the first style of
       implementation. */

    // allocate state here
    protected SmplObj lastResult;	// lastResult of evaluation
    private GraphingPanel graphicsPanel;
    private Environment<SmplObj> globalEnv;
    
    public SmplEval() {
	// perform initialisations here
	lastResult = SmplObj.DEFAULT;
    }
    
    /**
     * Set the graphics panel (in case one is to be used)
     * @param panel
     */
    public void setView(GraphingPanel panel) {
        graphicsPanel = panel;
    }
    
    /**
     * Initialise this interpeter with a fresh global environment
     */
    public void init() {
        globalEnv = Environment.makeGlobalEnv(SmplObj.class);
    }
    
    /**
     *
     * @return The top level environment being used by this interpreter instance
     */
    public Environment<SmplObj> getGlobalEnv() {
        return globalEnv;
    }

    @Override
    public SmplObj visitSmplProgram(SmplProgram p,
				    Environment<SmplObj> arg)
	throws SmplException 
    {
	lastResult = p.getSeq().visit(this, arg);
	return lastResult;
    }

    @Override
    public SmplObj visitStmtExpr(StmtExpr s, Environment<SmplObj> arg)
	throws SmplException {
	return s.getExp().visit(this, arg);
    }

    @Override
    public SmplObj visitStmtSequence(StmtSequence sseq,
				    Environment<SmplObj> arg)
	throws SmplException
    {
	// remember that arg is the environment
	Statement s;
	ArrayList<Statement> seq = sseq.getSeq();
	Iterator iter = seq.iterator();
	SmplObj result = SmplObj.DEFAULT; // default lastResult
      while(iter.hasNext()) {
		s = (Statement) iter.next();
    }
	for (Statement st : seq) {
	    result = st.visit(this, arg);
	}
	// return last value evaluated
	return result;
    }
    
    public SmplObj visitStmtDefinition(StmtDefinition sd,
				    Environment<SmplObj> arg)
	throws SmplException
    {
	Environment<SmplObj> env = (Environment<SmplObj>) arg;
	SmplObj result;
	if (sd.getStmt() == null){
		result = sd.getExp().visit(this, env);
		env.put(sd.getVar(), result);
		return result;
	} else {
		result = sd.getStmt().visit(this, env);
		System.out.println(result);
		env.put(sd.getVar(), result);
		return result;
	}
    }
    
    
    public SmplObj visitFunDefn(StmtFunDefn funDef, Environment<SmplObj> arg)
	throws SmplException {
	Environment<SmplObj> env = (Environment<SmplObj>) arg;
	Closure fun = new Closure(funDef.getParameters(),
				     funDef.getBody(),
				     env);
	env.put(funDef.getName(), fun);   
	return fun; 
    }

    public SmplObj visitFunCall(IRExpFunCall exp, Environment<SmplObj> arg)
	throws SmplException {
	// unimplemented for now
	int counter = 0, count = 0;
	Environment<SmplObj> env = arg;
	String name = exp.getName();
	ArrayList<IRExp> argExps = exp.getArguments();
	SmplObj fun = env.get(name); 
	ArrayList<String> ids = ((Closure) fun).getParameters();
	ArrayList<SmplObj> values = new ArrayList<>();
	for (IRExp fnarg : argExps) {
	    if (counter >= ids.size()){
	    	values.add(fnarg.visit(this, env));
	    } else {
	    	if (ids.get(counter).length() > 3){
	    		if (ids.get(counter).substring(0,4).equals("lazy")){
	    			Promise promise = new Promise(fnarg, env);
				values.add(promise);
				ids.set(counter, ids.get(counter).substring(4));
	    		} else {
	    			values.add(fnarg.visit(this, env));
	    		}
	    	} else {
	    		values.add(fnarg.visit(this, env));
	    	}
	    }
	    counter++;
	    //values.add(fnarg.visit(this, env));
	} 
	Environment<SmplObj> newEnv =
	    new Environment(ids, values, ((Closure) fun).getEnvironment()); 
	SmplObj result = ((Closure) fun).getBody().visit(this, newEnv);
	while (result instanceof Closure) {
		int num = 0;
		while (num != ids.size()){
			argExps.remove(0);
			num++;
		}
		ArrayList<String> newIds = ((Closure) result).getParameters();
		ArrayList<SmplObj> newValues = new ArrayList<>();
		for (IRExp fnargs : argExps) {
	    		if (count >= newIds.size()){
	    			newValues.add(fnargs.visit(this, env));
	    		} else {
	    			if (newIds.get(count).length() > 3){
	    				if (newIds.get(count).substring(0,4).equals("lazy")){
	    					Promise promise = new Promise(fnargs, env);
						newValues.add(promise);
						newIds.set(count, newIds.get(counter).substring(4));
	    				} else {
	    					newValues.add(fnargs.visit(this, env));
	    				}
	    			} else {
	    				newValues.add(fnargs.visit(this, env));
	    			}
	   	 	}
	    		counter++;
	    	}
		Environment<SmplObj> newCEnv = new Environment(newIds, newValues, ((Closure) result).getEnvironment()); 
		SmplObj res = ((Closure) result).getBody().visit(this, newCEnv);
		return res;
	} 
	return result;
    }
    
    public SmplObj visitIRExpIf(IRExpIf ifstmt, Environment<SmplObj> arg) 
    	throws SmplException
    {
    	SmplObj result = SmplObj.DEFAULT;
    	SmplObj pred = ifstmt.getPredicate().visit(this, arg);	
    	if (pred.isTrue()){
    		result = ifstmt.getConsequent().visit(this, arg);
    		return result;
    	} else {
    		if (ifstmt.getAlternative()!=null){
    			result = ifstmt.getAlternative().visit(this, arg);
    			return result;
    		}	
    	}
    	return result;
    }
	
    public SmplBool visitIRExpEq(IRExpEq exp, Environment<SmplObj> arg) 
    	throws SmplException
    {
    	SmplObj val1, val2;
	val1 = exp.getOperand1().visit(this, arg);
	val2 = exp.getOperand2().visit(this, arg);
        return val1.eq(val2);
    }
    
    public SmplBool visitIRExpLt(IRExpLt exp, Environment<SmplObj> arg) 
    	throws SmplException
    {
    	SmplObj val1, val2;
	val1 = exp.getOperand1().visit(this, arg);
	val2 = exp.getOperand2().visit(this, arg);
        return val1.lt(val2);
    }
    
    public SmplBool visitIRExpGt(IRExpGt exp, Environment<SmplObj> arg) 
    	throws SmplException
    {
    	SmplObj val1, val2;
	val1 = exp.getOperand1().visit(this, arg);
	val2 = exp.getOperand2().visit(this, arg);
        return val1.gt(val2);
    }
    
    public SmplBool visitIRExpLe(IRExpLe exp, Environment<SmplObj> arg) 
    	throws SmplException
    {
    	SmplObj val1, val2;
	val1 = exp.getOperand1().visit(this, arg);
	val2 = exp.getOperand2().visit(this, arg);
        return val1.le(val2);
    }
    
    public SmplBool visitIRExpGe(IRExpGe exp, Environment<SmplObj> arg) 
    	throws SmplException
    {
    	SmplObj val1, val2;
	val1 = exp.getOperand1().visit(this, arg);
	val2 = exp.getOperand2().visit(this, arg);
        return val1.ge(val2);
    }
	
    @Override
    public SmplObj visitExpAdd(IRExpAdd exp, Environment<SmplObj> arg)
	throws SmplException
    {
	SmplObj val1, val2;
	val1 = exp.getOperand1().visit(this, arg);
	val2 = exp.getOperand2().visit(this, arg);
        return val1.add(val2);	
    }

    @Override
    public SmplObj visitExpSub(IRExpSub exp, Environment<SmplObj> arg)
	throws SmplException
    {
	SmplObj val1, val2;
	val1 = exp.getOperand1().visit(this, arg);
	val2 = exp.getOperand2().visit(this, arg);
        return val1.sub(val2);	
    }

    @Override
    public SmplObj visitExpMul(IRExpMul exp, Environment<SmplObj> arg)
	throws SmplException
    {
	SmplObj val1, val2;
	val1 = exp.getOperand1().visit(this, arg);
	val2 = exp.getOperand2().visit(this, arg);
        return val1.mul(val2);	
    }

    @Override
    public SmplObj visitExpDiv(IRExpDiv exp, Environment<SmplObj> arg)
	throws SmplException
    {
	// unimplemented
      SmplObj val1, val2;
	val1 = exp.getOperand1().visit(this, arg);
	val2 = exp.getOperand2().visit(this, arg);
	return val1.div(val2);
    }

    @Override
    public SmplObj visitExpMod(IRExpMod exp, Environment<SmplObj> arg)
	throws SmplException
    {
	// unimplemented
      SmplObj val1, val2;
	val1 = exp.getOperand1().visit(this, arg);
	val2 = exp.getOperand2().visit(this, arg);
	return val1.mod(val2);
    }

    @Override
    public SmplObj visitExpLit(IRExpLit exp, Environment<SmplObj> arg)
	throws SmplException
    {
        // unimplemented
	return exp.getVal();
    }

    @Override
    public SmplObj visitExpVar(IRExpVar exp, Environment<SmplObj> arg)
	throws SmplException
    {
	// remember that arg is really the environment
	Environment<SmplObj> env = arg;
	SmplObj val = env.get(exp.getVar());
	if (val instanceof Promise) {
		Promise promise = (Promise) val;
		if (promise.getEval() == false){
			SmplObj cache = (promise.getExp()).visit(this,env);
			promise.setVal(cache);
			promise.setEval(true);
			val = cache;
		} else {
			val = promise.getVal();
		}
	} else {
		return val;
	}
	return val;
    }

	@Override
	public SmplObj visitStmtLet(StmtLet stmtlet, Environment<SmplObj> arg) throws SmplException {
		// TODO Implement this
		Environment<SmplObj> env = (Environment<SmplObj>) arg;
		ArrayList<Binding> bindings = stmtlet.getBindings();
		Statement body = stmtlet.getBody();

		int size = bindings.size();
		String[] vars = new String[size];
		SmplObj[] vals = new SmplObj[size];
		Binding b;
		for (int i = 0; i < size; i++) {
	    		b = bindings.get(i); 
	    		vars[i] = b.getVar();
	    		vals[i] = b.getValIRExp().visit(this, env); 
	    	/*Goes to visit of whatever b is (Could be expression or int) */
	      /* Populate vars[] and vals[] appropriately so that the
	      * body will be evaluated w.r.t. the correct
	      * environment */
		}
		// create new env as child of current
		Environment newEnv = new Environment(vars,vals, env); 
		return body.visit(this,newEnv);  /* Replace this line. Goes to statement visitor in this class.*/
    	}
}

