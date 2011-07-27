package edu.byu.nlp.annotationinterface.ccash.orm;

import java.sql.Timestamp;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DBTimelineEvent.class)
public abstract class DBTimelineEvent_ {

	public static volatile SingularAttribute<DBTimelineEvent, Timestamp> timestamp;
	public static volatile SetAttribute<DBTimelineEvent, DBTimelineEventTag> tags;
	public static volatile SingularAttribute<DBTimelineEvent, Long> id;
	public static volatile SingularAttribute<DBTimelineEvent, Integer> count;
	public static volatile SingularAttribute<DBTimelineEvent, String> eventName;
	public static volatile SingularAttribute<DBTimelineEvent, Long> durationMillis;
	public static volatile SingularAttribute<DBTimelineEvent, DBAnnotationInstance> annotationInstance;

}

