package org.craftercms.studio.test.cases.userstestcases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.BaseTest;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class DesignOfUsersPageTest extends BaseTest{

	private String userName;
	private String password;
	private String usersTitleXpath;
	private String crafterLogoXpath;
	private String accountTopNavOptionXpath;
	private String helpTopNavOptionXpath;
	private String sitesTopNavOptionXpath;
	private String usersTopNavOptionXpath;
	private String usersPerPageInputXpath;
	private String newUserButtonXpath;
	private String userSearchXpath;

	@BeforeMethod
	public void beforeTest() {

		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		usersTitleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.userstitle");
		crafterLogoXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.crafterlogo");
		usersTopNavOptionXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.homeusers");
		sitesTopNavOptionXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.homesites");
		helpTopNavOptionXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.homehelp");
		accountTopNavOptionXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.accountdropdown");
		usersPerPageInputXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.usersperpageinput");
		newUserButtonXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.newuserbutton");
		userSearchXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.users.userssearchinput");
	}


	@Test(priority = 0)
	public void verifyTheDesingOfUsersPageTest() {

		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// click On Users option
		createSitePage.clickOnUsersOption();

		// Assert header is present.
		WebElement header = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", usersTitleXpath);
		Assert.assertTrue(header.isDisplayed());

		// Assert crafter logo is present.
		WebElement crafterLogo = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				crafterLogoXpath);
		Assert.assertTrue(crafterLogo.isDisplayed());

		// Assert user menu option is present.
		WebElement userMenuOption = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				usersTopNavOptionXpath);
		Assert.assertTrue(userMenuOption.isDisplayed());

		// Assert sites menu option is present.
		WebElement sitesMenuOption = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				sitesTopNavOptionXpath);
		Assert.assertTrue(sitesMenuOption.isDisplayed());

		// Assert help menu option is present.
		WebElement helpMenuOption = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				helpTopNavOptionXpath);
		Assert.assertTrue(helpMenuOption.isDisplayed());

		// Assert admin dropdown option is present.
		WebElement adminOption = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				accountTopNavOptionXpath);
		Assert.assertTrue(adminOption.isDisplayed());

		// Assert users per page combo option is present.
		WebElement usersPerPage = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				usersPerPageInputXpath);
		Assert.assertTrue(usersPerPage.isDisplayed());

		// Assert new user option is present.
		WebElement newUser = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				newUserButtonXpath);
		Assert.assertTrue(newUser.isDisplayed());
		
		// Assert search option is present.
		WebElement search = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				userSearchXpath);
		Assert.assertTrue(search.isDisplayed());

	}
}
