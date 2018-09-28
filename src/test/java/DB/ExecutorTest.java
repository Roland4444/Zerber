package DB;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;
public class ExecutorTest {
    zerber.co.uk.Executor exec = new zerber.co.uk.Executor("192.0.0.14", "ODAY", "roman", "rtm37dex", true);

    public ExecutorTest() throws SQLException {
    }

    @Test
    public void insert() throws SQLException {
        ResultSet result =  exec.submit("select * from dbo.banks");
        ResultSetMetaData rm = result.getMetaData();
        while (result.next()){
            for (int i=1; i<=rm.getColumnCount(); i++)
                System.out.println(result.getString(i));
        }
    }

    @Test
    public void getColumn() throws SQLException {
        ResultSet result =  exec.submit("select * from dbo.banks");
        ResultSetMetaData rm = result.getMetaData();
        assertEquals(rm.getColumnName(1), "f_key");
        assertTrue(rm.getColumnCount()>1 );
        System.out.println(rm.getColumnCount());
        for (int i = 1; i <=rm.getColumnCount(); i++)
            System.out.println(rm.getColumnName(i));

    }

    @Test
    public void insert1() {
    }
}