package smpl.sys;

import smpl.syntax.IRExp;

/** Exception thrown when a variable is not found in current environment. */
public class SmplUnboundVarException extends SmplException {

    private static final long serialVersionUID = 1L;

    public SmplUnboundVarException() {
	super();
    }

    public SmplUnboundVarException(String mesg) {
	super(mesg);
    }

    public SmplUnboundVarException(IRExp exp, String var) {
	super("The variable " + var + " is unbound in expression " +
	      exp.toString());
    }
}
