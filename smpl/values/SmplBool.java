package smpl.values;

import smpl.sys.SmplException;
import smpl.sys.SmplTypeException;

/**
 * SMPL representation for integers *
 */
public class SmplBool extends SmplObj {

    /**
     * The true and false objects *
     */
    public static final SmplBool TRUE = new SmplBool(true);
    public static final SmplBool FALSE = new SmplBool(false);
    
    private boolean value;

    /**
     * Create an SMPL Boolean instance by wrapping a Java boolean.
     *
     * @param v The Java boolean.
     *
     */
    public SmplBool(boolean v) {
        super(Type.BOOLEAN);
        value = v;
    }

    /**
     * Convert a Java boolean to its equivalent SMPL constant. This will prevent
     * several copies of an instance representing the two boolean constants from
     * existing, since it will return a reference to either predefined constant
     * (without creating a fresh one).
     *
     * @param v The intended boolean value
     * @return The SMPL boolean equivalent constant
     *
     */
    public static SmplBool getConst(boolean v) {
        if (v) {
            return TRUE;
        } else {
            return FALSE;
        }
    }

    public boolean val() {
        return value;
    }

    /**
     * @return true, identifying this object as an SMPL Boolean
     *
     */
    @Override
    public boolean isBool() {
        return true;
    }

    /**
     * @return true if this object is an SMPL representation for true 
     *
     */
    @Override
    public boolean isTrue() {
        return this.equals(TRUE);
    }

    /**
     * @return true if this object is an SMPL representation for false 
     *
     */
    @Override
    public boolean isFalse() {
        return this.equals(FALSE);
    }

    // Should override methods for logical operations here.
    public boolean equals(SmplObj obj) {
      if (obj instanceof SmplBool){
      	return val() == ((SmplBool) obj).val();
      } else {
      	return false;
      }
    }

    
    public String toString(){
    	return String.format("%b", value);
    }
}
