/**
 * the following class implements sorting of userdefined data type.
 *
 * @author Sandeep Kolli
 */
class Solution {
	public Team[] sort(final Team[] teams) {
		// your code goes here
		Team temp = null;
		for (int i = 1; i < teams.length; i++) {
			for (int j = i; j > 0; j--) {
				if ((teams[j].compareTo(teams[j - 1])) == 1) {
					temp = teams[j];
					teams[j] = teams[j - 1];
					teams[j - 1] = temp;
				}
			}
		}
		return teams;
	}
}

/**
 * The following class is a userdefined datatype.
 */
class Team implements Comparable<Team> {
	/**
	 * teamName - name of team.
	 */
	private String teamName;
	/**
	 * noOfWins - wins of a pirticular team.
	 */
	private int noOfWins;
	/**
	 * noOfLosses - Losses of a pirticular team.
	 */
	private int noOfLosses;
	/**
	 * noOfDraws - Draws of a pirticular team.
	 */
	private int noOfDraws;

	Team(final String name, final int wins, final int losses, final int draws) {
		teamName = name;
		noOfDraws = draws;
		noOfWins = wins;
		noOfLosses = losses;
	}

	/**
	 * compareTo function implements comparision. this function is overridded which
	 * is there comparable class.
	 *
	 * @param a which need to be compared with current elements.
	 * @return value based on conditions.
	 */
	public int compareTo(final Team a) {
		if (this.noOfWins > a.noOfWins) {
			return 1;
		} else if (this.noOfWins < a.noOfWins) {
			return -1;
		} else if (this.noOfLosses < a.noOfLosses) {
			return 1;
		} else if (this.noOfLosses > a.noOfLosses) {
			return -1;
		} else if (this.noOfDraws > a.noOfDraws) {
			return 1;
		} else if (this.noOfDraws < a.noOfDraws) {
			return -1;
		} else {
			return 1;
		}
	}

	/**
	 * toString() is user defined.
	 *
	 * @return converted value.
	 */
	public String toString() {
		// retrun all the attributes as a string but appending with ", "
		String str = teamName + ", " + noOfWins + ", " + noOfLosses + ", " + noOfDraws;
		return str;
	}
}
