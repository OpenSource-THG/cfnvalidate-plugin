package com.thg.opensource.aws.tasks

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