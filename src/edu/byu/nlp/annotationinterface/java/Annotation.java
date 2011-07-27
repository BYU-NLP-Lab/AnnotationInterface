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


import edu.byu.nlp.annotationinterface.ValueCarrying;


/**
 * Represents an annotation for an instance (obtained 
 * via getAnnotationInstance().getInstance()).
 * 
 * @author rah67
 *
 * @param <A> type of annotations
 */
public interface Annotation<T, A> extends ValueCarrying<A> {

	// An ID is not necessary since CCASH never needs the ID
	//	long getId();
	
	/**
	 * @return the actual annotation
	 */
	A getValue();
	
	/**
	 * @return the AnnotationInstance for which this was an Annotation.
	 */
	JavaAnnotationInstance<T,A> getAnnotationInstance();
	
	// This easily could go here in our data model, but it can equally easily go
	// in AnnotationInstance. We chose the latter because then we can create
	// an AnnotationInstance and record who the intended recipient was
	// before sending it over the wire. This may be helpful as a "log"
	// when things get lost (or if a re-send is needed).
//	Annotator getAnnotator(); 
}
