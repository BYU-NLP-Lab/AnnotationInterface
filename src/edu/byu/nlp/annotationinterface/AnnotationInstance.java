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
package edu.byu.nlp.annotationinterface;

import java.util.List;

import edu.byu.nlp.middleware.Annotator;
import edu.byu.nlp.middleware.TimelineEvent;



/**
 * An instance of an Instance that is to be annotated
 * by a specific annotator with a specific pre-annotation.
 * 
 * Since CCASH is not in charge of managing the data, we
 * can only hold the IDs of instances and pre-annotations.
 * 
 * @author rah67
 */
public interface AnnotationInstance extends Identifiable {

	@Override
	long getId();
	
	/**
	 * The id of the Instance sent to the annotator.
	 * Since CCASH is not in charge of managing instances,
	 * we can't actually grab an Instance object.
	 * 
	 * @return the id of the Instance sent to the annotator
	 */
	long getInstanceId();
	
	/**
	 * The id of the model used to create the PreAnnotation
	 * sent to the annotator. If this value is null, then
	 * there wasn't a pre-annotation. If it is non-null,
	 * then it can be used in conjunction with InstanceId
	 * to look up the pre-annotation.
	 * Since CCASH is not in charge of annotations, pre-annotations
	 * or models, we cannot provide the actual PreAnnotation object.
	 * 
	 * @return the id of the PreAnnotation sent to the annotator; null if there was none
	 */
	Long getModelId();
	
	/**
	 * @return the annotator that will/did annotate this instance
	 */
	Annotator getAnnotator();
	
	// Via an inverse relationship
	List<TimelineEvent> getTimelineEvents();
	
	// Via an inverse relationship
	<T,A> AutomaticAnnotation<T,A> getPreAnnotation();
}
