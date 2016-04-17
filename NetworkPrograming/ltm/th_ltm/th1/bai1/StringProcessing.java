package th_ltm.th1.bai1;

import th_ltm.th1.IStringProcessing;

public class StringProcessing implements IStringProcessing {
	
	private static StringProcessing mInstance = new StringProcessing();
	
	private StringProcessing() {}
	
	public static StringProcessing getInstance() {
		return mInstance;
	}
	
	public String process(String s) {
		String upperString = s.toUpperCase();
		String lowerString = s.toLowerCase();
		int numOfWords = StringProcessing.countWords(s);
		return upperString + "\n" + lowerString + "\n" + numOfWords;
	}
	
	public static int countWords(String s){
		
	    int wordCount = 0;
	
	    boolean word = false;
	    int endOfLine = s.length() - 1;
	
	    for (int i = 0; i < s.length(); i++) {
	        // if the char is a letter, word = true.
	        if (Character.isLetter(s.charAt(i)) && i != endOfLine) {
	            word = true;
	            // if char isn't a letter and there have been letters before,
	            // counter goes up.
	        } else if (!Character.isLetter(s.charAt(i)) && word) {
	            wordCount++;
	            word = false;
	            // last word of String; if it doesn't end with a non letter, it
	            // wouldn't count without this.
	        } else if (Character.isLetter(s.charAt(i)) && i == endOfLine) {
	            wordCount++;
	        }
	    }
	    return wordCount;
	}
}
