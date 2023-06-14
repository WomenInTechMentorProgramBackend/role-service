# UserApi

All URIs are relative to *http://medicalcenter.roleservice/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addUser**](UserApi.md#addUser) | **POST** /createUser | Add a new user to the service
[**deleteUser**](UserApi.md#deleteUser) | **DELETE** /user/deleteByExternalID/{externalID} | Deletes a user
[**findUserByExternalID**](UserApi.md#findUserByExternalID) | **GET** /user/findByExternalID/{externalID} | Finds User by external id
[**updateUser**](UserApi.md#updateUser) | **PUT** /updateUser | Update an existing user


<a name="addUser"></a>
# **addUser**
> addUser(body)

Add a new user to the service

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://medicalcenter.roleservice/v1");

    UserApi apiInstance = new UserApi(defaultClient);
    User body = new User(); // User | User object that needs to be added to the service
    try {
      apiInstance.addUser(body);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#addUser");
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
 **body** | [**User**](User.md)| User object that needs to be added to the service |

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

<a name="deleteUser"></a>
# **deleteUser**
> deleteUser(externalID)

Deletes a user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://medicalcenter.roleservice/v1");

    UserApi apiInstance = new UserApi(defaultClient);
    String externalID = "externalID_example"; // String | External id that need to be considered for filter
    try {
      apiInstance.deleteUser(externalID);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#deleteUser");
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
 **externalID** | **String**| External id that need to be considered for filter |

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
**400** | Invalid user name value |  -  |

<a name="findUserByExternalID"></a>
# **findUserByExternalID**
> String findUserByExternalID(externalID)

Finds User by external id

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://medicalcenter.roleservice/v1");

    UserApi apiInstance = new UserApi(defaultClient);
    String externalID = "externalID_example"; // String | External id that need to be considered for filter
    try {
      String result = apiInstance.findUserByExternalID(externalID);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#findUserByExternalID");
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
 **externalID** | **String**| External id that need to be considered for filter |

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
**400** | Invalid externalID value |  -  |

<a name="updateUser"></a>
# **updateUser**
> updateUser(body)

Update an existing user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://medicalcenter.roleservice/v1");

    UserApi apiInstance = new UserApi(defaultClient);
    User body = new User(); // User | User object that needs to be added to the service
    try {
      apiInstance.updateUser(body);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#updateUser");
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
 **body** | [**User**](User.md)| User object that needs to be added to the service |

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
**400** | Invalid ID supplied |  -  |
**404** | User not found |  -  |
**405** | Validation exception |  -  |

