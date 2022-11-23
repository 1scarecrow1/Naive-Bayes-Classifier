/**
 * 
 * @author Monica Panigrahy 493108mp
 *
 */
public class WordCounter {

	final private String focusWord;
	private int focusWordSpam = 0;
	private int totalWordsSpam = 0;
	private int totalWordsNoSpam = 0;
	private int focusWordNoSpam = 0;
	private int spamDocuments = 0;
	private int notSpamDocuments = 0;

	/**
	 * Creates a new WordCounter object and sets the focus word to the argument
	 * @param focusWord - the focus word
	 */
	public WordCounter(String focusWord) {
		this.focusWord = focusWord;
	}

	/**
	 * It returns the focus word
	 * @return focusWord
	 */
	public String getFocusWord() {
		return focusWord;
	}

	/**
	 * It processes the document by splitting the document into individual words
	 * @param document argument to be processed
	 * @throws IllegalStateException catches an error where argument input is not compatible with output
	 */
	public void addSample(String document) throws IllegalStateException {
		String[] myArray = document.split(" ");
		int tempWordCount = 0;
		for (String word : myArray) {
			if (word.equals(focusWord)) {
				tempWordCount++;
			}
		}
		if (myArray[0].equals("0")) {
			focusWordNoSpam += tempWordCount;
			totalWordsNoSpam += myArray.length - 1;
			notSpamDocuments++;
		} else {
			focusWordSpam += tempWordCount;
			totalWordsSpam += myArray.length - 1;
			spamDocuments++;
		}
	}

	/**
	 * Method to check if program is trained to identify spam words
	 * @return true if it is trained, false otherwise
	 */
	public boolean isCounterTrained() {
		if(focusWordNoSpam + focusWordSpam >= 1 && notSpamDocuments >= 1 && spamDocuments >= 1) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return the probability of not getting a spam word 
	 * @throws IllegalStateException when argument input is not compatible with output so when program is not trained
	 */
	public double getConditionalNoSpam() throws IllegalStateException {
		if (!isCounterTrained()) {
			throw new IllegalStateException();
		}
		return (double) focusWordNoSpam / totalWordsNoSpam;

	}

	/**
	 * 
	 * @return probability of getting a spam word
	 * @throws IllegalStateException when argument input is not compatible with output so when program is not trained

	 */

	public double getConditionalSpam() throws IllegalStateException {
		if (!isCounterTrained()) {
			throw new IllegalStateException();
		}
		return (double) focusWordSpam/totalWordsSpam;
	}



}





