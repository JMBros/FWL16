import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


public class WordLadderOptimized {
	static HashSet<String> words = new HashSet<String>();
	final static int WORD_LENGTH = 4;
	final static int LETTERS_IN_ALPHABET = 26;
	
	public static void main(String[] args) throws FileNotFoundException {
		processWords();
		String begin = "BALL";
		String end = "CARS";
		ArrayList<String> result = wordLadder(begin.toUpperCase(), end.toUpperCase());
		for (String s : result) {
			System.out.print(s + " ");
		}
	}
	
	public static void processWords() throws FileNotFoundException {
		Scanner sc = new Scanner(new File("4LetterWords"));
		
		for (int i = 0; i < LETTERS_IN_ALPHABET; i ++) {
			String line = sc.nextLine();
			String[] values = line.split(" ");
			
			for (String s : values) {
				words.add(s);
			}
		}
	}
	
	public static ArrayList<String> wordLadder(String begin, String end) {
		ArrayList<String> result = new ArrayList<String>();
		
		if (begin.equals(end)) {
			result.add(end);
			return result;
		}
		
		// chars replace
		for (int i = 0; i < WORD_LENGTH; i++) {
			if (begin.charAt(i) != end.charAt(i)) {
				String replaced = begin.substring(0, i) + end.charAt(i) + begin.substring(i + 1, WORD_LENGTH);
				if (isWord(replaced)) {
					ArrayList<String> potential = wordLadder(replaced, end);
					if (potential.contains(end)) {
						result.add(begin);
						result.addAll(potential);
						return result;
					}
				}
			}
		}
		
		
		// chars swap
		for (int i = 0; i < WORD_LENGTH - 1; i++) {
			for (int j = i + 1; j < WORD_LENGTH; j++) {
				String swapped = begin.substring(0, i) + begin.charAt(j) + begin.substring(i, j) + begin.charAt(i) + begin.substring(j, WORD_LENGTH);
				if (isWord(swapped)) {
					ArrayList<String> potential = wordLadder(swapped, end);
					if (potential.contains(end)) {
						result.add(begin);
						result.addAll(potential);
						return result;
					}
				}
			}
		}
		
		return result;
	}
	
	private static boolean isWord(String w) {
		if (words.contains(w)) 
			return true;
		return false;
	}
}
