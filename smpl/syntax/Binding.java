package smpl.syntax;

public class Binding {

    String var;
    IRExp valExp;

    public Binding(String id, IRExp v) {
	var = id;
	valExp = v;
    }

    public String getVar() {
	return var;
    }

    public IRExp getValIRExp() {
	return valExp;
    }

}
