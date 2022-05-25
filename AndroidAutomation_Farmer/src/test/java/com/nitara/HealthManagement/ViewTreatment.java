package com.nitara.HealthManagement;

import java.util.Map;

import org.testng.annotations.Test;

import com.nitara.APIFunctions.RegisterMilkingCattle;
import com.nitara.utils.DataProviderUtils;
import appCommonClasses.GenericBase;
import appCommonClasses.Helper_AppNavigation;
import appCommonClasses.HelperFunctions;



public class ViewTreatment extends GenericBase{

	@Test (dataProvider = "getData",dataProviderClass = DataProviderUtils.class)
	public void Treatment_ViewData(Map<String,String> data) throws Throwable {

		/** Register cattle */
		String url = prop.getProperty("APIbaseUrl");
		RegisterMilkingCattle reg = new RegisterMilkingCattle();
		String Tag = reg.registerMilkingOrDryCattle(url,"RegisterMilkingOrDryCattle");

		/** This function
		 * Go to Cattle Profile page and selects the particular health related activity
		 * Farmer Homepage -> Search Cattle Page -> Search with Tag No. 
		 * -> Select Cattle -> Select Health option -> Health Activities listed - Select Deworming
		 * 
		 *  */
		farmerHomePage.waitForPageLoad();
		farmerHomePage.waitForPageLoad();
		new Helper_AppNavigation().goTo_ViewHealthActivityScreen(Tag,"Treatment");

		/** Add Treatment data from view treatment data page */
		viewTreatmentPage.click_addTreatment();

	
		/** Fill treatment form */
		String date = generateRandomData.getPastDate(40);
		addTreatmentPage.addTreatment(data, date);


		/* View Treatment Data screen assert */
		viewTreatmentPage.captureScreenshots("ViewTreatment");
		viewTreatmentPage.implicitWait();
		viewTreatmentPage.assert_Treatment(data.get("disease"));
		viewTreatmentPage.select_viewMore();
		viewTreatmentPage.assert_TreatmentMedicines(data.get("medicine"));
		//view.assertTreatmentDate(help.addDays(help.getPastDate(11),data.get("followUpAfter"));
		
	}

}
