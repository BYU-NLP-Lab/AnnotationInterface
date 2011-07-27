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

import java.util.List;

import edu.byu.nlp.annotationinterface.AutomaticAnnotator;

public abstract class AbstractAutomaticAnnotator<T, A> implements AutomaticAnnotator<T, A>{

	private long id;
	private List<Annotation<T, A>> orderedAnnotations;
	private long trainTime;
	
	public AbstractAutomaticAnnotator(long id, List<Annotation<T, A>> orderedAnnotations, long trainTime){
		this.setId(id);
		this.setOrderedAnnotations(orderedAnnotations);
		this.setTrainTime(trainTime);
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return id;
	}

	public void setOrderedAnnotations(List<Annotation<T, A>> orderedAnnotations) {
		this.orderedAnnotations = orderedAnnotations;
	}

	@Override
	public List<Annotation<T, A>> getOrderedAnnotations() {
		return orderedAnnotations;
	}

	public void setTrainTime(long trainTime) {
		this.trainTime = trainTime;
	}

	@Override
	public long getTrainTime() {
		return trainTime;
	}
	
}
