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

include(":features:available:ui")
include(":features:available:api")
include(":features:available:domain")
include(":features:available:data")

include(":features:booking:ui")
include(":features:booking:api")
include(":features:booking:domain")
include(":features:booking:data")

include(":features:client:ui")
include(":features:client:domain")
include(":features:client:data")
