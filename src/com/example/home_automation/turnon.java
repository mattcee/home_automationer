package com.example.home_automation;

import com.jcraft.jsch.ChannelExec;

public class turnon extends connection{

	public turnon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void open_turon()
	{
		super.establishConnection();
		try{
			
			channel = session.openChannel("exec");
		      ((ChannelExec)channel).setCommand("gpio write 4 1");
		      channel.connect(3*1000);
		      channel.disconnect();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	      session.disconnect();

	}
	
}
