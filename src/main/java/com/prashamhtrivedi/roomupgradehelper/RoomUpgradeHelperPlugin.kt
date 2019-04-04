package com.prashamhtrivedi.roomupgradehelper

import org.gradle.api.Plugin
import org.gradle.api.Project


open class RoomUpgradeHelperPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.run {
            create("roomUpgrade",Configuration::class.java)
        }

        project.afterEvaluate {
            if(extension.path.isEmpty()) return@afterEvaluate
            println("Whoa....")
        }

        with(project.tasks){
            create("getStatements",MigrationTask::class.java){
                it.group = "Room Upgrade"
                it.description = "Get migration queries when upgrading versions"
            }
        }
    }
}