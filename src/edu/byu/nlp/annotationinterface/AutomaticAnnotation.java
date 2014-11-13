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



/**
 * A pre-annotation is a machine generated annotation
 * to be corrected by a human annotator for a specific
 * instance.
 * 
 * A pre-annotation is uniquely defined by the instance
 * for which it was made and the model that produced it. 
 * In the entity-relatioship model, this is can be seen as
 * a weak entity that depends on Instance.
 *  
 * @author rah67
 *
 * @param <T> type of the instances (raw, unannotated data) 
 * @param <A> type of annotations
 * @param <M> type of model that produced this pre-annotation
 */
public interface AutomaticAnnotation<T, A> extends ValueCarrying<A> {
	
	// The Primary Key are these two methods

	/**
	 * @return the instance for which this is a pre-annotation
	 */
	Instance<T> getInstance();
	
	/**
	 * @return the model used to produce this pre-annotation
	 */
	AutomaticAnnotator<T,A> getModel();
	
	/**
	 * @return the actual pre-annotation
	 */
	A getValue();

	// via (inverse) relation
	/**
	 * The AnnotationInstances for which this pre-annotation was used.
	 * This may require a query.
	 * 
	 * @return the AnnotationInstances for which this pre-annotation was used
	 */
//	Collection<AnnotationInstance> getAnnotationInstances();
}
