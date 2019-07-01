import java.util.HashSet;
import java.util.Set;

/**
 * @author Siarhei Sviarkaltsau
 */

public class Solution {

    private Set<String> validExpressions = new HashSet<>();
    private int minimumRemoved;

    public Set<String> validate(String input) {
        return removeInvalidParentheses(input);
    }

    private Set<String> removeInvalidParentheses(String input) {
        int index = 0;
        int leftCount = 0;
        int rightCount = 0;
        StringBuilder expression = new StringBuilder();
        int removedCount = 0;

        reset();
        recurse(input, index, leftCount, rightCount, expression, removedCount);
        return validExpressions;
    }

    private void reset() {
        validExpressions.clear();
        minimumRemoved = Integer.MAX_VALUE;
    }

    private void recurse(
            String s,
            int index,
            int leftCount,
            int rightCount,
            StringBuilder expression,
            int removedCount) {

        if (index == s.length()) {
            if (leftCount == rightCount) {
                if (removedCount <= this.minimumRemoved) {
                    String possibleAnswer = expression.toString();
                    if (removedCount < this.minimumRemoved) {
                        this.validExpressions.clear();
                        this.minimumRemoved = removedCount;
                    }
                    this.validExpressions.add(possibleAnswer);
                }
            }
        } else {
            char currentCharacter = s.charAt(index);
            int length = expression.length();

            if (currentCharacter != '(' && currentCharacter != ')') {
                expression.append(currentCharacter);
                this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount);
                expression.deleteCharAt(length);
            } else {
                this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount + 1);
                expression.append(currentCharacter);

                if (currentCharacter == '(') {
                    this.recurse(s, index + 1, leftCount + 1, rightCount, expression, removedCount);
                } else if (rightCount < leftCount) {
                    this.recurse(s, index + 1, leftCount, rightCount + 1, expression, removedCount);
                }

                expression.deleteCharAt(length);
            }
        }
    }

}
