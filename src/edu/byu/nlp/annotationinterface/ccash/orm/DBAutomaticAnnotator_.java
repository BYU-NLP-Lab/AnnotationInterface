package edu.byu.nlp.annotationinterface.ccash.orm;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DBAutomaticAnnotator.class)
public abstract class DBAutomaticAnnotator_ {

	public static volatile ListAttribute<DBAutomaticAnnotator, DBAnnotationInstance> trainingData;
	public static volatile SingularAttribute<DBAutomaticAnnotator, Long> id;
	public static volatile SingularAttribute<DBAutomaticAnnotator, Long> trainTime;
	public static volatile CollectionAttribute<DBAutomaticAnnotator, DBAnnotationInstance> annotationInstances;

}

