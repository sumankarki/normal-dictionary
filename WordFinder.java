import java.io.*;

public class WordFinder {
	
	public static String WordFilePath = "";
	public static String DictionaryFilePath = "";
	
	public void runWordFinder(String wordFilePath, String dictionaryPath, String inputFilePath, String outputFilePath) throws IOException{
		WordFilePath = wordFilePath;
		DictionaryFilePath = dictionaryPath;
		
		File outputFile = new File(outputFilePath);
		if(outputFile.exists())
			outputFile.delete();
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath, true));
		//Read input line by line and find its meaning and write it in output file.
		try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	//Find the meaning
				String meaning = begin(line.trim().toUpperCase());				
				//Write in file
				try {
				    writer.write(line + "  " + meaning);					
					writer.write("\n");
				} catch (IOException ioe) {
				    System.err.format("IOException: %s%n", ioe);
				}
		    }
		} catch(Exception ex){
			System.out.println("Error Occurred : " + ex.getMessage());
		}
	    writer.close();
	}
	
	private static String begin(String inputWord) throws IOException {		
		//Check if input word is in word file
		if(!verifyWord(inputWord)){
			return "Word is not found.";
		} else {
			//Find the meaning of the word
			return findWordMeaning(inputWord);
		}
	}
	
	private static boolean verifyWord(String word){
		boolean result = false;
		try (BufferedReader br = new BufferedReader(new FileReader(WordFilePath))) {
		    String line;
		    while ((line = br.readLine()) != null) {
				if(line.toUpperCase().equals(word)){
				    result = true;
				    break;
			    }
		    }
		} catch(Exception ex){
			System.out.println("Error Occurred : " + ex.getMessage());
		}
		return result;
	}
	
	private static String findWordMeaning(String word) throws IOException{
		//Read the dictionary file
		String wordMeaning = "";
		try (BufferedReader br = new BufferedReader(new FileReader(DictionaryFilePath))) {
		    String line;
		    while ((line = br.readLine()) != null) {
				if(line.equals(word)){
					//Until next word is hit print all the line
					while((line = br.readLine()) != null){
						if(!processLine(line, word))
							break;
						wordMeaning += " " +line;
					}
					break;
				}
		    }
		} catch(Exception ex){
			System.out.println("Error Occurred : " + ex.getMessage());
		}
		return wordMeaning;
	}
	
	private static boolean processLine(String line, String inputWord){
		//Check if line has only single word
		String[] splitLine = line.split(" ");
		//If line's length is 1 that is single word and if it is upper case then next word is hit, so process it false.
		if(splitLine.length == 1 && line.matches("[A-Z]+") && !line.equals(inputWord))
			return false;
		return true;
	}
}
