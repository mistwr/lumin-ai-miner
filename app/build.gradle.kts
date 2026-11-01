plugins { id("com.android.application"); id("org.jetbrains.kotlin.android") }
android { namespace = "pt.luminai.miner"; compileSdk = 35
 defaultConfig { applicationId = "pt.luminai.miner"; minSdk = 26; targetSdk = 35; versionCode = 1; versionName = "0.1.0" }
 compileOptions { sourceCompatibility = JavaVersion.VERSION_17; targetCompatibility = JavaVersion.VERSION_17 }
 kotlinOptions { jvmTarget = "17" }
}
