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

package com.thg.opensource.aws.cfnvalidate.tasks

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File

open class YamlValidateTask: DefaultTask() {

    @Input
    lateinit var yamlFilesPath: String

    var allValid = true

    private fun File.isValidYaml(): Boolean {

        return try { YAMLMapper().readTree(inputStream()); true } catch (e: Exception) { allValid = false; false }
    }

    @TaskAction
    fun validateYamlFiles() {
        File(yamlFilesPath).walkTopDown().filter { it.isFile }.forEach {
            println("Validating yaml file: $it")
            println("valid?: ${it.isValidYaml()}")
        }

        if(!allValid) {
            throw GradleException("Some files were invalid.")
        }
    }
}