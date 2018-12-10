package smpl.values;

import smpl.sys.SmplException;
import smpl.sys.SmplTypeException;

/** 
 * SMPL representation for integers (this implementation is only a suggestion) 
 **/
public class SmplInt extends SmplObj {

    
    private int value;
    
    /** 
     * Construct an instance of an SMPL integer.
     * @param v The value of the number.
     **/
    public SmplInt(int v) {
      super(Type.INT);
      value = v;
    }
    
    // You will need to figure out how to make this work well with floating point numbers

    /**
     *
     * @return the value of this number as an integer.  (It will be cast to
     * integer, if it is not natively an integer).
     */
    public int intValue() {
	return value;
    }
    
    public int value() {
        return value;
    }
    
    @Override
    public SmplObj add(SmplObj arg) throws SmplException {
        // we dispatch on all types that we know how to add to an integer.
        if (arg instanceof SmplInt) {
            return new SmplInt(value() + ((SmplInt) arg).value());
        } else 	if (arg instanceof SmplDouble) {
	    return new SmplDouble(value() + ((SmplDouble)arg).value());
        } else
            // go to parent class for default behaviour
	    return super.add(arg);
    }
    
    @Override
    public SmplObj sub(SmplObj arg) throws SmplException {
        // we dispatch on all types that we know how to add to an integer.
        if (arg instanceof SmplInt) {
            return new SmplInt(value() - ((SmplInt) arg).value());
        } else 	if (arg instanceof SmplDouble) {
	    return new SmplDouble(value() - ((SmplDouble)arg).value());
        } else
            // go to parent class for default behaviour
	    return super.add(arg);
    }
    
    public SmplObj mul(SmplObj arg) throws SmplException {
        // we dispatch on all types that we know how to add to an integer.
        if (arg instanceof SmplInt) {
            return new SmplInt(value() * ((SmplInt) arg).value());
        } else 	if (arg instanceof SmplDouble) {
	    return new SmplDouble(value() * ((SmplDouble)arg).value());
        } else
            // go to parent class for default behaviour
	    return super.mul(arg);
    }
    
    public SmplObj div(SmplObj arg) throws SmplException {
        // we dispatch on all types that we know how to add to an integer.
        if (arg instanceof SmplInt) {
            return new SmplInt(value() / ((SmplInt) arg).value());
        } else 	if (arg instanceof SmplDouble) {
	    return new SmplDouble(value() / ((SmplDouble)arg).value());
        } else
            // go to parent class for default behaviour
	    return super.div(arg);
    }
    
    public SmplObj mod(SmplObj arg) throws SmplException {
        // we dispatch on all types that we know how to add to an integer.
        if (arg instanceof SmplInt) {
            return new SmplInt(value() % ((SmplInt) arg).value());
        } else 	if (arg instanceof SmplDouble) {
	    return new SmplDouble(value() % ((SmplDouble)arg).value());
        } else
            // go to parent class for default behaviour
	    return super.mod(arg);
    }
    
    public SmplBool eq(SmplObj arg) throws SmplException {
        // we dispatch on all types that we know how to add to an integer.
        if (arg instanceof SmplInt) {
            if (value() == ((SmplInt) arg).value()) {
            	return new SmplBool(true);
            } else {
            	return new SmplBool(false);
            }
        } else	if (arg instanceof SmplDouble) {
	     if (value() == ((SmplDouble)arg).value()) {
	     		return new SmplBool(true);
	     } else {
            	return new SmplBool(false);
            }
        } else
            // go to parent class for default behaviour
	    return super.eq(arg);
    }
    
    public SmplBool lt(SmplObj arg) throws SmplException {
        // we dispatch on all types that we know how to add to an integer.
        if (arg instanceof SmplInt) {
            if (value() < ((SmplInt) arg).value()) {
            	return new SmplBool(true);
            } else {
            	return new SmplBool(false);
            }
        } else	if (arg instanceof SmplDouble) {
	     if (value() < ((SmplDouble)arg).value()) {
	     		return new SmplBool(true);
	     } else {
            	return new SmplBool(false);
            }
        } else
            // go to parent class for default behaviour
	    return super.lt(arg);
    }
    
    public SmplBool le(SmplObj arg) throws SmplException {
        // we dispatch on all types that we know how to add to an integer.
        if (arg instanceof SmplInt) {
            if (value() <= ((SmplInt) arg).value()) {
            	return new SmplBool(true);
            } else {
            	return new SmplBool(false);
            }
        } else	if (arg instanceof SmplDouble) {
	     if (value() <= ((SmplDouble)arg).value()) {
	     		return new SmplBool(true);
	     } else {
            	return new SmplBool(false);
            }
        } else
            // go to parent class for default behaviour
	    return super.le(arg);
    }
    
    public SmplBool gt(SmplObj arg) throws SmplException {
        // we dispatch on all types that we know how to add to an integer.
        if (arg instanceof SmplInt) {
            if (value() > ((SmplInt) arg).value()) {
            	return new SmplBool(true);
            } else {
            	return new SmplBool(false);
            }
        } else	if (arg instanceof SmplDouble) {
	     if (value() > ((SmplDouble)arg).value()) {
	     		return new SmplBool(true);
	     } else {
            	return new SmplBool(false);
            }
        } else
            // go to parent class for default behaviour
	    return super.gt(arg);
    }
    
    public SmplBool ge(SmplObj arg) throws SmplException {
        // we dispatch on all types that we know how to add to an integer.
        if (arg instanceof SmplInt) {
            if (value() >= ((SmplInt) arg).value()) {
            	return new SmplBool(true);
            } else {
            	return new SmplBool(false);
            }
        } else	if (arg instanceof SmplDouble) {
	     if (value() >= ((SmplDouble)arg).value()) {
	     		return new SmplBool(true);
	     } else {
            	return new SmplBool(false);
            }
        } else
            // go to parent class for default behaviour
	    return super.ge(arg);
    }

    // you will need to fill in the rest of the applicable operations

    @Override
    public SmplObj neg() throws SmplException {
	return new SmplInt(- value());
    }
    
    public String toString(){
    	return String.format("%d", value);
    }

}
