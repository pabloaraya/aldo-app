plugins {
    id("com.android.library")
    id("module-plugin")
}

android {
    buildFeatures.viewBinding = true
}

dependencies {
    implementation(project(":core:core-ui"))

    addAndroidMaterial()
    addLifecycleExtensions()
    addJetPackNavigation()
    addGlide()
    implementation("com.google.code.gson:gson:$gson")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:$swipeRefreshLayout")
}