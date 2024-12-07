# Modern Food Recipes App

Modern Food Recipes App is a fully functional Android application built with Kotlin. The app allows users to browse and explore recipes from various meal and diet types, providing an intuitive and seamless user experience. The app integrates data from the Spoonacular API and incorporates offline caching for enhanced usability when there is no internet connection.

## ✨ Features

#### 🌐 Fetch Recipes from Spoonacular API
Users can explore recipes based on meal types (e.g., breakfast, lunch, dinner) and dietary preferences (e.g., vegetarian, keto).

#### 📂 Offline Caching
Recipes are stored locally using ROOM database, ensuring accessibility even without an internet connection.

#### 🌗 Dark and Light Themes
Implements modern Material Design principles, allowing users to switch between light and dark themes.

#### 🔍 Search Functionality
Users can search recipes by ingredients, meal types, or specific keywords.

#### 🔄 Live Data Updates
Leverages LiveData and ViewModel for efficient data flow and UI updates.

## 🛠️ Technologies Used
The project incorporates a wide range of modern Android development tools and technologies:

- Programming Language: Kotlin
- Dependency Injection: Dagger-Hilt
- Networking: Retrofit
- Local Database: ROOM
- Asynchronous Programming: Kotlin Coroutines
- State Management: ViewModel, LiveData
- Navigation: Navigation Component
- Preferences Storage: DataStore
- UI Components: Data Binding, Material Design

## 📋 Project Structure
The project follows the MVVM (Model-View-ViewModel) architecture for clean, maintainable, and scalable code.

```
|-- data/
|   |-- api/          # API integration using Retrofit
|   |-- database/     # Local database setup using ROOM
|   |-- repository/   # Repository for handling data logic
|
|-- ui/
|   |-- activities/   # Activities for different screens
|   |-- fragments/    # Fragments for modular UI components
|   |-- adapters/     # RecyclerView Adapters for lists
|
|-- utils/            # Utility classes and helpers
```

