/**
 * 
 */
package org.craftercms.studio.test.api.objects;

import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.Map;

import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.JsonTester;

/**
 * @author luishernandez
 *
 */
public class GroupManagementAPI extends BaseAPI {

	private String groupName1 = "contributors01";
	private String groupName2 = "contributors02";
	private String description = "description";

	public GroupManagementAPI(JsonTester api, APIConnectionManager apiConnectionManager) {
		super(api, apiConnectionManager);
	}

	public void testCreateStudioGroup01(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_name", groupName1);
		json.put("site_id", siteId);
		json.put("description", description);

		api.post("/studio/api/1/services/api/1/group/create.json").json(json).execute().status(201)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/group/get.json?group_name=" + groupName1))
				.json("$.message", is("OK")).debug();
	}

	public void testCreateStudioGroup02(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_name", groupName2);
		json.put("site_id", siteId);
		json.put("description", description);

		api.post("/studio/api/1/services/api/1/group/create.json").json(json).execute().status(201)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/group/get.json?group_name=" + groupName2))
				.json("$.message", is("OK")).debug();
	}

	public void testAddUserToGroup01(String newUserName, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newUserName);
		json.put("group_name", groupName1);
		json.put("site_id", siteId);

		api.post("/studio/api/1/services/api/1/group/add-user.json").json(json).execute().status(200)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/group/get.json?group_name=" + groupName1))
				.json("$.message", is("OK")).debug();
	}

	public void testAddUserToGroup02(String newUserName, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", newUserName);
		json.put("group_name", groupName2);
		json.put("site_id", siteId);

		api.post("/studio/api/1/services/api/1/group/add-user.json").json(json).execute().status(200)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/group/get.json?group_name=" + groupName2))
				.json("$.message", is("OK")).debug();
	}

	public void testCreateStudioGroupInvalidParameters(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_nameInvalidParameter", groupName1);
		json.put("site_id", siteId);
		json.put("description", description);

		api.post("/studio/api/1/services/api/1/group/create.json").json(json).execute().status(400)
				.json("$.message", is("Invalid parameter(s): [group_name]")).debug();

	}

	public void testCreateStudioGroupAlreadyExists(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_name", groupName1);
		json.put("site_id", siteId);
		json.put("description", description);

		api.post("/studio/api/1/services/api/1/group/create.json").json(json).execute().status(409)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/group/get.json?group_name=" + groupName1))
				.json("$.message", is("Group already exists")).debug();

	}

	public void testGetGroup(String siteId) {
		api.get("/studio/api/1/services/api/1/group/get.json").urlParam("group_name", groupName1)
				.urlParam("site_id", siteId).execute().status(200).header("Location", is(headerLocationBase
						+ "/studio/api/1/services/api/1/get/get.json?site_id=" + siteId + "&group_name=" + groupName1));

	}

	public void testGetGroupInvalidParameter(String siteId) {
		api.get("/studio/api/1/services/api/1/group/get.json").urlParam("group_name", groupName1)
				.urlParam("site_idnonvalid", siteId).execute().status(400)
				.json("$.message", is("Invalid parameter(s): [site_id]")).debug();
	}

	public void testGetGroupGroupNotFound(String siteId) {
		api.get("/studio/api/1/services/api/1/group/get.json").urlParam("group_name", groupName1 + "nonvalid")
				.urlParam("site_id", siteId).execute().status(404).json("$.message", is("Group not found")).debug();

	}

	public void testGetGroups() {
		Map<String, Object> json = new HashMap<>();
		json.put("start", "0");
		json.put("number", "25");

		api.get("/studio/api/1/services/api/1/group/get-all.json").json(json).execute().status(200).header("Location",
				is(headerLocationBase + "/studio/api/1/services/api/1/group/get-all.json?start=0&number=25"));
	}

	public void testGetUsersPerGroup(String siteId) {
		api.get("/studio/api/1/services/api/1/group/users.json").urlParam("group_name", groupName1)
				.urlParam("site_id", siteId).execute().status(200)
				.header("Location", is(headerLocationBase + "/studio/api/1/services/api/1/group/users.json?site_id="
						+ siteId + "&group_name=" + this.groupName1 + "&start=0&number=25"));
	}

	public void testGetUsersPerGroupSiteNotFound(String siteId) {
		api.get("/studio/api/1/services/api/1/group/users.json").urlParam("group_name", groupName1)
				.urlParam("site_id", siteId + "nonvalid").execute().status(404).debug();

	}

	// TODO: Uncomment the check for the location after verify this:
	// https://github.com/craftercms/craftercms/issues/1637
	public void testGetGroupsPerSite(String siteId) {
		api.get("/studio/api/1/services/api/1/group/get-per-site.json").urlParam("site_id", siteId).execute()
				.status(200);
		// .header("Location",is(headerLocationBase+"/studio/api/1/services/api/1/group/get-per-site.json?site_id="+siteId+"&start=0&number=25"));
	}

	public void testUpdateGroup(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_name", groupName1);
		json.put("site_id", siteId);
		json.put("description", description + "updated");

		api.post("/studio/api/1/services/api/1/group/update.json").json(json).execute().status(200)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/group/get.json?group_name=" + groupName1))
				.json("$.message", is("OK")).debug();
	}

	public void testUpdateGroupInvalidParameters(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_namenonvalid", groupName1);
		json.put("site_id", siteId);
		json.put("description", description + "updated");

		api.post("/studio/api/1/services/api/1/group/update.json").json(json).execute().status(400)
				.json("$.message", is("Invalid parameter(s): [group_name]")).debug();
	}

	public void testUpdateGroupGroupNotFound(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_name", groupName1 + "nonvalid");
		json.put("site_id", siteId);
		json.put("description", description + "updated");

		api.post("/studio/api/1/services/api/1/group/update.json").execute().status(404)
				.json("$.message", is("Group not found")).debug();
	}

	public void testDeleteGroup(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_name", groupName1);
		json.put("site_id", siteId);
		api.post("/studio/api/1/services/api/1/group/delete.json").json(json).execute().status(204);
	}

	public void testDeleteGroupInvalidParameters(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_namenonvalid", groupName1);
		json.put("site_id", siteId);

		api.post("/studio/api/1/services/api/1/group/delete.json").json(json).execute().status(400)
				.json("$.message", is("Invalid parameter(s): [group_name]")).debug();
	}

	public void testDeleteGroupGroupNotFound(String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("group_name", groupName1 + "nonvalid");
		json.put("site_id", siteId);

		api.post("/studio/api/1/services/api/1/group/delete.json").json(json).execute().status(404)
				.json("$.message", is("Group not found")).debug();

	}

	public void testAddUserToGroup(String username, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", username);
		json.put("group_name", groupName1);
		json.put("site_id", siteId);
		api.post("/studio/api/1/services/api/1/group/add-user.json").json(json).execute().status(200)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/group/get.json?group_name=" + groupName1))
				.json("$.message", is("OK")).debug();
	}

	public void testAddUserToGroupInvalidParameters(String username, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("usernameInvalid", username);
		json.put("group_name", groupName1);
		json.put("site_id", siteId);
		api.post("/studio/api/1/services/api/1/group/add-user.json").json(json).execute().status(400)
				.json("$.message", is("Invalid parameter(s): [username]")).debug();

	}

	public void testAddUserToGroupUserNotFound(String username, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", username + "non-valid");
		json.put("group_name", groupName1);
		json.put("site_id", siteId);
		api.post("/studio/api/1/services/api/1/group/add-user.json").json(json).execute().status(404)
				.json("$.message", is("User not found")).debug();

	}

	public void testAddUserToGroupGroupNotFound(String username, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", username);
		json.put("group_name", groupName1 + "non-valid");
		json.put("site_id", siteId);
		api.post("/studio/api/1/services/api/1/group/add-user.json").json(json).execute().status(404)
				.json("$.message", is("Group not found")).debug();

	}

	public void testAddUserToGroupAlreadyInGroup(String username, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", username);
		json.put("group_name", groupName1);
		json.put("site_id", siteId);

		api.post("/studio/api/1/services/api/1/group/add-user.json").json(json).execute().status(409)
				.header("Location",
						is(headerLocationBase + "/studio/api/1/services/api/1/group/get.json?group_name=" + groupName1))
				.json("$.message", is("User already in group")).debug();
	}

	public void testRemoveUserFromGroup(String username, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", username);
		json.put("group_name", groupName1);
		json.put("site_id", siteId);

		api.post("/studio/api/1/services/api/1/group/remove-user.json").json(json).execute().status(204);
	}

	public void testRemoveUserFromGroupInvalidParameters(String username, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("usernamenonvalid", username);
		json.put("group_namenonvalid", groupName1);
		json.put("site_idnonvalid", siteId);

		api.post("/studio/api/1/services/api/1/group/remove-user.json").json(json).execute().status(400)
				.json("$.message", is("Invalid parameter(s): [group_name, site_id, username]"));
	}

	public void testRemoveUserFromGroupGroupNotFound(String username, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", username);
		json.put("group_name", groupName1 + "nonvalid");
		json.put("site_id", siteId);

		api.post("/studio/api/1/services/api/1/group/remove-user.json").json(json).execute().status(404)
				.json("$.message", is("Group not found"));

	}

	public void testRemoveUserFromGroupUserNotFound(String username, String siteId) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", username + "nonvalid");
		json.put("group_name", groupName1);
		json.put("site_id", siteId);

		api.post("/studio/api/1/services/api/1/group/remove-user.json")
				.json(json).execute().status(404).json("$.message", is("User not found"));

	}

}
