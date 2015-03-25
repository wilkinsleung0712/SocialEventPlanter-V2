package view;

import java.util.Calendar;

import model.Event;
import model.EventModel;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.socialeventplaner_v2.R;

public class EditEventActivity extends Activity{
private String currentEventId;
private Event currentEvent;
private Calendar oldDate;
public static final int DATE_CHANGE=1;
public static final int DIALOG_YES_NO_CONFIRM_SAVE_MESSAGE=99;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.edit_event_activity_layout);
		this.currentEventId=this.getIntent().getStringExtra(EventModel.EVENT_ID_EXTRA);
		currentEvent = EventModel.getInstanceOfSingletonEventModel().findEventById(currentEventId);
		this.oldDate=currentEvent.getEventDateAndTime();
		//retreive all component from the view/intent and configure the data to view
		EditText eventName = (EditText) this.findViewById(R.id.edit_event_title);
		eventName.setText(currentEvent.getTitle());
		//set the event date button
		setDateButtonFromDate(currentEvent.getEventDateAndTime());
		//set the event date picker
		DatePicker editEventDatePicker = (DatePicker) this.findViewById(R.id.edit_event_create_event_datePicker);
		setDateToCalendarView(editEventDatePicker,currentEvent.getEventDateAndTime());
		//set the event to calendar view
		CalendarView calendarView = (CalendarView) this.findViewById(R.id.edit_event_calendarView);
		calendarView.setDate(currentEvent.getEventDateAndTime().getTimeInMillis());
		
		
	}
	/*
	 * Setting the eventDate  data into editEventButton and display the selected date
	 */
	private void setDateButtonFromDate(Calendar eventDate){
		Button editEventButton = (Button) this.findViewById(R.id.edit_event_button);
		editEventButton.setText(java.text.DateFormat.getInstance().format(eventDate.getTime()));
	}
	
	/*
	 * Setting the datePicker data into CalendarView
	 */
	private void setDateToCalendarView(DatePicker datePicker, Calendar calendar){
		datePicker.init(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
			
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				//if the date had changed notify the model
				//however,as the "setDateButtonFromDate" method takes a Calendar,
				//so we need to use the updated calendar that specify by this date change 
				setDateButtonFromDate(getCalendarFromDateField(year,monthOfYear,dayOfMonth));
			}
		});
	}

	/*
	 * takes the year, month,day and create an instance of the calendar
	 * @return Calendar - the new up-to-date calendar
	 */
	private Calendar getCalendarFromDateField(int year,int monthOfYear, int dayOfMonth){
		Calendar newEventDate = Calendar.getInstance();
		newEventDate.set(year, monthOfYear, dayOfMonth);
		return newEventDate;
	}
	/*
	 * takes a dataPicker value and return the calendar that contains the values
	 * @return Calendar calendar
	 */
	private Calendar getDateFromDatePicker(DatePicker datePicker){
		Calendar calendar = Calendar.getInstance();
		calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
		return calendar;
	}
	
	/*
	 * compare two date to verify if the data has changed
	 * @parameter oldDate - the date that was on currentEvent previously
	 * @parameter newDate - the date that is on calendar rightnow
	 * @return true - if both event is same.
	 */
	private boolean compareDate(Calendar oldDate,Calendar newDate){
		
		return oldDate.get(Calendar.YEAR)==newDate.get(Calendar.YEAR)
				&&oldDate.get(Calendar.MONTH)==newDate.get(Calendar.MONTH)
				&&oldDate.get(Calendar.DAY_OF_MONTH)==newDate.get(Calendar.DAY_OF_MONTH);
		
	}
	/*
	 * save date from intent to the model
	 */
	private void save(){
		EditText titleText = (EditText) this.findViewById(R.id.edit_event_title);
		this.currentEvent.setTitle(titleText.getText().toString());
		DatePicker datePicker = (DatePicker) this.findViewById(R.id.edit_event_create_event_datePicker);
		this.currentEvent.setEventDateAndTime(getDateFromDatePicker(datePicker));
		//need to verify if the date has been updated
		if(!compareDate(oldDate,currentEvent.getEventDateAndTime())){
			
			//this.showDialog(DIALOG_YES_NO_CONFIRM_SAVE_MESSAGE); 
			EventModel.getInstanceOfSingletonEventModel().editEventById(oldDate,currentEvent);
			Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show();
			Intent mainActivityIntent = new Intent(this,MainActivity.class);
			this.startActivityForResult(mainActivityIntent, DATE_CHANGE);
			
		}
		
	}
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onMenuItemSelected(int, android.view.MenuItem)
	 */
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		return super.onMenuItemSelected(featureId, item);
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
	 * @see android.app.Activity#finish()
	 */
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		save();
		super.finish();
	}
	/* overriding the create dialog method to generate custom dialog
	 * @see android.app.Activity#onCreateDialog(int)
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		if(id==this.DIALOG_YES_NO_CONFIRM_SAVE_MESSAGE){
			return new AlertDialog.Builder(EditEventActivity.this)
					.setIcon(R.drawable.alert_dialog_icon)
					.setTitle("Warnning")
					.setMessage("Are you sure to save changes?")
					.setPositiveButton("YES", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					})
					.setNegativeButton("NO", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					}).create();
		}
		return super.onCreateDialog(id);
	}

	
}
