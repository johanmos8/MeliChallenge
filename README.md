# Meli
Android App for "Mercado Libre" store using latest Architecture components and Dependency Injection

## Preview for first approach


## Technologies

### Libraries
- **Android ViewModels**
- **Android Flow:** Get list of items
- **Dagger-Hilt:** Dependency injection
- **Retrofit:** Api consumption
- **Gson**: Map responses from requests
- **Okhttp3-logging-interceptor:** An OkHttp interceptor that logs HTTP request and response data.
- **Mockito:** Mocks in testing
- **Coil:** Load Images
- **Compose:** Create UI
- **Compose-navigation:** Helps navigate between composable destinations.
- 
### Background Strategy:
- **Kotlin Coroutines**

### Code Quiality
**Unit Testing**: Created unit tests for the modules using Mockito and Junit.

## Architecture
- Using **Clean Architecture** for the whole application with 3 layers: Data, Domain, presentation  and using **MVVM** for the presentation layer.

### UI Layer

1. Presentation Pattern: MVVM
2. Unidirectional Data Flow
3. MVVM Observables with State (Compose)
4. UI States: data class
5. UI Events: sealed class
6. Navigation

### Data Layer

1. Dependency Inversion 
2. Repositories and Data Sources
3. Error Handling

### TODO
  **Add to cart** : Add selected item to a cart
  **Model error with more details** : Add new models for errors to handle it in a specific way
- **UI Testing**: Created unit test for UI.
- **BuildSrc Strategy**: Improve dependencies build sources
- **Animations**: Add animations to UI

