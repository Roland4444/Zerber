import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONWriter;
import org.json4s.JsonAST;

import java.util.Iterator;
import java.util.Map;

class jSon {
    public JSONObject getJSonfromMap(Map<String, String> input) throws JSONException {
        Iterator it = input.entrySet().iterator();
        JSONObject res = new JSONObject();
        while (it.hasNext()){
            Map.Entry pair = (Map.Entry) it.next();
            String key = (String) pair.getKey();
            String value = (String) pair.getValue();
            res.append(key, value);
        }
        return res;

    }


}
