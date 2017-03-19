package practize.singleton;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.Assert.assertEquals;

/**
 * @author Sandip Singh
 */
public class EagerSingletonTest {

    @Test
    public void testEagerSingleton() {
        EagerSingleton singleton1 = EagerSingleton.getInstance();
        EagerSingleton singleton2 = EagerSingleton.getInstance();

        assertEquals(singleton1, singleton2);
    }

    @Test
    public void testSerialisation() throws IOException, ClassNotFoundException {
        EagerSingleton singleton = EagerSingleton.getInstance();

        File file = new File(System.getProperty("java.io.tmpdir") + File.separator + "Singleton.dat");

        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(singleton);
        outputStream.flush();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
        EagerSingleton singleton2 = (EagerSingleton) inputStream.readObject();

        assertEquals(singleton, singleton2);

    }
}
