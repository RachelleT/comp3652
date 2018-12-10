/*
 * StringArtParseException.java
 * Created on 19-Oct-2018, 3:41:06 AM
 */

/*
 * (c) Daniel Coore.
 * This code was distributed for an assignment in COMP3652 at UWI, Mona.
 * Permission is hereby granted to make copies and modifications
 *  under the terms of GPL 2.0
 */


package smpl.sys;

/**
 *
 * @author newts
 */
public class SmplParseException extends SmplException {

    private static final long serialVersionUID = 1L;

    public SmplParseException(String reason) {
        super(reason);
    }
    
    public SmplParseException(String message, Throwable cause) {
        super(message, cause);
    }

}
