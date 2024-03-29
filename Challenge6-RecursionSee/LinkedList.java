/**
 * @author dyang42
 *
 */
public class LinkedList {
	// Get and Set methods are NOT necessary!

	private LinkedList next; 	
	private String word;

	/** Constructs this link.
	 * @param word ; a single word (never null).
	 * @param next ; the next item in the chain (null, if there are no more items).
	 */
	public LinkedList(String word, LinkedList next) {
		this.word = word;
		this.next = next;
	}

	/**
	 * Converts the entire linked list into a string representation.
	 */
	public String toString() {
		if (next == null)
			return word;// BASE CASE; no more recursion required

		// Recursive case:
		String restOfString = next.toString(); // Forward Recursion
		return word + ";" + restOfString;
	}

	/**
	 * Returns the number of entries in the linked list.
	 * @return number of entries.
	 */
	public int getCount() {
		if (next == null)
			return 1; // BASE CASE; no more recursion required!

		// Recursive case:
		return 1 + next.getCount(); // Forward recursion
	}
	
	/** Creates a new LinkedList entry at the end of this linked list.
	 * Recursively finds the last entry then adds a new link to the end.
	 * @param word
	 */
	public void append(String word) {
		if (this.next == null)
			this.next = new LinkedList(word,null);
		else
			next.append(word);

	}
	/**
	 * Recursively counts the total number of letters used.
	 * 
	 * @return total number of letters in the words of the linked list
	 */
	public int getLetterCount() {
		if (this.next == null)
			return this.word.length();
		else
			return this.word.length() + this.next.getLetterCount();
		// returns the total number of letters. word1.length() +
		// word2.length()+...
		// "A" -> "CAT" -> null returns 1 + 3 = 4.
	}

	/**
	 * Recursively searches for and the returns the longest word.
	 * @return the longest word i.e. word.length() is maximal.
	 */
	public String getLongestWord() {
		// recursive searches for the longest word\
		if (this.next == null)
			return this.word;
		String thisWord = this.word;
		String restWord = this.next.getLongestWord();
		int thisLength = thisWord.length();
		int restLength = restWord.length();
		if (thisLength > restLength)
			return thisWord;
		else
			return restWord;
	}

	/** Converts linked list into a sentence (a single string representation).
	* Each word pair is separated by a space.
	* A period (".") is appended after the last word.
	* The last link represents the last word in the sentence.*/
	public String getSentence() {
		if (next == null)
			return word + ".";// BASE CASE; no more recursion required

		// Recursive case:
		String restOfString = next.getSentence(); // Forward Recursion
		return word + " " + restOfString;
	}
	
	/**
	 * Converts linked list into a sentence (a single string representation).
	 * Each word pair is separated by a space. A period (".") is appended after
	 * the last word. The last link represents the first word in the sentence
	 * (and vice versa). The partialResult is the partial string constructed
	 * from earlier links. This partialResult is initially an empty string. 
	 */
	private String getReversedSentencePrivate(String partialResult) {
		if (this.next == null)
			return this.word;
		else
			return this.next.getReversedSentencePrivate(partialResult) + " " + this.word;
	}
	
	public String getReversedSentence(String partialResult){
		return getReversedSentencePrivate(partialResult) + ".";
	}

	/** Creates a linked list of words from an array of strings.
	 * Each string in the array is a word. */
	public static LinkedList createLinkedList(String[] words) {
		int index = words.length;
		//System.out.println(index);
		return createLinkedListP(words, index);
		}
	private static LinkedList createLinkedListP(String[] words, int index){
		int len = words.length;
		String value;
		LinkedList nextLink;
		if (index == 1){
			value = words[len-1];
			//System.out.println(value);
			nextLink = null;
		}
		else{
			value = words[len-index];
			//System.out.println(value);
			nextLink = createLinkedListP(words, index-1);
		}
		LinkedList result = new LinkedList(value, nextLink);
		return result;
		// Hint: This is a wrapper method. You'll need to create your
		// own recursive method.
		// Yes this is possible _without_ loops!
	}

	/**
	 * Searches for the following word in the linked list. Hint: use .equals not ==
	 * to compare strings.
	 * 
	 * @param word
	 * @return true if the linked list contains the word (case sensivitive)
	 */
	public boolean contains(String word) {
		if (this.word.equals(word))
			return true;
		else if (this.next == null)
			return false;
		else
			return this.next.contains(word);
	}

	/** Recursively searches for the given word in the linked list.
	 * If this link matches the given word then return this link.
	 * Otherwise search the next link.
	 * If no matching links are found return null.
	 * @param word the word to search for.
	 * @return The link that contains the search word.
	 */
	public LinkedList find(String word) {
		if (this.word.equals(word))
			return this;
		else if (this.next == null)
			return null;
		else
			return this.next.find(word);

	}

	/**
	 * Returns the last most link that has the given word, or returns null if
	 * the word cannot be found.
	 * Hint: Would forward recursion be useful?
	 * @param word the word to search for.
	 * @return the last LinkedList object that represents the given word, or null if it is not found.
	 */
	public LinkedList findLast(String word) {
		String thisWord = this.word;
		if (this.next == null){
			if (thisWord.equals(word))
				return this;
			else
				return null;
		}
		else
			if (this.next.findLast(word) == null && thisWord.equals(word))
				return this;
			else if (this.next.findLast(word) == null && !thisWord.equals(word))
				return null;
			else
				return this.next.findLast(word);
	}
	
	
	public boolean contain(String target){
		if (this.word.equals(target))
			return true;
		else if (this.next == null)
			return false;
		else
			return this.next.contain(target);
	}
	
	public void removeLastSeven(LinkedList acc){
		if (this.next == null && acc != null){
			acc.next = acc.next.next;
			return;
		}
		if (this.next.word.equals("7"))
			acc = this;//acc is the link before 7
		this.next.removeLastSeven(acc);
	}
	
	
	
	public LinkedList insert(String string) {
		if (string.charAt(0) >= this.word.charAt(0)){
			if (this.next == null){
				LinkedList nextLinkedList = new LinkedList(string,null);
				this.next = nextLinkedList;
			}
			else
				this.next.insert(string);
			return this;
		}
		else{
			LinkedList prevLinkedList = new LinkedList(string,this);
			return prevLinkedList;
		}
	}

}
