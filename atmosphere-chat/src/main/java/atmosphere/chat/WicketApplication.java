package atmosphere.chat;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.wicket.Application;
import org.apache.wicket.atmosphere.EventBus;
import org.apache.wicket.atmosphere.config.AtmosphereLogLevel;
import org.apache.wicket.atmosphere.config.AtmosphereTransport;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see atmosphere.chat.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	private EventBus eventBus;

	@Override
	public Class<HomePage> getHomePage()
	{
		return HomePage.class;
	}

	public EventBus getEventBus()
	{
		return eventBus;
	}

	public static WicketApplication get()
	{
		return (WicketApplication)Application.get();
	}

	@Override
	public void init()
	{
		super.init();
		eventBus = new EventBus(this);
		eventBus.getParameters().setTransport(AtmosphereTransport.STREAMING);
		eventBus.getParameters().setLogLevel(AtmosphereLogLevel.DEBUG);

		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		final Runnable beeper = new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					eventBus.post(new Date());
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		};
		scheduler.scheduleWithFixedDelay(beeper, 2, 2, TimeUnit.SECONDS);
	}
}