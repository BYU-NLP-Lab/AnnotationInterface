package edu.byu.nlp.annotationinterface.java;

import java.util.List;

import edu.byu.nlp.annotationinterface.AutomaticAnnotation;
import edu.byu.nlp.annotationinterface.AutomaticAnnotator;
import edu.byu.nlp.annotationinterface.Instance;

/**
 * An auto annotator that is not actually capable of annotating anything.
 * It just exists as a placeholder for holding an annotator id.
 */
public class NullAutomaticAnnotator<T,A> implements AutomaticAnnotator<T, A> {

	private long automaticAnnotatorId;

	public NullAutomaticAnnotator(long automaticAnnotatorId){
		this.automaticAnnotatorId=automaticAnnotatorId;
	}

	@Override
	public long getId() {
		return automaticAnnotatorId;
	}

	@Override
	public AutomaticAnnotation<T, A> annotate(Instance<T> instance) {
		return null;
	}

	@Override
	public List<Annotation<T, A>> getOrderedAnnotations() {
		return null;
	}

	@Override
	public long getTrainTime() {
		return 0;
	}
	

}
