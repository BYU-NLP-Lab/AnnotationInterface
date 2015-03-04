package edu.byu.nlp.annotationinterface;

/**
 * A set of constants that allow special values to 
 * be communicated. 
 */
public class Constants {

	private Constants(){}
	
	/**
	 * Annotations that come from this annotator are accepted to be 
	 * the ground truth, and additionally are a matter of public record. 
	 * They are available as training data to any supervised or semi-supervised 
	 * algorithm and may be used as training/test material for annotators. 
	 */
	public static final long OBSERVED_GOLD_AUTOMATIC_ANNOTATOR = Long.MAX_VALUE; 

	/**
	 * The id of the automatic annotator that is understood to 
	 * be completely trustworthy, and whose annotations correspond 
	 * to the accepted ground truth. Unlike those of 
	 * PUBLIC_CONSENSUS_AUTOMATIC_ANNOTATOR, annotations produced by this 
	 * annotator are NOT available to (semi-)supervised learning algorithms. 
	 * These annotations should only be used for evaluation metrics. 
	 */
	public static final long CONCEALED_GOLD_AUTOMATIC_ANNOTATOR = OBSERVED_GOLD_AUTOMATIC_ANNOTATOR-1; 
}
