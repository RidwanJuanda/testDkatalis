plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.testdkatalis"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.testdkatalis"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
//        create("debug") {
//            keyAlias = "key0"
//            keyPassword = "eventori123"
//            storeFile =  file("keystore.jks")
//            storePassword = "eventori123"
//        }
        create("release") {
            keyAlias = "key0"
            keyPassword = "eventori123"
            storeFile =  file("keystore.jks")
            storePassword = "eventori123"
        }
    }

    buildTypes {
        debug {
            // Konfigurasi untuk build debug
            isDebuggable = true
            applicationIdSuffix = ".debug" // Suffix untuk membedakan aplikasi debug
            versionNameSuffix = "-debug"; // Versi debug
            isShrinkResources = false // Tidak menyusutkan resource
            isMinifyEnabled = false
        }

        release {
            // Konfigurasi untuk build release
            isDebuggable = false
            isShrinkResources = true // Tidak menyusutkan resource
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions.add("environment")

    productFlavors {
        create("development") {
            dimension = "environment"
            applicationIdSuffix = ".dev"
            resValue("string", "app_name", "Example Dev")
            buildConfigField("String", "SERVICES_URL", "\"api-core-dev.eventori.id/api/\"")
        }
        create("production") {
            dimension = "environment"
            resValue("string", "app_name", "Example")
            // ini versi prodiction seharusnya diganti dengan API url utk production
            buildConfigField("String", "SERVICES_URL", "\"api-core-dev.eventori.id/api/\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
    compileSdk = 34
}

dependencies {
//    implementation fileTree(dir: 'libs', include: ['.aar', '.jar'], exclude: [])

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.4")
    implementation("androidx.multidex:multidex:2.0.1")

    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
    implementation("id.zelory:compressor:2.1.1")
    implementation("com.github.bumptech.glide:glide:4.16.0")

    //------------------------------OKHTTP---------------------------//
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //    //---------------------------------UNIT TEST-----------------------------//
    debugImplementation ("com.github.chuckerteam.chucker:library:4.0.0")
//    debugImplementation ("com.github.chuckerteam.chucker:library-no-op:4.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}