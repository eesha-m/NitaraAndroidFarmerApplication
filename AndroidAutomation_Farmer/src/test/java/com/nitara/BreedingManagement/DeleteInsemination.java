package com.nitara.BreedingManagement;

import java.util.Map;
import org.testng.annotations.Test;

import com.nitara.APIFunctions.RegisterMilkingCattle;
import com.nitara.utils.DataProviderUtils;
import appCommonClasses.GenericBase;
import appCommonClasses.Helper_AppNavigation;
import appCommonClasses.HelperFunctions;

public class DeleteInsemination extends GenericBase{

	@Test(dataProvider = "getData",dataProviderClass = DataProviderUtils.class)
	public void Insemination_DeleteData(Map<String,String> data) throws Throwable {

		/** Register cattle */
		String url = prop.getProperty("APIbaseUrl");
		RegisterMilkingCattle reg = new RegisterMilkingCattle();
		String Tag = reg.registerMilkingOrDryCattle(url,"RegisterMilkingOrDryCattle");

		/** Go to cattle Profile page -> Select Breeding */
		farmerHomePage.waitForPageLoad();
		farmerHomePage.waitForPageLoad();
		new Helper_AppNavigation().goTo_BreedingActivityListfromCattleProfile(Tag);
		
		/** Select type of insemination and fill form */
		if(data.get("type").equalsIgnoreCase("Artificial")) {
			breedingActivityListPage.select_ArtificialInsemination(); 
			addAIPage.assert_CattleTag(Tag); 
			addAIPage.enter_BullId(data.get("bullID"));
			addAIPage.enter_InseminationDate(generateRandomData.getPastDate(21));
			addAIPage.press_SaveButton();
		}
		else {
			breedingActivityListPage.select_NaturalInsemination(); 
			addNIPage.assert_CattleTag(Tag); 
			addNIPage.enter_BullId(data.get("bullID"));
			addNIPage.enter_Remarks(data.get("remarks"));
			addNIPage.enter_InseminationDate(generateRandomData.getPastDate(21)); //
			addNIPage.press_SaveButton();
		}

		/** Assert cattletag in breeding successfully recorded page */
		breedingSuccessPage.cattletag_Assert(Tag);
		breedingSuccessPage.click_BackToBreedingTimeline();

		/** Delete the added insemination */
		breedingTimelinePage.press_DeleteBtnLeft();
		breedingTimelinePage.press_YesBtn();

		/** Assert Delete message */
		breedingTimelinePage.captureScreenshots("DeleteInsemination");
		breedingTimelinePage.assertDeleteMsg("Insemination",generateRandomData.getPastDate(21));
	}
}
