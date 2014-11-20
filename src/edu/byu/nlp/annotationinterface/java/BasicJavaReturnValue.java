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
import edu.byu.nlp.annotationinterface.Instance;

public class BasicJavaReturnValue<T, A> implements JavaReturnValue<T, A> {

	private Instance<T,A> instance;
	private AutomaticAnnotation<T,A> preAnnotation;

	public BasicJavaReturnValue(Instance<T,A> instance, AutomaticAnnotation<T,A> preAnnotation){
		this.instance = instance;
		this.preAnnotation = preAnnotation;
	}
	
	@Override
	public Instance<T, A> getInstance() {
		return instance;
	}

	@Override
	public AutomaticAnnotation<T, A> getPreAnnotation() {
		return preAnnotation;
	}

	@Override
	public String toString() {
		return "BasicReturnValue [instance=" + instance + ", preAnnotation="
				+ preAnnotation + "]";
	}

}
