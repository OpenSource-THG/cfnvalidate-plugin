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

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File


class CfnValidationTaskFunctionalTest {

    @get:Rule
    var temp = TemporaryFolder()

    private fun File.copyFromResource(resourceName: String) {
        ClassLoader.getSystemResourceAsStream(resourceName).use { inputStream ->
            outputStream().use { inputStream.copyTo(it) }
        }
    }

    @Test
    fun `check that plugin is loaded correctly`() {
        temp.newFile("build.gradle").copyFromResource("test.gradle")
        GradleRunner.create()
                .withProjectDir(temp.root)
                .withTestKitDir(temp.newFolder())
                .withPluginClasspath()
                .build()
    }

    @Test
    fun `check that gradle executes cfnvalidate correctly`() {
        temp.newFile("build.gradle").copyFromResource("test.gradle")
        val result: BuildResult = GradleRunner.create()
                .withProjectDir(temp.root)
                .withTestKitDir(temp.newFolder())
                .withPluginClasspath()
                .withArguments("cfnvalidate")
                .build()

        Assert.assertNotNull(result)
        Assert.assertTrue(result.output.contains("A sample template"))
    }


}