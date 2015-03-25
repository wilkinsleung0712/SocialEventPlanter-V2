package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public abstract class AbstractEvent implements Event {

	private String id;
	private String title;
	//location of the event
	//private String location;
	
	//event date&time
	private Calendar eventDateAndTime;
	//event note
	private String note;
	//a list of event attendees of an event
	private ArrayList<String> attendees;
	
	public AbstractEvent(){
		super();
		this.id=UUID.randomUUID().toString();
		attendees = new ArrayList<>();
	}

	public AbstractEvent(String title, Calendar eventDateAndTime) {
		this();//call the default constructor to generate the UUID for each event
		this.title = title;
		this.eventDateAndTime = eventDateAndTime;
		
	}

	/* (non-Javadoc)
	 * @see Model.Event#getId()
	 */
	@Override
	public String getId() {
		return id;
	}

	
	/* (non-Javadoc)
	 * @see Model.Event#getTitle()
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/* (non-Javadoc)
	 * @see Model.Event#setTitle(java.lang.String)
	 */
	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	/* (non-Javadoc)
	 * @see Model.Event#getEventDateAndTime()
	 */
	@Override
	public Calendar getEventDateAndTime() {
		return eventDateAndTime;
	}

	/* (non-Javadoc)
	 * @see Model.Event#setEventDateAndTime(java.util.Calendar)
	 */
	@Override
	public void setEventDateAndTime(Calendar eventDateAndTime) {
		this.eventDateAndTime = eventDateAndTime;
	}

	/* (non-Javadoc)
	 * @see Model.Event#getNote()
	 */
	@Override
	public String getNote() {
		return note;
	}

	/* (non-Javadoc)
	 * @see Model.Event#setNote(java.lang.String)
	 */
	@Override
	public void setNote(String note) {
		this.note = note;
	}

	/* (non-Javadoc)
	 * @see Model.Event#getAttendees()
	 */
	@Override
	public ArrayList<String> getAttendees() {
		return attendees;
	}

	/* (non-Javadoc)
	 * @see Model.Event#setAttendees(java.util.ArrayList)
	 */
	@Override
	public void setAttendees(ArrayList<String> attendees) {
		this.attendees = attendees;
	}

	/* (non-Javadoc)
	 * @see Model.Event#toString()
	 */
	@Override
	public String toString() {
		return "AbstractEvent [id=" + id + ", title=" + title
				+ ", eventDateAndTime=" + eventDateAndTime + ", note=" + note
				+ ", attendees=" + attendees + "]";
	}
	
	
	
}
