plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.apollographql.apollo3").version("3.8.2")
}

android {
    namespace = "com.example.composecleanarchitecture"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.composecleanarchitecture"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }


    /* val properties =  Properties()
     if (project.rootProject.file("local.properties").canRead()) {
         properties.load(project.rootProject.file("local.properties").newDataInputStream())
     }*/

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:${DependencyVersions.kotlin_version}")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.core:core-ktx:+")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


    //hilt
    implementation("com.google.dagger:hilt-android:${DependencyVersions.hilt_version}")
    kapt("com.google.dagger:hilt-compiler:${DependencyVersions.hilt_version}")
    implementation("androidx.hilt:hilt-work:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // viewmodel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    //bottom nav
    implementation("androidx.navigation:navigation-compose:${DependencyVersions.nav_version}")

    //room db
    implementation("androidx.room:room-runtime:${DependencyVersions.room_version}")
    annotationProcessor("androidx.room:room-compiler:${DependencyVersions.room_version}")
    kapt("androidx.room:room-compiler:${DependencyVersions.room_version}")
    implementation("androidx.room:room-paging:${DependencyVersions.room_version}")
    implementation ("androidx.room:room-ktx:${DependencyVersions.room_version}")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:${DependencyVersions.retrofit_version}")
    implementation("com.squareup.retrofit2:converter-gson:${DependencyVersions.retrofit_version}")
    implementation("com.squareup.retrofit2:converter-moshi:${DependencyVersions.retrofit_version}")

    // okhttp3
    implementation("com.squareup.okhttp3:okhttp:${DependencyVersions.okHttp3_version}")
    implementation("com.squareup.okhttp3:logging-interceptor:${DependencyVersions.okHttp3_version}")

    //paging3
    implementation("androidx.paging:paging-runtime:${DependencyVersions.paging_version}")
    implementation("androidx.paging:paging-compose:${DependencyVersions.paging_version}")

    //memory leak
   // debugImplementation("com.squareup.leakcanary:leakcanary-android:2.12")

    //lottie animation
    implementation("com.airbnb.android:lottie-compose:${DependencyVersions.lottie_version}")

    //coil image load
    implementation("io.coil-kt:coil-compose:${DependencyVersions.coil_version}")

    // workmanager
    implementation("androidx.work:work-runtime-ktx:${DependencyVersions.workmanager_version}")

    // material
    implementation("androidx.compose.material:material-icons-extended:1.5.1")

    implementation("com.apollographql.apollo3:apollo-runtime:${DependencyVersions.graphql_version}")

    // math view
     implementation ("com.github.judemanutd:katexview:1.0.2")
   // implementation ("io.github.kexanie.library:MathView:0.0.6")
}

apollo {

    /*  service("service") {

      }*/
    service("default") {
        // packageName = "com.example.composecleanarchitecture"
        //  sourceFolder = file("src/main/graphql/schema.graphql")
        packageName.set("com.example.composecleanarchitecture")
        generateKotlinModels.set(true)
        // packageNamesFromFilePaths()
    }

    //generateKotlinModels.set(true)
    //schemaFile = file("/Users/s.m.zahidulislam/Desktop/Android_projects/ComposeCleanArchitecture/app/src/main/graphql")

}

kapt {
    correctErrorTypes = true
}



object DependencyVersions {
    const val hilt_version = "2.48"
    const val kotlin_version = "1.8.0"
    const val nav_version = "2.7.2"
    const val room_version = "2.5.2"
    const val retrofit_version = "2.9.0"
    const val paging_version = "3.2.0"
    const val lottie_version = "6.0.1"
    const val coil_version = "2.3.0"
    const val okHttp3_version = "4.9.3"
    const val workmanager_version = "2.8.1"
    const val graphql_version = "3.8.2"
}