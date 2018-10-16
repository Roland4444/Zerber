package DB;
import org.junit.Test;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
public class ExecutorTest {
    zerber.co.uk.Executor exec = new zerber.co.uk.Executor("192.0.0.14", "ODAY", "roman", "rtm37dex", true);
    zerber.co.uk.Executor exec2 = new zerber.co.uk.Executor("192.0.0.14", "RBOOK", "roman", "rtm37dex", true);

    public ExecutorTest() throws SQLException {
    }

    @Test
    public void insert() throws SQLException {
        ResultSet result =  exec2.submit("select * from dbo.smev3files where f_key = '19';");
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
    public void insert1() throws SQLException {
        ArrayList<String> etalon = new ArrayList<>();
        ResultSet result =  exec.submit("select * from dbo.banks");
        ResultSetMetaData rm = result.getMetaData();
        assertEquals(rm.getColumnName(1), "f_key");
        assertTrue(rm.getColumnCount()>1 );
        System.out.println(rm.getColumnCount());
        for (int i = 1; i <=rm.getColumnCount(); i++)
            etalon.add(rm.getColumnName(i));
        assertEquals(etalon, exec.columnsName("select * from dbo.banks"));
    }

    @Test
    public void getHeader(){
        ArrayList columnsName = exec.columnsName("select * from dbo.banks");
        String headerArray ="[";
        for (int i = 0; i< columnsName.size()-2; i++)
            headerArray += "\""+columnsName.get(i)+"\",";
        headerArray += "\""+columnsName.get(columnsName.size()-1)+"\"]";
        System.out.println(headerArray);
    }
}