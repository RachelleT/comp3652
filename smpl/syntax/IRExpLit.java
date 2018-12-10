package smpl.syntax;

import smpl.values.SmplInt;
import smpl.values.SmplObj;
import smpl.semantics.Visitor;
import smpl.sys.SmplException;

/**
 * AST node to represent a literal value (eg number, string, character).
 * @author newts
 */
public class IRExpLit extends IRExp {

    SmplInt val;

    public IRExpLit(Integer v) {
        val = new SmplInt(v);
    }

    public SmplObj getVal() {
        return val;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitExpLit(this, arg);
    }

    @Override
    public String toString() {
        return val.toString();
    }
}
