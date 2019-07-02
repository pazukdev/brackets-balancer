import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author Siarhei Sviarkaltsau
 */

public class BracketBalancer {

    /**
     * wrapper method just for match concrete test task from company
     */
    public Set<String> validate(final String input) {
        return new HashSet<>(removeInvalidParentheses(input));
    }

    public List<String> removeInvalidParentheses(final String input) {
        final List<String> balancedCombinations = new ArrayList<>();
        if (input == null) {
            return balancedCombinations;
        }

        String s = input;
        final Set<String> visited = new HashSet<>();
        final Queue<String> queue = new LinkedList<>();
        queue.add(s);
        visited.add(s);

        boolean balancedCombinationFound = false;

        while (!queue.isEmpty()) {

            s = queue.poll();

            if (isBalanced(s)) {
                balancedCombinations.add(s);
                balancedCombinationFound = true;
            }

            if (!balancedCombinationFound) {
                visitNodes(s, queue, visited);
            }
        }

        return balancedCombinations;
    }

    private void visitNodes(final String s, final Queue<String> queue, final Set<String> visited) {
        for (int i = 0; i < s.length(); i++) {
            if (isBracket(s, i)) {
                final String node = excludeCurrentChar(s, i);

                if (!visited.contains(node)) {
                    queue.add(node);
                    visited.add(node);
                }
            }
        }
    }

    private String excludeCurrentChar(final String s, final int charIndex) {
        return s.substring(0, charIndex) + s.substring(charIndex + 1);
    }

    private boolean isBracket(final String s, final int charIndex) {
        return BracketUtil.isBracket(s.charAt(charIndex));
    }

    private boolean isBalanced(String s) {
        int balance = 0;
        for (char c : s.toCharArray()) {
            if (BracketUtil.isOpenBracket(c)) {
                balance++;
            }
            if (BracketUtil.isCloseBracket(c) && --balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }

    private static final class BracketUtil {
        private static final char[][] BRACKETS = {{'(', ')'}, {'{', '}'}, {'[', ']'}};

        private static boolean isOpenBracket(final char c) {
            for (final char[] tokens : BRACKETS) {
                if (tokens[0] == c) {
                    return true;
                }
            }

            return false;
        }

        private static boolean isCloseBracket(final char c) {
            return isBracket(c) && !isOpenBracket(c);
        }

        private static boolean isBracket(final char c) {
            for (final char[] tokens : BRACKETS) {
                for (final char token : tokens) {
                    if (token == c) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

}




























