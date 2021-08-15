import com.sour.mybatis.bean.Employee;
import com.sour.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 两级缓存
 *  一级缓存(本地缓存) (SqlSession的一个map) :
 *      没法关闭, 一直开启
 *      与数据库'同一次会话期间Session'查询到的数据会放到本地缓存
 *      以后如果需要获取相同的数据, 直接从缓存中拿, 没必要再去查数据库
 *
 *      失效的情况
 *          1, SqlSession变了
 *          2, SqlSession相同, 两次查询操作直接执行了增删改, 这次增删改可能会对当前数据库有影响 (修改了就会失效)
 *          3, SqlSession相同, 手动清空了一级缓存
 *
 *  二级缓存(全局缓存) (基于nameSpace级别的缓存, 一个nameSpace对应一个缓存)
 *      工作机制:
 *          1, 一个会话查询一条数据, 这个数据就会放在当前一级缓存中;
 *          2, 如果会话关闭, 一级缓存的数据会被保存到二级缓存中; 新的会话查询信息, 就可以参照二级缓存中的内容;
 *          3, 不同nameSpace查出的数据会放在自己的map中
 *              sqlSession==EmployeeMapper   => Employee
 *                           ==DepartmentMapper => Department
 *          ***  查出的数据都会先存在一级缓存中, 只有session关闭后才会转移到二级缓存中  ***
 *
 *      使用步骤:
 *          1, 开启全局配置
 *          2, 在需要的mapper.xml中配置使用二级缓存
 *              <cache/>
 *          3, POJO需要实现Serializable
 *
 *      设置:
 *          1, cacheEnabled = false: 关闭的是二级缓存 (一级缓存是一直可用的)
 *          2, 每个select标签都有useCache="true" (二级缓存)
 *          3, 每个增删改标签, 都有flushCache=true  意味着: 增删改执行后都会清掉'一级缓存'和'二级缓存'
 *          4, sqlSession.clearCache(): 只会清除当前session的'一级缓存'
 */
public class MyBatisCache {

    private static SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    public static void main(String[] args) throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
/*            ---------- 一级缓存 ---------------------------  */

            // 两次查询是查询同一个  一级缓存生效
//            cache01(openSession);

            // 两次的session不一样, 一级缓存失效
//            cache02(openSession);


/*            ---------- 二级缓存 ---------------------------  */

            cache03();


        } finally {
            openSession.close();
        }
    }

    /**
     * 两次查询是查询同一个  一级缓存生效
     */
    private static void cache01(SqlSession openSession) {
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
        Employee employee01 = mapper.getEmployeeById(1);
        System.out.println(employee01);

        Employee employee02 = mapper.getEmployeeById(1);
        System.out.println(employee02);
        System.out.println(employee01==employee02);
    }


    /**
     * 两次的session不一样, 一级缓存失效
     */
    private static void cache02(SqlSession openSession) throws IOException {
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
        Employee employee01 = mapper.getEmployeeById(1);
        System.out.println(employee01);

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession2 = sqlSessionFactory.openSession();
        EmployeeMapper mapper02 = openSession2.getMapper(EmployeeMapper.class);
        Employee employee02 = mapper02.getEmployeeById(1);
        System.out.println(employee02);
        System.out.println(employee01==employee02);
    }


    /**
     * 二级缓存
     */
    private static void cache03() throws IOException {
        SqlSessionFactory sqlSessionFactory01 = getSqlSessionFactory();
        SqlSessionFactory sqlSessionFactory02 = getSqlSessionFactory();
        SqlSession openSession01 = sqlSessionFactory01.openSession();
        SqlSession openSession02 = sqlSessionFactory02.openSession();

        EmployeeMapper mapper01 = openSession01.getMapper(EmployeeMapper.class);
        EmployeeMapper mapper02 = openSession02.getMapper(EmployeeMapper.class);
        try {
            Employee employee01 = mapper01.getEmployeeById(1);
            Employee employee02 = mapper02.getEmployeeById(1);
            // 注意看sql
            System.out.println(employee01);
            System.out.println(employee02);
        } finally {
            openSession01.close();
            openSession02.close();
        }
    }

}
