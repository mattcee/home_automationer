package com.example.home_automation;

import com.jcraft.jsch.ChannelExec;

public class turnoff extends connection{
	
	public turnoff()
	{
		super();
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
