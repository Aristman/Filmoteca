plugins {
    id("com.android.application")
    id("androidx.navigation.safeargs")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("kotlin-android")
    id("kotlinx-serialization")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.google.gms.google-services")
}

val kotlinVersion = rootProject.extra.get("kotlin_version") as String
val hiltVersion = rootProject.extra.get("hilt_version") as String
val retrofitVersion = rootProject.extra.get("retrofit_version") as String
val coroutinesVersion = rootProject.extra.get("coroutines_version") as String
val roomVersion = rootProject.extra.get("room_version") as String
val pagingVersion = "3.0.1"


android {
    compileSdk = 31
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "ru.marslab.filmoteca"
        minSdk = 23
        //noinspection OldTargetApi
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("com.google.android.gms:play-services-maps:17.0.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    implementation("androidx.core:core-ktx:1.6.0")
    //noinspection GradleDependency
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion")

    //Navigation KTX
    implementation("androidx.navigation:navigation-runtime-ktx:2.3.5")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")

    //Hilt Dagger
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")

    //Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

    //Coil
    implementation("io.coil-kt:coil:1.3.2")

    //Room
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    //Room Paging 3 Integration
    implementation("androidx.room:room-paging:2.4.0-alpha05")

    //Google Maps
    implementation("com.google.android.gms:play-services-maps:17.0.1")
    // KTX for the Maps SDK for Android library
    implementation("com.google.maps.android:maps-ktx:3.1.0")
    // KTX for the Maps SDK for Android Utility Library
    implementation("com.google.maps.android:maps-utils-ktx:3.1.0")
    // It is recommended to also include the latest Maps SDK and/or Utility Library versions
    // as well to ensure that you have the latest features and bug fixes.
    implementation("com.google.android.gms:play-services-maps:17.0.1")
    implementation("com.google.maps.android:android-maps-utils:2.2.3")

    //FireBase
    implementation("com.google.firebase:firebase-bom:28.4.1")
    implementation("com.google.firebase:firebase-messaging:22.0.0")

    //Paging 3
    implementation("androidx.paging:paging-runtime:$pagingVersion")


}