package org.craftercms.studio.test.cases.previewtoolstestcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.craftercms.studio.test.cases.StudioBaseTest;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class PresetEachDesignTest extends StudioBaseTest{	
	private String userName;
	private String password;

	@BeforeMethod
	public void beforeTest() {
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.password");

	}

	public void changeBodyToNotRequiredOnEntryContent() {

		previewPage.changeBodyOfEntryContentPageToNotRequired();

	}

	public void createContent() {
		// right click to see the the menu

		dashboardPage.rightClickToSeeMenu();

		// Select Entry Content Type
		dashboardPage.clickEntryCT();

		// Confirm the Content Type selected

		dashboardPage.clickOKButton();

		// Switch to the iframe
		driverManager.getDriver().switchTo().defaultContent();
		driverManager.getDriver().switchTo()
				.frame(this.driverManager.driverWaitUntilElementIsPresentAndDisplayed(
						"cssSelector", ".studio-ice-dialog > .bd iframe"));					

		// Set basics fields of the new content created
		dashboardPage.setBasicFieldsOfNewContent("PRESET", "PRESET TESTING");

		// Set the title of main content
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed(
				"cssSelector", "#title > div > input").sendKeys("MainTitle");
	

		// click necessary to validate all fields required
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed(
				"cssSelector", "#cstudio-form-expand-all").click();

		// save and close
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed(
				"id", "cstudioSaveAndClose").click();
	
		// Switch back to the dashboard page
		driverManager.getDriver().switchTo().defaultContent();

	}

	public void presets() {

		// open publishing channel combo
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed(
				"cssSelector", "#medium-panel-elem > div.acn-accordion-header > a").click();

		 String contentURL = this.driverManager.driverWaitUntilElementIsPresentAndDisplayed(
					"cssSelector", "#engineWindow").getText();
		
		 Assert.assertTrue(contentURL.contains(contentURL));
	}

	@Test(priority = 0)

	public void verifyTheDesingOfPresetsOnPreviewToolsTest() {

		// login to application
		loginPage.loginToCrafter(userName, password);
		
		//Wait for login page to close
		driverManager.waitUntilLoginCloses();

		// go to preview page
		homePage.goToPreviewPage();

		// reload page
		driverManager.getDriver().navigate().refresh();

		// body not required
		changeBodyToNotRequiredOnEntryContent();

		// expand pages folder
		dashboardPage.expandPagesTree();

		// create content
		createContent();

		// Expand Home Tree
		dashboardPage.expandHomeTree();

		// select content
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed(
				"cssSelector", "#ygtvlabelel3").click();
		
		// open tools
		this.driverManager.driverWaitUntilElementIsPresentAndDisplayed(
				"cssSelector", "#acn-preview-tools-image").click();

		// presets and asserts
		presets();

	}

}
