package practize.singleton;

import java.io.Serializable;

/**
 * @author Sandip Singh
 */
public class EagerSingleton implements Serializable {

    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

    private Object readResolve(){
        return INSTANCE;
    }
}
