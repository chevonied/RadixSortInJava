import queuepackage.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ChevonieDanielProgramVI {
	
	public static void main(String[] args) throws FileNotFoundException {
		//Reads string from file
		Scanner in = new Scanner(new File("testwords.txt"));
		//Creates array and fills with strings from file (Array length must be adjusted according to file)
		String[] strings = new String[20];

		while (in.hasNextLine())
		for (int i = 0; i < strings.length; i++) {

			strings[i] = in.nextLine();
		}

		//printArray(strings);

		//Finds largest string in array
		int maxWordLength = maxWordLength(strings);
		
		//Adds hashes to strings to make them same length
		addHash(strings, maxWordLength);

		//Maxlength initialized again due to extra hash added to longest word
		maxWordLength = maxWordLength(strings);

		//Sorts array of strings
		radixSort(strings, strings.length, maxWordLength);

		//System.out.println("++Sorted Array++");
		removeHash(strings, maxWordLength);
		printArray(strings);
	}

	public static void radixSort(String[] strArray, int arrayLength, int maxWordLength) {
		//Determines what values strings will have based on index of chars & creates Queue
		char[] validChars = {'!', '(', ')', '.', '<', '>', '?', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', ']', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '}', '#'};
		Queue[] strQueue = new Queue[64];
		for (int queueChar = 0; queueChar < strQueue.length; queueChar++) {

			strQueue[queueChar] = new Queue();
		}
		//Checks letter position in word from right to left then checks this letter in each word
		for (int letterPos = maxWordLength-1; letterPos >= 0; letterPos--) {

			for (int word = 0; word < arrayLength; word++) {
				
				char letterInd = strArray[word].charAt(letterPos);
				for (int valLetter = 0; valLetter < validChars.length; valLetter++) {
					//checks whether letter is valid then adds it to queue
					if (letterInd == validChars[valLetter]) {

						strQueue[valLetter].enqueue(new String(strArray[word]));
					}
				}
			}
			//Iterates through queue and extracts from it if it isn't empty
			int currLetter = 0;
			for (int queuePos = 0; queuePos < 64; queuePos++) {

				while (!strQueue[queuePos].isEmpty()) {

					strArray[currLetter] = ((String) strQueue[queuePos].dequeue()).toString();
					currLetter++;
				}
			}
		}
	}

	public static void printArray(String[] array) {

		for (int j = 0; j < array.length; j++) {

			System.out.println(array[j]);
		}
	}

	public static String getLetter(String word, int location) {

		try {
			char k = word.charAt(location);
			String letter = String.valueOf(k);
			return letter;
		}
		catch (StringIndexOutOfBoundsException exc) {

			return "#";
		}
	}

	public static int maxWordLength(String[] str) {

		int maxLength = 0;
		for (int l = 0; l < str.length; l++) {

			if (str[l].length() > maxLength) {

				maxLength = str[l].length();
			}
		}

		return maxLength;
	}

	public static void addHash(String[] strArr, int maxLength) {

		for (int m = 0; m < strArr.length; m++) {

			int times = (maxLength+1) - (strArr[m].length());
			if (strArr[m].length() < (maxLength+1)) {

				for (int n = 0; n < times; n++) {

					strArr[m] = strArr[m] + "#";
				}
			}
		}
	}

	public static void removeHash(String[] strArr, int maxLength) {

		for (int o = 0; o < strArr.length; o++) {

			int firstHash = strArr[o].indexOf("#");
			//System.out.println("test");
			strArr[o] = strArr[o].substring(0, firstHash);
		} 
	} 
}