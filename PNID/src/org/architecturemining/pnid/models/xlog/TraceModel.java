package org.architecturemining.pnid.models.xlog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XTrace;

/**
 * @author J.J. Ree
 *
 * Model for a trace  containing a list of events.
 * 
 */
@SuppressWarnings("serial")
public class TraceModel extends ArrayList<EventModel> {
	
	private List<EventModel> events;
	
	public TraceModel(XTrace trace) {
		super(convertXEventsToEventModels(trace));
	}
	
	public List<EventModel> getEvents() {
		return events;
	}

	public void setEvents(List<EventModel> events) {
		this.events = events;
	}
	
	private static List<EventModel> convertXEventsToEventModels(XTrace trace) {
		List<EventModel> events = new ArrayList<EventModel>();
		
		for(XEvent event : trace) {
			events.add(new EventModel(event));
		}
		
		return events;
	}
}
