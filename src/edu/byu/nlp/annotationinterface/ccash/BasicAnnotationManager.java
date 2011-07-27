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
package edu.byu.nlp.annotationinterface.ccash;

import java.util.List;

import edu.byu.nlp.annotationinterface.java.Annotation;
import edu.byu.nlp.annotationinterface.java.JavaAnnotationObserver;
import edu.byu.nlp.annotationinterface.java.JavaReturnValue;

public class BasicAnnotationManager<T,A> implements AnnotationManager<T,A> {

	private JavaAnnotationObserver<T, A> annotationObserver;

	public BasicAnnotationManager(JavaAnnotationObserver<T, A> annotationObserver){
		this.annotationObserver=annotationObserver;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JavaReturnValue<T, A> nextInstance(long annotator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void annotationReceived(Annotation<T, A> annotation) {
		annotationObserver.annotationReceived(annotation);
	}

	@Override
	public void annotationsReceived(List<Annotation<T, A>> annotations) {
		annotationObserver.annotationsReceived(annotations);
	}

}
