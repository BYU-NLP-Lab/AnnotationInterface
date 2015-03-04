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

import java.util.List;

import edu.byu.nlp.annotationinterface.AnnotationInstance;
import edu.byu.nlp.annotationinterface.AutomaticAnnotation;
import edu.byu.nlp.annotationinterface.Instance;
import edu.byu.nlp.middleware.Annotator;
import edu.byu.nlp.middleware.TimelineEvent;

public class BasicJavaAnnotationInstance<T, A> extends AbstractIdentifiable implements JavaAnnotationInstance<T, A> {

	private final Annotator annotator;
	private final Instance<T> instance;
	private final AutomaticAnnotation<T, A> preAnnotation;
	private final List<TimelineEvent> timelineEvents;
	
	public BasicJavaAnnotationInstance(long aiId, Annotator annotator, Instance<T> instance, AutomaticAnnotation<T,A> preAnnotation, List<TimelineEvent> timelineEvents){
		super(aiId);
		this.annotator = annotator;
		this.instance = instance;
		this.preAnnotation = preAnnotation;
		this.timelineEvents = timelineEvents;
	}
	
	
	@Override
	public Annotator getAnnotator() {
		return annotator;
	}

	@Override
	public Instance<T> getInstance() {
		return instance;
	}

	@Override
	public List<TimelineEvent> getTimelineEvents() {
		return timelineEvents;
	}


	@Override
	public AutomaticAnnotation<T, A> getAutomaticAnnotation() {
		return preAnnotation;
	}


	@Override
	public AnnotationInstance asAnnotationInstance() {
		return new AnnotationInstance() {

			@Override
			public Annotator getAnnotator() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getId() {
				return BasicJavaAnnotationInstance.this.getId();
			}

			@Override
			public long getInstanceId() {
				return getInstance().getId();
			}

			@Override
			public Long getModelId() {
				AutomaticAnnotation<T, A> pa = getPreAnnotation();
				if (pa == null) return null;
				return pa.getModel().getId();
			}

			// FIXME : is there a way to fix these generics?
			@SuppressWarnings("unchecked")
			@Override
			public <X, Y> AutomaticAnnotation<X,Y> getPreAnnotation() {
				return (AutomaticAnnotation<X, Y>) preAnnotation;
			}

			@Override
			public List<TimelineEvent> getTimelineEvents() {
				return BasicJavaAnnotationInstance.this.getTimelineEvents();
			}
			
		};
	}


	@Override
	public String toString() {
		return "BasicJavaAnnotationInstance [annotator=" + annotator + ", id="
				+ getId() + ", instance=" + instance + ", preAnnotation="
				+ preAnnotation + ", timelineEvents=" + timelineEvents + "]";
	}

}
