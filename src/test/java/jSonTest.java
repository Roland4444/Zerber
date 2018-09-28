import org.json.JSONException;
import org.json.JSONObject;
import org.json4s.JsonAST;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

class jSonTest {

    @Test
    void getJSon() throws JSONException {
        jSon m = new jSon();
        Map<String, String> input = new HashMap<>();
        input.put("0", "555");
        input.put("1", "7777");
        JSONObject jsO = new JSONObject();
        jsO.append("0", "555");
        jsO.append("1", "7777");
        assertEquals(jsO.toString(), m.Map2JSON(input).toString());
    }

    @Test
    public void appendArrayToArray() {
        jSon n = new jSon();
        assertEquals("[\"02\"]", n.appendArrayToArray_Str("[]", "\"02\""));

    }

    @Test
    public void arrayListtoJSON() {
        jSon r = new jSon();
        ArrayList<String> arr = new ArrayList<>();
        arr.add("001");
        arr.add("002");
        assertEquals(2, arr.size());
        assertEquals("[\"001\",\"002\"]", r.JSON2String(r.ArrayList2JSON(arr)));
    }

    @Test
    public void jsonToString() {
        jsonTestDefs defs = new jsonTestDefs();
        jSon qq = new jSon();
        JsonAST.JValue j = defs.primitive4test();
        assertEquals(qq.JSON2String(j), "[\"2\",\"3\"]");
    }

    @Test
    public void testAppending() {
        jsonTestDefs defs = new jsonTestDefs();
        jSon qq = new jSon();
        JsonAST.JArray j = (JsonAST.JArray) defs.primitive4test();
        JsonAST.JArray added = (JsonAST.JArray) defs.added_test();
        JsonAST.JValue Result = defs.result_test();
        assertEquals(qq.JSON2String(Result), qq.JSON2String(qq.appendArrArr_JValue(j, added)));
        System.out.println(qq.JSON2String(qq.appendArrArr_JValue(j, added)));

    }

    @Test
    public void arrayList2JSON() {
        jSon m = new jSon();
        ArrayList<String> arr = new ArrayList<>();
        arr.add("001");
        arr.add("002");
        assertEquals("[\"001\",\"002\"]", m.JSON2String(m.ArrayList2JSON(arr)));
    }
}