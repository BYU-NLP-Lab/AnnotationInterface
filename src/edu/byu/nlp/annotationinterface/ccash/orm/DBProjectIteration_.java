package edu.byu.nlp.annotationinterface.ccash.orm;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DBProjectIteration.class)
public abstract class DBProjectIteration_ {

	public static volatile SingularAttribute<DBProjectIteration, Long> id;
	public static volatile SingularAttribute<DBProjectIteration, DBProject> project;
	public static volatile ListAttribute<DBProjectIteration, DBAnnotationInstance> annotationInstances;
	public static volatile SingularAttribute<DBProjectIteration, String> name;

}

