pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/") {
            name = "Fabric"
        }
        gradlePluginPortal()
    }
}

plugins {
    // No fking clue what this does but shit hits the fan if i dont have it ¯\_(ツ)_/¯
    id("org.gradle.toolchains.foojay-resolver-convention") version("0.8.0")
}