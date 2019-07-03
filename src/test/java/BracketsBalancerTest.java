import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Siarhei Sviarkaltsau
 */

public class BracketsBalancerTest {

    private final BracketsBalancer bracketsBalancer = new BracketsBalancer();

    @Test
    public void testForDebug() {
        test("()x)", "(x), ()x");
    }

    @Test
    public void test() {
        test("()", "()");
        test("(())", "(())");
        test("(x)", "(x)");
        test("(()x)", "(()x)");
        test("())", "()");
        test("(()", "()");
        test(")()", "()");
        test("()(", "()");
        test("())", "()");
        test("()(()", "()()");
        test("(()x", "()x");
        test(")x()", "x()");
        test("(x)(", "(x)");
        test("()())()", "(())(), ()()()");
        test("(a)())()", "(a())(), (a)()()");
        test(")(", "");
        test("())())", "(()), ()()");
        test("()x)x", "(x)x, ()xx");
        test("(", "");
        test("(()))(x)())", "(()(x)()), (())(x()), (())(x)()");
    }

    private void test(final String input, final String expectedOutput) {
        final String message = null;
        final String actualOutput = listToCustomString(bracketsBalancer.removeInvalidBrackets(input));
        Assert.assertEquals(message, expectedOutput, actualOutput);
    }

    private String listToCustomString(final List<String> strings) {
        return strings.toString().replaceFirst("\\[", "").replaceFirst("]", "");
    }

}
