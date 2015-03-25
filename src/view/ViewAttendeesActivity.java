package view;

import java.util.ArrayList;

import view.model.ViewAttendeesAdapter;
import model.EventModel;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.socialeventplaner_v2.R;

public class ViewAttendeesActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.view_attendee_activity_layout);
		//get event id from the intent
		String eventId  = this.getIntent().getStringExtra(EventModel.EVENT_ID_EXTRA);
		//get a list of attendee who going to attend the event.
		ArrayList<String> attendeelist = EventModel.getInstanceOfSingletonEventModel().findEventById(eventId).getAttendees();
		if(attendeelist.isEmpty()){
			attendeelist.add("no result");
		}
		ListView listview = (ListView) this.findViewById(R.id.view_attendee_listview);
		listview.setAdapter(new ViewAttendeesAdapter(this,0,attendeelist));
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}

}
