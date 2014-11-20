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
 * An instance in the machine learning sense, e.g. sentence, document, feature vector, etc.
 * An instance may be associated with one or more annotations.  
 * 
 * @param <T> type of the instances (raw, unannotated data)
 * @param <A> type of annotations
 * 
 * @author rah67
 *
 */
public interface Instance<T>
	extends Identifiable, ValueCarrying<T> {

	// TODO : could remove these first two
	
	@Override
	long getId();
	
	/**
	 * @return the actual instance
	 */
	@Override
	T getValue();

	// via (inverse) relationships, i.e. query needed
	//Collection<AnnotationInstance> getAnnotationInstances();
	//<M extends Identifiable> Collection<PreAnnotation<T, A, M>> getPreAnnotations();
}
