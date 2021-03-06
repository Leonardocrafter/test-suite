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
package org.craftercms.studio.test.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UIElementsPropertiesManager {
	Properties sharedUIElementsLocators;

	public UIElementsPropertiesManager(String filePath) {
		sharedUIElementsLocators = new Properties();
		try {
			sharedUIElementsLocators.load(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Properties getSharedUIElementsLocators() {
		return sharedUIElementsLocators;
	}

	public void setSharedUIElementsLocators(Properties sharedUIElementsLocators) {
		this.sharedUIElementsLocators = sharedUIElementsLocators;
	}
	
	public Properties getSharedDataOfExecutionLocators() {
		return sharedUIElementsLocators;
	}

}
