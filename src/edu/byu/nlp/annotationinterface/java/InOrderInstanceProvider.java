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

import java.util.Iterator;

import edu.byu.nlp.annotationinterface.AutomaticAnnotation;
import edu.byu.nlp.annotationinterface.AutomaticAnnotator;
import edu.byu.nlp.annotationinterface.Instance;
import edu.byu.nlp.annotationinterface.NewPreAnnotatorListener;


/**
 * @author rah67
 *
 */
public class InOrderInstanceProvider<T,A> implements JavaInstanceProvider<T,A>, NewPreAnnotatorListener<T, A> {
	
	private final Iterator<Instance<T>> it;
	private AutomaticAnnotator<T,A> preAnnotator;
	
	/**
	 * No pre-annotator.
	 * 
	 * @param iterator
	 */
	public InOrderInstanceProvider(Iterator<Instance<T>> iterator) {
		this(iterator, null);
	}
	
	public InOrderInstanceProvider(Iterator<Instance<T>> iterator,
			AutomaticAnnotator<T, A> preAnnotator) {
		this.it = iterator;
		this.preAnnotator = preAnnotator;
	}

	/* (non-Javadoc)
	 * @see edu.byu.nlp.alfa.al.InstanceProvider#nextInstance(long)
	 */
	@Override
	public JavaReturnValue<T,A> nextInstance(long annotator) {

		// Ignore the annotator
		
		// Is there any data to dispense?
		if (it.hasNext()) {
			Instance<T> instance = it.next();
			AutomaticAnnotation<T, A> annotation = null;
			
			// avoids synchronization issues
			AutomaticAnnotator<T, A> localPA = preAnnotator;
			
			if (localPA != null) {
				annotation = localPA.annotate(instance);
			}
			return new BasicJavaReturnValue<T,A>(instance, annotation);
		}
		return null;
	}

	@Override
	public void start() { /* no-op */ }

	@Override
	public void newPreAnnotator(AutomaticAnnotator<T, A> preAnnotator) {
		this.preAnnotator = preAnnotator;
	}

}
