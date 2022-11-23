plugins {
    id("com.android.library")
    id("module-plugin")
    id("kotlin-kapt")
}

dependencies {
    addDataStore()
    addKoinDI()
}