/*
 * Swagger role-service
 * This is a sample server role-service.
 *
 * The version of the OpenAPI document: 1.0.0
 * Contact: wit2022.v1@gmail.com>
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.openapitools.client.ApiCallback;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.Configuration;
import org.openapitools.client.Pair;
import org.openapitools.client.ProgressRequestBody;
import org.openapitools.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import org.openapitools.client.model.Role;
import org.openapitools.client.model.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleApi {
    private ApiClient localVarApiClient;

    public RoleApi() {
        this(Configuration.getDefaultApiClient());
    }

    public RoleApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for addRole
     * @param body Role object that needs to be added to the service (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 405 </td><td> Invalid input </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call addRoleCall(Role body, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = body;

        // create path and map variables
        String localVarPath = "/createRole";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call addRoleValidateBeforeCall(Role body, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new ApiException("Missing the required parameter 'body' when calling addRole(Async)");
        }
        

        okhttp3.Call localVarCall = addRoleCall(body, _callback);
        return localVarCall;

    }

    /**
     * Add a new role to the service
     * 
     * @param body Role object that needs to be added to the service (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 405 </td><td> Invalid input </td><td>  -  </td></tr>
     </table>
     */
    public void addRole(Role body) throws ApiException {
        addRoleWithHttpInfo(body);
    }

    /**
     * Add a new role to the service
     * 
     * @param body Role object that needs to be added to the service (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 405 </td><td> Invalid input </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> addRoleWithHttpInfo(Role body) throws ApiException {
        okhttp3.Call localVarCall = addRoleValidateBeforeCall(body, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Add a new role to the service (asynchronously)
     * 
     * @param body Role object that needs to be added to the service (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 405 </td><td> Invalid input </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call addRoleAsync(Role body, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = addRoleValidateBeforeCall(body, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for deleteRole
     * @param rolesName Roles name that need to be considered for filter (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 400 </td><td> Invalid roles name value </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteRoleCall(String rolesName, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/role/deleteByRolesName/{rolesName}"
            .replaceAll("\\{" + "rolesName" + "\\}", localVarApiClient.escapeString(rolesName.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deleteRoleValidateBeforeCall(String rolesName, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'rolesName' is set
        if (rolesName == null) {
            throw new ApiException("Missing the required parameter 'rolesName' when calling deleteRole(Async)");
        }
        

        okhttp3.Call localVarCall = deleteRoleCall(rolesName, _callback);
        return localVarCall;

    }

    /**
     * Deletes a role
     * 
     * @param rolesName Roles name that need to be considered for filter (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 400 </td><td> Invalid roles name value </td><td>  -  </td></tr>
     </table>
     */
    public void deleteRole(String rolesName) throws ApiException {
        deleteRoleWithHttpInfo(rolesName);
    }

    /**
     * Deletes a role
     * 
     * @param rolesName Roles name that need to be considered for filter (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 400 </td><td> Invalid roles name value </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> deleteRoleWithHttpInfo(String rolesName) throws ApiException {
        okhttp3.Call localVarCall = deleteRoleValidateBeforeCall(rolesName, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Deletes a role (asynchronously)
     * 
     * @param rolesName Roles name that need to be considered for filter (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 400 </td><td> Invalid roles name value </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call deleteRoleAsync(String rolesName, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteRoleValidateBeforeCall(rolesName, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for findRoleByRolesName
     * @param rolesName Roles name that need to be considered for filter (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid roles name value </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call findRoleByRolesNameCall(String rolesName, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/role/findByName/{rolesName}"
            .replaceAll("\\{" + "rolesName" + "\\}", localVarApiClient.escapeString(rolesName.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call findRoleByRolesNameValidateBeforeCall(String rolesName, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'rolesName' is set
        if (rolesName == null) {
            throw new ApiException("Missing the required parameter 'rolesName' when calling findRoleByRolesName(Async)");
        }
        

        okhttp3.Call localVarCall = findRoleByRolesNameCall(rolesName, _callback);
        return localVarCall;

    }

    /**
     * Finds Role by roles name
     * 
     * @param rolesName Roles name that need to be considered for filter (required)
     * @return String
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid roles name value </td><td>  -  </td></tr>
     </table>
     */
    public String findRoleByRolesName(String rolesName) throws ApiException {
        ApiResponse<String> localVarResp = findRoleByRolesNameWithHttpInfo(rolesName);
        return localVarResp.getData();
    }

    /**
     * Finds Role by roles name
     * 
     * @param rolesName Roles name that need to be considered for filter (required)
     * @return ApiResponse&lt;String&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid roles name value </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<String> findRoleByRolesNameWithHttpInfo(String rolesName) throws ApiException {
        okhttp3.Call localVarCall = findRoleByRolesNameValidateBeforeCall(rolesName, null);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Finds Role by roles name (asynchronously)
     * 
     * @param rolesName Roles name that need to be considered for filter (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> successful operation </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> Invalid roles name value </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call findRoleByRolesNameAsync(String rolesName, final ApiCallback<String> _callback) throws ApiException {

        okhttp3.Call localVarCall = findRoleByRolesNameValidateBeforeCall(rolesName, _callback);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for updateRole
     * @param body Role object that needs to be added to the service (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 400 </td><td> Invalid roles name supplied </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Roles name not found </td><td>  -  </td></tr>
        <tr><td> 405 </td><td> Validation exception </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call updateRoleCall(User body, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = body;

        // create path and map variables
        String localVarPath = "/updateRole";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(localVarPath, "PUT", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call updateRoleValidateBeforeCall(User body, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new ApiException("Missing the required parameter 'body' when calling updateRole(Async)");
        }
        

        okhttp3.Call localVarCall = updateRoleCall(body, _callback);
        return localVarCall;

    }

    /**
     * Update an existing role
     * 
     * @param body Role object that needs to be added to the service (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 400 </td><td> Invalid roles name supplied </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Roles name not found </td><td>  -  </td></tr>
        <tr><td> 405 </td><td> Validation exception </td><td>  -  </td></tr>
     </table>
     */
    public void updateRole(User body) throws ApiException {
        updateRoleWithHttpInfo(body);
    }

    /**
     * Update an existing role
     * 
     * @param body Role object that needs to be added to the service (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 400 </td><td> Invalid roles name supplied </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Roles name not found </td><td>  -  </td></tr>
        <tr><td> 405 </td><td> Validation exception </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<Void> updateRoleWithHttpInfo(User body) throws ApiException {
        okhttp3.Call localVarCall = updateRoleValidateBeforeCall(body, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Update an existing role (asynchronously)
     * 
     * @param body Role object that needs to be added to the service (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 400 </td><td> Invalid roles name supplied </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Roles name not found </td><td>  -  </td></tr>
        <tr><td> 405 </td><td> Validation exception </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call updateRoleAsync(User body, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = updateRoleValidateBeforeCall(body, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
}
