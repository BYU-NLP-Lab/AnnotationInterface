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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ai_automatic_annotator")
@NamedQueries({
	@NamedQuery(name="DBAutomaticAnnotator.findall",query="from DBAutomaticAnnotator")
})
public class DBAutomaticAnnotator implements Serializable, HasId {
	
	private static final long serialVersionUID = 1L;

	@Id 
	@Column(name="automatic_annotator_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	
	/**
	 * This automatic annotator was used to pre-label
	 * this set of annotation instances
	 */
	@OneToMany(mappedBy="automaticAnnotator")
	private Collection<DBAnnotationInstance> annotationInstances = Collections.synchronizedCollection(new ArrayList<DBAnnotationInstance>());
	
	/**
	 * This automatic annotator was trained using the
	 * following (ordered) list of annotation instances
	 */
	@ManyToMany
	@JoinTable(name="ai_automatic_annotator_training_data",
			joinColumns=@JoinColumn(name="automatic_annotator_id",referencedColumnName="automatic_annotator_id"),
			inverseJoinColumns=@JoinColumn(name="annotation_instance_id",referencedColumnName="annotation_instance_id"))
	private List<DBAnnotationInstance> trainingData;
	
	@Column(name="train_time")
	private long trainTime;

	
	public DBAutomaticAnnotator(){
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getTrainTime() {
		return trainTime;
	}

	public void setTrainTime(long trainTime) {
		this.trainTime = trainTime;
	}

	public List<DBAnnotationInstance> getTrainingData() {
		return Collections.unmodifiableList(trainingData);
	}

	@SuppressWarnings("unused")
	private void setTrainingData(List<DBAnnotationInstance> trainingData) {
		this.trainingData = trainingData;
	}
	
	public void addTrainingDatum(DBAnnotationInstance datum){
		if (trainingData==null)
			trainingData = new ArrayList<DBAnnotationInstance>();
		if (datum!=null){
			trainingData.add(datum);
			datum.addAutomaticAnnotatorTrainedWith(this);
		}
	}
	
	
	public Collection<DBAnnotationInstance> getAnnotationInstances() {
		return Collections.unmodifiableCollection(annotationInstances);
	}

	@SuppressWarnings("unused")
	private void setAnnotationInstances(
			Collection<DBAnnotationInstance> annotationInstances) {
		this.annotationInstances = annotationInstances;
	}


	public void addAnnotationInstance(DBAnnotationInstance ai){
		if (ai!=null){
			annotationInstances.add(ai);
			ai.setAutomaticAnnotator(this);
		}
	}

	public boolean removeAnnotationinstance(DBAnnotationInstance ai){
		if (ai!=null){
			return annotationInstances.remove(ai);
		}
		return false;
	}
	
}
