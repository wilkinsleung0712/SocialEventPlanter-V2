package view.model;

import java.util.List;

import com.example.socialeventplaner_v2.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewAttendeesAdapter extends ArrayAdapter<String> {
private LayoutInflater inflater = (LayoutInflater) this.getContext()
									.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
private List<String> attendeesList;
private Activity activity;
	public ViewAttendeesAdapter(Activity context, int resource,
			List<String> attendeesList) {
		super(context, resource, attendeesList);
		this.activity=activity;
		this.attendeesList=attendeesList;
		
		// TODO Auto-generated constructor stub
	}

	private class ViewContentController{
		 private TextView attendeeName;
		 private TextView attendeePhone;
		 private ImageView imageView;
	}
	
	
	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View inflatedView, ViewGroup parent) {
		String attendeeName = this.getItem(position);
		// TODO Auto-generated method stub
		if(inflatedView==null){
			//the system first to load up this layout.
			inflatedView = inflater.inflate(R.layout.attendee_activity_layout, null);
			ViewContentController holder = new ViewContentController();
			holder.attendeeName=(TextView) inflatedView.findViewById(R.id.attendeeName);
			holder.attendeePhone=(TextView) inflatedView.findViewById(R.id.attendeePhone);
			holder.imageView=(ImageView) inflatedView.findViewById(R.id.icon);
			inflatedView.setTag(holder);
		}
		//retreive the layout and the content holder in the second time when the layout is loaded
		ViewContentController holder = (ViewContentController) inflatedView.getTag();

		if(attendeeName.equalsIgnoreCase("no result")){
			 holder.attendeeName.setText("no result");
			    holder.attendeePhone.setVisibility(View.INVISIBLE);
			    holder.imageView.setVisibility(View.INVISIBLE);
		}else{
			 holder.attendeeName.setText(attendeeName);
			holder.attendeePhone.setVisibility(View.VISIBLE);
		    holder.imageView.setVisibility(View.VISIBLE);
		}
	   
		return inflatedView;
	}
	
	

}
