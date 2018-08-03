import java.io.IOException;

/**
 * @author Suman Karki
 *
 */
public class MainClass {

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		WordFinder wordFinder = new WordFinder();
		wordFinder.runWordFinder("words.txt", "dictionary.txt", "input500.txt", "output100.txt");
		long endTime = System.currentTimeMillis();
		System.out.println((endTime-startTime)/1000.0);
	}

}
