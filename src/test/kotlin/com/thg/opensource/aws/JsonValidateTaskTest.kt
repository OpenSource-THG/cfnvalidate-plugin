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