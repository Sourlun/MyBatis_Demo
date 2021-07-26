import com.sour.mybatis.bean.Employee;
import com.sour.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 接口式 : 推荐
 *  原先:     dao => daoImpl
 *  mybatis: mapper => xxMapper.xml
 *
 *  sqlSession: 代表和数据库的一次会话, 用完必须关闭
 *  sqlSession和connection一样不是线程安全, 每次使用都应该获取新的对象
 *  mapper没有实现类, 但是mybatis会为这个接口接口生成一个代理对象
 *  两个重要的配置文件:
 *      mybatis全局配置文件: 数据库连接信息. 事务管理器...
 *      sql映射文件: 保存了每一个sql的映射信息
 *          将sql抽取出来
 */
public class MyBatisTest01 {


    /**
     * 通过xml的方式
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // 1. 根据xml文件(全局配置文件) , 创建一个SqlSessionFactory对象
        //  有数据源一些运行环境信息
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2, 获取SqlSession实例, 能直接执行已经映射的sql语句
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            // 获取接口实现类对象
            // 会为接口自动地创建代理对象, 代理对象去执行sql
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmployeeById(1);
            System.out.println(mapper.getClass());
            System.out.println(employee);
        } finally {
            openSession.close();
        }
    }
}
