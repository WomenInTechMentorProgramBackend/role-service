# PermissionApi

All URIs are relative to *http://medicalcenter.roleservice/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addPermission**](PermissionApi.md#addPermission) | **POST** /createPermission | Add a new permission to the service
[**deletePermission**](PermissionApi.md#deletePermission) | **DELETE** /user/deleteByPermissionsName/{PermissionsName} | Deletes a permission
[**findPermissionByPermissionsName**](PermissionApi.md#findPermissionByPermissionsName) | **GET** /permission/findByName/{permissionName} | Finds permission by permission name
[**updatePermission**](PermissionApi.md#updatePermission) | **PUT** /updatePermission | Update an existing permission


<a name="addPermission"></a>
# **addPermission**
> addPermission(body)

Add a new permission to the service

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PermissionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://medicalcenter.roleservice/v1");

    PermissionApi apiInstance = new PermissionApi(defaultClient);
    Permission body = new Permission(); // Permission | Permission object that needs to be added to the service
    try {
      apiInstance.addPermission(body);
    } catch (ApiException e) {
      System.err.println("Exception when calling PermissionApi#addPermission");
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
 **body** | [**Permission**](Permission.md)| Permission object that needs to be added to the service |

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

<a name="deletePermission"></a>
# **deletePermission**
> deletePermission(permissionsName)

Deletes a permission

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PermissionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://medicalcenter.roleservice/v1");

    PermissionApi apiInstance = new PermissionApi(defaultClient);
    String permissionsName = "permissionsName_example"; // String | Permissions name that need to be considered for filter
    try {
      apiInstance.deletePermission(permissionsName);
    } catch (ApiException e) {
      System.err.println("Exception when calling PermissionApi#deletePermission");
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
 **permissionsName** | **String**| Permissions name that need to be considered for filter |

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
**400** | Invalid Permissions name value |  -  |

<a name="findPermissionByPermissionsName"></a>
# **findPermissionByPermissionsName**
> String findPermissionByPermissionsName(permissionName)

Finds permission by permission name

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PermissionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://medicalcenter.roleservice/v1");

    PermissionApi apiInstance = new PermissionApi(defaultClient);
    String permissionName = "permissionName_example"; // String | Permission name that need to be considered for filter
    try {
      String result = apiInstance.findPermissionByPermissionsName(permissionName);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PermissionApi#findPermissionByPermissionsName");
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
 **permissionName** | **String**| Permission name that need to be considered for filter |

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

<a name="updatePermission"></a>
# **updatePermission**
> updatePermission(body)

Update an existing permission

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PermissionApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://medicalcenter.roleservice/v1");

    PermissionApi apiInstance = new PermissionApi(defaultClient);
    Permission body = new Permission(); // Permission | Permission object that needs to be added to the service
    try {
      apiInstance.updatePermission(body);
    } catch (ApiException e) {
      System.err.println("Exception when calling PermissionApi#updatePermission");
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
 **body** | [**Permission**](Permission.md)| Permission object that needs to be added to the service |

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

