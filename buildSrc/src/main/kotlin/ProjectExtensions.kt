import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


fun Project.addCoroutines() {
    dependencies {
        add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")
        add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")
        add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutines")
    }
}

fun Project.addWorkManager() {
    dependencies {
        add("implementation", "androidx.work:work-runtime-ktx:$workManager")
    }
}

fun Project.addAndroidMaterial() {
    dependencies {
        add("implementation", "com.google.android.material:material:$material")
        add("implementation", "androidx.constraintlayout:constraintlayout:$constraint")
    }
}

fun Project.addGlide() {
    dependencies {
        add("implementation", "com.github.bumptech.glide:glide:$glide")
        add("implementation", "com.github.bumptech.glide:okhttp3-integration:$glide")
        add("annotationProcessor", "com.github.bumptech.glide:compiler:$glide")
    }
}

fun Project.addJetPackNavigation() {
    dependencies {
        add("implementation", "androidx.navigation:navigation-fragment-ktx:$navigation")
        add("implementation", "androidx.navigation:navigation-ui-ktx:$navigation")
    }
}

fun Project.addKoinDI() {
    dependencies {
        add("implementation", "io.insert-koin:koin-android:$koin")
        add("implementation", "io.insert-koin:koin-androidx-scope:$koin")
        add("implementation", "io.insert-koin:koin-androidx-viewmodel:$koin")
        add("implementation", "io.insert-koin:koin-androidx-fragment:$koin")
        add("implementation", "io.insert-koin:koin-androidx-ext:$koin")
    }
}

fun Project.addLifecycleExtensions() {
    dependencies {
        add("implementation", "androidx.lifecycle:lifecycle-extensions:$lifecycle")
        add("implementation", "androidx.lifecycle:lifecycle-common-java8:$lifecycle")
        add("implementation", "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle")
        add("implementation", "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle")
    }
}

fun Project.addRetrofit() {
    dependencies {
        add("implementation", "com.squareup.retrofit2:retrofit:$retrofit")
        add("implementation", "com.squareup.retrofit2:converter-gson:$retrofit")
        add("implementation", "com.squareup.okhttp3:logging-interceptor:$okhttp3LoggingInterceptor")
        add("implementation", "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:$jakeWhartonCoroutinesAdapter")
    }
}

fun Project.addDataStore() {
    dependencies {
        add("implementation", "androidx.datastore:datastore-preferences:$dataStore")
    }
}

fun Project.addLottie() {
    dependencies {
        add("implementation", "com.airbnb.android:lottie:$lottie")
    }
}

fun Project.addYoutubePlayer() {
    dependencies {
        add("implementation", "com.pierfrancescosoffritti.androidyoutubeplayer:core:$youtubePlayer")
    }
}

fun Project.addGoogleMaps() {
    dependencies {
        add("implementation", "com.google.android.gms:play-services-maps:$googleMaps")
        add("implementation", "com.google.android.gms:play-services-location:$googleMaps")
    }
}

fun Project.addVision() {
    dependencies {
        add("implementation", "com.google.android.gms:play-services-vision:$playServiceVision")
    }
}

fun Project.addGoogleLogin() {
    dependencies {
        add("implementation", "com.google.android.gms:play-services-auth:$googleLogin")
    }
}

fun Project.addFacebook() {
    dependencies {
        add("implementation", "com.facebook.android:facebook-android-sdk:$facebook")
        add("implementation", "com.facebook.android:facebook-login:$facebookLogin")
    }
}

fun Project.addMask() {
    dependencies {
        add("implementation", "io.github.vicmikhailau:MaskedEditText:$maskedEditText")
    }
}

fun Project.addFirebase() {
    dependencies {
        add("implementation", "com.google.firebase:firebase-auth-ktx")
        add("implementation","com.google.firebase:firebase-firestore-ktx")
//        add("implementation", "com.google.firebase:firebase-analytics")
//        add("implementation", "com.google.firebase:firebase-messaging")
    }
}