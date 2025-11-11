package testcases;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class CustomerCommunication {
	
	@Test
	public void checkingMail(ITestContext context) 
	{
		String bookingId = (String) context.getAttribute("bookingId");
		System.out.println("Checking mail for confirmation for bookingId: "+bookingId);
		
	}

}
