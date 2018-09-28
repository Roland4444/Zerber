import org.junit.Test;

import static org.junit.Assert.*;

public class JSTest {

    @Test
    public void appendArrayToArray() {
        JS js = new JS();
        assertEquals("[\"02\"]", js.appendArrayToArray("[]", "\"02\""));
        assertEquals("[\"02\"]", js.appendArrayToArray("[]", "\"02\""));
    }
}