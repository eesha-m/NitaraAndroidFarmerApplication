package com.nitara.CattleManagement;


import org.testng.annotations.Test;

import com.nitara.APIFunctions.RegisterMilkingCattle;

import appCommonClasses.GenericBase;
import appCommonClasses.Helper_AppNavigation;
import appCommonClasses.HelperFunctions;

public class EditCattleProfile extends GenericBase{

	@Test 
	public void CattleProfile_EditData() throws Throwable {

		/** Register cattle */
		String url = prop.getProperty("APIbaseUrl");
		RegisterMilkingCattle reg = new RegisterMilkingCattle();
		String Tag = reg.registerMilkingOrDryCattle(url,"RegisterMilkingOrDryCattle");

		/** This function
		 * Go to Cattle Profile page and selects the particular health related activity
		 * Farmer Homepage -> Search Cattle Page -> Search with Tag No. 
		 * -> Select Cattle -> Select Edit option
		 * 
		 *  */
		farmerHomePage.waitForPageLoad();
		farmerHomePage.waitForPageLoad();
		new Helper_AppNavigation().goTo_CattleProfileSelectActivity(Tag,"Edit");
		
		/** Edit Cattle Profile */
		editCattleProfilePage.enter_earTagNumber(generateRandomData.generateRandomNumber(7));
		editCattleProfilePage.enter_cooperativetagNumber(generateRandomData.generateRandomNumber(12));
		editCattleProfilePage.click_updateBtn();
		cattleProfilePage.assert_CattleProfile();

	}

}
