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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="ai_instance",
		uniqueConstraints=@UniqueConstraint(columnNames={"external_id","project_id"})		
)
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name="DBInstance.findall",query="from DBInstance"),
	@NamedQuery(name="DBInstance.findByProjectAndExternalId",query="FROM DBInstance i WHERE i.project=:project AND i.externalId = :external_id")
})
public class DBInstance implements Serializable, HasId, HasExternalId, HasSerializedValue {

	public static final String queryFindAll = "DBInstance.findall";
	public static final String queryFindByProjectAndExternalId = "DBInstance.findByProjectAndExternalId";
	
	private static final long serialVersionUID = 1L;

	@Id 
	@Column(name="instance_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	
	@Column(name="external_id")
	private Long externalId;
	
	@OneToMany(mappedBy="instance")
	private Collection<DBAnnotationInstance> annotationInstances = Collections.synchronizedCollection(new ArrayList<DBAnnotationInstance>());

	@ManyToOne
	@JoinColumn(name="project_id")
	private DBProject project;
	
	@Transient
	private String serializedValue;
	
	
	public DBInstance(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerializedValue() {
		return serializedValue;
	}

	public void setSerializedValue(String serializedValue) {
		this.serializedValue = serializedValue;
	}


	public void addAnnotationInstance(DBAnnotationInstance ai){
		if (ai!=null){
			annotationInstances.add(ai);
			ai.setInstance(this);
		}
	}

	public boolean removeAnnotationinstance(DBAnnotationInstance ai){
		if (ai!=null){
			return annotationInstances.remove(ai);
		}
		return false;
	}
	
	public Collection<DBAnnotationInstance> getAnnotationInstances() {
		return Collections.unmodifiableCollection(annotationInstances);
	}

	@SuppressWarnings("unused")
	private void setAnnotationInstances(
			Collection<DBAnnotationInstance> annotationInstances) {
		this.annotationInstances = annotationInstances;
	}

	public Long getExternalId() {
		return externalId;
	}

	public void setExternalId(Long externalId) {
		this.externalId = externalId;
	}

	public void setProject(DBProject project) {
		this.project = project;
	}

	public DBProject getProject() {
		return project;
	}


}
