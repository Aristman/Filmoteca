// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply {
        set("kotlin_version", "1.5.10")
        set("nav_version", "2.3.5")
        set("retrofit_version", "2.9.0")
        set("hilt_version", "2.38.1")
        set("coroutines_version", "1.5.0")
        set("room_version", "2.3.0")
    }

    val navVersion = rootProject.extra.get("nav_version") as String
    val kotlinVersion = rootProject.extra.get("kotlin_version") as String

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")

        classpath("com.google.gms:google-services:4.3.10")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.0")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files

    }

    allprojects {
        repositories {
            google()
            mavenCentral()
        }
    }

    tasks {
        register("clean", Delete::class) {
            delete(rootProject.buildDir)
        }
    }
}
