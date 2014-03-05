package com.example.home_automation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.*;

public class HeaderFragment extends ListFragment {

	ListClickListener mCallBack;
	//List<String> plug_names = new ArrayList();
	Context file = getActivity(); // for writing and reading
	 String FILENAME = "saved_devices";

	
    public static ArrayAdapter<String> adapter;

	public interface ListClickListener{
		public void onPlugSelected(int position);
	}
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
        	new SSHconnect_read().execute(null,null,null);


	        // We need to use a different list item layout for devices older than Honeycomb
	        
        	int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
	                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;
	        // Create an array adapter for the list view, using the Ipsum headlines array
	        //listOfPlugs plugs = new listOfPlugs();
	        adapter = new ArrayAdapter<String>(getActivity(), layout, listOfPlugs.plug_namez);
	       // writeOutSavedDevices();

	        setListAdapter(adapter);
	        //readSavedDevices();

	    }

	 //test writing files
	 private void writeOutSavedDevices()
	 {
		 //String test = "hello world!";
		 FileOutputStream fos;

		 try{
			 
			 fos = file.getApplicationContext().openFileOutput(FILENAME, Context.MODE_PRIVATE);
			 for(String str : listOfPlugs.plug_namez)
			 {
				 fos.write(str.getBytes());

			 }
			 fos.close();
			 
			 System.out.println("finished writing");
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 
	 }
	

	private class SSHconnect_read extends AsyncTask<URL, Integer, Long>
		{

			@Override
			protected Long doInBackground(URL... arg0) {
				// TODO Auto-generated method stub
				
				try{
					
					connectAndRead c = new connectAndRead();
					c.establishConnection();
					listOfPlugs.plug_namez = c.readFile();


				}catch(Exception e)
				{
					e.printStackTrace();
				}
				
				return null;
			}
			
			
			  protected void onPostExecute(Long result) {
				  int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
			                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;
			      adapter = new ArrayAdapter<String>(getActivity(), layout, listOfPlugs.plug_namez);
				  adapter.notifyDataSetChanged();
			      setListAdapter(adapter);


			     }
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
	 public void onStop()
	 {
		 super.onStop();
		 writeOutSavedDevices();
	 }

	 @Override
	    public void onListItemClick(ListView l, View v, int position, long id) {
	        // Notify the parent activity of selected item
		 	mCallBack.onPlugSelected(position);
	        
	    }
}
