package com.thg.opensource.aws.tasks

import com.fasterxml.jackson.databind.ObjectMapper
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File

open class JsonValidateTask: DefaultTask() {

    @Input
    lateinit var jsonFilesPath: String

    var allValid = true

    private fun File.isValidJson(): Boolean {

        return try { ObjectMapper().readTree(inputStream()); true } catch (e: Exception) { allValid = false; false }
    }

    @TaskAction
    fun validateJsonFiles() {
        File(jsonFilesPath).walkTopDown().filter { it.isFile }.forEach {
            println("Validating json file: $it")
            println("valid?: ${it.isValidJson()}")
        }

        if(!allValid) {
            throw GradleException("Some files were invalid.")
        }
    }
}