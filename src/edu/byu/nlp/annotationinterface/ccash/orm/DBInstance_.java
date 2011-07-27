package edu.byu.nlp.annotationinterface.ccash.orm;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DBInstance.class)
public abstract class DBInstance_ {

	public static volatile SingularAttribute<DBInstance, Long> id;
	public static volatile SingularAttribute<DBInstance, DBProject> project;
	public static volatile CollectionAttribute<DBInstance, DBAnnotationInstance> annotationInstances;
	public static volatile SingularAttribute<DBInstance, Long> externalId;

}

