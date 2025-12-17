# Auzaar

A modern Android e-commerce mobile application built with Jetpack Compose for creative shopping needs.

## Overview

Auzaar is a creative shopping app that allows users to browse and purchase products online. The app features a clean, modern UI built entirely with Jetpack Compose and leverages Firebase for authentication and cloud storage.

## Tech Stack

### Core Technologies
- **Language:** Kotlin
- **UI Framework:** Jetpack Compose (Material Design 3)
- **Architecture:** MVVM (Model-View-ViewModel)
- **Dependency Injection:** Dagger Hilt
- **Build System:** Gradle 7.1.2

### Libraries & Dependencies
- **Firebase**
  - Firebase Auth (v21.0.3) - User authentication
  - Firebase Firestore (v24.1.0) - Cloud database

- **Networking**
  - Retrofit 2.9.0 - HTTP client
  - OkHttp 5.0.0-alpha.2 - Network interceptor

- **Jetpack Components**
  - Navigation Compose (v2.4.0-alpha06)
  - Lifecycle & ViewModel
  - Coroutines (v1.6.0) - Asynchronous programming
  - LiveData (v2.4.1)
  - Paging Compose (v1.0.0-alpha12)

### Android SDK
- **Min SDK:** 21 (Android 5.0 Lollipop)
- **Target SDK:** 30 (Android 11)
- **Compile SDK:** 31

## Features

### Implemented
- User authentication (login/sign-up) with Firebase
- Animated splash screen with auth state routing
- Dashboard with bottom navigation
- Product browsing with categories (Sneakers, Jacket, Watch)
- Product search and filter UI
- Shopping cart UI
- Product details screen
- Navigation drawer with sign-out
- Custom Material Design 3 theme

### In Development
- Add to cart functionality (backend logic)
- Favorites/Wishlist system
- Product API integration
- Payment gateway
- Order management
- User profile editing

## Project Structure

```
app/src/main/java/com/example/auzaar/
├── MainActivity.kt                 # Entry point
├── MainViewModel.kt                # Main view model
├── app/
│   └── AuzaarApp.kt               # Hilt application
├── navigation/
│   ├── Screen.kt                  # Route definitions
│   └── Navigation.kt              # NavHost setup
├── view/                          # UI screens
│   ├── Dashboard.kt
│   ├── HomeScreen.kt
│   ├── AddToCartScren.kt
│   ├── ProductDetailsScreen.kt
│   ├── login/
│   ├── signUp/
│   ├── splashScreen/
│   └── components/
├── model/                         # Data layer
│   ├── User.kt
│   ├── Product.kt
│   ├── Response.kt                # Sealed class wrapper
│   ├── DataDummy.kt               # Mock data
│   └── repository/
│       ├── AuthRepository.kt
│       └── UserRepository.kt
├── component/                     # Reusable UI components
│   ├── BottomBar.kt
│   ├── TopAppBarWithBack.kt
│   └── NavDrawer.kt
└── ui/theme/                      # Design system
    ├── Color.kt
    ├── Type.kt
    └── Theme.kt
```

## Architecture

### MVVM Pattern
The app follows the Model-View-ViewModel architecture pattern:
- **View:** Jetpack Compose UI components
- **ViewModel:** Business logic and state management
- **Model:** Data classes and repository layer

### Repository Pattern
- `AuthRepository` - Handles Firebase authentication operations
- `UserRepository` - Manages Firestore user data operations

### State Management
Uses a `Response<T>` sealed class for handling async operations:
```kotlin
sealed class Response<out T> {
    object Loading : Response<Nothing>()
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val message: String) : Response<Nothing>()
}
```

## Setup Instructions

### Prerequisites
- Android Studio Arctic Fox or later
- JDK 11 or higher
- Firebase project with Auth and Firestore enabled

### Installation

1. Clone the repository
```bash
git clone https://github.com/yourusername/Auzaar.git
cd Auzaar
```

2. Open the project in Android Studio

3. Add your Firebase configuration
   - Download `google-services.json` from Firebase Console
   - Place it in the `app/` directory

4. Sync Gradle files

5. Run the app on an emulator or physical device

### Firebase Setup

1. Create a Firebase project at [Firebase Console](https://console.firebase.google.com/)
2. Enable Authentication with Email/Password provider
3. Create a Firestore database
4. Download and add `google-services.json` to your project

## Key Implementation Details

### Authentication Flow
1. Splash screen checks Firebase auth state
2. Authenticated users → Dashboard
3. Unauthenticated users → Login screen
4. Sign-up creates Firebase Auth account + Firestore user document

### Navigation
- Jetpack Navigation Compose for screen routing
- Sealed class for type-safe route definitions
- Bottom navigation for main sections
- Navigation drawer for settings

### Reactive Programming
- Kotlin Coroutines for async operations
- Flow for reactive data streams
- ViewModelScope for lifecycle-aware coroutines

## Current Status

**Version:** Early Development

The app has a solid foundation with working authentication and basic UI structure. Core shopping features are in development.

## Future Enhancements

- Real product API integration
- Payment processing
- Order tracking
- Push notifications
- Product reviews and ratings
- Social sharing
- Advanced search and filtering
- User profiles and preferences

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is open source and available under the [MIT License](LICENSE).

## Contact

For questions or feedback, please open an issue on GitHub.
