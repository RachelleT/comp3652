package smpl.syntax;

import java.util.ArrayList;
import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class StmtFunDefn extends Statement {

    private String name;
    private ArrayList<String> parameters;
    private StmtSequence body;

    public StmtFunDefn(ArrayList<String> params, StmtSequence body) {
	//name = nm;
	parameters = params;
	this.body = body;
    }

    public String getName() {
	return name;
    }

    public ArrayList<String> getParameters() {
	return parameters;
    }

    public StmtSequence getBody() {
	return body;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg)
	throws SmplException {
	return v.visitFunDefn(this, arg);
    }

    public String toString() {
	StringBuilder paramStr = new StringBuilder("");
	int n = parameters.size();
	if (n > 0) {
	    paramStr.append(parameters.get(0));
	    for (int i = 1; i < n; i++) {
		paramStr.append(", ");
		paramStr.append(parameters.get(i));
	    }
	}
	return String.format("fn %s(%s) {%s}", name,
			     paramStr.toString(),
			     body.toString());
	}
}
