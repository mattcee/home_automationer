package com.example.home_automation;

import java.net.URL;
import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.Switch;

public class ListActivity extends FragmentActivity implements HeaderFragment.ListClickListener, ModifyFragment.scheduleButton, ScheduleFragment.currentSchedule {

	//private String clicked_plugName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		
		if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of ExampleFragment
            HeaderFragment firstFragment = new HeaderFragment();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
		

	}
	

	//handles the plug that is selected from the main page
	public void onPlugSelected(int position)
	{
		// Create fragment and give it an argument specifying the article it should show
		ModifyFragment newFrag = new ModifyFragment();
		Bundle args = new Bundle();
		args.putInt(ModifyFragment.ARG_POSITION, position);
		newFrag.setArguments(args);
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		
		// Replace whatever is in the fragment_container view with this fragment,
		// and add the transaction to the back stack so the user can navigate back
		transaction.replace(R.id.fragment_container, newFrag);
        transaction.addToBackStack(null);
        
        // Commit the transaction
        transaction.commit();
	}
	
	//happens on the schedule fragment, handles schedule botton click
	public void schedule_botton_click()
	{
		ScheduleFragment newFrag = new ScheduleFragment();
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		
		// Replace whatever is in the fragment_container view with this fragment,
		// and add the transaction to the back stack so the user can navigate back
		transaction.replace(R.id.fragment_container, newFrag);
        transaction.addToBackStack(null);
        
        // Commit the transaction
        transaction.commit();
	}

	//handles current schedule button click on the schedule fragment page
	public void currentSchedule_button()
	{
		MyProfilesFragment newFrag = new MyProfilesFragment();
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		
		// Replace whatever is in the fragment_container view with this fragment,
		// and add the transaction to the back stack so the user can navigate back
		transaction.replace(R.id.fragment_container, newFrag);
        transaction.addToBackStack(null);
        
        // Commit the transaction
        transaction.commit();
	}
	

	//turning on the lights
	private class SSHconnections_ON extends AsyncTask<URL, Integer, Long>
	{
	

		@Override
		protected Long doInBackground(URL... arg0) {
			// TODO Auto-generated method stub
			 connection c = new connection();
			 c.establishConnection();
			 turnon t = new turnon();
			

			return null;
		}
		
		//need to add onPreExecute() to prepare the load screen for the program
		
		//need to add onProgressUpdate() to add a spinner that shows that is connecting to turn on/off
		//on postexecute maybe let the user know that the device is on with a green light button or a red button
	
	}
	
	//turning off the lights
	private class SSHconnections_OFF extends AsyncTask<URL, Integer, Long>
	{

		@Override
		protected Long doInBackground(URL... arg0) {
			// TODO Auto-generated method stub
			
			//might be able to get rid of the connections at this part
			 connection c = new connection();
			c.establishConnection();
			turnoff t = new turnoff();
			

			return null;
		}
		
	
	}
	
	private class SSHconnection_overall extends AsyncTask<URL, Integer, Long>
	{

		@Override
		protected Long doInBackground(URL... arg0) {
			// TODO Auto-generated method stub
			connection c = new connection();
			c.establishConnection();
			
			return null;
		}
		
	}


    //only use when on uci wifi
    public void onToggleClicked(View view)
    {
    	
    	boolean on = ((Switch)view).isChecked();
    	if(on)
    	{
    		System.out.println("on");
        	new SSHconnections_ON().execute(null,null,null);


    	}
    	else
    	{
    		System.out.println("off");
        	new SSHconnections_OFF().execute(null,null,null);

    	}
    }

    
    


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

}
