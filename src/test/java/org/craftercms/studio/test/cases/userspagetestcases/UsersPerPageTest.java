package org.craftercms.studio.test.cases.userspagetestcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.commons.lang3.RandomStringUtils;
import org.craftercms.studio.test.pages.CreateSitePage;
import org.craftercms.studio.test.pages.HomePage;
import org.craftercms.studio.test.pages.LoginPage;
import org.craftercms.studio.test.pages.UsersPage;
import org.craftercms.studio.test.utils.FilesLocations;
import org.craftercms.studio.test.utils.UIElementsPropertiesManager;
import org.craftercms.studio.test.utils.WebDriverManager;

/**
 * 
 * @author Gustavo Andrei Ortiz Alfaro
 *
 */

public class UsersPerPageTest {

	private WebDriverManager driverManager;

	private LoginPage loginPage;

	private HomePage homePage;

	private CreateSitePage createSitePage;

	private UsersPage usersPage;

	@BeforeClass
	public void beforeTest() {
		this.driverManager = new WebDriverManager();
		UIElementsPropertiesManager uIElementsPropertiesManager = new UIElementsPropertiesManager(
				FilesLocations.UIELEMENTSPROPERTIESFILEPATH);
		this.loginPage = new LoginPage(driverManager, uIElementsPropertiesManager);
		this.homePage = new HomePage(driverManager, uIElementsPropertiesManager);
		this.createSitePage = new CreateSitePage(driverManager, uIElementsPropertiesManager);
		this.usersPage = new UsersPage(driverManager, uIElementsPropertiesManager);

	}

	@AfterClass
	public void afterTest() {
		driverManager.closeConnection();
	}

	public void createUserRandom() {

		// click On Users option

		createSitePage.clickOnUsersOption();

		// click on new user button

		usersPage.clickOnNewUser();

		// Follow the form

		driverManager.getDriver().findElement(By.cssSelector("#firstName")).sendKeys("Name");

		driverManager.getDriver().findElement(By.cssSelector("#lastName")).sendKeys("Last Name");

		driverManager.getDriver().findElement(By.cssSelector("#email")).sendKeys("email@email.com");

		driverManager.getDriver().findElement(By.cssSelector("#username"))
				.sendKeys(RandomStringUtils.randomAlphabetic(5));

		driverManager.getDriver().findElement(By.cssSelector("#password")).sendKeys("password");

		driverManager.getDriver().findElement(By.cssSelector("#passwordVerification")).sendKeys("password");

		// Save Button

		usersPage.clickOnSaveNewUser();

		// wait for element is clickeable

		homePage.getDriverManager().driverWait();
	}

	public void filters() {

		// Show 8 users

		driverManager.getDriver().findElement(By.cssSelector("#container > div > div > div > div > div > div > input"))
				.clear();

		driverManager.getDriver().findElement(By.cssSelector("#container > div > div > div > div > div > div > input"))
				.sendKeys("1");

		// wait for element is clickeable

		homePage.getDriverManager().driverWait();

		// Asser only 8 users displayed

		WebElement user1 = driverManager.getDriver().findElement(By.cssSelector(
				"#container > div > div > div > div > div > table > tbody > tr:nth-child(1) > td:nth-child(1) > a"));

		Assert.assertTrue(user1.isDisplayed());

		// wait for element is clickeable

		homePage.getDriverManager().driverWait();

		// Show 5 users

		driverManager.getDriver().findElement(By.cssSelector("#container > div > div > div > div > div > div > input"))
				.clear();

		driverManager.getDriver().findElement(By.cssSelector("#container > div > div > div > div > div > div > input"))
				.sendKeys("2");

		// wait for element is clickeable

		homePage.getDriverManager().driverWait();

		// Asser only 5 users displayed

		WebElement user2 = driverManager.getDriver().findElement(By.cssSelector(
				"#container > div > div > div > div > div > table > tbody > tr:nth-child(2) > td:nth-child(1) > a"));

		Assert.assertTrue(user2.isDisplayed());

		// wait for element is clickeable

		homePage.getDriverManager().driverWait();

		// Show 11 users

		driverManager.getDriver().findElement(By.cssSelector("#container > div > div > div > div > div > div > input"))
				.clear();

		driverManager.getDriver().findElement(By.cssSelector("#container > div > div > div > div > div > div > input"))
				.sendKeys("3");

		// wait for element is clickeable

		homePage.getDriverManager().driverWait();

		// Asser only 1 user displayed

		WebElement user3 = driverManager.getDriver().findElement(By.cssSelector(
				"#container > div > div > div > div > div > table > tbody > tr:nth-child(3) > td:nth-child(1) > a"));

		Assert.assertTrue(user3.isDisplayed());

		// wait for element is clickeable

		homePage.getDriverManager().driverWait();

		// Show 11 users

		driverManager.getDriver().findElement(By.cssSelector("#container > div > div > div > div > div > div > input"))
				.clear();

		driverManager.getDriver().findElement(By.cssSelector("#container > div > div > div > div > div > div > input"))
				.sendKeys("4");

		// wait for element is clickeable

		homePage.getDriverManager().driverWait();

		// Asser only 1 users displayed

		WebElement user4 = driverManager.getDriver().findElement(By.cssSelector(
				"#container > div > div > div > div > div > table > tbody > tr:nth-child(4) > td:nth-child(1) > a"));

		Assert.assertTrue(user4.isDisplayed());

		driverManager.getDriver().findElement(By.cssSelector("#container > div > div > div > div > div > div > input"))
		.clear();

        driverManager.getDriver().findElement(By.cssSelector("#container > div > div > div > div > div > div > input"))
		.sendKeys("4");
	}

	public void deleteUsers() {
		// Click on delete user

		usersPage.clickOnDeleteUserCreated();

		// wait for element is clickeable

		homePage.getDriverManager().driverWait();

		// Confirmation to delete user connected

		driverManager.getDriver()
				.findElement(By.cssSelector(
						"body > div.modal.fade.ng-isolate-scope.centered-dialog.in > div > div > div.modal-footer.ng-scope > button:nth-child(1)"))
				.click();

		// wait for element is clickeable

		homePage.getDriverManager().driverWait();

		// Click on delete user

		usersPage.clickOnDeleteUserCreated();

		// wait for element is clickeable

		homePage.getDriverManager().driverWait();

		// Confirmation to delete user connected

		driverManager.getDriver()
				.findElement(By.cssSelector(
						"body > div.modal.fade.ng-isolate-scope.centered-dialog.in > div > div > div.modal-footer.ng-scope > button:nth-child(1)"))
				.click();

		// wait for element is clickeable

		homePage.getDriverManager().driverWait();

		// Click on delete user

		usersPage.clickOnDeleteUserCreated();

	}

	@Test(priority = 0)

	public void usersPerPage() {

		// login to application

		loginPage.loginToCrafter("admin", "admin");

		// wait for element is clickeable

		homePage.getDriverManager().driverWait();

		// Create user 1

		createUserRandom();

		// Create user 2

		createUserRandom();

		// Create user 3

		createUserRandom();

		// wait for element is clickeable

		homePage.getDriverManager().driverWait();

		// filters

		filters();

		// wait for element is clickeable

		homePage.getDriverManager().driverWait();

		// Delete users block 1

		deleteUsers();

	}

}