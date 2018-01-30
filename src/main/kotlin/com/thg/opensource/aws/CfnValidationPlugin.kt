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

import com.thg.opensource.aws.tasks.CfnValidationTask
import com.thg.opensource.aws.tasks.JsonValidateTask
import com.thg.opensource.aws.tasks.YamlValidateTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class CfnValidationPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            project.tasks?.let {
                it.create("cfnvalidate", CfnValidationTask::class.java)
            }

            project.tasks?.let {
                it.create("jsonvalidate", JsonValidateTask::class.java)
            }

            project.tasks?.let {
                it.create("yamlvalidate", YamlValidateTask::class.java)
            }

            project.afterEvaluate {
                project.logger.info("configured cfnvalidate")
            }

        }
    }
}