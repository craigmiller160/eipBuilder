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

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by craigmiller on 7/7/16.
 */
public class FileTableModel extends AbstractTableModel {

    private final List<SrcFile> srcFiles = new ArrayList<>();
    private final String[] colNames = {
            "File Name", "Subproject?", "Subproject Name"
    };

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 1){
            return Boolean.class;
        }
        return String.class;
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(srcFiles.size() + 1 <= rowIndex){
            return false;
        }

        if(columnIndex == 2){
            SrcFile srcFile = srcFiles.get(rowIndex);
            if(srcFile.isSubproject()){
                return true;
            }
            else{
                return false;
            }
        }
        return true;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return srcFiles.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(rowIndex >= srcFiles.size()){
            return null;
        }
        SrcFile srcFile = srcFiles.get(rowIndex);
        if(columnIndex == 0){
            return srcFile.getName();
        }
        else if(columnIndex == 1){
            return srcFile.isSubproject();
        }
        else{
            return srcFile.getSubprojectName();
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        SrcFile srcFile = null;
        if(rowIndex == srcFiles.size()){
            srcFile = new SrcFile();
        }
        else{
            srcFile = srcFiles.get(rowIndex);
        }

        if(columnIndex == 0){
            srcFile.setName((String) value);
        }
        else if(columnIndex == 1){
            srcFile.setSubproject((Boolean) value);
        }
        else{
            srcFile.setSubprojectName((String) value);
        }

        fireTableDataChanged();
    }
}
