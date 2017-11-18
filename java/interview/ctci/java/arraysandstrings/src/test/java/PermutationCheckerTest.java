import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Sandip Singh.
 */
public class PermutationCheckerTest {

    @Test
    public void testPermutatedStrings() throws Exception {
        PermutationChecker checker = new PermutationChecker();

        assertTrue(checker.method1("abcbd", "cbbda"));
        assertTrue(checker.method2("abcbd", "cbbda"));
    }

    @Test
    public void testNonPermutatedStringsOfDifferentLength() throws Exception {
        PermutationChecker checker = new PermutationChecker();

        assertTrue(!checker.method1("abcbd", "cbbdad"));
        assertTrue(!checker.method2("abcbd", "cbbdad"));
    }

    @Test
    public void testNonPermutatedStrings() throws Exception {
        PermutationChecker checker = new PermutationChecker();

        assertTrue(!checker.method1("abcbd", "cbdda"));
        assertTrue(!checker.method2("abcbd", "cbdda"));
    }
}