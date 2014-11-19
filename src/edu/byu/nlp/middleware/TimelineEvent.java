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

import java.sql.Timestamp;

import edu.byu.nlp.annotationinterface.AnnotationInstance;

/**
 * An individual event in a timeline associated with an AnnotationInstance.
 * JPA has some provisions for storing a list, but I don't believe
 * you can store additional attributes (in this case, Timestamp)
 * without explicitly adding another class like this.
 * 
 * @author rah67
 * @author plf1
 *
 */
public interface TimelineEvent {

	/**
	 * The name of timeline events that indicate the time that an 
	 * annotator STARTS being visually presented with an annotationinstance.
	 */
	public static final String ANNOTATOR_GAIN_FOCUS = "ANNOTATOR_GAIN_FOCUS";

	/**
	 * The name of timeline events that indicate the time that an 
	 * annotator STOPS being visually presented with an annotationinstance.
	 */
	public static final String ANNOTATOR_LOSE_FOCUS = "ANNOTATOR_LOSE_FOCUS";

	
	// In a weak entity relationship the PK consists of
	// the identifying entity's PK and the weak entity's PK
	
	// Strong entity's identifier
	AnnotationInstance getAnnotationInstance();
	
	// Weak entity's identifier
	int getOrdering(); // use "ordering" here because "order" is a reserved word in mysql
	
	Timestamp getTimestamp();
	
	// If we want to constrain the list of events
	// we need another entity Event with primary key EventName
	String getEventName();
	
}
