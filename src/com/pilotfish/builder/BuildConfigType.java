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

package com.pilotfish.builder;

/**
 * Created by craigmiller on 7/5/16.
 */
public enum BuildConfigType {

    NONE (""),
    FULL_APP ("Full Application"),
    CUSTOM_MODULE ("Custom Module");

    private final String displayTitle;

    BuildConfigType(String displayTitle){
        this.displayTitle = displayTitle;
    }

    public static BuildConfigType getTypeForTitle(String title){
        for(BuildConfigType bt : values()){
            if(bt.toString().equals(title)){
                return bt;
            }
        }
        throw new IllegalArgumentException("No BuildConfigType for title: " + title);
    }

    @Override
    public String toString(){
        return displayTitle;
    }

}
