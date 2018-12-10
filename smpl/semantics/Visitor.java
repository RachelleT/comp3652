package smpl.semantics;

import smpl.syntax.*;
import smpl.sys.SmplException;

/**
 *
 * @author newts
 * @param <S> The type of the state passed down to children nodes while visiting
 * @param <T> The return type of the visit process
 */
public interface Visitor<S, T> {

    // program

    /**
     * Visit a top level Smpl program
     * @param p the value of p
     * @param state the value of state
     * @return the T
     * @throws smpl.sys.SmplException
     */
    public T visitSmplProgram(SmplProgram p, S state)
	throws SmplException;

    // statements

    /**
     * Visit an expression being used as a statement
     * @param exp the expression / statement
     * @param state the context for the visitor.
     * @return the result of the visit
     * @throws SmplException if an error arises during the visit
     */
    public T visitStmtExpr(StmtExpr exp, S state)
	throws SmplException ;

    /**
     * Visit a sequence of statements
     * @param exp the value of exp
     * @param state the value of state
     * @return the T
     * @throws SmplException
     */
    public T visitStmtSequence(StmtSequence exp, S state)
	throws SmplException ;

    /**
     * Visit a statement definition
     * @param sd the value of sd
     * @param state the value of state
     * @return the T
     * @throws SmplException
     */
    public T visitStmtDefinition(StmtDefinition sd, S state)
	throws SmplException;

    
    public T visitFunDefn(StmtFunDefn funDef, S state)
	throws SmplException;

    public T visitFunCall(IRExpFunCall exp, S state)
    	throws SmplException;
    	
    public T visitIRExpIf(IRExpIf ifstmt, S state)
    	throws SmplException;
	
	
    public T visitIRExpEq(IRExpEq exp, S state)
    	throws SmplException;
    
    public T visitIRExpLt(IRExpLt exp, S state)
    	throws SmplException;
    	
    public T visitIRExpGt(IRExpGt exp, S state)
    	throws SmplException;
    	
    public T visitIRExpLe(IRExpLe exp, S state)
    	throws SmplException;
    	
    public T visitIRExpGe(IRExpGe exp, S state)
    	throws SmplException;
    	
    /**
     * Visit an addition expression
     * @param exp the value of exp
     * @param state the value of state
     * @return the T
     * @throws SmplException
     */
     public T visitExpAdd(IRExpAdd exp, S state)
	throws SmplException;
     
    /**
     * Visit a subtraction expression
     * @param exp the value of exp
     * @param state the value of state
     * @return the T
     * @throws SmplException
     */
    public T visitExpSub(IRExpSub exp, S state)
	throws SmplException;

    /**
     * Visit a multiplication expression
     * @param exp the value of exp
     * @param state the value of state
     * @return the T
     * @throws SmplException
     */
    public T visitExpMul(IRExpMul exp, S state)
	throws SmplException;

    /**
     * Visit a division expression
     * @param exp the value of exp
     * @param state the value of state
     * @return the T
     * @throws SmplException
     */
    public T visitExpDiv(IRExpDiv exp, S state)
	throws SmplException;

    /**
     * Visit a modulo expression
     * @param exp the value of exp
     * @param state the value of state
     * @return the T
     * @throws SmplException
     */
    public T visitExpMod(IRExpMod exp, S state)
	throws SmplException;

    /**
     * Visit a literal expression (Eg. an integer or character)
     * @param exp the value of exp
     * @param state the value of state
     * @return the T
     * @throws SmplException
     */
    public T visitExpLit(IRExpLit exp, S state)
	throws SmplException;

    /**
     * Visit a variable expression
     * @param exp the value of exp
     * @param state the value of state
     * @return the T
     * @throws SmplException
     */
    public T visitExpVar(IRExpVar exp, S state)
	throws SmplException;
    
    public T visitStmtLet(StmtLet stmt, S state)
    throws SmplException;

}
