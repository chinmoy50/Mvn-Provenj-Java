package provenj;

import java.util.UUID;
import org.json.simple.JSONObject;

public class Manifest {
//    private UUID m_uuid;
    private String m_filename;

    public void addFile(String filename) {
	m_filename = filename;
    }
    public JSONObject get(){
       JSONObject result = new JSONObject();
       result.put("FileName", m_filename);
       return result;
    }
}
