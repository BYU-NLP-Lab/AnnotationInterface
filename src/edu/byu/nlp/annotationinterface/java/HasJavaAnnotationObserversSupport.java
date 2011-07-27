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
import java.util.Vector;


public class HasJavaAnnotationObserversSupport<T, A> implements HasJavaAnnotationObservers<T, A> {


	protected Vector<JavaAnnotationObserver<T, A>> observers;
	
	public HasJavaAnnotationObserversSupport(){
		observers = new Vector<JavaAnnotationObserver<T,A>>();
	}
	
	@Override
	public void addJavaAnnotationObserver(JavaAnnotationObserver<T,A> JavaAnnotationObserver) {
		observers.add(JavaAnnotationObserver);
	}


	@Override
	public void removeJavaAnnotationObserver(JavaAnnotationObserver<T, A> JavaAnnotationObserver) {
		observers.remove(JavaAnnotationObserver);
	}
	
	protected void notifyObservers(List<Annotation<T, A>> annotations) {
		for( JavaAnnotationObserver<T,A> jao : observers.toArray(new JavaAnnotationObserver[0])) {
			jao.annotationsReceived(annotations);
		}
	}
	
	protected void notifyObservers(Annotation<T, A> annotation) {
		for( JavaAnnotationObserver<T,A> jao : observers.toArray(new JavaAnnotationObserver[0])) {
			jao.annotationReceived(annotation);
		}
	}
	
	protected void flushObservers() {
		for( JavaAnnotationObserver<T,A> jao : observers.toArray(new JavaAnnotationObserver[0])) {
			jao.flush();
		}
	}
}
