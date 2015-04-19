/*
 * Copyright 2015 the original author or authors.
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

package org.gradle.tooling;

import org.gradle.api.Incubating;
import org.gradle.api.Nullable;
import org.gradle.tooling.events.TestKind;

/**
 * Describes a test that runs on the JVM and for which an event has occurred. At least
 * a suite name, class name, or method name is available for each JVM test.
 *
 * @since 2.4
 */
@Incubating
public interface JvmTestDescriptor extends TestDescriptor {

    /**
     * The type of the test (suite or atomic for example).
     *
     * @return the test kind
     */
    TestKind getTestKind();

    /**
     * Returns the name of the test suite, if any.
     *
     * @return The name of the test suite.
     */
    @Nullable
    String getSuiteName();

    /**
     * Returns the name of the test class, if any.
     *
     * @return The name of the test class.
     */
    @Nullable
    String getClassName();

    /**
     * Returns the name of the test method, if any.
     *
     * @return The name of the test method.
     */
    @Nullable
    String getMethodName();

}
