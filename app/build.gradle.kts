plugins {
    id("com.android.application")
    id("module-plugin")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    defaultConfig {
        applicationId = "com.cooler.cl"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.*"))))

    implementation(project(":core:core-db"))
    implementation(project(":core:core-ui"))
    implementation(project(":core:core-common"))
    implementation(project(":core:core-base"))

    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    addAndroidMaterial()
    addJetPackNavigation()
    addKoinDI()
    addLifecycleExtensions()
    addWorkManager()
    addCoroutines()
    addRetrofit()
    addGlide()
    addDataStore()

    addMask()

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}