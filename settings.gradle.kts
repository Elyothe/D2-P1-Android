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

//Core
include(":core:ui")
include(":core:domain")
include(":core:data")

//Admin
include(":features:admin:ui")
include(":features:admin:api")
include(":features:admin:domain")
include(":features:admin:data")

include(":features:available:ui")
include(":features:available:api")
include(":features:available:domain")
include(":features:available:data")



//Client
include(":features:client:ui")
include(":features:client:domain")
include(":features:client:data")


//Api (interface)
include(":features:api")

//Login
include(":features:login")
include(":features:login:data")
include(":features:login:domain")
include(":features:login:ui")
include(":features:login:api")


