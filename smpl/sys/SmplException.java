package smpl.sys;

import smpl.syntax.IRExp;

/** Parent class of all SMPL exceptions **/
public class SmplException extends Exception {

    private static final long serialVersionUID = 1L;
    

    IRExp offender;		// offending expression
    SmplException previous;	// support walking the execution chain

    public SmplException() {
	super();
    }

    public SmplException(String message) {
	super(message);
    }
    
    public SmplException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmplException(IRExp exp) {
	super();
	offender = exp;
    }

    public SmplException(IRExp exp, String message) {
	super(message);
	offender = exp;
    }

    public SmplException(SmplException se, IRExp exp) {
	super();
	previous = se;
	offender = exp;
    }
}
