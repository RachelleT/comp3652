package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class IRExpLt extends IRExpOp {

    IRExp e1, e2;
    
    public IRExpLt(IRExp e1, IRExp e2) {
        super("<", e1, e2);
    }
    

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
	return v.visitIRExpLt(this, arg);
    }
    
    public String toString() {
    	return e1.toString() + " < " + e2.toString();
    }
	
}
