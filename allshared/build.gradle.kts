@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("multiplatform")
    id("co.touchlab.kmmbridge")
    id("co.touchlab.skie")
    id("com.vanniktech.maven.publish") version "0.25.3"
}

kotlin {
    @Suppress("OPT_IN_USAGE")
    targetHierarchy.default()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            export(project(":analytics"))
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":breeds"))
                api(project(":analytics"))
            }
        }
    }
}

addGithubPackagesRepository()

kmmbridge {
    mavenPublishArtifacts()
    spm()
}
