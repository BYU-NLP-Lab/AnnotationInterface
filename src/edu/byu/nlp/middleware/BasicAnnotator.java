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
package edu.byu.nlp.middleware;

import java.util.Collection;

import edu.byu.nlp.annotationinterface.AnnotationInstance;


/**
 * @author rah67
 *
 */
public class BasicAnnotator implements Annotator {

	private final long id;
	private final String firstName;
	private final String lastName;
	
	public BasicAnnotator(final long id, final String firstName, final String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public long getId() {
		return id;
	}
	
	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public Collection<AnnotationInstance> getAnnotationInstances() {
		throw new UnsupportedOperationException();
	}

}
