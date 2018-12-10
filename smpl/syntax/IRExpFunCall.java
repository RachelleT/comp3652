package smpl.syntax;

import java.util.ArrayList;
import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class IRExpFunCall extends IRExp {

    String name;
    ArrayList<IRExp> argList;

    public IRExpFunCall(String nm, ArrayList<IRExp> args) {
	name = nm;
	argList = args;
    }

    public String getName() {
	return name;
    }

    public ArrayList<IRExp> getArguments() {
	return argList;
    }

    public <S, T> T visit(Visitor<S, T> v, S state)
	throws SmplException {
	return v.visitFunCall(this, state);
    }

	@Override
	public String toString() {
		// TODO Implement this
		StringBuilder argStr = new StringBuilder("");
		int n = argList.size();
		if (n > 0) {
	    		argStr.append(argList.get(0));
	    		for (int i = 1; i < n; i++) {
				argStr.append(", ");
				argStr.append(argList.get(i));
	    		}
		}
		return String.format("%s(%s)", name, argStr.toString());
	}
}


