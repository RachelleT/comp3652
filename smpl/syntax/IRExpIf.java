package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class IRExpIf extends IRExp {
    IRExp predicate;    
    IRExp consequent;
    IRExp alternative;

    public IRExpIf(IRExp predicate, IRExp consequent, IRExp alternative) {
        super();
        this.predicate = predicate;
        this.consequent = consequent;
        this.alternative = alternative;
    }
    
    public IRExpIf(IRExp predicate, IRExp consequent) {
    	this.predicate = predicate;
      this.consequent = consequent;
    }
    
    public IRExp getPredicate(){
    	return predicate;
    }
    
    public IRExp getConsequent(){
    	return consequent;
    }
    
    public IRExp getAlternative(){
    	return alternative;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException
    {
	return v.visitIRExpIf(this, arg);
    }

	@Override
	public String toString() {
		// TODO Implement this
		if (alternative == null){
			return "IF" + predicate + "then" + consequent;
		}else{
			return "IF" + predicate + "then" + consequent + "else" + alternative;
		}
	}

}
