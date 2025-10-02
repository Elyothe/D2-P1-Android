pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "D2-P1"
include(":app")

include(":core:ui")
include(":core:domain")
include(":core:data")

include(":features:admin:ui")
include(":features:admin:api")
include(":features:admin:domain")
include(":features:admin:data")

include(":features:client:ui")
include(":features:client:domain")
include(":features:client:data")



include(":features:api")
