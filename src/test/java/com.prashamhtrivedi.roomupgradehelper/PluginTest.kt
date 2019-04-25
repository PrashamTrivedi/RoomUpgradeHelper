package com.prashamhtrivedi.roomupgradehelper

import com.prashamhtrivedi.roomupgradehelper.testData.first.a_1
import com.prashamhtrivedi.roomupgradehelper.testData.first.a_2
import com.prashamhtrivedi.roomupgradehelper.testData.first.a_3
import junit.framework.Assert.assertEquals
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Assert
import org.junit.Assert.assertNotEquals
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

        assert(buildResult.task(":getStatements")!!.outcome == TaskOutcome.FAILED)

        assert(buildResult.output.contains("roomUpgrade.path should be set")) {
            "Expecting an error message in absence of path"
        }
    }

    @Test
    fun `test if path does not have more than one entry plugin should throw error`(){
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

        val buildResult = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withPluginClasspath()
                .withArguments("getStatements")
                .buildAndFail()

        println(buildResult.output)

        assert(buildResult.task(":getStatements")!!.outcome == TaskOutcome.FAILED)

        assert(buildResult.output.contains("More than one versions are needed to upgrade")){
            "Expecting a proper error message"
        }
    }

    @Test
    fun `test if no files should throw an error`(){
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


        val buildResult = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withPluginClasspath()
                .withArguments("getStatements")
                .buildAndFail()

        println(buildResult.output)

        assert(buildResult.task(":getStatements")!!.outcome == TaskOutcome.FAILED)

        assert(buildResult.output.contains("You don't have any files ready for migration")){
            "Expecting a proper error message"
        }
    }

    @Test
    fun `test should fail in absence of proper json schema`(){
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
            writeText("Failing this test anyhow")
        }
        File(sourceDir, "2.txt").apply {
            parentFile.mkdirs()
            createNewFile()
            writeText("Failing this test anyhow")
        }

        val buildResult = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withPluginClasspath()
                .withArguments("getStatements")
                .buildAndFail()

        println(buildResult.output)

        assert(buildResult.task(":getStatements")!!.outcome == TaskOutcome.FAILED)


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

        assert(buildResult.task(":getStatements")!!.outcome != TaskOutcome.FAILED)

        assert(buildResult.output.contains(query)) {
            "Newly created tables must be there in output"
        }

    }

    @Test
    fun `task should be up to date if task is run twice without change`(){
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


        val buildResult = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withPluginClasspath()
                .withArguments("getStatements")
                .build()

        val buildResultUpdate = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withPluginClasspath()
                .withArguments("getStatements")
                .build()
        println(buildResult.output)
        println(buildResultUpdate.output)
        assertEquals(buildResult.task(":getStatements")!!.outcome , TaskOutcome.SUCCESS)
        assertEquals(buildResultUpdate.task(":getStatements")!!.outcome,TaskOutcome.UP_TO_DATE)
    }


    @Test
    fun `task should run again if contents are changed`(){
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


        val buildResult = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withPluginClasspath()
                .withArguments("getStatements")
                .build()

        println(buildResult.output)
        assert(buildResult.task(":getStatements")!!.outcome == TaskOutcome.SUCCESS)

        File(sourceDir, "3.json").apply {
            parentFile.mkdirs()
            createNewFile()
            writeText(a_3)
        }

        val buildResultUpdate = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withPluginClasspath()
                .withArguments("getStatements")
                .build()

        println(buildResultUpdate.output)

        assertNotEquals(buildResultUpdate.task(":getStatements")!!.outcome,TaskOutcome.UP_TO_DATE)
        assertEquals(buildResultUpdate.task(":getStatements")!!.outcome,TaskOutcome.SUCCESS)
    }
}