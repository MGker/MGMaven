import com.mg.Application;
import com.mg.controller.TestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @ClassName T1
 * @Author fujian
 * @Date 2018-06-26 10:51
 **/
@SpringBootTest(classes=Application.class)
@RunWith(SpringRunner.class)
public class T1 {
    @Resource
    TestController testController;
    @Test
    public void m1(){
        System.err.println(testController);
    }
}
