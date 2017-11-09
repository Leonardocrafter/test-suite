package org.craftercms.studio.test.cases.sitespagetestcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.craftercms.studio.test.pages.CreateSitePage;
import org.craftercms.studio.test.pages.HomePage;
import org.craftercms.studio.test.pages.LoginPage;
import org.craftercms.studio.test.utils.ConstantsPropertiesManager;
import org.craftercms.studio.test.utils.FilesLocations;
import org.craftercms.studio.test.utils.UIElementsPropertiesManager;
import org.craftercms.studio.test.utils.WebDriverManager;
import org.openqa.selenium.NoSuchElementException;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class PaginationOfListOfSitesTest {

	private WebDriverManager driverManager;
	private LoginPage loginPage;
	private HomePage homePage;

	private String userName;
	private String password;

	private CreateSitePage createSitePage;
	private String sitesTopBarOptionId;
	private String sitesPerPageInputXpath;
	private String lastNumberOfPaginationXpath;
	private String firstNumberOfPaginationXpath;
	private String lastArrowOfPaginationXpath;
	private String firstArrowOfPaginationXpath;

	@BeforeClass
	public void beforeTest() {
		this.driverManager = new WebDriverManager();
		UIElementsPropertiesManager uIElementsPropertiesManager = new UIElementsPropertiesManager(
				FilesLocations.UIELEMENTSPROPERTIESFILEPATH);
		ConstantsPropertiesManager constantsPropertiesManager = new ConstantsPropertiesManager(
				FilesLocations.CONSTANTSPROPERTIESFILEPATH);

		this.driverManager.setConstantsPropertiesManager(constantsPropertiesManager);

		this.loginPage = new LoginPage(driverManager, uIElementsPropertiesManager);
		this.homePage = new HomePage(driverManager, uIElementsPropertiesManager);
		this.createSitePage = new CreateSitePage(driverManager, uIElementsPropertiesManager);

		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		sitesTopBarOptionId= uIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.preview.sitesoption");
		sitesPerPageInputXpath= uIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.sitesperpageinput");
		lastNumberOfPaginationXpath= uIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.pagination.lastnumberelement");
		firstNumberOfPaginationXpath= uIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.pagination.firstnumberelement");
		lastArrowOfPaginationXpath= uIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.pagination.lastarrowelement");
		firstArrowOfPaginationXpath= uIElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sites.pagination.firstarrowelement");
		
		loginPage.loginToCrafter(userName, password);
		// Create Site 1
		createSitesRandom();
		// Create Site 2
		createSitesRandom();
		// Create Site 3
		createSitesRandom();
	}

	@AfterClass
	public void afterTest() {

		// Delete Site 1
		deleteSite();
		// Delete Site 2
		deleteSite();
		// Delete Site 3
		deleteSite();
		driverManager.closeConnection();
	}

	public void createSitesRandom() {

		// Click on the create site button

		homePage.clickOnCreateSiteButton();

		// Filling the name of site

		createSitePage.fillSiteName();

		// Filling the description of the site

		createSitePage.fillDescription("Description");

		// Open blueprint combo

		createSitePage.openBlueprintCombo();

		// Select empty blueprint

		createSitePage.selectEmptyBlueprint();

		// Click on Create button

		createSitePage.clickOnCreateSiteButton();

		if (this.driverManager.isElementPresentById(sitesTopBarOptionId))
			this.driverManager
					.driverWaitUntilElementIsPresentAndDisplayed("id", sitesTopBarOptionId)
					.click();
		else
			throw new NoSuchElementException(
					"Site creation process is taking too long time and the element was not found");
	}

	public void navigationOfPage() {

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				sitesPerPageInputXpath).clear();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				sitesPerPageInputXpath).sendKeys("1");

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				sitesPerPageInputXpath).clear();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				sitesPerPageInputXpath).sendKeys("2");

		// navigation
		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath",
						lastNumberOfPaginationXpath)
				.click();

		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath",
						firstNumberOfPaginationXpath)
				.click();

		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath",
						lastArrowOfPaginationXpath)
				.click();

		this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed("xpath",
						firstArrowOfPaginationXpath)
				.click();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				sitesPerPageInputXpath).clear();

		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath",
				sitesPerPageInputXpath).sendKeys("10");
	}

	public void deleteSite() {

		// Click on Delete icon

		homePage.clickOnDeleteSiteIcon();

		// Click on YES to confirm the delete.

		homePage.clickOnYesToDeleteSite();
		driverManager.getDriver().navigate().refresh();

	}

	@Test(priority = 0)

	public void paginationOfTheListOfSites() {

		navigationOfPage();

	}

}
