package com.prashamhtrivedi.roomupgradehelper

import com.google.common.truth.ExpectFailure.assertThat
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.testkit.runner.GradleRunner
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import java.io.FileWriter
import java.io.BufferedWriter
import java.io.IOException



class PluginTest {
    @get:Rule val testProjectDir = TemporaryFolder()

    lateinit var settingsFile: File
    lateinit var buildFile: File
    private val pluginName = "com.prashamhtrivedi.roomupgradehelper"


    @Before
    fun setup() {
        settingsFile = testProjectDir.newFile("settings.gradle")
        buildFile = testProjectDir.newFile("build.gradle.kts")
    }


    @Test
    fun `test if plugin is applied it should print message`(){

        buildFile.writeText("""
            plugins{
                id("com.prashamhtrivedi.roomupgradehelper")
            }
        """.trimIndent())
        val buildResult = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withPluginClasspath()
                .build()

        print(buildResult.tasks)
        print(buildResult.output)

        assert(buildResult.output.indexOf("Whoa....")!=-1){
            "Your plugin should print something"
        }

    }

    @Test
    fun `test if plugin is applied without path it should throw error`(){
        buildFile.writeText("""
            plugins{
                id("com.prashamhtrivedi.roomupgradehelper")
            }

            roomUpgrade {
            }
        """.trimIndent())

        val buildResult = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withPluginClasspath()
                .withArguments("getStatements")
                .buildAndFail()

        print(buildResult.tasks)
        println(buildResult.output)

        assert(buildResult.output.contains("roomUpgrade.path should be set")){
            "Expecting an error message in absence of path"
        }
    }



    @Test
    fun `test if plugin is applied it has a task named getStatements`(){
        val project = ProjectBuilder.builder().build()
        with(project){
            pluginManager.apply(RoomUpgradeHelperPlugin::class.java)

            assert(tasks.findByName("getStatements")!=null){
                "Adding project should have task called `getStatements`"
            }
        }
    }



}