package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class StmtExpr extends Statement {

    IRExp exp;

    public StmtExpr(IRExp e) {
	exp = e;
    }

    /**
     *
     * @return the expression wrapped in this statement
     */
    public IRExp getExp() {
	return exp;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
	return v.visitStmtExpr(this, arg);
    }

    @Override
    public String toString() {
	return exp.toString();
    }
}
