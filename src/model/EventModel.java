package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class EventModel {
	//idMap is extracting the eventId as the key, enable fast search on the event
	private Map<String,Event> idMap=new HashMap<>();
	//sortedMap is extracting the calendar time as the key, enable sort time and fast search 
	//using calendar
	private SortedMap<Calendar,Event> sortedMap=new TreeMap<>();
	//class field for the other class to access the method.
	private static EventModel singletonInstance;
	//a feild value to store extra event information in order to syn all the event with 
	//the same event id when nevigating.
	 public static final String EVENT_ID_EXTRA = "eventIdExtra";
	//singleton constructor
	private EventModel(){
		//initialize the system
		for(int i=10;i>=1;i--){
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, i);
			Event event = new SimpleEvent("Event "+i,calendar);
			addEvent(event);
		}
	}
	

	
	/*
	 * Class level method enable other class calling this class data in public
	 */
	public static EventModel getInstanceOfSingletonEventModel(){
		if(singletonInstance==null){
			singletonInstance=new EventModel();
		}
		return singletonInstance;
	}
	
	/*
	 * add event to the system
	 */
	public void addEvent(Event event){
		this.idMap.put(event.getId(), event);
		this.sortedMap.put(event.getEventDateAndTime(), event);
	}
	
	/*
	 * find event from the system
	 */
	public Event findEventById(String eventId){
		return this.idMap.get(eventId);
	}
	
	/*
	 * delete event from the system
	 */
	public boolean removeEventById(String eventId){
		return this.idMap.remove(eventId)!=null
				&&this.sortedMap.remove(eventId)!=null;
	}
	
	/*
	 * edit event from the system
	 */
	public void editEventById(Calendar oldDate, Event newEvent){
		this.sortedMap.remove(oldDate);
		this.sortedMap.put(newEvent.getEventDateAndTime(), newEvent);
		
	}
	
	/*
	 * get event list from the system
	 */
	public List<Event> getEventList(){
		return new ArrayList<Event>(this.sortedMap.values());
	}
	
}
