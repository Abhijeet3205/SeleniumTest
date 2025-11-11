package keywords;

public class ValidationKeywords extends GenericKeywords {
	
	public void validateTitle() {

	}

	public boolean validateText(String expectedTextLocatorKey, String actualTextLocatorKey)
	{
		String actualText = getText(actualTextLocatorKey);
		String expectedText = envProp.getProperty(expectedTextLocatorKey);
		System.out.println("actualText: "+actualText+ " expectedText:"+expectedText);
		if(expectedText.equals((actualText)))
		{
			return true;
		}
		return false;
				
	}

	public boolean validateElementPresent(String locator) 
	{
		boolean result = isElementPresent(locator);
		return result;
	}


}
