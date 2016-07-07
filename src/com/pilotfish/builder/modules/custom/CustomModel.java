/*
 * Copyright {yyyy} Craig Miller
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pilotfish.builder.modules.custom;

import com.pilotfish.builder.ModuleModel;

/**
 * Created by craigmiller on 7/6/16.
 */
public class CustomModel extends ModuleModel {

    public static final String JAR_TITLE_PROP = "JarTitle";
    public static final String JAR_VERSION_PROP = "JarVersion";

    private String jarTitle;
    private String jarVersion;

    public void setJarTitle(String jarTitle) {
        String oldValue = this.jarTitle;
        this.jarTitle = jarTitle;
        firePropertyChangeEvent(JAR_TITLE_PROP, oldValue, jarTitle);
    }

    public String getJarTitle(){
        return jarTitle;
    }

    public String getJarVersion() {
        return jarVersion;
    }

    public void setJarVersion(String jarVersion) {
        String oldValue = this.jarVersion;
        this.jarVersion = jarVersion;
        firePropertyChangeEvent(JAR_VERSION_PROP, oldValue, jarVersion);
    }
}
