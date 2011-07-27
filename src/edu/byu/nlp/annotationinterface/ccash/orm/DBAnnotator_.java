package edu.byu.nlp.annotationinterface.ccash.orm;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DBAnnotator.class)
public abstract class DBAnnotator_ {

	public static volatile SingularAttribute<DBAnnotator, Long> id;
	public static volatile SingularAttribute<DBAnnotator, String> lastName;
	public static volatile CollectionAttribute<DBAnnotator, DBAnnotationInstance> annotationInstances;
	public static volatile SingularAttribute<DBAnnotator, String> firstName;
	public static volatile SingularAttribute<DBAnnotator, String> password;
	public static volatile SingularAttribute<DBAnnotator, String> loginName;

}

