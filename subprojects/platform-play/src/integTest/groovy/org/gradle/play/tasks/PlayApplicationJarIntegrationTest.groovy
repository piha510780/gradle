/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.play.tasks

import org.gradle.integtests.fixtures.AbstractIntegrationSpec
import org.gradle.play.integtest.fixtures.app.BasicPlayApp
import org.gradle.test.fixtures.archive.JarTestFixture

class PlayApplicationJarIntegrationTest extends AbstractIntegrationSpec {
    def setup() {
        buildFile << """
            plugins {
                id 'play'
            }

            repositories{
                jcenter()
                maven{
                    name = "typesafe-maven-release"
                    url = "https://repo.typesafe.com/typesafe/maven-releases"
                }
            }
        """

        new BasicPlayApp().writeSources(testDirectory.file("."))
    }

    def "does not rebuild when public assets remain unchanged" () {
        when:
        succeeds "createPlayBinaryJar"

        then:
        executed(":createPlayBinaryJar")
        jar("build/playBinary/lib/play.jar").containsDescendants(
                "public/images/favicon.svg",
                "public/stylesheets/main.css",
                "public/javascripts/hello.js")


        when:
        succeeds "createPlayBinaryJar"

        then:
        skipped(":createPlayBinaryJar")
    }

    def "rebuilds when public assets change" () {
        when:
        succeeds "createPlayBinaryJar"

        then:
        executed(":createPlayBinaryJar")
        jar("build/playBinary/lib/play.jar").containsDescendants(
                "public/images/favicon.svg",
                "public/stylesheets/main.css",
                "public/javascripts/hello.js")


        when:
        file("public/stylesheets/main.css") << "\n"
        succeeds "createPlayBinaryJar"

        then:
        executed(":createPlayBinaryJar")

        and:
        jar("build/playBinary/lib/play.jar").assertFileContent("public/stylesheets/main.css", file("public/stylesheets/main.css").text)
    }

    def "rebuilds when public assets are removed" () {
        when:
        succeeds "createPlayBinaryJar"

        then:
        executed(":createPlayBinaryJar")
        jar("build/playBinary/lib/play.jar").containsDescendants(
                "public/images/favicon.svg",
                "public/stylesheets/main.css",
                "public/javascripts/hello.js")

        when:
        file("public/stylesheets/main.css").delete()
        succeeds "createPlayBinaryJar"

        then:
        executed(":createPlayBinaryJar")

        and:
        jar("build/playBinary/lib/play.jar").countFiles("public/stylesheets/main.css") == 0
    }

    JarTestFixture jar(String fileName) {
        new JarTestFixture(file(fileName))
    }
}