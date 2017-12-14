package org.craftercms.studio.test.cases.contenttypepagetestcases;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.BaseTest;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class ContentTypesDragAndDropTest extends BaseTest{

	private String userName;
	private String password;
	private String siteDropdownXpath;
	private String controlsSectionFromSection;
	private String contentFormName;
	private String contentFormContentSection;

	@BeforeMethod
	public void beforeTest() {
		
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");
		
		siteDropdownXpath = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("general.sitedropdown");
		controlsSectionFromSection = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.controlssection");
		contentFormName = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contentformname");
		contentFormContentSection = uiElementsPropertiesManager.getSharedUIElementsLocators()
				.getProperty("adminconsole.contenttype.entry.contentformcontentsection");
	}

	@Test(priority = 0)
	public void contentTypesDragAndDropTest() {

		// login to application
		loginPage.loginToCrafter(
				userName,password);

		//Wait for login page to closes
		driverManager.waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage();

		// Show site content panel
		this.driverManager
		.driverWaitUntilElementIsPresentAndDisplayed( "xpath", siteDropdownXpath).click();

		// go to admin console page

		previewPage.goToAdminConsolePage();

		// select content types
		siteConfigPage.selectContentTypeOption();

		// open content types
		siteConfigPage.clickExistingTypeOption();

		// Confirm the content type selected

		siteConfigPage.confirmContentTypeSelected();

		// wait for element is clickeable
		WebElement From = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed( "cssSelector", controlsSectionFromSection);
		
		WebElement To = this.driverManager
				.driverWaitUntilElementIsPresentAndDisplayed( "cssSelector", contentFormName);

		Actions builder = new Actions(driverManager.getDriver());

		Action dragAndDrop = builder.clickAndHold(From)

				.moveToElement(To)

				.release(To)

				.build();

		dragAndDrop.perform();

		// Save the drag and drop process

		siteConfigPage.saveDragAndDropProcess();
		// validate the control added

		Assert.assertTrue(driverManager.isElementPresentBycssSelector(contentFormContentSection));
		
	}

}