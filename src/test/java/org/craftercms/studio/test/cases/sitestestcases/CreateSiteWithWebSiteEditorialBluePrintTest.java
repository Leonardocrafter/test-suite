package org.craftercms.studio.test.cases.sitestestcases;

import org.craftercms.studio.test.cases.BaseTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;


/**
 * 
 * 
 * @author luishernandez
 *
 */

public class CreateSiteWithWebSiteEditorialBluePrintTest extends BaseTest {

	private String userName;
	private String password;
	private String siteDropdownElementXPath;


	@BeforeClass
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		siteDropdownElementXPath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("complexscenarios.general.sitedropdown");

	}

	@Test(priority = 0)
	public void createSiteWithWebSiteEditorialBluePrintTest() {

		// login to application
		loginPage.loginToCrafter(userName, password);
		
		driverManager.waitUntilLoginCloses();

		// Click on the create site button
		homePage.clickOnCreateSiteButton();

		// Filling the name of site

		createSitePage.fillSiteName();

		// Filling the description of the site

		createSitePage.fillDescription("Description");

		// Open blueprint combo
		// Select blueprint

		createSitePage.selectWebSiteEditorialBluePrintOption();

		// Click on Create button

		createSitePage.clickOnCreateSiteButton();

		this.driverManager.waitWhileElementIsDisplayedAndClickableByXpath(siteDropdownElementXPath);

		Assert.assertTrue(this.driverManager.isElementPresentAndClickableByXpath(siteDropdownElementXPath));
	}

}
