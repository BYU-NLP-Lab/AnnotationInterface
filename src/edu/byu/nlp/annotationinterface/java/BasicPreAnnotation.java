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

import edu.byu.nlp.annotationinterface.AutomaticAnnotation;
import edu.byu.nlp.annotationinterface.AutomaticAnnotator;
import edu.byu.nlp.annotationinterface.Instance;

// TODO : shouldn't this be renamed to BasicAutomaticAnnotation to match its interface?
public class BasicPreAnnotation<T, A> implements AutomaticAnnotation<T, A> {

	private final Instance<T> instance;
	private final AutomaticAnnotator<T, A> model;
	private final A value;
	
	public BasicPreAnnotation(Instance<T> instance, AutomaticAnnotator<T, A> model, A value) {
		this.instance = instance;
		this.model = model;
		this.value = value;
	}
	
	@Override
	public Instance<T> getInstance() {
		return instance;
	}
	
	@Override
	public AutomaticAnnotator<T, A> getModel() {
		return model;
	}
	
	@Override
	public A getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "BasicPreAnnotation [instance=" + instance + ", model=" + model
				+ ", value=" + value + "]";
	}

}
