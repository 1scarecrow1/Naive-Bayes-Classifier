import java.util.ArrayList;

/**
 * 
 * @author Monica Panigrahy 493108mp
 *
 */
public class ConfusionMatrix {
	

	private ArrayList<Integer> trueClassifications;
	private ArrayList<Integer> testClassifications;
	
	/**
	 * Creates a new ConfusionMatrix object, and sets the information to the arguments
	 * @param trueClassifications - the true classifications of the documents
	 * @param testClassifications - the test classifications of the documents 
	 */
	public ConfusionMatrix(ArrayList<Integer> trueClassifications, ArrayList<Integer> testClassifications) {
		this.trueClassifications = trueClassifications;
		this.testClassifications = testClassifications;
	}
	
	/**
	 * Computes the number of true negatives and returns it
	 * @return the number of true negatives
	 */
	public int getTrueNegatives() {
		int counter = 0;
		for (int i = 0; i < trueClassifications.size(); i++) {
			if (trueClassifications.get(i) == 0 && testClassifications.get(i) == 0) {
				counter++;
			}
		}
		return counter;
	}
	
	/**
	 * Computes the number of true positives and returns it
	 * @return the number of true positives
	 */
	public int getTruePositives() {
		int counter = 0;
		for (int i = 0; i < trueClassifications.size(); i++) {
			if (trueClassifications.get(i) == 1 && testClassifications.get(i) == 1) {
				counter++;
			}
		}
		return counter;
	}
	
	/**
	 * Computes the number of false negatives and returns it
	 * @return the number of false negatives
	 */
	public int getFalseNegatives() {
		int counter = 0;
		for (int i = 0; i < trueClassifications.size(); i++) {
			if (trueClassifications.get(i) == 1 && testClassifications.get(i) == 0) {
				counter++;
			}
		}
		return counter;
	}
	
	/**
	 * Computes the number of false positives and returns it
	 * @return the number of false positives
	 */
	public int getFalsePositives() {
		int counter = 0;
		for (int i = 0; i < trueClassifications.size(); i++) {
			if (trueClassifications.get(i) == 0 && testClassifications.get(i) == 1) {
				counter++;
			}
		}
		return counter;
	}

	

}
