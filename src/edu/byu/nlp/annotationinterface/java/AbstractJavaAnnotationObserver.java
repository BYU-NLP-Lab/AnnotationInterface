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

/**
 * A convenient abstract class that implements the bulk method 
 * edu.byu.nlp.annotationinterface.java.JavaAnnotationObserver#annotationReceived(java.util.Collection)
 * by iterating over the collection and invoking
 * edu.byu.nlp.annotationinterface.java.JavaAnnotationObserver#annotationReceived(edu.byu.nlp.annotationinterface.Annotation)
 * each time.
 * <br/>
 * If you have a smarter way of dealing with bulk annotations, then there
 * is no need to extend this abstract class.
 * 
 * @author robbie
 *
 */
public abstract class AbstractJavaAnnotationObserver<T, A>
		implements JavaAnnotationObserver<T, A> {

	/* (non-Javadoc)
	 * @see edu.byu.nlp.annotationinterface.java.JavaAnnotationObserver#annotationReceived(java.util.Collection)
	 */
	@Override
	public void annotationsReceived(List<Annotation<T, A>> annotations) {
		for( Annotation<T,A> ann : annotations ) {
			annotationReceived(ann);
		}
	}

}
