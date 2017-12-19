package org.craftercms.studio.test.cases.apitestcases;

import org.craftercms.studio.test.api.objects.SecurityAPI;
import org.craftercms.studio.test.api.objects.SiteManagementAPI;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by chris lim
 */

public class GetUserRolesAPITest {

    private SecurityAPI securityAPI;
    private SiteManagementAPI siteManagementAPI;
    private String siteId = "getUserRolesSiteTest";
    
    public GetUserRolesAPITest(){
    	APIConnectionManager apiConnectionManager = new APIConnectionManager();
		JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
				apiConnectionManager.getPort());
    	
    	securityAPI = new SecurityAPI(api, apiConnectionManager);
    	siteManagementAPI = new SiteManagementAPI(api, apiConnectionManager);
    }

    @BeforeTest
    public void beforeTest(){
    	securityAPI.logInIntoStudioUsingAPICall();
    	siteManagementAPI.testCreateSite(siteId);
    }
    
    @Test(priority=1)
    public void testGetUserRoles(){
    	
    	securityAPI.testGetUserRoles(siteId);
    }
    
    @AfterTest
    public void afterTest(){
    	siteManagementAPI.testDeleteSite(siteId);
    	securityAPI.logOutFromStudioUsingAPICall();
    }
 
}