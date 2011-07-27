package edu.byu.nlp.annotationinterface.ccash.orm;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DBTimelineEventTag.class)
public abstract class DBTimelineEventTag_ {

	public static volatile SingularAttribute<DBTimelineEventTag, Long> id;
	public static volatile SetAttribute<DBTimelineEventTag, DBTimelineEvent> timelineEvents;
	public static volatile SingularAttribute<DBTimelineEventTag, String> text;

}

