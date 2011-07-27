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
 * 
 * @author rah67
 *
 * @param <A> type of annotations
*/
@Deprecated
public interface AnnotationObserver {
	
	/**
	 * All Object[] are really String[], but that is not directly representable by xmlrpc
	 */
	void annotationReceived(long aiId, long instanceId, long annotatorId, long automaticAnnotatorId, long[] eventTimestamps, Object[] eventTypes, String annotation);
	
	void annotationsReceived(long[] aiId, long[] instanceId, long[] annotatorId, long[] automaticAnnotatorId, long[][] eventTimestamps, Object[][] eventTypes, String[] annotation);
	
}
