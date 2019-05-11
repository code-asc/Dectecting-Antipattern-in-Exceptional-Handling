package beans;

/**
 * This is a simple bean used to store the data of each individual
 * pattern while processing the project.
 * Here we use singleton pattern for creation of object.
 * 
 * @author sandeepchowdaryannabathuni
 *
 */
public class PatternInfo {
	
	public int numOfNestedTry;
	public int numOfThrowKitchen;
	public int numOfGetCause;
	public int numOfNullCatch;
	public int numOfFinallyBlockWithThrows;
	public int numOfGenericCatch;
	public int numOfAbortInCatch;
	public int numThrownGeneric;
	public int numIncompleteImpl;
	public int numCatchReturnNull;

	
	public PatternInfo() {
		numOfNestedTry = 0;
		numOfThrowKitchen = 0;
		numOfGetCause = 0;
		numOfNullCatch = 0;
		numOfFinallyBlockWithThrows = 0;
		numOfGenericCatch = 0;
		numOfAbortInCatch = 0;
		numThrownGeneric = 0;
		numIncompleteImpl = 0;
		numCatchReturnNull = 0;
	}
	
	
	
	
}
