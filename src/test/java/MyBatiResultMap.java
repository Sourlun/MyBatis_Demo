import com.sour.mybatis.bean.Department;
import com.sour.mybatis.bean.Employee;
import com.sour.mybatis.dao.DepartmentMapper;
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

        getEmployeeById(sqlSessionFactory);

        getEmpAndDept(sqlSessionFactory);

        getEmpAndDeptByAssociation(sqlSessionFactory);

        // 分步
        getEmployeeByIdStep(sqlSessionFactory);

        // 一对多的获取
        getByIdPlus(sqlSessionFactory);

        // 一对多获取 (分步)
        getByIdStep(sqlSessionFactory);
    }

    /**
     * 一对多获取 (分步)
     */
    private static void getByIdStep(SqlSessionFactory sqlSessionFactory) {
        SqlSession openSession = sqlSessionFactory.openSession();
        DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
        Department department = mapper.getByIdStep(1);
        System.out.println(department);
    }

    /**
     * 一对多的获取
     */
    private static void getByIdPlus(SqlSessionFactory sqlSessionFactory) {
        SqlSession openSession = sqlSessionFactory.openSession();
        DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
        Department department = mapper.getByIdPlus(1);
        System.out.println(department);
    }

    /**
     * 分步
     */
    private static void getEmployeeByIdStep(SqlSessionFactory sqlSessionFactory) {
        SqlSession openSession = sqlSessionFactory.openSession();
        EmployeePlusMapper mapper = openSession.getMapper(EmployeePlusMapper.class);
        Employee empAndDept = mapper.getEmployeeByIdStep(1);
        System.out.println(empAndDept);
    }

    private static void getEmpAndDeptByAssociation(SqlSessionFactory sqlSessionFactory) {
        SqlSession openSession = sqlSessionFactory.openSession();
        EmployeePlusMapper mapper = openSession.getMapper(EmployeePlusMapper.class);
        Employee empAndDept = mapper.getEmpAndDeptByAssociation(1);
        System.out.println(empAndDept);
    }

    private static void getEmpAndDept(SqlSessionFactory sqlSessionFactory) {
        SqlSession openSession = sqlSessionFactory.openSession();
        EmployeePlusMapper mapper = openSession.getMapper(EmployeePlusMapper.class);
        Employee empAndDept = mapper.getEmpAndDept(1);
        System.out.println(empAndDept);
    }

    private static void getEmployeeById(SqlSessionFactory sqlSessionFactory) {
        SqlSession openSession = sqlSessionFactory.openSession();
        EmployeePlusMapper mapper = openSession.getMapper(EmployeePlusMapper.class);
        Employee employee = mapper.getEmployeeById(1);
        System.out.println(employee);
    }
}
