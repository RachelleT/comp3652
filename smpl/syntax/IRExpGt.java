package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class IRExpGt extends IRExpOp {

    IRExp e1, e2;
    
    public IRExpGt(IRExp e1, IRExp e2) {
        super(">", e1, e2);
    }
    

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
	return v.visitIRExpGt(this, arg);
    }
    
    public String toString() {
    	return e1.toString() + " > " + e2.toString();
    }

}
