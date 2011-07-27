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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.byu.nlp.annotationinterface.ccash.orm.DBTimelineEvent;
import edu.byu.nlp.middleware.TimelineEvent;


/**
 * Convenience class for working with lists of TimelineEvent 
 * @author plf1
 *
 */
public class Timeline {

	/**
	 * startTokens and endTokens are parallel lists that refer whose corresponding elements 
	 * open and close time periods of interest.  These elements must be 
	 * @param events
	 * @param startTokens
	 * @param stopTokens
	 * @return
	 */
	public static Double[] getDurations(List<TimelineEvent> events,
			String[] startTokens, 
			String[] stopTokens) throws IllegalArgumentException{

		Map<String,Integer> eventIndices = calculateIndices(startTokens, stopTokens);
		
		return getDurations(events, startTokens, stopTokens, eventIndices);
	}

	/**
	 * For efficiency (if this is being called multiple times with the same events,
	 * allow precalculated event indices
	 * @param events
	 * @param startTokens
	 * @param stopTokens
	 * @param precalculatedEventIndices
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static Double[] getDurations(List<TimelineEvent> events,
			String[] startTokens, 
			String[] stopTokens, 
			Map<String,Integer> eventIndices) throws IllegalArgumentException{

		if (startTokens.length != stopTokens.length)
			throw new IllegalArgumentException("startTokens and endTokens are of different lengths. They must be parallel lists.");
		if (startTokens.length == 0)
			return null;
		
		TimelineEvent[] startEvents = new TimelineEvent[startTokens.length];
		Double[] differences = new Double[startTokens.length];
		for (int i=0; i<differences.length; i++)
			differences[i] = 0.;
		
		for (TimelineEvent event: events){
			String eventName = event.getEventName();
			Integer index = eventIndices.get(eventName);
			if (index == null)
				continue;
			
			// start
			if (eventName.equals(startTokens[index])){
				startEvents[index] = event;
			}
			// end
			else if (eventName.equals(stopTokens[index])){
				if (startEvents[index] == null){
					throw new IllegalArgumentException("the stop token \""+stopTokens[index]+"\" appeared before its corresponding start token \""+startTokens[index]+"\".");
				}
				differences[index] = differenceInSeconds(startEvents[index], event);
			}
		}
		
		
		return differences;
	}
	
	/**
	 * Calculate a reverse mapping (from string to index in either array)
	 * @param startTokens
	 * @param endTokens
	 * @return
	 */
	public static Map<String,Integer> calculateIndices(String[] startTokens, String[] stopTokens){
		Map<String,Integer> eventIndices = new HashMap<String,Integer>();
		for (int i=0; i<startTokens.length; i++){
			eventIndices.put(startTokens[i], i);
			eventIndices.put(stopTokens[i], i);
		}
		return eventIndices;
	}
	
	/**
	 * endEvent must be later than startEvent.
	 * TODO: look further into java's weird time api and make sure this really does what we want
	 * @param startEvent
	 * @param endEvent
	 * @return
	 */
	public static double differenceInSeconds(TimelineEvent start, TimelineEvent end){
		if (start==null || end==null)
			throw new IllegalArgumentException();
		return differenceInSeconds(start.getTimestamp(), end.getTimestamp());
		
	}
	
	public static double differenceInSeconds(DBTimelineEvent start, DBTimelineEvent end){
		if (start==null || end==null)
			throw new IllegalArgumentException();
		return differenceInSeconds(start.getTimestamp(), end.getTimestamp());
	}

	public static double differenceInSeconds(Timestamp start, Timestamp end){
		double diffInMillis = end.getTime() - start.getTime();
		return diffInMillis*0.001;
	}
	
	
//	/**
//	 * Generate a mapping from event names to their relevant summary statistics--either counts in the
//	 * case of regular TimelineEvents, or else durations in the case of SpanningTimelineEvents.
//	 * @param eventLists
//	 * @return
//	 */
//	public static Map<String,Number> getStatistics(List<DBTimelineEvent> eventList){
//		Map<String,Number> result = new HashMap<String,Number>();
//		addStatisticsToExistingMap(eventList,result);
//		return result;
//	}

	//FIXME: this shouldn't be necesssary after moving to the new timeline event types
//	public static void addStatisticsToExistingMap(List<DBTimelineEvent> eventList, Map<String,Number> map){
//		final String SPANNING_SUFFIX = "_DURATION";
//		final String COUNT_SUFFIX = "_COUNT";
//		final String OVERLAPPING_SEGMENTS = "OVERLAPPING_SPAN_ERRORS_";
//		Map<String,DBSpanningTimelineEvent> spanningEventStarts = new HashMap<String, DBSpanningTimelineEvent>();
//		
//		for (DBTimelineEvent event: eventList){
//			
//			// record the DURATION of time spanning events
//			if (event instanceof DBSpanningTimelineEvent){
//				DBSpanningTimelineEvent spanEvent = (DBSpanningTimelineEvent) event;
//				String spanEventName = spanEvent.getEventName()+SPANNING_SUFFIX; 
//				if (spanEvent.isStartOfSpan()){
//					// start of span
//					if (spanningEventStarts.get(spanEventName)!=null)
//						// record an error if this start event is overlapping with another
//						incrementCount(OVERLAPPING_SEGMENTS+spanEventName, map, 1);
//					else
//						// record event
//						spanningEventStarts.put(spanEventName, spanEvent);
//				}
//				else{
//					// end of span
//					// increment duration and remove start event from record
//					DBSpanningTimelineEvent spanStartEvent = spanningEventStarts.remove(spanEventName);
//					if (spanStartEvent==null)
//						// record an error if this end event is overlapping with another
//						incrementCount(OVERLAPPING_SEGMENTS+spanEventName, map, 1);
//					else
//						// record event
//						incrementCount(spanEventName, map, differenceInSeconds(spanStartEvent, spanEvent));
//				}
//			}
//			
//			// COUNT the event if it is normal or if it is the start of a spanning pair
//			if (!(event instanceof DBSpanningTimelineEvent) ||
//					((DBSpanningTimelineEvent)event).isStartOfSpan()){
//				String eventName = event.getEventName()+COUNT_SUFFIX; 
//				incrementCount(eventName, map, 1);
//			}
//			
//		}
//	}
//	
//	private static void incrementCount(String key, Map<String,Number> map, Number incrementValue){
//		assert (incrementValue!=null);
//		
//		// Get current count
//		Number currentCount = map.get(key);
//		if (currentCount==null)
//			currentCount = 0;
//		
//		// Increment
//		if (currentCount instanceof Integer && incrementValue instanceof Integer)
//			map.put(key, currentCount.intValue()+incrementValue.intValue());
//		else
//			map.put(key, currentCount.doubleValue()+incrementValue.doubleValue());
//	}

	
}
