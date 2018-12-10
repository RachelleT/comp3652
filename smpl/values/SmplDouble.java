/*
 * SmplDouble.java
 * Created on 7-Nov-2018, 10:15:48 AM
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smpl.values;

import smpl.sys.SmplException;

/**
 * A suggested implementation (sketch) for floating point numbers in SMPL.
 * @author newts
 */
public class SmplDouble extends SmplObj {

    private double value;
    
    public SmplDouble(double val) {
        super(Type.REAL);
        value = val;
    }
    
    public double value() {
        return value;
    }
    
    /**
     *
     * @param val
     * @return
     * @throws smpl.sys.SmplException
     */
    @Override
    public SmplObj add(SmplObj val) throws SmplException {
        if (val instanceof SmplDouble) {
            return new SmplDouble(value + ((SmplDouble) val).value());
        } else if (val instanceof SmplInt) {
            return new SmplDouble(value + ((SmplInt) val).value());    
        } else {
            return super.add(val);
        }
    }
    
    public SmplObj sub(SmplObj val) throws SmplException {
        if (val instanceof SmplDouble) {
            return new SmplDouble(value - ((SmplDouble) val).value());
        } else if (val instanceof SmplInt) {
            return new SmplDouble(value - ((SmplInt) val).value());    
        } else {
            return super.sub(val);
        }
    }
    
    public SmplObj mul(SmplObj val) throws SmplException {
        if (val instanceof SmplDouble) {
            return new SmplDouble(value * ((SmplDouble) val).value());
        } else if (val instanceof SmplInt) {
            return new SmplDouble(value * ((SmplInt) val).value());    
        } else {
            return super.mul(val);
        }
    }
    
    public SmplObj div(SmplObj val) throws SmplException {
        if (val instanceof SmplDouble) {
            return new SmplDouble(value / ((SmplDouble) val).value());
        } else if (val instanceof SmplInt) {
            return new SmplDouble(value / ((SmplInt) val).value());    
        } else {
            return super.div(val);
        }
    }
    
    public SmplObj mod(SmplObj val) throws SmplException {
        if (val instanceof SmplDouble) {
            return new SmplDouble(value % ((SmplDouble) val).value());
        } else if (val instanceof SmplInt) {
            return new SmplDouble(value % ((SmplInt) val).value());    
        } else {
            return super.mod(val);
        }
    }
    
    public SmplBool eq(SmplObj val) throws SmplException {
        if (val instanceof SmplDouble) {
            if (value == ((SmplDouble) val).value()){
            	return new SmplBool(true);
            } else {
            	return new SmplBool(false);
            }
        } else if (val instanceof SmplInt) {
            if (value == ((SmplInt) val).value()){
            	return new SmplBool(true);
            } else {
            	return new SmplBool(false);
            }   
        } else {
            return super.eq(val);
        }
    }
    
    public SmplBool lt(SmplObj val) throws SmplException {
        if (val instanceof SmplDouble) {
            if (value < ((SmplDouble) val).value()){
            	return new SmplBool(true);
            } else {
            	return new SmplBool(false);
            }
        } else if (val instanceof SmplInt) {
            if (value < ((SmplInt) val).value()){
            	return new SmplBool(true);
            } else {
            	return new SmplBool(false);
            }   
        } else {
            return super.lt(val);
        }
    }
    
    public SmplBool le(SmplObj val) throws SmplException {
        if (val instanceof SmplDouble) {
            if (value <= ((SmplDouble) val).value()){
            	return new SmplBool(true);
            } else {
            	return new SmplBool(false);
            }
        } else if (val instanceof SmplInt) {
            if (value <= ((SmplInt) val).value()){
            	return new SmplBool(true);
            } else {
            	return new SmplBool(false);
            }   
        } else {
            return super.le(val);
        }
    }
    
    public SmplBool gt(SmplObj val) throws SmplException {
        if (val instanceof SmplDouble) {
            if (value > ((SmplDouble) val).value()){
            	return new SmplBool(true);
            } else {
            	return new SmplBool(false);
            }
        } else if (val instanceof SmplInt) {
            if (value > ((SmplInt) val).value()){
            	return new SmplBool(true);
            } else {
            	return new SmplBool(false);
            }   
        } else {
            return super.gt(val);
        }
    }
    
    public SmplBool ge(SmplObj val) throws SmplException {
        if (val instanceof SmplDouble) {
            if (value >= ((SmplDouble) val).value()){
            	return new SmplBool(true);
            } else {
            	return new SmplBool(false);
            }
        } else if (val instanceof SmplInt) {
            if (value >= ((SmplInt) val).value()){
            	return new SmplBool(true);
            } else {
            	return new SmplBool(false);
            }   
        } else {
            return super.ge(val);
        }
    }
    
    public SmplObj neg() throws SmplException {
	return new SmplDouble(- value());
    }
    
    public String toString(){
    	return String.format("%f", value);
    }
}
