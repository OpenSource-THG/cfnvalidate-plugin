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

import com.amazonaws.services.cloudformation.AmazonCloudFormationClient
import com.amazonaws.services.cloudformation.model.ValidateTemplateRequest
import com.amazonaws.services.cloudformation.model.ValidateTemplateResult
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File

open class CfnValidationTask : DefaultTask() {

    @Input
    lateinit var cfnTemplatesPath: String

    fun validateCfn(templateBody: String): Boolean {
        val request: ValidateTemplateRequest = ValidateTemplateRequest().withTemplateBody(templateBody)
        return try {
            val awsResult: ValidateTemplateResult = AmazonCloudFormationClient().validateTemplate(request)
            println(awsResult)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    @TaskAction
    fun validateCfnTemplates() {
        File(cfnTemplatesPath).walkTopDown().filter { it.isFile }.forEach {
            println("Calling aws cloudformation validation with $it")

            if(!validateCfn(it.readText())) {
                throw GradleException("Template failed to validate")
            }
        }
    }
}