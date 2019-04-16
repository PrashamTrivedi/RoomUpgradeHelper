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


    @Before
    fun setup() {
        settingsFile = testProjectDir.newFile("settings.gradle")
        buildFile = testProjectDir.newFile("build.gradle")
    }


    @Test
    fun testApplyAndEnableShouldPrint(){

        buildFile.appendText("""
            plugins{
                id 'com.prashamhtrivedi.roomupgradehelper'
            }
        """)
        val buildResult = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withPluginClasspath()
                .build()

        assert(buildResult.output.indexOf("Whoa....")!=-1){
            "Your plugin should print something"
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