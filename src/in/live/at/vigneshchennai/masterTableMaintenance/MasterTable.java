/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.live.at.vigneshchennai.masterTableMaintenance;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author vignesh
 */
public class MasterTable {
    private String id;
    private String displayName;
    private String cabinet;
    private String tableName;
    private String permittedGroup;
    private List<String> columns = new LinkedList<String>();
    private List<String> uniqueColumns = new LinkedList<String>();

    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ID : ").append(id).append("\n");
        builder.append("Cabinet : ").append(cabinet).append("\n");
        builder.append("Display Name : ").append(displayName).append("\n");
        builder.append("Table Name : ").append(tableName).append("\n");
        builder.append("Permitted Group : ").append(permittedGroup).append("\n");
        builder.append("Columns : ");
        for(String column: columns) {
                builder.append(column).append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("\n");
        builder.append("Unique Columns : ");
        for(String column: uniqueColumns) {
                builder.append(column).append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("\n");
        return builder.toString();
    }
    public void addUniqueColumns(String column) {
        uniqueColumns.add(column);
    }
    public void addColumns(String column) {
        columns.add(column);
    }

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermittedGroup() {
        return permittedGroup;
    }

    public void setPermittedGroup(String permittedGroup) {
        this.permittedGroup = permittedGroup;
    }
    

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<String> getUniqueColumns() {
        return uniqueColumns;
    }

    public void setUniqueColumns(List<String> uniqueColumns) {
        this.uniqueColumns = uniqueColumns;
    }
}
