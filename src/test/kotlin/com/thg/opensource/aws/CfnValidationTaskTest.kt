package com.thg.opensource.aws

import com.thg.opensource.aws.cfnvalidate.tasks.CfnValidationTask
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.File

open class CfnValidationTaskTest {

    lateinit var validator: CfnValidationTask

    @Before
    fun setUp() {
        val project: Project = ProjectBuilder.builder().build()
        validator = project.tasks.create("cfnvalidate", CfnValidationTask::class.java)
    }

    @Test
    fun `test validateCfn with valid template string`() {
        val validationResult: Boolean = validator.validateCfn(File("src/test/resources/templates/test-template.cfn.yaml").readText())
        Assert.assertTrue(validationResult)
    }

    @Test
    fun `test validateCfn with invalid template string`() {
        val validationResult: Boolean = validator.validateCfn(File("src/test/resources/templates/invalid-template.cfn.yaml").readText())
        Assert.assertFalse(validationResult)
    }
}