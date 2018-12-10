package smpl.sys;

import smpl.values.SmplObj;

/** 
 * Exception thrown when the object type is wrong for the current operation. 
 */
public class SmplTypeException extends SmplException {

    private static final long serialVersionUID = 1L;

    /**
     * Create an exception triggered by trying to perform an operation with 
     * an inappropriate object.
     * @param so the inappropriate operand object
     * @param op the name (string representation) of the operation.
     */
    public SmplTypeException(SmplObj so, String op) {
	super( "The object " + so.toString() +
	       " used with " + op + " is of the wrong type");
    }
}
