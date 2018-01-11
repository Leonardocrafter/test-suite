package org.craftercms.studio.test.cases.apitestcases;

import org.craftercms.studio.test.api.objects.ContentAssetAPI;
import org.craftercms.studio.test.api.objects.SecurityAPI;
import org.craftercms.studio.test.api.objects.SiteManagementAPI;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SetItemStateAPITest {

    private ContentAssetAPI contentAssetAPI;
    private SecurityAPI securityAPI;
    private SiteManagementAPI siteManagementAPI;
    
    public SetItemStateAPITest(){
    	APIConnectionManager apiConnectionManager = new APIConnectionManager();
		JsonTester api = new JsonTester(apiConnectionManager.getProtocol(), apiConnectionManager.getHost(),
				apiConnectionManager.getPort());
		
		securityAPI = new SecurityAPI(api, apiConnectionManager);
    	contentAssetAPI = new ContentAssetAPI(api, apiConnectionManager);
    	siteManagementAPI = new SiteManagementAPI(api, apiConnectionManager);
    }
    
    @BeforeTest
    public void beforeTest(){
    	securityAPI.logInIntoStudioUsingAPICall();
    	siteManagementAPI.testCreateSite(siteManagementAPI.getSiteId());
    	contentAssetAPI.testWriteContent(siteManagementAPI.getSiteId());
    }
    
    @Test(priority=1)
    public void testSetItemState(){
    	
    	contentAssetAPI.testSetItemState(siteManagementAPI.getSiteId());
    }
    
    @AfterTest
    public void afterTest(){
    	siteManagementAPI.testDeleteSite(siteManagementAPI.getSiteId());
    	securityAPI.logOutFromStudioUsingAPICall();
    }
    
}