import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 
 * @author Monica Panigrahy 493108mp
 *
 */
public class NaiveBayes {
	private int spamDocuments = 0;
	private int notSpamDocuments = 0;
	private WordCounter[] wordTab;

	/**
	 * creates an array which matches a wordTab object to WordCounter
	 * 
	 * @param focusWords finds focus words of the object
	 */

	public NaiveBayes(String[] focusWords) {
		wordTab = new WordCounter[focusWords.length];
		for (int i = 0; i <= focusWords.length - 1; i++) {
			wordTab[i] = new WordCounter(focusWords[i]);
		}
	}

	/**
	 * checks the first character to see if document is spam or not and adds it to
	 * wordCounter objects
	 * 
	 * @param document checks each element of array
	 */

	public void addSample(String document) {
		for (WordCounter wc : wordTab) {
			wc.addSample(document);
		}
		if (document.substring(0, 1).equals("0")) {
			notSpamDocuments++;
		} else {
			spamDocuments++;
		}
	}

	/**
	 * returns true if classified as spam
	 * 
	 * @param unclassifiedDocument is the document to be classified
	 * @return true or false as described by formula
	 */

	public boolean classify(String unclassifiedDocument) {
		double spamScore = (double) spamDocuments / (spamDocuments + notSpamDocuments);
		double noSpamScore = (double) notSpamDocuments / (spamDocuments + notSpamDocuments);
		String[] newArray = unclassifiedDocument.split(" ");
		for (String word : newArray) {
			for (WordCounter wc : wordTab) {
				if (wc.getFocusWord().equals(word)) {
					spamScore *= wc.getConditionalSpam();
					noSpamScore *= wc.getConditionalNoSpam();
				}

			}

		}
		return (spamScore > noSpamScore);
	}

	/**
	 * uses BufferedReader to read each line of document and feed them to the
	 * WordCounter objects
	 * 
	 * @param trainingFile - the file to be consumed
	 * @throws IOException if no file is found
	 */
	public void trainClassifier(File trainingFile) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(trainingFile))) {
			String line = reader.readLine();
			while (line != null) {
				this.addSample(line);
				line = reader.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads every line of the file, classifies it, and prints the result to the
	 * output file
	 * 
	 * @param input  - the unclassified documents
	 * @param output - the classified documents
	 * @throws IOException if the file is not found
	 */
	public void classifyFile(File input, File output) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(input));
				PrintWriter pw = new PrintWriter(output)) {
			String line = reader.readLine();
			while (line != null) {
				if (this.classify(line)) {
					pw.println("1");
				} else {
					pw.println("0");
				}
				line = reader.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests how accurate the naive bayes object is by matching the experimental
	 * classifications to the true classifications of given documents.
	 * 
	 * @param testdata - the data to test with
	 * @return a ConfusionMatrix object containing the required accuracy information
	 * @throws IOException if no file is found
	 */
	public ConfusionMatrix computeAccuracy(File testdata) throws IOException {
		ArrayList<Integer> trueClassifications = new ArrayList<>();
		ArrayList<Integer> testClassifications = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(testdata))) {
			String line = reader.readLine();
			while (line != null) {
				trueClassifications.add(Integer.parseInt(line.substring(0, 1)));
				if (classify(line.substring(2))) {
					testClassifications.add(1);
				} else {
					testClassifications.add(0);
				}
				line = reader.readLine();
			}
		}
		ConfusionMatrix outputObject = new ConfusionMatrix(trueClassifications, testClassifications);
		return outputObject;
	}

}
