/*
 * ASTNode.java
 * Created on 7-Nov-2018, 1:39:27 AM
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

/**
 * The topmost level intermediate representation class, which is an 
 * abstract syntax tree.
 * @author newts
 */
public abstract class ASTNode {
    
    /**
     * Visit this expression using a visitor and its context (state).
     * @param <S> The type of the state needed by the visitor
     * @param <T> The return type of the visitor
     * @param v The visitor
     * @param state The context required by v
     * @return The appropriate result for visiting this expression
     * @throws SmplException if the visitor encounters a problem 
     */
    public abstract <S, T> T visit(Visitor<S, T> v, S state) throws SmplException;

    /** Return the printed representation of this expression
     * @return  The string representation of this expression
     */
    @Override
    public abstract String toString();    

}
