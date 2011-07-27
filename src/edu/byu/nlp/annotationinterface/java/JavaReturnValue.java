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

import edu.byu.nlp.annotationinterface.AutomaticAnnotation;
import edu.byu.nlp.annotationinterface.Instance;


/**
 * The Return value for InstanceProvider, basically a Pair
 * of Instance and (possibly null) PreAnnotation.
 * note that getInstance().equals(getPreAnnotation.getInstance()) MUST
 * be true if there is a PreAnnotation.
 * 
 * @author rah67
 *
 * @param <T> the type of the instance (actual, raw data)
 * @param <A> the type of the pre-annotation
 */
//TODO : Rename
public interface JavaReturnValue<T, A> {
	/**
	 * @return the instance to be sent to the client.
	 */
	Instance<T,A> getInstance();
	
	/**
	 * @return the pre-annotation the client should use for the instance.
	 */
	AutomaticAnnotation<T,A> getPreAnnotation();
}
