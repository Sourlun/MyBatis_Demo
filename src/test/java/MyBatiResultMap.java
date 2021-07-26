import com.sour.mybatis.bean.Employee;
import com.sour.mybatis.dao.EmployeeMapper;
import com.sour.mybatis.dao.EmployeePlusMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 使用resultMap
 */
public class MyBatiResultMap {

    public static void main(String[] args) throws IOException {
        // 1. 根据xml文件(全局配置文件) , 创建一个SqlSessionFactory对象
        //  有数据源一些运行环境信息
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession openSession = sqlSessionFactory.openSession();
        EmployeePlusMapper mapper = openSession.getMapper(EmployeePlusMapper.class);
        Employee employee = mapper.getEmployeeById(1);
        System.out.println(employee);
    }
}
