package smpl.semantics;
import java.util.*;
import smpl.sys.SmplUnboundVarException;

/**
 * An instance of class <code>Environment</code> maintains a
 * collection of bindings from valid identifiers to program values.
 * It supports storing and retrieving bindings, just as would
 be expected in any frame.
 *
 * @author <a href="mailto:dcoore@uwimona.edu.jm">Daniel Coore</a>
 * @version 1.0
 * @param <T> The type of values stored in this environment
 */
public class Environment<T> {

    private Environment<T> parent;
    private HashMap<String, T> frame;

    /**
     * Create a new (empty) top level Environment.
     *
     */
    public Environment() {
        parent = null;
	frame = new HashMap<>();
    }
    
    protected Environment(HashMap<String, T> frame, Environment<T> parent) {
        this.parent = parent;
        this.frame = new HashMap<>();
        this.frame.putAll(frame);
    }

    /**
     * Creates a new <code>Environment</code> instance that extends the given 
     * parent with the given collection of bindings
     * (presented as separate arrays of names and values).
     *
     * @param ids the collection of identifiers to be bound.
     * @param values the corresponding collection of values
     * for the identifiers.  Note that the two arrays must
     * have the same length.
     * @param parent The environment being extended.
     */
    public Environment(String[] ids, T[] values, Environment<T> parent) {
        this.parent = parent;
	frame = new HashMap<>();
	for (int i = 0; i < ids.length; i++) {
	    frame.put(ids[i], values[i]);
	}
    }

    public Environment(String[] ids, T[] values) {
        this(ids, values, null);
    }
    
    public Environment(ArrayList<String> ids, ArrayList<T> values, Environment<T> parent) {
        this.parent = parent;
        frame = new HashMap<>();
        for (int i = 0; i < ids.size(); i++) {
            frame.put(ids.get(i), values.get(i));
        }
    }
    
    /**
     * Create an instance of a global environment suitable for
     * evaluating an program.
     *
     * @param <T> The type of environment to create
     * @param cls The runtime class instance of T
     * @return the <code>Environment</code> created.
     */
    public static <T> Environment<T> makeGlobalEnv(Class<T> cls) {
	Environment<T> result =  new Environment<>();
	// add definitions for any primitive procedures or
	// constants here
	return result;
    }
    
    /**
     * Extend this environment with a single binding, and return the new
     * environment thus created.
     * @param var The name of the variable to be bound in the new environment
     * @param val The value of the variable
     * @return The extending environment (which starts with the newly created 
     * frame)
     */
    public Environment<T> extend(String var, T val) {
        HashMap<String, T> newFrame = new HashMap<>();
        newFrame.put(var, val);
        return new Environment<>(newFrame, this);
    }
    
    /**
     *
     * @param vars
     * @param values
     * @return
     */
    public Environment<T> extend(ArrayList<String> vars, ArrayList<T> values) {
        return new Environment<>(vars, values, this);
    }

    /**
     * Store a binding for the given identifier to the given
     * int within this environment.
     *
     * @param id the name to be bound
     * @param value the value to which the name is bound.
     */
    public void put(String id, T value) {
	frame.put(id, value);
    }

    /**
     * Return the int associated with the given identifier.
     *
     * @param id the identifier.
     * @return the int associated with the identifier in
     * this environment.
     * @exception SmplUnboundVarException <code>id</code> is unbound
     */
    public T get(String id) throws SmplUnboundVarException {
	T result = frame.get(id);
	if (result == null) {
            if (parent == null) {
                throw new SmplUnboundVarException("Unbound variable " + id);
            } else {
                return parent.get(id);
            }
        }
	else
	    return result;
    }

    /**
     * Create a string representation of this environment.
     *
     * @return a string of all the names bound in this
     *         environment.
     */
    @Override
    public String toString() {
	StringBuffer result = new StringBuffer();

	Iterator<String> iter = frame.keySet().iterator();
	while (iter.hasNext()) {
	    result = result.append(iter.next());
	}
	return result.toString();
    }

}
