import com.sour.mybatis.bean.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {


    public static void main(String[] args) throws IOException {
        // 1. 根据xml文件(全局配置文件) , 创建一个SqlSessionFactory对象
        //  有数据源一些运行环境信息
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2, 获取SqlSession实例, 能直接执行已经映射的sql语句
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            // sql映射文件, 配置了每一个sql, 以及sql封装规则
            // 将sql映射文件注册在全局配置文件中
            // SqlSession代表和数据库的一次会话, 用完关闭
            // 使用sql的唯一标志来告诉mybatis执行的是哪个sql. sql都是保存在sql映射文件里面
            Employee employee = openSession.selectOne("com.sour.mybatis.bean.Employee.selectEmployee", 1);
            System.out.println(employee);
        } finally {
            openSession.close();
        }
    }
}
