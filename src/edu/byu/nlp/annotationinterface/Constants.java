package edu.byu.nlp.annotationinterface;

/**
 * A set of constants that allow special values to 
 * be communicated. A notable example is the id of the 
 * automatic annotator that is understood to be completely 
 * trustworthy.
 */
public class Constants {

	private Constants(){}
	
	/**
	 * The id of the automatic annotator that is understood to 
	 * be completely trustworthy, and whose annotations correspond 
	 * to the accepted ground truth.
	 */
	public static final long GOLD_AUTOMATIC_ANNOTATOR_ID = Long.MAX_VALUE; 
	
}
