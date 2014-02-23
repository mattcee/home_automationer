package com.example.home_automation;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HeaderFragment extends ListFragment {

	ListClickListener mCallBack;
	
	public interface ListClickListener{
		public void onPlugSelected(int position);
	}
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

	        // We need to use a different list item layout for devices older than Honeycomb
	        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
	                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

	        // Create an array adapter for the list view, using the Ipsum headlines array
	        listOfPlugs plugs = new listOfPlugs();
	        setListAdapter(new ArrayAdapter<String>(getActivity(), layout, plugs.plug_names));

	    }
	 @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        
	        // This makes sure that the container activity has implemented
	        // the callback interface. If not, it throws an exception
	        try {
	        	mCallBack = (ListClickListener) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " must implement ListClickListener");
	        }
	    }


	 @Override
	 public void onStart()
	 {
		 super.onStart();
		 getActivity().setTitle("List of Plugs");

	 }

	 @Override
	    public void onListItemClick(ListView l, View v, int position, long id) {
	        // Notify the parent activity of selected item
		 	mCallBack.onPlugSelected(position);
	        
	    }
}
