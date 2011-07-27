package edu.byu.nlp.annotationinterface.ccash.orm;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DBProject.class)
public abstract class DBProject_ {

	public static volatile SingularAttribute<DBProject, Long> id;
	public static volatile SingularAttribute<DBProject, DBProjectIteration> activeIteration;
	public static volatile ListAttribute<DBProject, DBProjectIteration> iterations;
	public static volatile SingularAttribute<DBProject, String> description;
	public static volatile SingularAttribute<DBProject, String> name;
	public static volatile CollectionAttribute<DBProject, DBAnnotation> annotations;
	public static volatile CollectionAttribute<DBProject, DBInstance> instances;

}

