plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.d2_p1.features.booking.data"
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.bundles.compose.ui)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    debugImplementation(libs.androidx.compose.ui.tooling)

    // Ajout des dépendances vers les modules core
    implementation(project(":core:data"))
    implementation(project(":core:ui"))
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // ajout de l'implementation de koin pour injecter les dépendances
    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.bundles.koin)

    implementation(project(":features:booking:domain"))
    implementation(project(":core:data"))


}