/*******************************************************************************
 * This file is part of the project annotationinterface. 
 * See the main README for more information about annotationinterface.
 * 
 * Copyright (c) 2011 Brigham Young University (BYU).
 * annotationinterface is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * annotationinterface is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with annotationinterface.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package edu.byu.nlp.annotationinterface.ccash.orm;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="ai_timeline_event")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@NamedQueries({
	@NamedQuery(name="DBTimelineEvent.findall",query="from DBTimelineEvent"),
})
public class DBTimelineEvent implements Serializable, HasId {

	public static final String queryFindAll = "DBTimelineEvent.findall";
	
	private static final long serialVersionUID = 1L;

	@Id 
	@Column(name="timeline_event_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	
	@ManyToOne
	@JoinColumn(name="annotation_instance_id")
	private DBAnnotationInstance annotationInstance;

	@Column(name="event_name")
	private String eventName;
	
	@Column(name="count")
	private Integer count;
	
	@Column(name="duration_millis")
	private Long durationMillis;

	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(
			name="ai_timeline_event_tagging",
			joinColumns=@JoinColumn(name="timeline_event_id"),
			inverseJoinColumns=@JoinColumn(name="tag_id"))
	private Set<DBTimelineEventTag> tags = Collections.synchronizedSet(new HashSet<DBTimelineEventTag>());
	
	@Column(name="timestamp")
	private Timestamp timestamp;

	protected DBTimelineEvent(){ }
	
	public DBTimelineEvent(String eventName, DBAnnotationInstance annotationInstance, Timestamp timestamp, Long durationMillis, Integer count, Set<DBTimelineEventTag> tags){
		this.eventName=eventName;
		this.annotationInstance=annotationInstance;
		this.timestamp=timestamp;
		this.durationMillis=durationMillis;
		this.count=count;
		this.tags=tags;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public DBAnnotationInstance getAnnotationInstance() {
		return annotationInstance;
	}

	/**
	 * Should be called only by DBAnnotationInstance
	 * @param annotationInstance
	 */
	protected void setAnnotationInstance(DBAnnotationInstance annotationInstance) {
		this.annotationInstance = annotationInstance;
	}

	public Long getDurationMillis() {
		return durationMillis;
	}

	public void setDurationMillis(Long durationMillis) {
		this.durationMillis = durationMillis;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Set<DBTimelineEventTag> getTags() {
		return Collections.unmodifiableSet(tags);
	}

	public void setTags(Set<DBTimelineEventTag> tags) {
		this.tags=tags;
	}

	public void setTags(Set<String> tags, EntityManager em) {
		this.tags = DBTimelineEventTag.getOrCreateTimelineEventTags(tags, em);
		if (tags!=null){
			for (DBTimelineEventTag tag : this.tags){
				tag.addTimelineEvent(this);
			}
		}
	}


}
