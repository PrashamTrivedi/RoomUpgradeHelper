# Room Upgrade helper

A Gradle plugin to help you finding out right queries when you upgrade your room schemas.


## Installation

Add the following to your `build.gradle`

In Groovy

```groovy
plugins {
  id "com.prashamhtrivedi.roomupgradehelper" version "0.1"
}
```

In Kotlin

```kotlin
plugins {
  id("com.prashamhtrivedi.roomupgradehelper") version "0.1"
}
```

Or 

In Groovy

```groovy
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "gradle.plugin.com.prashamhtrivedi:roomUpgradeHelper:0.1"
  }
}

apply plugin: "com.prashamhtrivedi.roomupgradehelper"

```

In Kotlin

```kotlin
buildscript {
  repositories {
    maven {
      url = uri("https://plugins.gradle.org/m2/")
    }
  }
  dependencies {
    classpath("gradle.plugin.com.prashamhtrivedi:roomUpgradeHelper:0.1")
  }
}

apply(plugin = "com.prashamhtrivedi.roomupgradehelper")
```

## Usage

You have to define the location of schema json generated by room. You can do so by adding following block.

```groovy
 roomUpgrade {
    jsonPath YourSchemaPath (e.g. "$projectDir/schemas")
 }
```

Room will add json schema every time you upgrade your database models and DB version. 

This plugin will read those json files, find out the difference and will show you the queries you have to run in your migration grouped by each upgrade from version to version.

To get these queries, you have to run `getStatements` task added by this plugin.


