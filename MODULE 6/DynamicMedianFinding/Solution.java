/**
 * The following is the implementation of DynamicMedian.
 *
 * @author Sandeep Kolli.
 */
final class Solution {
	private Solution() {

	}

	/**
	 * The following method will implement the function. the value in finaln array
	 * inserted based on previous median value. if the current value is greater than
	 * current median then add it minPQ else add it to maxPQ.
	 *
	 * make sure that all the time both minPQ and maxPQ is balanced correctly.
	 * Maximum difference in size should be 1.
	 *
	 * @param arr the array for which median need to be found.
	 * @return the array of medians
	 */
	public static double[] dynamicMedian(final double[] arr) {
		double[] medianArr = new double[arr.length];
		MinPQ mini = new MinPQ();
		MaxPQ maxi = new MaxPQ();
		mini.insert(arr[0]);
		double median = arr[0];
		medianArr[0] = median;
		for (int i = 1; i < arr.length; i++) {
			if (maxi.getEleSize() > mini.getEleSize()) {
				if (arr[i] < median) {
					mini.insert(maxi.delMax());
					maxi.insert(arr[i]);
				} else {
					mini.insert(arr[i]);
				}
				median = ((mini.minElement() + maxi.maxElement()) / 2.0);
			} else if (maxi.getEleSize() < mini.getEleSize()) {
				if (arr[i] > median) {
					maxi.insert(mini.delMin());
					mini.insert(arr[i]);
				} else {
					maxi.insert(arr[i]);
				}
				median = ((mini.minElement() + maxi.maxElement()) / 2.0);
			} else {
				if (arr[i] < median) {
					maxi.insert(arr[i]);
					median = maxi.maxElement();
				} else {
					mini.insert(arr[i]);
					median = mini.minElement();
				}
			}
			medianArr[i] = median;
		}
		return medianArr;
	}
}
