# Df Android Guideline

**DESCRIPTION**

- Android App included all up-to-date Android Jetpack and best practise during development lifecycle

**TECHSTACKS**

- Language: Kotlin
- Architecture: MVVM
- Libraries & Dependences: 
    - UI: Navigation, RecycleView, ViewBinding
    - Coroutine, Kotlin Flow: Executes asynchronously
    - Interact Restful API: Retrofit, OkHttp, Gson
    - Dependency Injection: Dagger-Hilt, 
    - Support MVVM Architecture: lifecycle ViewModel, Kotlin Flow
    - Testing: JUnit Test, AndroidX Test Core, Mockito

**PROJECT STRUCTURE**

- Project structure follows MVVM architecture: 

    - Data, Service, Repository: help interact Restful API
    - DI: support dependency injection using dagger-Hilt
    - Data: come from service
    - Entity: come from database
    - Domain: For update UI
    - Test: It includes unit test for data and ui testing

**CODE QUALITY ANALYSIS**
- Coming soon

**TESTING**
- Coming soon

**CI/CD**
- Coming soon