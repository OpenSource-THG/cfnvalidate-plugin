package com.thg.opensource.aws

import com.thg.opensource.aws.cfnvalidate.tasks.JsonValidateTask
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.File

open class JsonValidateTaskTest {

    lateinit var validator: JsonValidateTask

    @Before
    fun setUp() {
        val project: Project = ProjectBuilder.builder().build()
        validator = project.tasks.create("cfnvalidate", JsonValidateTask::class.java)
    }

    @Test
    fun `test validateJson with valid json string`() {
        val validationResult: Boolean = validator.validateJson(File("src/test/resources/json/valid.json").readText())
        Assert.assertTrue(validationResult)
    }

    @Test
    fun `test validateJson with invalid json string`() {
        val validationResult: Boolean = validator.validateJson(File("src/test/resources/json/invalid.json").readText())
        Assert.assertFalse(validationResult)
    }

}