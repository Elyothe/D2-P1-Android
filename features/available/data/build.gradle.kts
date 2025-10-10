plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.d2_p1.features.available.data"
    compileSdk = 36


    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

}

dependencies {
    implementation("com.jakewharton.threetenabp:threetenabp:1.4.0")

    implementation(project(":features:available:domain"))
    implementation(project(":core:data"))
    implementation(libs.androidx.annotation.jvm)


}