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
package edu.byu.nlp.annotationinterface.java;

import edu.byu.nlp.annotationinterface.Instance;

public class BasicInstance<T, A> implements Instance<T,A>{

//	private Collection<AnnotationInstance> annotationInstances;
	private final long id;
	private final T value;
//	private Collection<PreAnnotation<T, A, Identifiable>> preAnnotations;
	
	public BasicInstance(
			long id, 
			T value 
//			Collection<AnnotationInstance> annotationInstances, 
//			Collection<PreAnnotation<T, A, Identifiable>> preAnnotations
			){
		this.id = id;
		this.value = value;
//		this.annotationInstances = annotationInstances;
//		this.preAnnotations = preAnnotations;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "BasicInstance [id=" + id + ", value=" + value + "]";
	}

	/*
	@Override
	public Collection<AnnotationInstance> getAnnotationInstances() {
		return annotationInstances;
	}

	@Override
	public Collection<PreAnnotation<T, A, Identifiable>> getPreAnnotations() {
		return preAnnotations;
	}
*/

}
