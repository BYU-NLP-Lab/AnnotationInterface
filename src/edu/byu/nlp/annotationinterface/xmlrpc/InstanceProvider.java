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
package edu.byu.nlp.annotationinterface.xmlrpc;





/**
 * Implementors are capable of providing the next instance for any given annotator.
 * 
 * @author rah67
 *
 * @param <T> type of the raw, unannotated data
 * @param <I> type of instance to be provided
 */
@Deprecated
public interface InstanceProvider {
	
	// TODO : suppose I'm given access to the CCASH database
	// what would we have to do to, say, get access to all the instance(ID)s
	// and annotations and times for this annotator given just the ID?
	
	/**
	 * Provide the next instance for the specified annotator.
	 * <br/>
	 * The call to nextInstance should either return null if there is no instance
	 * or block until there is an instance available for the annotator.
	 * The reason for not having a hasNext(Annotator) method is because classes
	 * would be responsible for guaranteeing that the time between the call
	 * to hasNext() and nextInstance(), the instance was still available.
	 * <br/>
	 * If this class is being used in conjunction with RPC,
	 * we recommend sending Instance.getId() and instance.getValue() (serialized).
	 * 
	 * @param annotator the annotator for whom an instance is being requested
	 * @return null if there are no instances; otherwise, the next instance
	 */
	InstanceReturnValue nextInstance(long annotator);

	/**
	 * Performs any initialization that might depend on models having been 
	 * initialized with the seed set. This method can be time-consuming since
	 * we presume no annotators are waiting at this time.
	 * Before this method is called, models are guaranteed to have been trained
	 * on the seed set.
	 */
	void start();

}
