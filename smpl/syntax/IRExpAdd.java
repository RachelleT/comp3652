package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

/**
 * Class to represent an addition AST node
 * @author newts
 */
public class IRExpAdd extends IRExpOp {

	IRExp e1, e2;

    /**
     * Construct an addition expression from the two subexpressions
     * @param e1 the left subexpression.
     * @param e2 the right subexpression
     */
    public IRExpAdd(IRExp e1, IRExp e2) {
      super("+", e1, e2);
    }

  @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException
    {
	return v.visitExpAdd(this, arg);
    }
    
    public String toString() {
    	return e1.toString() + " + " + e2.toString();
    }
	
}

