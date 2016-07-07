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

/**
 * Created by craigmiller on 7/7/16.
 */
public class SrcFile {

    private String name;
    private boolean subproject;
    private String subprojectName;

    public SrcFile(){
        this("", false, "");
    }

    public SrcFile(String name, boolean subproject, String subprojectName) {
        this.name = name;
        this.subproject = subproject;
        this.subprojectName = subprojectName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSubproject() {
        return subproject;
    }

    public void setSubproject(boolean subproject) {
        this.subproject = subproject;
    }

    public String getSubprojectName() {
        return subprojectName;
    }

    public void setSubprojectName(String subprojectName) {
        this.subprojectName = subprojectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SrcFile srcFile = (SrcFile) o;

        if (isSubproject() != srcFile.isSubproject()) return false;
        if (getName() != null ? !getName().equals(srcFile.getName()) : srcFile.getName() != null) return false;
        return getSubprojectName() != null ? getSubprojectName().equals(srcFile.getSubprojectName()) : srcFile.getSubprojectName() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (isSubproject() ? 1 : 0);
        result = 31 * result + (getSubprojectName() != null ? getSubprojectName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        if(subproject){
            builder.append(subprojectName).append("_");
        }
        builder.append(name);
        return builder.toString();
    }
}
