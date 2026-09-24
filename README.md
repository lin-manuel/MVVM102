# MVVM102

An Android app built with **Kotlin** and **Jetpack Compose**, demonstrating the **MVVM (Model-View-ViewModel)** architecture pattern with Firebase Authentication.

## Firebase setup

The Firebase configuration is intentionally kept out of version control. Download a new `google-services.json` for the Android app from the Firebase console and place it at `app/google-services.json` before building.

Use `app/google-services.json.example` as a structural reference. Do not commit the downloaded configuration.

## Features

- **Authentication** — Register, log in, and reset password via Firebase Auth
- **Navigation** — Type-safe, Compose-based navigation between screens
- **MVVM architecture** — Clear separation between UI (screens), state/logic (ViewModels), and data (repositories/models)
- **Lottie animations** — Used on the authentication screens

## Screens

| Screen | Description |
|---|---|
| Register | Create a new account |
| Login | Sign in with email and password |
| Password Reset | Send a password reset email |
| Home | Landing screen after authentication |
| About | App info screen |

## Tech Stack

- Kotlin
- Jetpack Compose (Material 3)
- Navigation Compose
- Firebase Authentication
- Kotlinx Serialization
- Lottie for Compose
- JUnit / Espresso (testing)

## Project Structure

```
app/src/main/java/com/jayr/mvvm101/
├── data/
│   ├── models/          # Data models (e.g. StudentModel)
│   └── repository/      # AuthRepository — wraps Firebase Auth calls
├── ui/
│   ├── Navigation/      # Routes and NavHost setup
│   ├── screens/
│   │   ├── authentication/  # Login, Register, Password Reset + shared ViewModel
│   │   ├── home/             # Home screen + ViewModel
│   │   └── about/             # About screen + ViewModel
│   └── theme/            # Compose theming (colors, typography)
└── MainActivity.kt
```

## Getting Started

### Prerequisites

- Android Studio (latest stable)
- JDK 11
- A Firebase project with Authentication (Email/Password) enabled

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/MVVM102.git
   ```
2. Open the project in Android Studio.
3. Add your own `google-services.json` file to the `app/` directory (get it from your Firebase console).
4. Sync Gradle and run the app on an emulator or device (minSdk 24, targetSdk 36).
