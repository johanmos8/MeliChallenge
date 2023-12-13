plugins {

    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.johanmos8.presentation"
    compileSdk = 34
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
dependencies{

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    implementation("com.google.dagger:hilt-android:2.44")
}