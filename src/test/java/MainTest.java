import com.mylibrary.util.JdbcUtil;

import java.sql.Connection;

public class MainTest {

    public static void main(String[] args) throws Exception {
        Connection connection = JdbcUtil.getConnection();
        System.out.println("success");
        JdbcUtil.closeResource(connection,null,null);
    }
}
