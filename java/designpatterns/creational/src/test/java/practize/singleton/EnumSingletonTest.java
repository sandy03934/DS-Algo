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
 * @author Sandip Singh.
 */
public class EnumSingletonTest {

    @Test
    public void testEnumSingleton() {
        EnumSingleton singleton = EnumSingleton.INSTANCE;
        EnumSingleton singleton2 = EnumSingleton.INSTANCE;

        assertEquals(singleton, singleton2);
    }

    @Test
    public void testSerialisation() throws IOException, ClassNotFoundException {
        EnumSingleton singleton = EnumSingleton.INSTANCE;

        File file = new File(System.getProperty("java.io.tmpdir") + File.separator + "Singleton.dat");

        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(singleton);
        outputStream.flush();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
        EnumSingleton singleton2 = (EnumSingleton) inputStream.readObject();

        assertEquals(singleton, singleton2);

    }
}
