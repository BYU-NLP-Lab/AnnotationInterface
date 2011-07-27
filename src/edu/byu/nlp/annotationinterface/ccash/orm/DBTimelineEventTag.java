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
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.Table;

@Entity
@Table(name="ai_timeline_event_tag") 
@NamedQueries({
	@NamedQuery(name="DBTimelineEventTag.findAll",query="from DBTimelineEventTag"),
	@NamedQuery(name="DBTimelineEventTag.findByText",query="from DBTimelineEventTag tag where tag.text=:text"),
})
/**
 * 
 */
public class DBTimelineEventTag implements Serializable, HasId {

	private static final long serialVersionUID = 1L;

	public final static String queryFindAll = "DBTimelineEventTag.findAll";
	public final static String queryFindByText = "DBTimelineEventTag.findByText";
	
	@Id
	@Column(name="tag_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="text",unique=true)
	private String text;
	
	@ManyToMany(mappedBy="tags")
	private Set<DBTimelineEvent> timelineEvents = Collections.synchronizedSet(new HashSet<DBTimelineEvent>());

	public DBTimelineEventTag(){}
	
	public DBTimelineEventTag(String text) {
		this.text=text;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Set<DBTimelineEvent> getTimelineEvents() {
		return Collections.unmodifiableSet(timelineEvents);
	}

	@SuppressWarnings("unused")
	private void setTimelineEvents(Set<DBTimelineEvent> timelineEvents) {
		this.timelineEvents = timelineEvents;
	}
	
	/**
	 * This should be called only by DBTimelineEvent.setTags() 
	 */
	protected void addTimelineEvent(DBTimelineEvent tle){
		if (tle!=null){
			timelineEvents.add(tle);
		}
	}

	public static Set<DBTimelineEventTag> getOrCreateTimelineEventTags(Set<String> tags, EntityManager em){
		if (tags.contains(null)){
			throw new IllegalArgumentException("attempt to add tags that contain a null");
		}
		Set<DBTimelineEventTag> retval = new HashSet<DBTimelineEventTag>();
		for (String tag: tags){
			Query q = em.createNamedQuery(DBTimelineEventTag.queryFindByText);
			q.setParameter("text", tag);
			DBTimelineEventTag dbTag = null;
			try{
				dbTag = (DBTimelineEventTag) q.getSingleResult();
			}
			catch (NoResultException e){
				dbTag = new DBTimelineEventTag(tag);
			}
			retval.add(dbTag);
		}
		return retval;
	}
	
	@Override
	public String toString() {
		return text;
	}
}
