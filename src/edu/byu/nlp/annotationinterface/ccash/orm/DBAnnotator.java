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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ai_annotator")
@NamedQueries({
	@NamedQuery(name="DBAnnotator.findByLoginnameAndPassword", query="from DBAnnotator as u where u.loginName = :login_name and u.password = :password"),
	@NamedQuery(name="DBAnnotator.findAll",query="from DBAnnotator")
})
@Inheritance(strategy=InheritanceType.JOINED) 
public class DBAnnotator implements Serializable, HasId {
	
	private static final long serialVersionUID = 1L;

	@Id 
	@Column(name="annotator_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	

	@OneToMany(mappedBy="annotator")
	private Collection<DBAnnotationInstance> annotationInstances = Collections.synchronizedCollection(new ArrayList<DBAnnotationInstance>());
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;

	@Column(name="login_name", nullable=false, unique=true)
	private String loginName;
	
	@Column(name="password", nullable=false)
	private String password;
	
	public DBAnnotator(){
		
	}
	
	public DBAnnotator(long id, String firstName, String lastName, Collection<DBAnnotationInstance> annotationInstances){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.annotationInstances = annotationInstances;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void addAnnotationInstance(DBAnnotationInstance ai){
		if (ai!=null){
			annotationInstances.add(ai);
			ai.setAnnotator(this);
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

}
