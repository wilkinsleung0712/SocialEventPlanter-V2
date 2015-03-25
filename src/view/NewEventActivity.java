package view;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.socialeventplaner_v2.R;

import controller.CreateEventButtonListener;

public class NewEventActivity extends Activity{
public static final int NEW_EVENT_ADDED=3;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.create_event_activity_layout);
		Button createButton = (Button) this.findViewById(R.id.create_event_button);
		createButton.setOnClickListener(new CreateEventButtonListener(this));
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}

	/*
	 * @return Event title data from the intent
	 */
	public String getEventTitleFromCreateEventView(){
		TextView eventTitle = (TextView) this.findViewById(R.id.new_event_name);
		return eventTitle.getText().toString();
	}

	/*
	 * @return Event calendar data from the intent
	 */
	public Calendar getEventTimeAndDateFromCreateEventView(){
		Calendar calendar = Calendar.getInstance();
		DatePicker datePicker = (DatePicker) this.findViewById(R.id.create_event_datePicker);
		calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
		return calendar;
	}
}
