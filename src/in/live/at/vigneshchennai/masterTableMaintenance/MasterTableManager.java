/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.live.at.vigneshchennai.masterTableMaintenance;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vignesh
 */
public class MasterTableManager {
    private Map<String, MasterTable> 
            mtds = new HashMap<String, MasterTable>();
    
    public MasterTable getMasterTable(String id) {
        return mtds.get(id);
    }
    
    public void addMasterTable(MasterTable masterTable) {
        mtds.put(masterTable.getId(), masterTable);
    }
}
