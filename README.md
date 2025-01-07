# MVVM Clean Architecture with Hilt and Retrofit

This is an Android application that follows the **MVVM (Model-View-ViewModel)** pattern, implemented using **Clean Architecture** principles, and utilizing **Hilt** for dependency injection, **Retrofit** for network requests, and **RecyclerView** to display data. The app demonstrates how to make API calls and display data in a list.

## Features

- **Login and Signup** functionality using an API.
- Fetch and display a list of users using a **RecyclerView**.
- Clean architecture separation with **Use Cases**, **Repositories**, and **ViewModels**.
- Dependency Injection using **Hilt**.
- Networking with **Retrofit** and **Gson**.

## Structure of the project

/MyApp
│
├── /app
│   ├── /src
│   │   ├── /main
│   │   │   ├── /java
│   │   │   │   └── /com
│   │   │   │       └── /myapp
│   │   │   │           ├── /data                   # Data Layer
│   │   │   │           │   ├── /model               # Data models (DTOs, Entities)
│   │   │   │           │   ├── /repository          # Repository interfaces and implementations
│   │   │   │           │   ├── /remote              # Remote data sources (API calls)
│   │   │   │           │   └── /local               # Local data sources (Room, SharedPreferences)
│   │   │   │           │
│   │   │   │           ├── /domain                 # Domain Layer
│   │   │   │           │   ├── /model               # Domain models
│   │   │   │           │   ├── /repository          # Repository interfaces (abstract)
│   │   │   │           │   ├── /usecase             # UseCases (application business logic)
│   │   │   │           │   └── /domain_repository   # Domain repository implementation
│   │   │   │           │
│   │   │   │           ├── /presentation           # Presentation Layer (UI/ ViewModel)
│   │   │   │           │   ├── /viewmodels          # ViewModel classes (handles UI data)
│   │   │   │           │   ├── /ui                  # UI components (Activity/Fragment)
│   │   │   │           │   └── /adapter             # Adapters, etc.
│   │   │   │           │
│   │   │   │           ├── /utils                  # Utility classes (helpers)
│   │   │   │           │   └── NetworkUtils.kt      # Network-related utility methods
│   │   │   │           │
│   │   │   │           └── /MyApp.kt               # Main Application class (if needed)
│   │   │   └── /res
│   │   │   └── /AndroidManifest.xml
│   │   └── /build.gradle
│   ├── /gradle
│   └── /build.gradle
└── /settings.gradle


---

## Prerequisites

Before you can run this app, you need to have the following installed:

- **Android Studio**: For building and running the app.
- **Java 11** or higher: Required for the app to compile.

---


