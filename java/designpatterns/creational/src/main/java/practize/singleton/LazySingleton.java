package practize.singleton;

import java.io.Serializable;

/**
 * @author Sandip Singh.
 */
public class LazySingleton implements Serializable {
    private static volatile LazySingleton instance = null;

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }

        return instance;
    }

    private Object readResolve() {
        return instance;
    }
}
