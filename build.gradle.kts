plugins {
    id("com.android.application")            version "8.7.0"  apply false
    id("org.jetbrains.kotlin.android")       version "1.9.24" apply false
    id("org.jetbrains.kotlin.kapt")          version "1.9.24" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.7.7" apply false
}

buildscript {
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
    }
}
