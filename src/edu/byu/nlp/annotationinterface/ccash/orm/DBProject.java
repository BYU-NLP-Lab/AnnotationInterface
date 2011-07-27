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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name="ai_project")
@NamedQueries({
	@NamedQuery(name="DBProject.findAll",query="from DBProject")
})
/**
 * 
 */
@Inheritance(strategy=InheritanceType.JOINED) 
public class DBProject implements Serializable, HasId {

	private static final long serialVersionUID = 1L;
	
	public final static String queryFindAll = "DBProject.findAll";
	
	@Id
	@Column(name="project_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique=true)
	private String name;

	@Column(length = 4096)
	private String description;
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER)
	@OrderColumn(name="ordering")
	@JoinTable(name="ai_project_iteration_ordering",joinColumns={@JoinColumn(name="project_id")},inverseJoinColumns={@JoinColumn(name="project_iteration_id")})
	private List<DBProjectIteration> iterations = Collections.synchronizedList(new ArrayList<DBProjectIteration>());
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="active_iteration")
	private DBProjectIteration activeIteration;

	@OneToMany(mappedBy="project")
	private Collection<DBAnnotation> annotations = Collections.synchronizedCollection(new ArrayList<DBAnnotation>());
	
	@OneToMany(mappedBy="project")
	private Collection<DBInstance> instances = Collections.synchronizedCollection(new ArrayList<DBInstance>());

	
	public DBProject() {
	}
	
	public DBProject(DBProjectIteration itr){
		addIteration(itr);
		setActiveIteration(itr);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String title) {
		this.name = title;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DBProjectIteration getActiveIteration() {
		return activeIteration;
	}

	public void setActiveIteration(DBProjectIteration activeIteration) {
		if (!this.iterations.contains(activeIteration))
			throw new IllegalArgumentException("Active iteration must be a member of a project's iterations");
		this.activeIteration = activeIteration;
	}
	
	
	public List<DBProjectIteration> getIterations() {
		return Collections.unmodifiableList(iterations);
	}

	@SuppressWarnings("unused")
	private void setIterations(List<DBProjectIteration> iterations) {
		this.iterations = iterations;
	}
	
	public DBProjectIteration addNewActiveIteration(){
		// copy it
		DBProjectIteration newIteration = new DBProjectIteration(getActiveIteration());
		// add it
		addIteration(newIteration);
		// activate it
		setActiveIteration(newIteration);
		return newIteration;
	}

	public void addIteration(DBProjectIteration itr){
		if (itr!=null){
			this.iterations.add(itr);
			itr.setProject(this);
		}
	}



	public void addAnnotation(DBAnnotation annotation){
		if (annotation!=null){
			annotations.add(annotation);
			annotation.setProject(this);
		}
	}
	
	public boolean removeAnnotation(DBAnnotation annotation){
		if (annotation!=null){
			annotation.setProject(null);
			return annotations.remove(annotation);
		}
		return false;
	}

	public void addInstance(DBInstance instance){
		if (instance!=null){
			instances.add(instance);
			instance.setProject(this);
		}
	}
	
	public boolean removeInstance(DBInstance instance){
		if (instance!=null){
			instance.setProject(null);
			return instances.remove(instance);
		}
		return false;
	}

	public Collection<DBAnnotation> getAnnotations() {
		return annotations;
	}

	@SuppressWarnings("unused")
	private void setAnnotations(Collection<DBAnnotation> annotations) {
		this.annotations = annotations;
	}

	public Collection<DBInstance> getInstances() {
		return instances;
	}
	
	@SuppressWarnings("unused")
	private void setInstances(Collection<DBInstance> instances) {
		this.instances = instances;
	}
	
}
