package edu.byu.nlp.annotationinterface.ccash.orm;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DBAutomaticAnnotation.class)
public abstract class DBAutomaticAnnotation_ {

	public static volatile SingularAttribute<DBAutomaticAnnotation, Long> id;
	public static volatile SingularAttribute<DBAutomaticAnnotation, DBAnnotationInstance> annotationInstance;

}

