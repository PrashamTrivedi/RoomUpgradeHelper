import com.gradle.publish.PluginBundleExtension

buildscript {

    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(kotlin("gradle-plugin",version="1.3.30") )
        classpath("com.gradle.publish:plugin-publish-plugin:0.10.1")
    }
}

plugins{
    kotlin("jvm") version("1.3.30")
    java
    id("java-gradle-plugin")
    id("com.gradle.plugin-publish") version "0.10.1"
}



configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.squareup.moshi:moshi-kotlin:1.8.0")

    testImplementation("junit:junit:4.12")
    testImplementation("com.google.truth:truth:0.44")
    testImplementation(gradleTestKit())
}


(tasks.findByName("test") as Test).useJUnitPlatform()

group = "com.prashamhtrivedi"
version = "0.1"
val gradlePlugin = configure<GradlePluginDevelopmentExtension> {
    plugins {
        create("roomUpgradeHelper"){
            id = "com.prashamhtrivedi.roomupgradehelper"
            implementationClass = "com.prashamhtrivedi.roomupgradehelper.RoomUpgradeHelperPlugin"
            displayName = "Room Upgrade Helper"
        }
    }
}

configure<PluginBundleExtension> {
    website = "https://github.com/PrashamTrivedi/RoomUpgradeHelper"
    vcsUrl = "https://github.com/PrashamTrivedi/RoomUpgradeHelper"
    tags = listOf("Room","Upgrade")


}

//pluginBundle {
//    website = "https://github.com/runningcode/fladle"
//    vcsUrl = "https://github.com/runningcode/fladle"
//    tags = listOf("flank", "testing", "android", "fladle")
//
//    mavenCoordinates {
//        artifactId = "fladle"
//        groupId = group
//    }
//}

//intellij {
//    version '2018.1'
//    sandboxDirectory = "$project.buildDir/myCustom-sandbox"
//    pluginName 'RoomUpgradeHelper'
//}
//patchPluginXml {
//    changeNotes """
//      Add change notes here.<br>
//      <em>most HTML tags may be used</em>"""
//}