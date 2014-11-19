package edu.byu.nlp.annotationinterface.java;

import java.sql.Timestamp;
import java.util.List;

import com.google.common.collect.Lists;

import edu.byu.nlp.annotationinterface.AutomaticAnnotation;
import edu.byu.nlp.annotationinterface.AutomaticAnnotator;
import edu.byu.nlp.annotationinterface.Constants;
import edu.byu.nlp.annotationinterface.Instance;
import edu.byu.nlp.middleware.Annotator;
import edu.byu.nlp.middleware.BasicAnnotator;
import edu.byu.nlp.middleware.TimelineEvent;
import edu.byu.nlp.util.Indexer;

/**
 * 
 * @author plf1
 *
 * A heterogenous collection of utils that have proven 
 * useful when using annotation interface objects 
 * in java programs.
 * 
 * In the java paradigm, objects may not have come from a 
 * database and so might not naturally come with unique ids.
 * Accordingly, whenever possible the source field is used to 
 * populate the id field and so determined equality, 
 * assuming that if instance1.source equals instance2.source
 * then instance1.id equals instance2.id and therefore
 * instance1 equals instance2
 *
 */
public class AnnotationInterfaceJavaUtils {
	
	private AnnotationInterfaceJavaUtils(){}

	public static final long NULL_TIMESTAMP = -1;
	public static final int NULL_TIMELINE_ORDER = -1;
	
	
	public static <T, A> AutomaticAnnotation<T, A> newLabeledInstance(T data, A label, String source){
		Instance<T> instance = new BasicInstance<T>(instanceIdFromSource(source), data, source);
		AutomaticAnnotator<T, A> model = newGoldAnnotator();
		return new BasicAutomaticAnnotation<T, A>(instance, model, label);
	}

	public static synchronized Annotator newAnnotator(String annotatorId){
		return new BasicAnnotator(annotatorIdFromUsername(annotatorId), null, null);
	}

	public static synchronized<T, A> Annotation<T, A> newAnnotatedInstance(String annotatorId, A annotation, String source){
		return newAnnotatedInstance(annotatorId, annotation, NULL_TIMESTAMP, NULL_TIMESTAMP, null, source);
	}
	
	public static synchronized<T, A> Annotation<T, A> newAnnotatedInstance(String annotatorId, A annotation, T data, String source){
		return newAnnotatedInstance(annotatorId, annotation, NULL_TIMESTAMP, NULL_TIMESTAMP, data, source);
	}
	
	/**
	 * Create an annotation connected to a java annotationinstance (with a timeline) and an instance
	 */
	public static synchronized <T, A> Annotation<T, A> newAnnotatedInstance(
			String annotatorId, A annotation, long startTs, long endTs, T data, String source){
		
		Instance<T> instance = new BasicInstance<T>(instanceIdFromSource(source), data, source);
		Annotator annotator = newAnnotator(annotatorId);
		AutomaticAnnotation<T, A> preAnnotation = null;
		
		List<TimelineEvent> timelineEvents = Lists.newArrayList();
		timelineEvents.add(newTimelineEvent(startTs, TimelineEvent.ANNOTATOR_GAIN_FOCUS));
		timelineEvents.add(newTimelineEvent(endTs, TimelineEvent.ANNOTATOR_LOSE_FOCUS));
		
		JavaAnnotationInstance<T, A> ai = new BasicJavaAnnotationInstance<T, A>(
				nextSequentialAnnotationInstanceId(), annotator, instance, preAnnotation, timelineEvents);
		
		return new BasicAnnotation<T, A>(annotation, ai);
	}
	
	/**
	 * This constructor exists for use with java programs (java annotationinstances 
	 * currently do not implement AnnotationInstance so that backlink cannot be 
	 * set)
	 */
	public static TimelineEvent newTimelineEvent(long timestamp, String eventName){
		return new BasicTimelineEvent(null, new Timestamp(timestamp), NULL_TIMELINE_ORDER, eventName);
	}

	public static <T, A> AutomaticAnnotator<T, A> newGoldAnnotator(){
		return new NullAutomaticAnnotator<T,A>(Constants.GOLD_AUTOMATIC_ANNOTATOR_ID);
	}
	
	private static Indexer<String> sourceIndexer = new Indexer<String>();
	public static synchronized long instanceIdFromSource(String instanceSource) {
		sourceIndexer.add(instanceSource);
		return sourceIndexer.indexOf(instanceSource);
	} 

	private static Indexer<String> usernameIndexer = new Indexer<String>();
	public static synchronized long annotatorIdFromUsername(String username){
		usernameIndexer.add(username);
		return usernameIndexer.indexOf(username);
	}
	
	private static long uniqueAnnotationInstanceId = 0;
	public static synchronized long nextSequentialAnnotationInstanceId(){
		return uniqueAnnotationInstanceId++;
	}
	
}
