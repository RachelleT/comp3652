package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

/**
 * AST node to represent a variable definition.
 * @author newts
 */
public class StmtDefinition extends Statement {

    String var;
    IRExp exp;
    Statement stmt;

    /**
     * Create an instance of a variable definition
     * @param id The variable being defined
     * @param e The expression producing its value
     */
    public StmtDefinition(String id, IRExp e) {
	var = id;
	exp = e;
    }
    
    public StmtDefinition(String id, Statement s) {
	var = id;
	stmt = s;
    }

    public String getVar(){
	return var;
    }

    public IRExp getExp() {
	return exp;
    }
    
    public Statement getStmt(){
    	return stmt;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException
    {
	return v.visitStmtDefinition(this, arg);
    }
    
    @Override
    public String toString() {
        return String.format("%s = %s", var, exp);
    }

}
