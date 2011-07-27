package edu.byu.nlp.annotationinterface.ccash.orm;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DBAnnotation.class)
public abstract class DBAnnotation_ {

	public static volatile SingularAttribute<DBAnnotation, Long> id;
	public static volatile SingularAttribute<DBAnnotation, DBProject> project;
	public static volatile ListAttribute<DBAnnotation, DBAnnotationInstance> annotationInstances;
	public static volatile SingularAttribute<DBAnnotation, Long> externalId;

}

