import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
class jSonTest {

    @Test
    void getJSon() throws JSONException {
        jSon js = new jSon();
        Map<String, String> input = new HashMap<>();
        input.put("0", "555");
        input.put("1", "7777");
        JSONObject jsO = new JSONObject();
        jsO.append("0", "555");
        jsO.append("1", "7777");
        assertEquals(jsO.toString(), js.getJSonfromMap(input).toString());


    }

    @Test
    void printJSON() throws JSONException {
        JS m = new JS();
        m.main(null);


    }
}