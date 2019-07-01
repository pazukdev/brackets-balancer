import org.junit.Assert;
import org.junit.Test;

/**
 * @author Siarhei Sviarkaltsau
 */


import java.util.Set;

public class SolutionTest {

    private final Solution solution = new Solution();

    private boolean replaceBracesWithParenthesises;

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
        test("(()x", "()x");
        test(")x()", "x()");
        test("(x)(", "(x)");
        test("()())()", "()()(), (())()");
        test("(a)())()", "(a)()(), (a())()");
        test(")(", "");
        test("{}}{}}", "{}{}, {{}}");
        test("{}x}x", "{}xx, {x}x");
        test("{", "");
    }

    private void test(final String input, final String expectedOutput) {
        final String actualOutput = getActualOutput(input);
        Assert.assertEquals(null, expectedOutput, actualOutput);
    }

    private String getActualOutput(String input) {
        if (input.contains("{") || input.contains("}")) {
            replaceBracesWithParenthesises = true;
            input = replaceBracesWithParenthesises(input);
        }
        String actualOutput = setToCustomString(solution.validate(input));
        if (replaceBracesWithParenthesises) {
            actualOutput = replaceParenthesisesWithBraces(actualOutput);
        }
        return actualOutput;
    }

    private String setToCustomString(final Set<String> strings) {
        return strings.toString().replaceFirst("\\[", "").replaceFirst("]", "");
    }

    private String replaceBracesWithParenthesises(final String s) {
        return s.replaceAll("\\{", "(").replaceAll("}", ")");
    }

    private String replaceParenthesisesWithBraces(final String s) {
        return s.replaceAll("\\(", "\\{").replaceAll("\\)", "}");
    }

}
