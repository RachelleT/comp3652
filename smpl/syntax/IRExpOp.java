package smpl.syntax;

public abstract class IRExpOp extends IRExp {
    /** Parent class of all expressions using operators **/

    IRExp operand1, operand2;
    String symbol;

    public IRExpOp(String sym) {
	super();
        symbol = sym;
    }
    
    /** 
     * Constructor for unary operation expressions
     * @param sym The symbol of the operator
     * @param exp The operand expression.
     */
    public IRExpOp(String sym, IRExp exp) {
        this(sym);
	operand1 = exp;
    }
    
    /** Constructor for binary operation expressions
     * @param sym The symbol for the operator
     * @param exp1 The first operand expression
     * @param exp2 The second operand expression
     **/
    public IRExpOp(String sym, IRExp exp1, IRExp exp2) {
        this(sym);
	operand1 = exp1;
	operand2 = exp2;
    }

    /**
     *
     * @return The first operand expression
     */
    public IRExp getOperand1() {
        return operand1;
    }

    /**
     *
     * @return The second operand expression
     */
    public IRExp getOperand2() {
        return operand2;
    }
    
    @Override
    public String toString() {
        if (operand2 == null) {
            return String.format("%s %s", symbol, operand1.toString());
        } else {
            return String.format("%s %s %s", operand1.toString(), symbol, 
                                 operand2.toString());
        }
    }
}
