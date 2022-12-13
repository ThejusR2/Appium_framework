package frame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Capabilities {
	protected static String appactivity;
	protected static String apppackage;
	protected static String deviceName;
	protected static String chromeexcutable;
	public AppiumDriverLocalService service;
	public AppiumDriverLocalService startServer()
	{
		boolean flag = checkifserverisRunning(4723);
		if(!flag)
		{
	service = AppiumDriverLocalService.buildDefaultService();
	service.start();
			}
		return service;
	}
	
	public static boolean checkifserverisRunning(int port)
	{
		boolean isServerRunning=false;
		ServerSocket serversocket;
		try{
			serversocket = new ServerSocket(port);
			serversocket.close();
		}
		catch(IOException e)
		{
			isServerRunning = true;
		}
		finally {
			serversocket=null;
		}
		
		return isServerRunning;
	}
	
	
//	public static void startEmulator() throws IOException, InterruptedException
//	{
//		Runtime.getRuntime().exec(System.getProperty("user.dir")+"//src//test//resources//emulator.bat");
//		Thread.sleep(60000);
//	}

	public static AndroidDriver<AndroidElement> hybrid_capabilities(String appactivity, String apppackage, String deviceName, String chromeexcutable) throws IOException{
		
		FileReader gp = new FileReader(System.getProperty("user.dir")+"//src//main//java//global.properties");
		Properties pro= new Properties();
		pro.load(gp);
		appactivity=pro.getProperty("appactivity");
		apppackage=pro.getProperty("apppackage");
		deviceName=pro.getProperty("deviceName");
		chromeexcutable=pro.getProperty("chromeexcutable");
		
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, apppackage);
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appactivity);
		dc.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, chromeexcutable);
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"),dc);
		return driver;
	}

}
