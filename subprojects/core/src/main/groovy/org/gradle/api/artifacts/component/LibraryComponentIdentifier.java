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
package org.gradle.api.artifacts.component;

import org.gradle.api.Incubating;

/**
 * An identifier for a library instance that is built as part of the current build.
 *
 */
@Incubating
public interface LibraryComponentIdentifier extends ComponentIdentifier {
    static final String API_CONFIGURATION_NAME = "Component API";

    /**
     * The project path of the library.
     * @return The project path of the library.
     */
    String getProjectPath();

    /**
     * The name of the library
     * @return the name of the library
     */
    String getLibraryName();
}
