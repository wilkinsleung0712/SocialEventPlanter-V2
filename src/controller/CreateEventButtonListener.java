package controller;


import model.EventModel;
import model.SimpleEvent;
import view.MainActivity;
import view.NewEventActivity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class CreateEventButtonListener implements OnClickListener{
private NewEventActivity newEventActivity;
private String eventName;
	public CreateEventButtonListener(NewEventActivity newEventActivity) {
		// TODO Auto-generated constructor stub
		this.newEventActivity=newEventActivity;
	}

	@Override
	public void onClick(View v) {
		eventName= newEventActivity.getEventTitleFromCreateEventView();
		// TODO Auto-generated method stub
		EventModel.getInstanceOfSingletonEventModel()
				.addEvent(
						new SimpleEvent(
								eventName,
								newEventActivity.getEventTimeAndDateFromCreateEventView()));
		//after finish creating the new event, send the intent back to the main 
		//activity and call for result activity update.
		Intent mainActivityIntent = new Intent (newEventActivity,MainActivity.class);
		newEventActivity.startActivityForResult(mainActivityIntent, NewEventActivity.NEW_EVENT_ADDED);
		//notify user for success creation.
		Toast.makeText(newEventActivity, "Event: "+eventName+"has been created", Toast.LENGTH_SHORT).show();
	}

}
