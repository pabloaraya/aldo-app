plugins {
    id("com.android.library")
    id("module-plugin")
}

android {
    buildFeatures.dataBinding = true
    buildFeatures.viewBinding = true
}

dependencies {
    addLifecycleExtensions()
    addAndroidMaterial()
}