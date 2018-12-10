package smpl.values;

/** 
 * SMPL representation for the empty list 
 **/
public class SmplNil extends SmplObj {

    public SmplNil() {
        super(Type.LIST);
    }
  
    @Override
    public boolean isFalse() {
        return true;
    }
    
    @Override
    public String toString() {
        return "#e";
    }
}
