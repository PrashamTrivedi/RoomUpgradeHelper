package com.prashamhtrivedi.roomupgradehelper

import com.prashamhtrivedi.roomupgradehelper.testData.first.a_1
import com.prashamhtrivedi.roomupgradehelper.testData.first.a_2
import com.prashamhtrivedi.roomupgradehelper.testData.first.a_3
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.testkit.runner.GradleRunner
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File


class PluginTest {
    @get:Rule
    val testProjectDir = TemporaryFolder()

    lateinit var settingsFile: File
    lateinit var buildFile: File

    @Before
    fun setup() {
        settingsFile = testProjectDir.newFile("settings.gradle")
        buildFile = testProjectDir.newFile("build.gradle.kts")
    }

    @Test
    fun `test if plugin is applied it has a task named getStatements`() {
        val project = ProjectBuilder.builder().build()
        with(project) {
            pluginManager.apply("com.prashamhtrivedi.roomupgradehelper")

            assert(tasks.findByName("getStatements") != null) {
                "Adding project should have task called `getStatements`"
            }
        }
    }


    @Test
    fun `test if plugin is applied it should print message`() {

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

        assert(buildResult.output.indexOf("Whoa....") != -1) {
            "Your plugin should print something"
        }

    }

    @Test
    fun `test if plugin is applied without path it should throw error`() {
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

        assert(buildResult.output.contains("roomUpgrade.path should be set")) {
            "Expecting an error message in absence of path"
        }
    }

    @Test
    fun `test if plugin is applied with proper path it should start working`() {

        buildFile.writeText("""
            plugins{
                id("com.prashamhtrivedi.roomupgradehelper")
            }

            roomUpgrade {
                jsonPath = "${'$'}{projectDir.path}/source"
            }
        """.trimIndent())


        val sourceDir = File(testProjectDir.root,"source").apply {
            mkdirs()
        }
        File(sourceDir, "1.json").apply {
            parentFile.mkdirs()
            createNewFile()
            writeText(a_1)
        }
        File(sourceDir, "2.json").apply {
            parentFile.mkdirs()
            createNewFile()
            writeText(a_2)
        }
        File(sourceDir, "3.json").apply {
            parentFile.mkdirs()
            createNewFile()
            writeText(a_3)
        }

        val buildResult = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withPluginClasspath()
                .withArguments("getStatements")
                .build()

        println(buildResult.output)

        val query = "Alter table 'Google' Add column 'ref_no' TEXT "

        assert(buildResult.output.contains(query)) {
            "Newly created tables must be there in output"
        }

    }


}