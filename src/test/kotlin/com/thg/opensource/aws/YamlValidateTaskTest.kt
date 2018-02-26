package com.thg.opensource.aws

import com.thg.opensource.aws.cfnvalidate.tasks.YamlValidateTask
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.File

open class YamlValidateTaskTest {

    lateinit var validator: YamlValidateTask

    @Before
    fun setUp() {
        val project: Project = ProjectBuilder.builder().build()
        validator = project.tasks.create("cfnvalidate", YamlValidateTask::class.java)
    }

    @Test
    fun `test validateYaml with valid yaml string`() {
        val validationResult: Boolean = validator.validateYaml(File("src/test/resources/yaml/valid.yaml").readText())
        Assert.assertTrue(validationResult)
    }

    @Test
    fun `test validateYaml with invalid yaml string`() {
        val validationResult: Boolean = validator.validateYaml(File("src/test/resources/yaml/invalid.yaml").readText())
        Assert.assertFalse(validationResult)
    }
}