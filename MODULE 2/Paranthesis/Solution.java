/**
 * @author Sandeep Kolli.
 */

final class Solution {
	private Solution() {

	}

	/**
	 * checks the given String is proper or not.
	 *
	 * @param str String input
	 * @return YES if its proper else NO
	 */

	public static String isMatching(final String str) {
		Stack charStack = new Stack();
		// fill you code Here
		char first = str.charAt(0);
		if (first == ')' || first == ']' || first == '}') {
			return "NO";
		}
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				charStack.push('(');
			} else if (str.charAt(i) == '{') {
				charStack.push('{');
			} else if (str.charAt(i) == '[') {
				charStack.push('[');
			} else if (str.charAt(i) == ')') {
				if (charStack.pop() != '(') {
					return "NO";
				}
			} else if (str.charAt(i) == '}') {
				if (charStack.pop() != '{') {
					return "NO";
				}
			} else if (str.charAt(i) == ']') {
				if (charStack.pop() != '[') {
					return "NO";
				}
			}
		}
		if (charStack.isEmpty()) {
			return "YES";
		} else {
			return "NO";
		}
	}
}
