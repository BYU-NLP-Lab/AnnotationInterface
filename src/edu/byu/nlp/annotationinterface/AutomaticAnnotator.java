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

import edu.byu.nlp.annotationinterface.java.Annotation;


/**
 * Allows the machine to suggest annotations
 * 
 * @author rah67
 *
 */
public interface AutomaticAnnotator<T,A> extends Identifiable {
	
	@Override
	long getId();
	
	AutomaticAnnotation<T,A> annotate(Instance<T> instance);
	
	List<Annotation<T,A>> getOrderedAnnotations();

	long getTrainTime();
}
