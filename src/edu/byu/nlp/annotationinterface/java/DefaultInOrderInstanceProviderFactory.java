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

import java.util.Collection;

import edu.byu.nlp.annotationinterface.AutomaticAnnotator;
import edu.byu.nlp.annotationinterface.Instance;
import edu.byu.nlp.annotationinterface.InstanceProviderFactory;

/**
 * Uses the (potentially null) PreAnnotator "on-demand"
 * 
 * @author rah67
 *
 */
public class DefaultInOrderInstanceProviderFactory<T, A> implements
		InstanceProviderFactory<T, A> {
	
	private final AutomaticAnnotator<T, A> preAnnotator;
	
	public DefaultInOrderInstanceProviderFactory() {
		this(null);
	}
	
	public DefaultInOrderInstanceProviderFactory(AutomaticAnnotator<T, A> preAnnotator) {
		this.preAnnotator = preAnnotator;
	}

	/* (non-Javadoc)
	 * @see edu.byu.nlp.annotationinterface.InstanceProviderFactory#newInstanceProvider(java.util.Collection)
	 */
	@Override
	public JavaInstanceProvider<T, A> newInstanceProvider(Collection<Instance<T, A>> instances) {
		return new InOrderInstanceProvider<T, A>(instances.iterator(), preAnnotator);
	}

}
