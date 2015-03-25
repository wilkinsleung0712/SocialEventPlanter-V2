package model;

import java.util.ArrayList;
import java.util.Calendar;

public interface Event {

	public abstract String getId();

	public abstract String getTitle();

	public abstract void setTitle(String title);

	public abstract Calendar getEventDateAndTime();

	public abstract void setEventDateAndTime(Calendar eventDateAndTime);

	public abstract String getNote();

	public abstract void setNote(String note);

	public abstract ArrayList<String> getAttendees();

	public abstract void setAttendees(ArrayList<String> attendees);

	public abstract String toString();

}