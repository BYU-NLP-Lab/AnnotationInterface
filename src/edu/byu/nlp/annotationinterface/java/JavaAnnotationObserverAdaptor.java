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

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import edu.byu.nlp.annotationinterface.xmlrpc.AnnotationObserver;


/**
 * Uses the factory method pattern to turn strange XML-RPC into more objects
 * that are used in conjunction with the more convenient JavaAnnotationObserver.
 * 
 * @author rah67
 *
 */
public abstract class JavaAnnotationObserverAdaptor<T, A>
		implements AnnotationObserver {

	// delegate
	private final JavaAnnotationObserver<T,A> jObserver;
	

	private final class AnnotationList extends
			AbstractList<Annotation<T, A>> {
		private final long[] annotatorId;
		private final long[] aiId;
		private final List<A> annotation;
		private final long[] instanceId;

		private AnnotationList(long[] annotatorId, long[] aiId,
				List<A> annotation, long[] instanceId) {
			this.annotatorId = annotatorId;
			this.aiId = aiId;
			this.annotation = annotation;
			this.instanceId = instanceId;
		}

		@Override
		public Annotation<T, A> get(int index) {
			return newAnnotation(aiId[index], instanceId[index], annotatorId[index], annotation.get(index));
		}

		/*
		@Override
		public Iterator<Annotation<T, A>> iterator() {
			return new Iterator<Annotation<T,A>>() {
				
				int i = 0;
				
				@Override
				public boolean hasNext() {
					return i < aiId.length;
				}

				@Override
				public Annotation<T, A> next() {
					final int j = i++;
					return newAnnotation(aiId[j], instanceId[j], annotatorId[j], annotation[j]);
				}

				@Override
				public void remove() {
					throw new UnsupportedOperationException();
				}
			};
		}
*/
		@Override
		public int size() {
			return aiId.length;
		}
	}

	
	public JavaAnnotationObserverAdaptor(JavaAnnotationObserver<T, A> jObserver) {
		this.jObserver = jObserver;
	}

	
	/* (non-Javadoc)
	 * @see edu.byu.nlp.annotationinterface.AnnotationObserver#annotationReceived(long, long, long, java.io.Serializable)
	 */
	@Override
	public void annotationReceived(long aiId, long instanceId,
			long annotatorId, long automaticAnnotatorId,
			long[] eventTimestamps, Object[] eventTypes, String serializedValue) {

		// deserialize annotation
		A value = deserializeAnnotation(serializedValue);
		
		jObserver.annotationReceived(newAnnotation(aiId, instanceId, annotatorId, value));
	}


	
	/* (non-Javadoc)
	 * @see edu.byu.nlp.annotationinterface.AnnotationObserver#annotationsReceived(long[], long[], long[], A[])
	 */
	@Override
	public void annotationsReceived(long[] aiId, long[] instanceId,
			long[] annotatorId, long[] automaticAnnotatorId,
			long[][] eventTimestamps, Object[][] eventTypes, String[] serializedValue) {
		
		if (aiId.length != instanceId.length || instanceId.length != annotatorId.length
				|| annotatorId.length != serializedValue.length)
			throw new IllegalArgumentException("arrays are of differing lengths");
		
		// deserialize annotations
		List<A> annotationValues = deserializeAnnotations(serializedValue);
		
		jObserver.annotationsReceived(new AnnotationList(annotatorId, aiId, annotationValues, instanceId));
	}
	
	protected abstract Annotation<T,A> newAnnotation(long aiId, long instanceId, long annotatorId, A annotation);
	
	protected abstract A deserializeAnnotation(String serializedValue);
	
	public List<A> deserializeAnnotations(String[] serializedValues){
		List<A> values = new ArrayList<A>(serializedValues.length);
		for (String serializedValue: serializedValues){
			values.add(deserializeAnnotation(serializedValue));
		}
		return values;
	}
	
}

