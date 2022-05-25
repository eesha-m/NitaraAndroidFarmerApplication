package com.nitara.WeightManagement;

import java.util.Map;

import org.testng.annotations.Test;

import com.nitara.APIFunctions.RegisterMilkingCattle;
import com.nitara.Helper.GenerateRandomData;
import com.nitara.utils.DataProviderUtils;
import appCommonClasses.GenericBase;
import appCommonClasses.Helper_AppNavigation;
import appCommonClasses.HelperFunctions;

public class ViewWeight extends GenericBase{

	@Test(dataProvider = "getData",dataProviderClass = DataProviderUtils.class)
	public void Weight_ViewData(Map<String,String> data) throws Exception {
		
		/** Register cattle */
		String url = prop.getProperty("APIbaseUrl");
		RegisterMilkingCattle reg = new RegisterMilkingCattle();
		String Tag = reg.registerMilkingOrDryCattle(url,"RegisterMilkingOrDryCattle");
		
		/** Go to Cattle Profile - Select weight **/
		new Helper_AppNavigation().goTo_CattleProfileSelectActivity(Tag,"Weight");
		
		/** View Weight Screen */
		weightViewPage.click_addWeight();
		
		/** Fill Add weight form - Assert Success Page */
		data.put("Date",new GenerateRandomData().getPastDate(0) );
		String weight = addWeightPage.fillAddWeightForm(data);
		weightSuccessPage.assert_weightSuccessPage(data.get("calculateBy"));
		weightSuccessPage.click_recordAnotherWeightEntry(); // Go to view screen 
		
		/** View Weight details **/
		if(data.get("calculateBy").equalsIgnoreCase("girth")) {
			weightViewPage.assert_girthMethodDetails(data, weight);
		}
		else {
			weightViewPage.assert_directMethodDetails(data, weight);
			
		}
			
		
	}

}
