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
package com.thg.opensource.aws.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.util.concurrent.TimeUnit

open class CfnValidationTask : DefaultTask() {

    @Input
    lateinit var cfnTemplatesPath: String

    private fun String.exec(workingDir: File): Int {
         return try {
            val parts = split("\\s".toRegex())
            val proc = ProcessBuilder(*parts.toTypedArray())
                .directory(workingDir)
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .redirectError(ProcessBuilder.Redirect.PIPE)
                .start()

            proc.waitFor(1, TimeUnit.MINUTES)
            println(proc.inputStream.bufferedReader().readText())
            proc.exitValue()

        } catch (e: Exception) {
            e.printStackTrace()
            1
        }
    }

    @TaskAction
    fun validateCfnTemplates() {
        File(cfnTemplatesPath).walkTopDown().filter { it.isFile }.forEach {
            println("Calling aws cloudformation validation with $it")
            val awsResult: Int = "aws cloudformation validate-template --template-body file://$it".exec(File("."))
            if(awsResult > 0) {
                throw GradleException("Result of validating template was non-zero")
            }
        }
    }
}