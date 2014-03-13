package com.example.home_automation;

import com.jcraft.jsch.ChannelExec;

public class startuprun extends connection {
	
	public startuprun()
	{
		super();
	}
	public void running()
	{
		super.establishConnection();

		 try
		    {
		    	channel = session.openChannel("exec");
			      ((ChannelExec)channel).setCommand("python toplevel.py");
			      channel.connect(3*1000);
			      channel.disconnect();
		    }catch(Exception e)
		    {
		    	e.printStackTrace();
		    }
	      session.disconnect();

		 
	}
}
