package smpl.syntax;

import java.util.ArrayList;
import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class StmtLet extends Statement {
    ArrayList<Binding> bindings;
    Statement body;

    public StmtLet(ArrayList<Binding> bs, Statement bod) {
	bindings = bs;
	body = bod;
    }

    public ArrayList<Binding> getBindings() {
	return bindings;
    }

    public Statement getBody() {
	return body;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
    	return v.visitStmtLet(this, arg);
    }

    public String toString() {
	return "let " + bindings + " in " + body;
    }

}
