plugins {
    id("com.android.library")
    id("module-plugin")
    id("kotlin-kapt")
}

dependencies {
    addAndroidMaterial()
    addLottie()
}
android {
    sourceSets {
        getByName("dev") {
            res {
                srcDirs("src\\dev\\res", "src\\dev\\res")
            }
            assets {
                srcDirs("src\\dev\\assets", "src\\dev\\assets")
            }
        }
        getByName("prod") {
            res {
                srcDirs("src\\prod\\res", "src\\prod\\res")
            }
            assets {
                srcDirs("src\\prod\\assets", "src\\prod\\assets")
            }
        }
    }
}

