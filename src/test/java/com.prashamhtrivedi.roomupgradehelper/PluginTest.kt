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
    fun testApplyAndEnableShouldPrint(){

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

        assert(buildResult.output.indexOf("Whoa....")!=-1){
            "Your plugin should print something"
        }

    }


    @Test
    fun testAppliedPluginHasTask(){
        val project = ProjectBuilder.builder().build()
        with(project){
            pluginManager.apply(RoomUpgradeHelperPlugin::class.java)

            assert(tasks.findByName("getStatements")!=null){
                "Adding project should have task called `getStatements`"
            }
        }
    }



    @Throws(IOException::class)
    private fun writeFile(destination: File, content: String) {
        var output: BufferedWriter? = null
        try {
            output = BufferedWriter(FileWriter(destination))
            output.write(content)
        } finally {
            output?.close()
        }
    }

}