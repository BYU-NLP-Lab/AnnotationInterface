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


/**
 * To obtain timing information, use getAnnotationInstance().getTimeline();
 * 
 * 
 * @author rah67
 *
 */
public class BasicAnnotation<T, A> implements Annotation<T,A> {

	private final A annotation;
	private final JavaAnnotationInstance<T,A> ai;
	
	public BasicAnnotation(A annotation, JavaAnnotationInstance<T,A> ai) {
		this.annotation = annotation;
		this.ai = ai;
	}

	@Override
	public JavaAnnotationInstance<T, A> getAnnotationInstance() {
		return ai;
	}

	@Override
	public A getValue() {
		return annotation;
	}

	@Override
	public String toString() {
		return "BasicAnnotation [ai=" + ai + ", annotation=" + annotation + "]";
	}

}
