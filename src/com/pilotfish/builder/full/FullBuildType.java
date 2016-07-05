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

package com.pilotfish.builder.full;

import com.pilotfish.builder.RunConfigType;

/**
 * Created by craigmiller on 7/5/16.
 */
public enum FullBuildType {

    NONE (""),
    EI_CONSOLE ("eiConsole"),
    EIP_WAR ("eiPlatform (war)"),
    EIC_BUNDLE ("eiConsole + eiPlatform");

    private final String displayTitle;

    FullBuildType(String displayTitle){
        this.displayTitle = displayTitle;
    }

    public static FullBuildType getTypeForTitle(String title){
        for(FullBuildType bt : values()){
            if(bt.toString().equals(title)){
                return bt;
            }
        }
        throw new IllegalArgumentException("No FullBuildType for title: " + title);
    }

    @Override
    public String toString(){
        return displayTitle;
    }

}
