package smpl.values;

import smpl.sys.SmplException;
import smpl.sys.SmplTypeException;

/** 
 * Parent class for all types of objects representable in SMPL.  This 
 * methodology for supporting a type system in a dynamically typed language is
 * not the only way.  It is merely a suggestion.  Feel free to make changes to
 * it as you see fit.
 **/
public class SmplObj {

    

    public enum Type {
        NONE("No Value"), INT("Int"), REAL("Real"), BOOLEAN("Boolean"), PROMISE("Promise"),
        CHAR("Char"), STRING("String"), PAIR("Pair"), LIST("List"), PROC("Proc");
        
        String name;
                      
       private Type(String name) {
           this.name = name;
       }
                      
       @Override
       public String toString() {
           return name;
       }
    }
    
    public static final SmplObj DEFAULT = new SmplObj(Type.NONE);
    public static final SmplObj NIL = new SmplNil();
    
    private Type type;
    
    public SmplObj(Type t) {
        type = t;
    }
    
    //* Internal representation for this object
    // protected T value = null; 

//    public SmplObj(T val) {
//        value = val;
//    }
//    
//    /** 
//     * Return internal representation for this object 
//     **/
//    public T value() {
//	return value;
//    }

    /** 
     * Returns true if this object is an SMPL number 
     **/
    public boolean isNum() {
	return type == Type.INT || type == Type.REAL;
    }

    /** 
     * Returns true if this object is an SMPL boolean 
     **/
    public boolean isBool() {
	return type == Type.BOOLEAN;
    }

    /** 
     * Returns true if this object is an SMPL boolean 
     * @return true if this object would not be considered false
     **/
    public boolean isTrue() {
	return !isFalse();
    }

    /** 
     * Returns true if this object is an SMPL boolean 
     * @return true if this object is equivalent to any of the values declared
     * by the SMPL language spec to be equivalent to false.
     **/
    public boolean isFalse() {
	return false;
    }

    /** 
     * Returns true if this object is an SMPL character 
     **/
    public boolean isChar() {
	return type == Type.CHAR;
    }

    /** 
     * Returns true if this object is an SMPL string 
     **/
    public boolean isString() {
	return type == Type.STRING;
    }

    /** 
     * Returns true if this object is an SMPL pair 
     **/
    public boolean isPair() {
	return false;    // incomplete
    }

    /** 
     * Returns true if this object is an SMPL vector 
     **/
    public boolean isVector() {
	return false;  // incomplete
    }

    /**
     * Returns true if this object is an SMPL Procedure 
     **/
    public boolean isProc() {
	return false;   // incomplete
    }

    /** 
     * Combine given arg with this object according to the
     * meaning of the + operator with the types of the operands.
     */
    public SmplObj add(SmplObj arg) throws SmplException {
	
	/* Cannot add to the default object, so throw an exception */
	throw new SmplTypeException(this, "+");
    }

    /** 
     * Combine given arg with this object according to the
     * meaning of the - operator with the types of the operands.
     */
    public SmplObj sub(SmplObj arg) throws SmplException {
	
	/* Cannot subtract from the default object, so throw an exception */
	throw new SmplTypeException(this, "-");
    }

    /** 
     * Combine given arg with this object according to the
     * meaning of the * operator with the types of the operands.
     */
    public SmplObj mul(SmplObj arg) throws SmplException {
	
	/* Cannot multiply by the default object, so throw an exception */
	throw new SmplTypeException(this, "*");
    }

    /** 
     * Combine given arg with this object according to the
     * meaning of the / operator with the types of the operands.
     */
    public SmplObj div(SmplObj arg) throws SmplException {
	
	/* Cannot divide the default object, so throw an exception */
	throw new SmplTypeException(this, "/");
    }

    /** 
     * Combine given arg with this object according to the
     * meaning of the % operator with the types of the operands.
     */
    public SmplObj mod(SmplObj arg) throws SmplException {
	
	/* Cannot modulo the default object, so throw an exception */
	throw new SmplTypeException(this, "%");
    }

    /** 
     * Apply the meaning of the unary - operator to this value.
     * @return The unary negative of this object
     * @throws smpl.sys.SmplException if this object cannot be negated.
     */
    public SmplObj neg() throws SmplException {
	
	/* Cannot modulo the default object, so throw an exception */
	throw new SmplTypeException(this, "-");
    }

    /** 
     * Combine given arg with this object according to the meaning
     * of the bitwise AND operator for the types of the operands.
     */
    public SmplObj andBits(SmplObj arg) throws SmplException {
	
	/* Cannot bitwise AND with the default object, so throw an exception */
	throw new SmplTypeException(this, "&");
    }

    /** 
     * Combine given arg with this object according to the meaning
     * of the bitwise OR operator with the types of the operands.
     */
    public SmplObj orBits(SmplObj arg) throws SmplException {
	
	/* Cannot bitwise OR with the default object, so throw an exception */
	throw new SmplTypeException(this, "|");
    }

    /** 
     * Find bitwise complement of this object according to the meaning
     * of operand1.
     */
    public SmplObj notBits() throws SmplException {
	
	/* Cannot bit complement the default object, so throw an exception */
	throw new SmplTypeException(this, "~");
    }

    /** 
     * Combine given arg with this object according to the meaning
     * of the logical AND operator with the types of the operands.
     */
    public SmplObj andLogical(SmplObj arg) throws SmplException {
	
	/* Cannot Logical AND with the default object, so throw an exception */
	throw new SmplTypeException(this, "AND");
    }

    /** 
     * Combine given arg with this object according to the meaning
     * of the logical OR operator with the types of the operands.
     */
    public SmplObj orLogical(SmplObj arg) throws SmplException {
	
	/* Cannot Logical OR with the default object, so throw an exception */
	throw new SmplTypeException(this, "OR");
    }

    /** 
     * Find the logical complement of this object according to the
     * meaning of the logical complement with the type of operand1.
     */
    public SmplObj notLogical() throws SmplException {
	
	/* Cannot complement the default object, so throw an exception */
	throw new SmplTypeException(this, "NOT");
    }

    /** 
     * Compare given arg with this object according to the
     * meaning of the = operator with the types of the operands.
     * @param arg the object being compared to this one
     * @return true if the given object is equivalent to this one.
     * @throws smpl.sys.SmplException if a problem arises while making the comparison
     */
    public SmplBool eq(SmplObj arg) throws SmplException {
	
	/* This is the only operation permitted on the default object */
	return SmplBool.getConst(this == arg);
    }

    /** 
     * Compare given arg with this object according to the
     * meaning of the < operator with the types of the operands.
     */
    public SmplBool lt(SmplObj arg) throws SmplException {
	
	/* Cannot compare the default object, so throw an exception */
	throw new SmplTypeException(this, "<");
    }

    /** 
     * Compare given arg with this object according to the
     * meaning of the <= operator with the types of the operands.
     */
    public SmplBool le(SmplObj arg) throws SmplException {
	
	/* Cannot compare the default object, so throw an exception */
	throw new SmplTypeException(this, "<=");
    }

    /** 
     * Compare given arg with this object according to the
     * meaning of the < operator with the types of the operands.
     */
    public SmplBool gt(SmplObj arg) throws SmplException {
	
	/* Cannot compare the default object, so throw an exception */
	throw new SmplTypeException(this, ">");
    }
    
    /** 
     * Compare given arg with this object according to the
     * meaning of the >= operator with the types of the operands.
     */
    public SmplBool ge(SmplObj arg) throws SmplException {
	
	/* Cannot compare the default object, so throw an exception */
	throw new SmplTypeException(this, ">=");
    }

    /** 
     * Apply this object to the given arguments and return the value 
     * @param args the arguments to this object
     * @return the result of applying this object to the given arguments
     * @throws smpl.sys.SmplException if this object is not applicable.
     */
    public SmplObj apply(SmplObj[] args) throws SmplException {

	/* Default object is non-applicable */
	throw new SmplTypeException(this, "apply");
    }

    /** 
     * Apply this object to the given arguments and return the value.
     * @param arg The argument to be passed to this object
     * @return the result of applying this object
     * @throws smpl.sys.SmplException if this object is not applicable
     */
    public SmplObj apply(SmplObj arg) throws SmplException {

	/* Default object is non-applicable */
	throw new SmplTypeException(this, "apply");
    }

    /** 
     * Return the size of this object. (Intended for vectors) 
     */
    public SmplInt size() throws SmplException {

	/* Default object cannot contain other elements */
	throw new SmplTypeException(this, "size");
    }

    /** 
     * @return the first part of this object. (for Pairs) 
     * @throws smpl.sys.SmplException if this object is not a pair
     */
    public SmplObj first() throws SmplException {
	/* Default object is not a pair */
	throw new SmplTypeException(this, "1st");
    }

    /** 
     * @return the second part of this object. (for Pairs) 
     * @throws smpl.sys.SmplException if this object is not a pair
     */
    public SmplObj second() throws SmplException {
	/* Default object is not a pair */
	throw new SmplTypeException(this, "2nd");
    }

    public boolean equals(SmplObj obj) {
        // this is up to each subclass to implement
	return false;	//TODO: Implement this (remember that the subclasses for SmplObj are SmplBool,SmplDouble,SmplInt)
    }

    @Override
    public String toString() {
        // override this method to provide more useful information on the 
        // specific value wrapped by this object
        return String.format("Some SMPL value of type %s", type);
    }
}
