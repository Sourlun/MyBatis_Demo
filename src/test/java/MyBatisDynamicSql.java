import com.sour.mybatis.bean.Employee;
import com.sour.mybatis.dao.EmployeeDynamicSqlMapper;
import com.sour.mybatis.dao.EmployeePlusMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 动态sql
 */
public class MyBatisDynamicSql {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 携带了哪个字段就用哪个字段查询
        getByConditionIf(sqlSessionFactory);

        // sql字符串前缀后缀规则
        getByConditionTrim(sqlSessionFactory);

        // 类似switch
        getByConditionChoose(sqlSessionFactory);

        // 根据实体类里面的id更新 带了哪一列的值就更新哪一列
        updateEmp(sqlSessionFactory);

        // 批量查询
        getEmpsByConditionForeach(sqlSessionFactory);

        // 批量保存 1
        addEmps(sqlSessionFactory);

        // bind的使用: 把%xx%弄成一个整体
        testBind(sqlSessionFactory);
    }

    /**
     * bind的使用: 把%xx%弄成一个整体
     */
    private static void testBind(SqlSessionFactory sqlSessionFactory) {
        SqlSession openSession = sqlSessionFactory.openSession();
        EmployeeDynamicSqlMapper mapper = openSession.getMapper(EmployeeDynamicSqlMapper.class);
        List<Employee> employees = mapper.testBind(new Employee("o", null, null));
        System.out.println(employees);
    }

    /**
     * 批量保存 1
     */
    private static void addEmps(SqlSessionFactory sqlSessionFactory) {
        SqlSession openSession = sqlSessionFactory.openSession();
        EmployeeDynamicSqlMapper mapper = openSession.getMapper(EmployeeDynamicSqlMapper.class);
        List<Employee> employees = Arrays.asList(new Employee("gene", "324234@wqw.com", "1"), new Employee("oaadfdf", "234234@qwe.com", "0"));
        mapper.addEmps(employees);
        openSession.commit();
        System.out.println(" ==================== foreach insert ==================================");
    }

    /**
     * 批量查询
     */
    private static void getEmpsByConditionForeach(SqlSessionFactory sqlSessionFactory) {
        System.out.println(" --------------- foreach ------------------");
        SqlSession openSession = sqlSessionFactory.openSession();
        EmployeeDynamicSqlMapper mapper = openSession.getMapper(EmployeeDynamicSqlMapper.class);
        List<Integer> ids = Arrays.asList(1,2,3,4,5);
        List<Employee> employees = mapper.getEmpsByConditionForeach(ids);
        System.out.println(employees);
        System.out.println(" ==================== foreach ==================================");
    }

    /**
     * 根据实体类里面的id更新 带了哪一列的值就更新哪一列
     */
    private static void updateEmp(SqlSessionFactory sqlSessionFactory) {
        SqlSession openSession = sqlSessionFactory.openSession();
        EmployeeDynamicSqlMapper mapper = openSession.getMapper(EmployeeDynamicSqlMapper.class);
        Employee employee = new Employee();
        employee.setId(6);
        employee.setEmail("9234i@plj.cpm");
        mapper.updateEmp(employee);
        openSession.commit();
        System.out.println(employee);
        System.out.println(" ============== 更新成功 =====================");
    }

    /**
     * 类似switch
     */
    private static void getByConditionChoose(SqlSessionFactory sqlSessionFactory) {
        System.out.println(" --------------- choose ------------------");

        SqlSession openSession = sqlSessionFactory.openSession();
        EmployeeDynamicSqlMapper mapper = openSession.getMapper(EmployeeDynamicSqlMapper.class);
        Employee employee = new Employee();
        employee.setLastName("%je%");
        List<Employee> byConditionIf = mapper.getByConditionChoose(employee);
        System.out.println(byConditionIf);

        Employee employee1 = new Employee();
        List<Employee> byConditionIfq = mapper.getByConditionChoose(employee1);
        System.out.println(byConditionIfq);

        System.out.println(" ==================== choose ==================================");
    }

    /**
     * sql字符串前缀后缀规则
     */
    private static void getByConditionTrim(SqlSessionFactory sqlSessionFactory) {
        SqlSession openSession = sqlSessionFactory.openSession();
        EmployeeDynamicSqlMapper mapper = openSession.getMapper(EmployeeDynamicSqlMapper.class);
        Employee employee = new Employee();
        employee.setEmail("%123%");
        List<Employee> byConditionIf = mapper.getByConditionTrim(employee);
        System.out.println(byConditionIf);
    }

    /**
     * 携带了哪个字段就用哪个字段查询
     */
    private static void getByConditionIf(SqlSessionFactory sqlSessionFactory) {
        SqlSession openSession = sqlSessionFactory.openSession();
        EmployeeDynamicSqlMapper mapper = openSession.getMapper(EmployeeDynamicSqlMapper.class);
        Employee employee = new Employee();
        employee.setEmail("%123%");
        List<Employee> byConditionIf = mapper.getByConditionIf(employee);
        System.out.println(byConditionIf);
    }
}
