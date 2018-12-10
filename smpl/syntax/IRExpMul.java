package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;



/** 
 * IR for Multiplication operation 
 **/
public class IRExpMul extends IRExpOp {

    /**
     * Create a node to represent multiplication.
     * @param term the left operand
     * @param factor the right operand
     */
    IRExp e1, e2;
    
    public IRExpMul(IRExp term, IRExp factor) {
	super("*", term, factor);
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitExpMul(this, state);
    }
    
    public String toString() {
    	return e1.toString() + " * " + e2.toString();
    }

}
