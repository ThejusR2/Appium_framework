package frame;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;

public class Test_case extends Capabilities {
	
	@BeforeTest
	public void KillAllProcesses() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(8000);
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"//run.bat");
		Thread.sleep(60000);
		
	}
	@Test
	public void tc1() throws IOException, InterruptedException {
		service = startServer();
		AndroidDriver<AndroidElement> driver= hybrid_capabilities(appactivity, apppackage, deviceName, chromeexcutable);
		
		Thread.sleep(5000);
		driver.findElement(MobileBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		Thread.sleep(2000);
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"))").click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Thejus");
		Thread.sleep(2000);
		driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Male\")")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Thread.sleep(5000);
		driver.findElementsByAndroidUIAutomator("text(\"ADD TO CART\")").get(0).click();
		driver.findElementsByAndroidUIAutomator("text(\"ADD TO CART\")").get(0).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().text(\"Jordan 6 Rings\"))"));
		Thread.sleep(2000);
		int count=driver.findElements(MobileBy.id("com.androidsample.generalstore:id/rvProductList")).size();
		for(int i=0;i<count;i++)
		{
			String J=driver.findElement(MobileBy.id("com.androidsample.generalstore:id/productName")).getText();
			System.out.println(J);
			
			if(J.equalsIgnoreCase("Jordan 6 Rings"))
			{
				driver.findElementsByAndroidUIAutomator("text(\"ADD TO CART\")").get(i).click();
			}
		
			break;
		}
		Thread.sleep(2000);
		driver.findElement(MobileBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(2000);
		
		String price1= driver.findElements(MobileBy.id("com.androidsample.generalstore:id/productPrice")).get(0).getText();
		price1=price1.substring(1);
		Double p1=Double.parseDouble(price1);
		System.out.println(p1);
		Thread.sleep(3000);
		
		String price2= driver.findElements(MobileBy.id("com.androidsample.generalstore:id/productPrice")).get(1).getText();
		price2=price2.substring(1);
		Double p2=Double.parseDouble(price2);
		System.out.println(p2);
		Thread.sleep(4000);
		
		TouchAction s1=new TouchAction(driver);
		s1.longPress(PointOption.point(769,1386)).moveTo(PointOption.point(769,1169)).release().perform();
		Thread.sleep(4000);
		TouchAction s2=new TouchAction(driver);
		s2.longPress(PointOption.point(769,1371)).moveTo(PointOption.point(769,833)).release().perform();
		
		String price3= driver.findElements(MobileBy.id("com.androidsample.generalstore:id/productPrice")).get(2).getText();
		price3=price3.substring(1);
		Double p3=Double.parseDouble(price3);
		System.out.println(p3);
		Thread.sleep(2000);
		
		Double Total=p1+p2+p3;
		System.out.println(Total);
		
		String PC=driver.findElement(MobileBy.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();

		PC=PC.substring(1);
		Double w=Double.parseDouble(PC);
		System.out.println(w);
		if(Total.equals(w)) {
			System.out.println("price matching");
		}
		else {
			System.out.println("price not matching");
		}
//		Assert.assertEquals(Total, w);
//		System.out.println("Assertion passed");
		Thread.sleep(2000);
		
		AndroidElement a1= driver.findElement(MobileBy.id("com.androidsample.generalstore:id/termsButton"));
		TouchAction t1=new TouchAction(driver);
		t1.longPress(longPressOptions().withElement(element(a1)).withDuration(ofSeconds(5))).release().perform();
		Thread.sleep(3000);
		driver.findElement(MobileBy.id("android:id/button1")).click();
		Thread.sleep(3000);
		driver.findElement(MobileBy.className("android.widget.CheckBox")).click();
		Thread.sleep(2000);
		driver.findElement(MobileBy.className("android.widget.Button")).click();
		
		service.stop();
	}
	
	

}
