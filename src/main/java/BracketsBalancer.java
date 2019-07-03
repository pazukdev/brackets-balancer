import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author Siarhei Sviarkaltsau
 */

public class BracketsBalancer {

    /**
     * wrapper method just for match concrete test task from company
     */
    public Set<String> validate(final String input) {
        return new HashSet<>(removeInvalidBrackets(input));
    }

    public List<String> removeInvalidBrackets(final String input) {
        final List<String> balancedCombinations = new ArrayList<>();
        if (input == null || input.replaceAll(" ","").isEmpty()) {
            return balancedCombinations;
        }

        final Set<String> visited = new HashSet<>();
        final Queue<String> queue = new LinkedList<>();
        String head = input;
        queue.add(head);
        visited.add(head);

        boolean balancedCombinationFound = false;

        while (!queue.isEmpty()) {
            head = queue.poll();

            if (isBalanced(head)) {
                balancedCombinations.add(head);
                balancedCombinationFound = true;
            }

            if (!balancedCombinationFound) {
                visitNodes(head, queue, visited);
            }
        }

        return balancedCombinations;
    }

    private void visitNodes(final String s, final Queue<String> queue, final Set<String> visited) {
        for (int i = 0; i < s.length(); i++) {
            if (isBracket(s, i)) {
                final String node = excludeCharFromString(s, i);

                if (!visited.contains(node)) {
                    queue.add(node);
                    visited.add(node);
                }
            }
        }
    }

    private String excludeCharFromString(final String s, final int charIndex) {
        return s.substring(0, charIndex) + s.substring(charIndex + 1);
    }

    private boolean isBracket(final String s, final int charIndex) {
        return s.charAt(charIndex) == '(' || s.charAt(charIndex) == ')';
    }

    private boolean isBalanced(String s) {
        int balance = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++;
            }
            if (c == ')') {
                balance--;

                if (balance < 0) {
                    return false;
                }
            }
        }
        return balance == 0;
    }

}




























