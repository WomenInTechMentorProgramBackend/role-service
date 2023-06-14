# RoleApi

All URIs are relative to *http://medicalcenter.roleservice/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addRole**](RoleApi.md#addRole) | **POST** /createRole | Add a new role to the service
[**deleteRole**](RoleApi.md#deleteRole) | **DELETE** /role/deleteByRolesName/{rolesName} | Deletes a role
[**findRoleByRolesName**](RoleApi.md#findRoleByRolesName) | **GET** /role/findByName/{rolesName} | Finds Role by roles name
[**updateRole**](RoleApi.md#updateRole) | **PUT** /updateRole | Update an existing role


<a name="addRole"></a>
# **addRole**
> addRole(body)

Add a new role to the service

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.RoleApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://medicalcenter.roleservice/v1");

    RoleApi apiInstance = new RoleApi(defaultClient);
    Role body = new Role(); // Role | Role object that needs to be added to the service
    try {
      apiInstance.addRole(body);
    } catch (ApiException e) {
      System.err.println("Exception when calling RoleApi#addRole");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**Role**](Role.md)| Role object that needs to be added to the service |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**405** | Invalid input |  -  |

<a name="deleteRole"></a>
# **deleteRole**
> deleteRole(rolesName)

Deletes a role

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.RoleApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://medicalcenter.roleservice/v1");

    RoleApi apiInstance = new RoleApi(defaultClient);
    String rolesName = "rolesName_example"; // String | Roles name that need to be considered for filter
    try {
      apiInstance.deleteRole(rolesName);
    } catch (ApiException e) {
      System.err.println("Exception when calling RoleApi#deleteRole");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **rolesName** | **String**| Roles name that need to be considered for filter |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**400** | Invalid roles name value |  -  |

<a name="findRoleByRolesName"></a>
# **findRoleByRolesName**
> String findRoleByRolesName(rolesName)

Finds Role by roles name

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.RoleApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://medicalcenter.roleservice/v1");

    RoleApi apiInstance = new RoleApi(defaultClient);
    String rolesName = "rolesName_example"; // String | Roles name that need to be considered for filter
    try {
      String result = apiInstance.findRoleByRolesName(rolesName);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling RoleApi#findRoleByRolesName");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **rolesName** | **String**| Roles name that need to be considered for filter |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | successful operation |  -  |
**400** | Invalid roles name value |  -  |

<a name="updateRole"></a>
# **updateRole**
> updateRole(body)

Update an existing role

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.RoleApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://medicalcenter.roleservice/v1");

    RoleApi apiInstance = new RoleApi(defaultClient);
    User body = new User(); // User | Role object that needs to be added to the service
    try {
      apiInstance.updateRole(body);
    } catch (ApiException e) {
      System.err.println("Exception when calling RoleApi#updateRole");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**User**](User.md)| Role object that needs to be added to the service |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**400** | Invalid roles name supplied |  -  |
**404** | Roles name not found |  -  |
**405** | Validation exception |  -  |

