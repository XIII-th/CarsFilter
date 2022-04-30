# Cars filter

## ℹ️ About
The application implements simple car's filter selection flow:
1. Select manufacturer
2. Select car model
3. Select build year
4. Show summary

## ✨ Features of implementation
- Regardless of the task's simplicity, the app is separated into different modules to show how to develop each type of a filter independently and navigate between them.
- Notifications and auto reloading implemented to help users when connection is lost. Backend provides filters (remote source of truth).
- Pure color's theming supported. Direct links to the colors only as theme's properties.

## 📚 Application stack
- Kotlin coroutines & Flow
- Hilt
- MVVM
- Paging 3
- Android Navigation
- OkHTTP & Retrofit 2 & Moshi
- Material design

## ⚒️ Build configuration
This client application can work with a custom backend. For this purpose it has build properties, you need to add into `gradle.properties` file or as the CI properties.
```properties
# The path to a base URL 
BASE_URL="https://my-backend.com"

# The name of an api key property. This key will be attached to each request with API_VALUE 
API_KEY="my_backend_api_key"
# A secret api key
API_VALUE="my_backend_api_key_value"

# The name of the container properties which contains a key-value (id, name) pairs of list items
JSON_CONTENT_FIELD="my_backend_content_field"
```
#### Page model
This sample of a page model your backend should send.
```kotlin
data class ManufacturersPage(
    val page: Int,
    val totalPageCount: Int,
    @field:Json(name = BuildConfig.JSON_CONTENT_FIELD)
    val manufacturers: Map<String, String>
)
```

## 📝 TODO list
- Implement caching of filters lists into local database 
- Provide test coverage
