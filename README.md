# Movie App (Android / Kotlin)

A native **Android** movie browser written in **Kotlin**, following **MVVM** with a repository pattern.

## Features

- 🎬 Browse movies with details, cast & crew
- 💾 **Offline caching** with Room (database + DAO)
- 🌐 Remote API layer with repository mediating local/remote data

## Structure

```
app/src/main/java/.../movie/
├── data/
│   ├── local/       # Room database + DAO
│   ├── remote/      # API service
│   ├── models/      # movie, cast, crew, collections
│   └── repository/
└── ui/
    ├── view/        # activities/fragments
    ├── viewmodel/
    └── adapter/     # RecyclerView adapters
```

## Run it

Open in Android Studio and run, or:

```bash
./gradlew assembleDebug
```
