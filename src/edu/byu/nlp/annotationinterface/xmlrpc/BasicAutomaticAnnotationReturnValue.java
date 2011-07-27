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
package edu.byu.nlp.annotationinterface.xmlrpc;



@Deprecated
public class BasicAutomaticAnnotationReturnValue implements AutomaticAnnotationReturnValue {

	private static final long serialVersionUID = 1L;

	private long automaticAnnotatorId;
	
	private String automaticAnnotationValue;
	
	
	public BasicAutomaticAnnotationReturnValue(){
	}
	
	public BasicAutomaticAnnotationReturnValue(
			long automaticAnnotatorId, String automaticAnnotationValue){
		this.automaticAnnotatorId = automaticAnnotatorId;
		this.automaticAnnotationValue = automaticAnnotationValue;
	}
	
	@Override
	public long getAutomaticAnnotatorId() {
		return automaticAnnotatorId;
	}

	@Override
	public String getAutomaticAnnotationValue() {
		return automaticAnnotationValue;
	}


	public void setAutomaticAnnotatorId(long automaticAnnotatorId) {
		this.automaticAnnotatorId = automaticAnnotatorId;
	}

	public void setAutomaticAnnotationValue(String automaticAnnotationValue) {
		this.automaticAnnotationValue = automaticAnnotationValue;
	}


}
