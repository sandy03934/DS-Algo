package practize.singleton;

/**
 * @author Sandip Singh.
 */
public enum EnumSingleton {

    INSTANCE {
        @Override
        public void print() {
            System.out.println("Singleton Instance Printed...");
        }
    };

    public abstract void print();

}
