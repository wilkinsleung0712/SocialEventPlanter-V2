package view;

import model.Event;
import model.EventModel;
import view.model.EventAdapter;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.socialeventplaner_v2.R;

public class MainActivity extends ListActivity {
private EventAdapter eventAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main_activity);
		eventAdapter=new EventAdapter(this,R.layout.main_activity,
				EventModel.getInstanceOfSingletonEventModel().getEventList());
		//adding adapter to inflate the with with a single row view.
		this.setListAdapter(eventAdapter);
		
		
	}
	
	/*
	 * Method is called when user click on to every single item from the list view.
	 * the method direct user to a selected single view of the event
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		//get the item on which button has been seleced.
		Event event = (Event) this.getListAdapter().getItem(position);
		// TODO Auto-generated method stub
		Toast.makeText(this, "clicked on:"+event.getTitle()	,Toast.LENGTH_SHORT).show();
		Intent viewEventActivityIntent = new Intent(this,ViewEventActivity.class);
		viewEventActivityIntent.putExtra(EventModel.EVENT_ID_EXTRA, event.getId());
		this.startActivity(viewEventActivityIntent);
		super.onListItemClick(l, v, position, id);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater menuInflater = this.getMenuInflater();
		menuInflater.inflate(R.menu.main_activity_menubar,menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId()==R.id.addNewEvent){
			//user had click on add event button
			//create an intent 
			Intent addNewEventIntent = new Intent(this,NewEventActivity.class);
			this.startActivity(addNewEventIntent);
		}
		return super.onOptionsItemSelected(item);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode==NewEventActivity.NEW_EVENT_ADDED){
			//clear all event cache from the adapter
			this.eventAdapter.clear();
			this.eventAdapter.addAll(EventModel.getInstanceOfSingletonEventModel().getEventList());
			
		}else if(requestCode==EditEventActivity.DATE_CHANGE){
			this.eventAdapter.clear();
			this.eventAdapter.addAll(EventModel.getInstanceOfSingletonEventModel().getEventList());
			
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	

}
