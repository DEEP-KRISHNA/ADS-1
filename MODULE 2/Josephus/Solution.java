/**
 * implementation of Joseph Problem.
 *
 * @author Sandeep Kolli.
 */
class Solution {
	/**
	 * implementation of joseph problem.
	 *
	 * @param a number of people.
	 * @param b number of turns.
	 * @return pattern of people who die,
	 */
	public static String Josephus(final int a, final int b) {
		// fill you code Here
		CircularQueue cq = new CircularQueue();
		for (int i = 0; i < a; i++) {
			cq.add(i);
		}
		return cq.removeAccordingToEle(b);
	}
}
