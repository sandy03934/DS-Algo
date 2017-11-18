import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Sandip Singh.
 */
public class UniqueCharactersTest {

    @Test
    public void testUnique() throws Exception {
        String str = "abcdefghijklmnopqrstuvwxyz1234567890@$!%";

        UniqueCharacters uniqueCharacters = new UniqueCharacters();
        assertTrue(uniqueCharacters.method1(str));
        assertTrue(uniqueCharacters.method2(str));
        assertTrue(uniqueCharacters.method3(str));
//        assertTrue(uniqueCharacters.method4(str));
    }

    @Test
    public void testNonUnique() throws Exception {
        String str = "abcdefghijklmnopqrstuvwxyz1234567890@$!%abcbcbdad";

        UniqueCharacters uniqueCharacters = new UniqueCharacters();
        assertTrue(!uniqueCharacters.method1(str));
        assertTrue(!uniqueCharacters.method2(str));
        assertTrue(!uniqueCharacters.method3(str));
//        assertTrue(!uniqueCharacters.method4(str));
    }

}