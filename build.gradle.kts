plugins {
    kotlin("multiplatform") version vers.kotlin
    id("tz.co.asoft.library") version vers.asoft.builders
    id("io.codearte.nexus-staging") version vers.nexus_staging
    signing
}

kotlin {
    jvm { library() }
    js(IR) { library() }
    val nativeTargets = nativeTargets(true)

    sourceSets {
        val commonMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:atomicfu:${vers.kotlinx.atomicfu}")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft("expect-core", vers.asoft.expect))
                implementation(asoft("expect-coroutines", vers.asoft.expect))
            }
        }
    }
}