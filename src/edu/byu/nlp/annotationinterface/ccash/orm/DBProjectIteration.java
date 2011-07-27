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
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name="ai_project_iteration")
@NamedQueries({
	@NamedQuery(name="DBProjectIteration.findAll",query="from DBProjectIteration")
})
/**
 * 
 */
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) 
public class DBProjectIteration implements Serializable, HasId {

	private static final long serialVersionUID = 1L;
	
	public static final String queryFindAll = "DBProjectIteration.findAll";
	
	@Id
	@Column(name="project_iteration_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="project_id")
	private DBProject project;
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@OrderColumn(name="ordering")
	@JoinTable(name="ai_annotation_instance_ordering",joinColumns={@JoinColumn(name="project_iteration_id")},inverseJoinColumns={@JoinColumn(name="annotation_instance_id")})
	private List<DBAnnotationInstance> annotationInstances = Collections.synchronizedList(new ArrayList<DBAnnotationInstance>());
	
	@Column
	private String name = null;
	
	public DBProjectIteration() {
	}
	
	/**
	 * Copy constructor copies all field references
	 */
	public DBProjectIteration(DBProjectIteration other) {
		setName(other.getName());
	}
	
	public String getName() {
		return name;
	}

	public void setName(String title) {
		this.name = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DBProject getProject() {
		return project;
	}

	protected void setProject(DBProject project) {
		this.project = project;
	}

	@SuppressWarnings("unused")
	private void setAnnotationInstances(List<DBAnnotationInstance> annotationInstances) {
		this.annotationInstances = annotationInstances;
	}

	public List<DBAnnotationInstance> getAnnotationInstances() {
		return Collections.unmodifiableList(annotationInstances);
	}

	public void addAnnotationInstance(DBAnnotationInstance ai){
		if (ai!=null){
			this.annotationInstances.add(ai);
			ai.setProjectIteration(this);
		}
	}

}
