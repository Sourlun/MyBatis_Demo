import com.sour.mybatis.bean.Employee;
import com.sour.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTestCRUD {


    public static void main(String[] args) throws IOException {
        // 1. 根据xml文件(全局配置文件) , 创建一个SqlSessionFactory对象
        //  有数据源一些运行环境信息
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        create(sqlSessionFactory);
//        update(sqlSessionFactory);


    }

    private static void update(SqlSessionFactory sqlSessionFactory) {
        // 2, 获取SqlSession实例, 能直接执行已经映射的sql语句 (获取到的sqlSession不会自动提交)
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmployeeById(1);
            employee.setEmail("555555@eq.com");
            mapper.updateEmployee(employee);

            // 需要手动提交
            openSession.commit();
        } finally {
            openSession.close();
        }
    }

    private static void create(SqlSessionFactory sqlSessionFactory) {
        // 2, 获取SqlSession实例, 能直接执行已经映射的sql语句 openSession(true): 自动提交
        SqlSession openSession = sqlSessionFactory.openSession(true);
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee("Jerry", "123@rr.com", "1");
            mapper.addEmployee(employee);
            System.out.println(employee.getId());

        } finally {
            openSession.close();
        }
    }
}
