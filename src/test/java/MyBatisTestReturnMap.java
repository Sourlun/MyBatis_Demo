import com.sour.mybatis.bean.Employee;
import com.sour.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 返回Map
 */
public class MyBatisTestReturnMap {

    public static void main(String[] args) throws IOException {
        // 1. 根据xml文件(全局配置文件) , 创建一个SqlSessionFactory对象
        //  有数据源一些运行环境信息
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //返回Map
//        onlyMap(sqlSessionFactory);

        // 返回Map (特殊) Map<主键, Employee>
        getReturnMapByLastNameLike(sqlSessionFactory);

    }

    /**
     * 返回Map
     * @param sqlSessionFactory
     */
    private static void onlyMap(SqlSessionFactory sqlSessionFactory) {
        SqlSession openSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
        Map<String, Object> returnMap = mapper.getReturnMap(1);
        System.out.println(returnMap);
    }


    /**
     * 返回Map (特殊)
     *  Map<主键, Employee>
     * @param sqlSessionFactory
     */
    private static void getReturnMapByLastNameLike(SqlSessionFactory sqlSessionFactory) {
        SqlSession openSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
        Map<Integer, Employee> returnMapByLastNameLike = mapper.getReturnMapByLastNameLike("%e%");
        System.out.println(returnMapByLastNameLike);
    }
}
