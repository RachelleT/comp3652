package smpl.sys;



public class TokenException extends SmplException {

    private static final String FMT = "Unrecognised input '%s'";
    private static final long serialVersionUID = 1L;
    
    public TokenException(String inp) {
	super(String.format(FMT, inp));
    }

    public TokenException(String inp, Throwable cause) {
	super(String.format(FMT, inp), cause);
    }

}
