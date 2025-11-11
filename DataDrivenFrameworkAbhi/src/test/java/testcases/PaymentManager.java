package testcases;

import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PaymentManager {
	
	@Test
	public void applyDiscount() 
	{
		System.out.println("Applying Discount");
	}
	
	@Parameters({"action"})
	@Test
	public void makePayment(String paymentType, ITestContext context) 
	{
		if(paymentType.equals("nodiscount"))
		{
			System.out.println("No Discount");
			System.out.println("Making Payment");
		}
		else if(paymentType.equals("payathotel"))
		{
			System.out.println("Give 10% Discount");
			System.out.println("Making Payment");
		}
		else 
		{
			System.out.println("Making Payment");
		}
		
		String bookingId ="Id67dhkijj";
		context.setAttribute("bookingId", bookingId);
	}

}
