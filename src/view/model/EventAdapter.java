package view.model;

import java.util.List;

import com.example.socialeventplaner_v2.R;

import model.Event;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EventAdapter extends ArrayAdapter<Event> {
	//inflater to inflate the layout with single layout xml
	private LayoutInflater inflater=(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	private Activity activity;
	private List<Event> eventList;
	
	

	public EventAdapter(Activity activity, int resource, List<Event> eventList) {
		// TODO Auto-generated constructor stub
		super(activity, resource, eventList);
		this.activity=activity;
		this.eventList=eventList;
		
	}

	
	private class ViewContentHolder{
		private TextView titleTextView;
		private TextView calendarTextView;
		
	}


	@Override
	public View getView(int position, View inflatedView, ViewGroup parent) {
		Event event = this.getItem(position);
		// TODO Auto-generated method stub
		if(inflatedView ==null){
			inflatedView = inflater.inflate(R.layout.single_activity_layout, null);
			ViewContentHolder holder = new ViewContentHolder();
			holder.titleTextView=(TextView) inflatedView.findViewById(R.id.titleLine);
			holder.calendarTextView=(TextView) inflatedView.findViewById(R.id.calendarLine);
			inflatedView.setTag(holder);
		}
		
		ViewContentHolder holder = (ViewContentHolder) inflatedView.getTag();
		holder.titleTextView.setText(event.getTitle());
		holder.calendarTextView.setText(java.text.DateFormat.getDateInstance().format(event.getEventDateAndTime().getTime()));
		
		return inflatedView;
	}

	
}
