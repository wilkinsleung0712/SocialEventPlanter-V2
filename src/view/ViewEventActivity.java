package view;

import model.Event;
import model.EventModel;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.socialeventplaner_v2.R;

public class ViewEventActivity extends Activity	{
private Event currentEvent;
private String currentEventId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.view_event_activity_layout);
		//receive the event id from the intent
		String eventId = this.getIntent().getStringExtra(EventModel.EVENT_ID_EXTRA);
		this.currentEventId=eventId;
		this.currentEvent=EventModel.getInstanceOfSingletonEventModel().findEventById(eventId);
		//setting the value to the view 
		TextView eventName = (TextView) this.findViewById(R.id.event_name_text);
		eventName.setText(currentEvent.getTitle());
		
		CalendarView calendarView = (CalendarView) this.findViewById(R.id.view_event_calendarView);
		calendarView.setDate(currentEvent.getEventDateAndTime().getTimeInMillis());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		this.getMenuInflater().inflate(R.menu.view_event_activity_menubar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
			case R.id.attendees_information:
				Intent viewAttendeesIntent = new Intent(this,ViewAttendeesActivity.class);
				viewAttendeesIntent.putExtra(EventModel.EVENT_ID_EXTRA, currentEventId);
				this.startActivity(viewAttendeesIntent);
				return true;
			case R.id.edit_event:
				Intent editEventActivity = new Intent(this,EditEventActivity.class);
				editEventActivity.putExtra(EventModel.EVENT_ID_EXTRA, currentEventId);
				this.startActivity(editEventActivity);
				return true;	
			
		}
		
		return  super.onOptionsItemSelected(item);
	}

}
