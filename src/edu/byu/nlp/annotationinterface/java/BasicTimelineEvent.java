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

import java.sql.Timestamp;

import edu.byu.nlp.annotationinterface.AnnotationInstance;
import edu.byu.nlp.middleware.TimelineEvent;

public class BasicTimelineEvent implements TimelineEvent {

	private AnnotationInstance annotationInstance;
	private Timestamp timestamp;
	private int order;
	private String eventName;

	
	public BasicTimelineEvent(AnnotationInstance annotationInstance, Timestamp timestamp, int order, String eventName){
		this.annotationInstance = annotationInstance;
		this.timestamp = timestamp;
		this.order = order;
		this.eventName = eventName;
	}
	
	@Override
	public AnnotationInstance getAnnotationInstance() {
		return annotationInstance;
	}

	@Override
	public String getEventName() {
		return eventName;
	}

	@Override
	public int getOrdering() {
		return order;
	}

	@Override
	public Timestamp getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return "BasicTimelineEvent [annotationInstance=" + annotationInstance
				+ ", eventName=" + eventName + ", order=" + order
				+ ", timestamp=" + timestamp + "]";
	}

}
