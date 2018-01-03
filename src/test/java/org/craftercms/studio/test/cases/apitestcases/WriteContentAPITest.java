package org.craftercms.studio.test.cases.apitestcases;

import org.craftercms.studio.test.api.objects.ContentAssetAPI;
import org.craftercms.studio.test.api.objects.SecurityAPI;
import org.craftercms.studio.test.api.objects.SiteManagementAPI;
import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WriteContentAPITest {

    private ContentAssetAPI contentAssetAPI;
    private SecurityAPI securityAPI;
    private SiteManagementAPI siteManagementAPI;
    
    public WriteContentAPITest(){
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
    }
    
    @Test(priority=1)
    public void testWriteContent(){
    	
    	contentAssetAPI.testWriteContent(siteManagementAPI.getSiteId());
    	
    }
    
    @AfterTest
    public void afterTest(){
    	siteManagementAPI.testDeleteSite(siteManagementAPI.getSiteId());
    	securityAPI.logOutFromStudioUsingAPICall();
    }
    
}
