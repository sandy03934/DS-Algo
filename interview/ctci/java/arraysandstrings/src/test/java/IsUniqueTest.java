import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Sandip Singh.
 */
public class IsUniqueTest {

    @Test
    public void testUnique() throws Exception {
        String str = "abcdefghijklmnopqrstuvwxyz1234567890@$!%";

        IsUnique isUnique = new IsUnique();
        assertTrue(isUnique.method1(str));
        assertTrue(isUnique.method2(str));
        assertTrue(isUnique.method3(str));
        assertTrue(isUnique.method4(str));
    }

    @Test
    public void testNonUnique() throws Exception {
        String str = "abcdefghijklmnopqrstuvwxyz1234567890@$!%abcbcbdad";

        IsUnique isUnique = new IsUnique();
        assertTrue(!isUnique.method1(str));
        assertTrue(!isUnique.method2(str));
        assertTrue(!isUnique.method3(str));
        assertTrue(!isUnique.method4(str));
    }

}