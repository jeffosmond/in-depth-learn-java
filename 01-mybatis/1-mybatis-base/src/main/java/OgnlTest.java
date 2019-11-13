import entity.User;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import org.junit.Test;

/**
 * @author JeffOsmond
 * @date 2019/11/13
 * Ognl表达式使用举例
 * OgnlContext用法
 *     1.使用Ognl表达式语言取值，如果取非根元素的值，必须用#号
 *     2.使用Ognl表达式语言取值，如果取根元素的值，不用#号
 *     3.Ognl可以调用静态方法
 */
public class OgnlTest {

    /**
     * 测试非根元素
     */
    @Test
    public void testNotRootElement() throws OgnlException {
        // 创建一个Ognl上下文对象
        OgnlContext context = new OgnlContext();

        // 1、OgnlContext放入基本变量数据
        context.put("cn", "China");
        // 获取数据
        System.out.println((String)context.get("cn"));

        // 2、OgnlContext放入对象数据
        User user = new User();
        user.setUserId(1L);
        user.setUserName("JeffOsmond");
        user.setUserSex(1);
        context.put("user",user);
        //  获取数据
        System.out.println(context.get("user"));

        // 3、使用Ognl表达式来取值
        // 举例：例如标签<s:a value="#user.id">取值，实际上就是运行了下面的代码获取的
        // 先构建一个Ognl表达式，再解析表达式
        Object ognl = Ognl.parseExpression("#user.userId");
        // 解析表达式
        Object userIdObj1 = Ognl.getValue(ognl, context, context.getRoot());
        System.out.println(userIdObj1);
    }

    /**
     * 测试根元素
     * @throws OgnlException
     */
    @Test
    public void testRootElement() throws OgnlException {
        OgnlContext context = new OgnlContext();
        User newUser = new User();
        newUser.setUserId(2L);
        newUser.setUserName("Jack");
        context.setRoot(newUser);
        Object ognl1 = Ognl.parseExpression("userId");
        // 解析表达式
        Object userIdObj2 = Ognl.getValue(ognl1, context, context.getRoot());
        System.out.println(userIdObj2);
    }

    /**
     * 测试对静态方法调用的支持
     */
    @Test
    public void testUseStaticFunction() throws OgnlException {
        OgnlContext context = new OgnlContext();
        // 一般写法
        Object ognl1 = Ognl.parseExpression("@Math@floor(10.9)");
        Object value1 = Ognl.getValue(ognl1, context, context.getRoot());
        System.out.println(value1);
        // 由于Math类在开发中比较常用，所有也可以这样写
        Object ognl2 = Ognl.parseExpression("@@floor(10.9)");
        Object value2 = Ognl.getValue(ognl2, context, context.getRoot());
        System.out.println(value2);
    }

    /**
     * 测试Mybatis中使用Ognl的写法
     * <if test="userName != null and userName != '' ">
     *     xxx
     * </if>
     */
    @Test
    public void testMybatisOgnl() throws OgnlException {
        OgnlContext context = new OgnlContext();
        User newUser = new User();
        newUser.setUserId(100L);
        newUser.setUserName("Alice");
        context.setRoot(newUser);
        Object ognl = Ognl.parseExpression("userName != null and userName != '' ");
        Object value = Ognl.getValue(ognl, context, context.getRoot());
        System.out.println(value);
    }
}
