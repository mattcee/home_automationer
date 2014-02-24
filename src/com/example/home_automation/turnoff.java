package com.example.home_automation;

import com.jcraft.jsch.ChannelExec;

public class turnoff extends connection{
	
	public turnoff()
	{
		super();
		open_turnoff();
	}
	private void open_turnoff()
	{
		 try
		    {
		    	channel = session.openChannel("exec");
			      ((ChannelExec)channel).setCommand("gpio write 4 0");
			      channel.connect(3*1000);
			      channel.disconnect();
		    }catch(Exception e)
		    {
		    	e.printStackTrace();
		    }
	}

}
