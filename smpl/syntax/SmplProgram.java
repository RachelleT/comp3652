package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

/**
 * Intermediate representation for a program.
 * @author newts
 */
public class SmplProgram extends ASTNode {
    StmtSequence seq;

    public SmplProgram(StmtSequence s) {
	seq = s;
    }

    public StmtSequence getSeq() {
	return seq;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException
    {
	return v.visitSmplProgram(this, arg);
    }

    @Override
    public String toString() {
	return seq.toString();
    }
}
