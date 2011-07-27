package edu.byu.nlp.annotationinterface.ccash.orm;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DBAnnotationInstance.class)
public abstract class DBAnnotationInstance_ {

	public static volatile SingularAttribute<DBAnnotationInstance, Long> id;
	public static volatile SingularAttribute<DBAnnotationInstance, DBAnnotation> annotation;
	public static volatile ListAttribute<DBAnnotationInstance, DBTimelineEvent> timelineEvents;
	public static volatile CollectionAttribute<DBAnnotationInstance, DBAutomaticAnnotator> automaticAnnotatorsTrainedWith;
	public static volatile SingularAttribute<DBAnnotationInstance, DBProjectIteration> projectIteration;
	public static volatile SingularAttribute<DBAnnotationInstance, DBAutomaticAnnotator> automaticAnnotator;
	public static volatile SingularAttribute<DBAnnotationInstance, DBAnnotator> annotator;
	public static volatile SingularAttribute<DBAnnotationInstance, DBInstance> instance;
	public static volatile SingularAttribute<DBAnnotationInstance, DBAutomaticAnnotation> automaticAnnotation;

}

