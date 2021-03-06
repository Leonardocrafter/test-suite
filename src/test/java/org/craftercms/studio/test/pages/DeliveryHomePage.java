/*
 * Copyright (C) 2007-2018 Crafter Software Corporation. All Rights Reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.craftercms.studio.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.craftercms.studio.test.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Luis Hernandez
 *
 */

public class DeliveryHomePage {

	private WebDriverManager driverManager;
	private WebDriver driver;
	private static Logger logger = LogManager.getLogger(DeliveryHomePage.class);

	public DeliveryHomePage(WebDriverManager driverManager,String siteId) {
		this.driverManager = driverManager;
		logger.info("Go to delivery site");
		this.driverManager.openConnectionAndGotoDelivery(siteId);
		this.driver = this.driverManager.getDriver();
	}

	public DeliveryHomePage(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriverManager getDriverManager() {
		return driverManager;
	}

	public void setDriverManager(WebDriverManager driverManager) {
		this.driverManager = driverManager;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
}