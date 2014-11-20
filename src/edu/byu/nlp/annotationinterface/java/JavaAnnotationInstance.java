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
/**
 * 
 */
package edu.byu.nlp.annotationinterface.java;

import java.util.List;

import edu.byu.nlp.annotationinterface.AnnotationInstance;
import edu.byu.nlp.annotationinterface.AutomaticAnnotation;
import edu.byu.nlp.annotationinterface.Identifiable;
import edu.byu.nlp.annotationinterface.Instance;
import edu.byu.nlp.middleware.Annotator;
import edu.byu.nlp.middleware.TimelineEvent;

/**
 * @author rah67
 *
 * @param <T> the type of raw instance (e.g. List<String> for sentences)
 * @param <A> the type of raw annotations (e.g. List<String> for tags)
 * @param <M> the type of model that produces PreAnnotations, Void if there are none 
 */
// TODO : should this extend AnnotationInstance?
// should it officially "have" an AnnotationInstance (like via a get)	
public interface JavaAnnotationInstance<T, A>
	extends Identifiable {
	
	@Override
	long getId();
	
	/**
	 * @return the Instance that was annotated 
	 */
	Instance<T,A> getInstance();
	
	/**
	 * The PreAnnotation provided at the time this instance was served to the client
	 * 
	 * 
	 * @return the PreAnnotation provided at the time this instance was served to the client
	 */
	AutomaticAnnotation<T,A> getAutomaticAnnotation();
	
	/**
	 * @return the annotator that will/did annotate this instance
	 */
	Annotator getAnnotator();

	/**
	 * @return a view of this instance as a standard annotation instance
	 */
	AnnotationInstance asAnnotationInstance();
	
	// Via a relationship (i.e. could require a query)
	List<TimelineEvent> getTimelineEvents();
	
}
