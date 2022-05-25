package com.nitara.PageObjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CreatePasswordPage extends BasePage {
	
	@AndroidFindBy(id = "createPinEt") 
	private MobileElement createPin;
	
	@AndroidFindBy(id = "reEnterPinEt") 
	private MobileElement reEnterPin;
	
	@AndroidFindBy(id = "savePin") 
	private MobileElement savePin;
	
	public void enter_createPin(int pin) {
		sendKeys(createPin, pin);
	}
	
	public void enter_reEnterPin(int pin) {
		sendKeys(reEnterPin, pin);
	}
	
	public void click_savePin() {
		click(savePin);
	}



}
