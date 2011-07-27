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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ai_annotation_instance")
@NamedQueries({
	@NamedQuery(name="DBAnnotationInstance.findall",query="FROM DBAnnotationInstance"),
	@NamedQuery(name="DBAnnotationInstance.findByProjectIterationAndAnnotator",query="FROM DBAnnotationInstance ai WHERE ai.projectIteration=:project_iteration AND ai.annotator=:annotator"),
	@NamedQuery(name="DBAnnotationInstance.findByInstanceExternalIdAndProjectIterationAndAnnotator",query="FROM DBAnnotationInstance ai WHERE ai.projectIteration=:project_iteration AND ai.instance.externalId=:instance_external_id AND ai.annotator=:annotator")
})
public class DBAnnotationInstance implements Serializable, HasId {

	public static final String queryFindAll = "DBAnnotationInstance.findall";
	public static final String queryFindByProjectIterationAndAnnotator = "DBAnnotationInstance.findByProjectIterationAndAnnotator";
	public static final String queryFindByInstanceExternalIdAndProjectIterationAndAnnotator = "DBAnnotationInstance.findByInstanceExternalIdAndProjectIterationAndAnnotator";
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="annotation_instance_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="annotator_id")
	private DBAnnotator annotator;
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name="instance_id")
	private DBInstance instance;

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name="annotation_id")
	private DBAnnotation annotation;

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name="project_iteration_id")
	private DBProjectIteration projectIteration;
	
	@Transient
	private transient String metadata;
	
	/**
	 * The annotator used to pre-label this AI
	 */
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name="automatic_annotator_id")
	private DBAutomaticAnnotator automaticAnnotator;	
	
	@OneToOne(mappedBy="annotationInstance")
	private DBAutomaticAnnotation automaticAnnotation;

	/**
	 * The annotators that these AIs were used to train
	 */
	@ManyToMany(mappedBy="trainingData")
	private Collection<DBAutomaticAnnotator> automaticAnnotatorsTrainedWith = Collections.synchronizedCollection(new ArrayList<DBAutomaticAnnotator>());
	
	@OneToMany(cascade={CascadeType.ALL})
	@OrderColumn(name="ordering")
	@JoinTable(name="ai_timeline_event_ordering",joinColumns={@JoinColumn(name="annotation_instance_id")},inverseJoinColumns={@JoinColumn(name="timeline_event_id")})
	private List<DBTimelineEvent> timelineEvents = Collections.synchronizedList(new ArrayList<DBTimelineEvent>());

	
	public DBAnnotationInstance(){
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DBAnnotator getAnnotator() {
		return annotator;
	}

	public void setAnnotator(DBAnnotator annotator) {
		this.annotator = annotator;
	}

	public DBInstance getInstance() {
		return instance;
	}

	public void setInstance(DBInstance instance) {
		this.instance = instance;
	}

	public DBAutomaticAnnotation getAutomaticAnnotation() {
		return automaticAnnotation;
	}

	public void setAutomaticAnnotation(DBAutomaticAnnotation automaticAnnotation) {
		this.automaticAnnotation = automaticAnnotation;
	}

	public DBAnnotation getAnnotation() {
		return annotation;
	}

	public void setAnnotation(DBAnnotation annotation) {
		this.annotation = annotation;
	}

	public DBAutomaticAnnotator getAutomaticAnnotator() {
		return automaticAnnotator;
	}

	public void setAutomaticAnnotator(DBAutomaticAnnotator automaticAnnotator) {
		this.automaticAnnotator = automaticAnnotator;
	}

	public Collection<DBAutomaticAnnotator> getAutomaticAnnotatorsTrainedWith() {
		return Collections.unmodifiableCollection(automaticAnnotatorsTrainedWith);
	}

	/**  
	 * Handle this relationship from the AutomaticAnnotator side
	 * @param automaticAnnotators
	 */
	@SuppressWarnings("unused")
	private void setAutomaticAnnotatorsTrainedWith(
			Collection<DBAutomaticAnnotator> automaticAnnotators) {
		this.automaticAnnotatorsTrainedWith = automaticAnnotators;
	}
	protected void addAutomaticAnnotatorTrainedWith(DBAutomaticAnnotator aa){
		if (aa!=null)
			automaticAnnotatorsTrainedWith.add(aa);
	}

	public List<DBTimelineEvent> getTimelineEvents() {
		return Collections.unmodifiableList(timelineEvents);
	}

	@SuppressWarnings("unused")
	private void setTimelineEvents(List<DBTimelineEvent> timelineEvents) {
		this.timelineEvents = timelineEvents;
	}

	public void addTimelineEvent(DBTimelineEvent event){
		if (event!=null){
			timelineEvents.add(event);
			event.setAnnotationInstance(this);
		}
	}

	public void addTimelineEvent(String eventName, Timestamp timestamp, Long durationMillis, Set<DBTimelineEventTag> tags){
		addTimelineEvent(eventName, timestamp, durationMillis, 1, tags);
	}
	
	public void addTimelineEvent(String eventName, Timestamp timestamp, Long durationMillis, int count, Set<DBTimelineEventTag> tags){
		addTimelineEvent(new DBTimelineEvent(eventName, this, timestamp, durationMillis, count, tags));
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public void setProjectIteration(DBProjectIteration projectIteration) {
		this.projectIteration = projectIteration;
	}

	public DBProjectIteration getProjectIteration() {
		return projectIteration;
	}

}
