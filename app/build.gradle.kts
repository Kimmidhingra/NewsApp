plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
//    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.ksp)

}
android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String","NEWS_API_KEY","${project.findProperty("NEWS_API_KEY")}")
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }



    buildTypes {
        release {
            buildConfigField("String","NEWS_API_KEY","${project.findProperty("NEWS_API_KEY")}")
//            buildConfigField("String","NEWS_BASE_URL","https://newsapi.org/v2")
//            buildConfigField ("boolean", "LOGGIN_ENABLED", "false")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
//        debug {
//            buildConfigField("String","NEWS_API_KEY","${project.findProperty("NEWS_API_KEY")}")
////            buildConfigField("String","NEWS_BASE_URL","https://newsapi.org/v2")
////            buildConfigField ("boolean", "LOGGIN_ENABLED", "true")
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {
    val dagger_version = "2.40"
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.logging.interceptor)
//    ksp(libs.dagger.android)
//    implementation (libs.dagger.android.support)
    // Define room
    val room_version = "2.6.1"

    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)


    implementation (libs.dagger)
    ksp (libs.dagger.compiler)

    implementation (libs.kotlinx.coroutines.android)
    implementation("io.coil-kt:coil:2.7.0")
    // dependency for test cases
    testImplementation (libs.junit)
    testImplementation (libs.mockito.core)
    testImplementation (libs.androidx.core.testing)
    testImplementation (libs.jetbrains.kotlinx.coroutines.test)
    testImplementation (libs.turbine.v110)
    androidTestImplementation (libs.androidx.junit.v113)
    androidTestImplementation (libs.androidx.espresso.core.v340)
    testImplementation (libs.mockito.inline)





}