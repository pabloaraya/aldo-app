import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.ProductFlavor
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class CommonModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.plugins.apply("kotlin-android")

        val androidExtensions = project.extensions.getByName("android")
        if (androidExtensions is BaseExtension) {
            androidExtensions.apply {
                compileSdkVersion(AppConfig.compile)
//                buildToolsVersion(AppConfig.buildTools)

                defaultConfig {
                    minSdkVersion(AppConfig.min)
                    targetSdkVersion(AppConfig.target)
                    versionCode = AppConfig.versionCode
                    versionName = AppConfig.versionName

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                testOptions {
                    unitTests.isReturnDefaultValues = true
                }

                project.tasks.withType(KotlinCompile::class.java).configureEach {
                    kotlinOptions {
                        jvmTarget = JavaVersion.VERSION_1_8.toString()
                    }
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_1_8
                    targetCompatibility = JavaVersion.VERSION_1_8
                }

                flavorDimensions(AppConfig.flavor)
                productFlavors {
                    // Google flavors
                    create("dev") {
                        dimension = AppConfig.flavor
                        buildConfigStringField("PROVIDER", "a")
                        if (isApp()) {
                            applicationId = "cl.blackmind.aldoapp"
                            buildResValueStringField("app_name", "AldoApp Dev")
                            buildResValueStringField("default_notification_channel_id", "fcm_ccl")
                        }
                    }
                    create("prod") {
                        dimension = AppConfig.flavor
                        if (isApp()) {
                            applicationId = "cl.blackmind.aldoapp"
                            buildResValueStringField("app_name", "AldoApp")
                            buildResValueStringField("default_notification_channel_id", "fcm_ccl")
                        }
                    }
                }

                when (this) {
                    is LibraryExtension -> {
                        defaultConfig {
                            consumerProguardFiles("consumer-rules.pro")
                        }
                    }

                    is AppExtension -> {
                        buildTypes {
                            getByName("release") {
                                isMinifyEnabled = false
                                proguardFiles(
                                    getDefaultProguardFile("proguard-android-optimize.txt"),
                                    "proguard-rules.pro"
                                )
                                buildConfigField(
                                    "String",
                                    "BASE_URL",
                                    "\"http://192.168.1.5/\""
                                )
                            }
                            getByName("debug") {
                                isMinifyEnabled = false
                                proguardFiles(
                                    getDefaultProguardFile("proguard-android-optimize.txt"),
                                    "proguard-rules.pro"
                                )
                                buildConfigField(
                                    "String",
                                    "BASE_URL",
                                    "\"http://192.168.1.5/\""
                                )
                            }
                        }
                    }
                }
            }
        }

        project.dependencies {
            add("implementation", "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
            add("implementation", "androidx.core:core-ktx:$coreKtx")
            add("implementation", "androidx.appcompat:appcompat:$appcompat")

            add("implementation", "com.jakewharton.timber:timber:$timber")

            add("testImplementation", "org.mockito:mockito-core:$mockitoCore")
            add("testImplementation", "org.junit.jupiter:junit-jupiter-api:$junitJupiter")
            add("testRuntimeOnly", "org.junit.jupiter:junit-jupiter-engine:$junitJupiter")
            add("testImplementation", "org.junit.jupiter:junit-jupiter-params:$junitJupiter")

            add("androidTestImplementation", "androidx.test.ext:junit:$extJunit")
            add("androidTestImplementation", "androidx.test.espresso:espresso-core:$espressoCore")
        }
    }
}

fun ProductFlavor.buildConfigStringField(name: String, value: String) {
    this.buildConfigField("String", name, "\"$value\"")
}

fun ProductFlavor.buildResValueStringField(name: String, value: String) {
    this.resValue("string", name, "\"$value\"")
}

fun BaseExtension.isApp(): Boolean {
    return this is AppExtension
}