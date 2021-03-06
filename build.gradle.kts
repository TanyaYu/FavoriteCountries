buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        val kotlinVersion = "1.3.72"
        extra["kotlinVersion"] = kotlinVersion

        classpath("com.android.tools.build:gradle:4.0.1")
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.0")
    }
}
allprojects {
    repositories {
        google()
        jcenter()
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}