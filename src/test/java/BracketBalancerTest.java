import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Siarhei Sviarkaltsau
 */

public class BracketBalancerTest {

    private final BracketBalancer bracketBalancer = new BracketBalancer();

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
        final String actualOutput = setToCustomString(bracketBalancer.removeInvalidParentheses(input));
        Assert.assertEquals(null, expectedOutput, actualOutput);
    }

    private String setToCustomString(final List<String> strings) {
        return strings.toString().replaceFirst("\\[", "").replaceFirst("]", "");
    }

}
