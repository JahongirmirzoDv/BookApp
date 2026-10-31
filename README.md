# Book App

An Android book-discovery project exploring bestseller lists, categories, and search. It combines a Model–View–Presenter layer with Retrofit networking, coroutines, and Room components.

## What it demonstrates

- Browse trending books and named book categories.
- View category-specific lists and open book details.
- Search the loaded book collection through the presenter.

## Explore the code

- `app/src/main/java/com/example/bookapp/view/` — activities and RecyclerView adapters.
- `app/src/main/java/com/example/bookapp/presenter/` — MVP contracts and presenter.
- `app/src/main/java/com/example/bookapp/Models/retrofit/` — book API interfaces and Retrofit configuration.
- `app/src/main/java/com/example/bookapp/Models/local/` — Room database and DAO components.

## Open and build

Open the repository in Android Studio and use a Gradle JDK compatible with its checked-in Android Gradle Plugin. Install Android SDK Platform 32 and let the Gradle wrapper resolve the project dependencies.

Review the New York Times Books API configuration in `Models/retrofit/` and supply your own API key before using network features. Keep keys out of published examples.

```bash
./gradlew :app:assembleDebug
```

Run the `app` configuration on a device or emulator. On Windows, use `gradlew.bat`.

## Project status

This repository is a learning project. Its original dependency versions are retained; dependency resolution and behavior on current Android versions may require maintenance. Book data depends on the external API and a valid account key.

