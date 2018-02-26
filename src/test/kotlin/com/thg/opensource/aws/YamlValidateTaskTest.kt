/**
 * Copyright 2018 THG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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