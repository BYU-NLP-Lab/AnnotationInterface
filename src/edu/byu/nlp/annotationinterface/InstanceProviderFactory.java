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

import java.util.Collection;

import edu.byu.nlp.annotationinterface.java.JavaInstanceProvider;

/**
 * @author rah67
 *
 */
public interface InstanceProviderFactory<T,A> {
	/**
	 * Constructs an instance provider given a seed set.
	 * The amount of time this method takes is assumed to be relatively
	 * unimportant since we presume that the humans won't yet be waiting
	 * for annotations. For instance, we can try to find the very "best"
	 * first instance after the "seed" sentences have been trained on
	 * without incurring additional costs.
	 * 
	 * @return an instance provider initialized with the seed set
	 */
	JavaInstanceProvider<T, A> newInstanceProvider(Collection<Instance<T>> instances);
}
