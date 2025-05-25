plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // Mantive o plugin do compose, embora tenhamos desabilitado o buildFeature.
    // Se você tiver certeza que não usará Compose neste projeto, pode remover a linha abaixo.
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "br.com.livrokotlin.listadecompras"
    compileSdk = 35

    defaultConfig {
        applicationId = "br.com.livrokotlin.listadecompras"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = false // Alterado para false para um projeto baseado em XML/Views
    }
}

dependencies {
    // Dependências essenciais que você já tinha
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx) // Útil para o ciclo de vida

    // --- Dependências ADICIONADAS para AppCompat e Material Design (Views) ---
    implementation("androidx.appcompat:appcompat:1.6.1") // Essencial para AppCompatActivity
    implementation("com.google.android.material:material:1.12.0") // Para componentes do Material Design em XML

    // Dependências do Compose (mantidas por enquanto, mas não serão usadas ativamente com compose = false)
    // Se você tiver certeza que não usará Compose, estas podem ser removidas para simplificar.
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3) // Material 3 é para Compose

    // Dependências de Teste (mantenha como estão)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}