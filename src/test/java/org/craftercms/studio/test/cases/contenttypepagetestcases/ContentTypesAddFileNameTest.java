/**
 * 
 */
package org.craftercms.studio.test.cases.contenttypepagetestcases;

import org.craftercms.studio.test.cases.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author luishernandez
 *
 */
public class ContentTypesAddFileNameTest extends BaseTest{
	
	private String userName;
	private String password;
	private String controlsSectionFormSectionLocator;
	private String contentTypeContainerLocator;
	private String controlsSectionFileNameLocator;
	private String contentTypeContainerFormSectionContainerLocator;
	private String contentTypeContainerFileNameTitleLocator;
	private String siteDropdownXpath;
	private String adminConsoleXpath;
	private String adminConsoleContentToolsFrame;

	@BeforeClass
	public void beforeTest() {
		
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		this.controlsSectionFormSectionLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.controlssectionformsection");
		this.contentTypeContainerLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainer");
		this.controlsSectionFileNameLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.controlsfilename");
		this.contentTypeContainerFormSectionContainerLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainerformsectioncontainer");
		this.contentTypeContainerFileNameTitleLocator = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contenttypecontainerfilenametitle");
		siteDropdownXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitedropdown");
		adminConsoleXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.adminconsole");
		adminConsoleContentToolsFrame= uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.content_tools_frame");
	}

	@AfterClass
	public void afterTest() {
		driverManager.closeConnection();
	}

	public void dragAndDrop() {

		// Getting the Form Section control input for drag and drop action
		WebElement FromControlSectionFormSectionElement = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed( "xpath", controlsSectionFormSectionLocator);

		// Getting the Content Type Container for drag and drop action
		// (destination)
		WebElement ToContentTypeContainer = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
				contentTypeContainerLocator);

		driverManager.dragAndDropElement(FromControlSectionFormSectionElement, ToContentTypeContainer);

		WebElement FromFileName = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
				controlsSectionFileNameLocator);

		WebElement ToDefaultSection = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
				contentTypeContainerFormSectionContainerLocator);
		
		siteConfigPage.getDriverManager().dragAndDropElement(FromFileName, ToDefaultSection);

		// Complete the input fields basics
		siteConfigPage.completeControlFieldsBasics("TestTitle", "TestICEGroup", "TestDescription", "TestDefault");

		// Save the data
		siteConfigPage.saveDragAndDropProcess();

	}

	@Test(priority = 0)
	public void verifyThatStudioAllowsToAddAFileNameControlToExistingContentTypeTest() {

		// login to application
		loginPage.loginToCrafter(
				userName,password);

		//Wait for login page to closes
		driverManager.waitUntilLoginCloses();
		
		// go to preview page
		homePage.goToPreviewPage();

		// Show site content panel
	
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed( "xpath",
				siteDropdownXpath).click();
		
		// Show admin console page
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed( "xpath", adminConsoleXpath).click();

		// Select the content type to the test
		siteConfigPage.selectEntryContentTypeFromAdminConsole();

		// drag and drop
		this.dragAndDrop();

		// open content types
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed("xpath", adminConsoleContentToolsFrame);
		siteConfigPage.clickExistingTypeOption();

		// Confirm the content type selected
		siteConfigPage.confirmContentTypeSelected();

		// Click on input section to can view the properties
		siteConfigPage.clickFileNameSection();

		// Asserts that fields are not empty.
		String titleText = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed( "xpath", contentTypeContainerFileNameTitleLocator)
				.getText();
		Assert.assertTrue(titleText.contains("TestTitle"));

	}
}
