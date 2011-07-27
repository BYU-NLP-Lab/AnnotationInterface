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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ai_automatic_annotation")
@NamedQueries({
	@NamedQuery(name="DBAutomaticAnnotation.findall",query="from DBAutomaticAnnotation")
})
public class DBAutomaticAnnotation implements Serializable, HasId {
	private static final long serialVersionUID = 1L;

	@Id 
	@Column(name="automatic_annotation_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	
	@OneToOne
	@JoinColumn(name="annotation_instance")
	private DBAnnotationInstance annotationInstance;
	
	@Transient
	private String serializedValue;


	public DBAutomaticAnnotation(){
		
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DBAnnotationInstance getAnnotationInstance() {
		return annotationInstance;
	}

	/**
	 * should be called only by DBAnnotationInstance
	 * @param annotationInstance
	 */
	protected void setAnnotationInstance(DBAnnotationInstance annotationInstance) {
		this.annotationInstance = annotationInstance;
	}


	public String getSerializedValue() {
		return serializedValue;
	}


	public void setSerializedValue(String serializedValue) {
		this.serializedValue = serializedValue;
	}

}
