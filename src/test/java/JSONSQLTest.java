import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.Assert.*;

public class JSONSQLTest {

    @Test
    public void getJSonfromQuery() throws FileNotFoundException {
        JSONSQL jsql = new JSONSQL();
        String result = jsql.getJSonfromQuery("select * from dbo.banks");
        assertNotEquals(null, result);
        PrintWriter pw = new PrintWriter("out.js");
        pw.write(result);
        pw.close();

    }
}