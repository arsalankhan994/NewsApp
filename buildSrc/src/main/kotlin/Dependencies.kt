object Gradle {
    val android by lazy { "com.android.tools.build:gradle:${Version.gradlePlugin}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}" }
}

object Plugin {
    val application by lazy { "com.android.application" }
    val library by lazy { "com.android.library" }
    val android by lazy { "android" }
    val kotlinAndroid by lazy { "org.jetbrains.kotlin.android" }
}

object Deps {
    val core by lazy { "androidx.core:core-ktx:${Version.core}" }
    val lifecycle by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Version.appCompat}" }

    object Compose {
        val composeActivity by lazy { "androidx.activity:activity-compose:${Version.composeActivity}" }
        val composeUI by lazy { "androidx.compose.ui:ui:${Version.compose}" }
        val composeTooling by lazy { "androidx.compose.ui:ui-tooling-preview:${Version.compose}" }
        val composeMaterial by lazy { "androidx.compose.material:material:${Version.composeMaterial}" }
    }

    object Tests {
        val jUnit by lazy { "junit:junit:${Version.jUnit}" }
    }

    object AndroidTest {
        val extJUnit by lazy { "androidx.test.ext:junit:${Version.extJUnit}" }
        val espresso by lazy { "androidx.test.espresso:espresso-core:${Version.espresso}" }
        val composeJUnit by lazy { "androidx.compose.ui:ui-test-junit4:${Version.compose}" }
    }

    object Modules {
        const val path = "path"

        object Common {
            private const val common = ":common:"
            const val database = "${common}database"
            const val datastore = "${common}datastore"
            const val network = "${common}network"
            const val resources = "${common}resources"
            const val utils = "${common}utils"
        }

        object Feature {
            private const val features = ":features:"
            const val news = "${features}news"
            const val newsDetails = "${features}news_details"
        }

        const val navigation = ":navigation"
    }
}