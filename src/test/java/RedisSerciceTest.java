import com.mg.Application;
import com.mg.beans.UserEntity;
import com.mg.dao.User.UserRepository;
import com.mg.service.RedisService;
import com.mg.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @Auther: fujian
 * @Date: 2018/9/3 11:54
 * @Description:
 */
@SpringBootTest(classes=Application.class)
@RunWith(SpringRunner.class)
public class RedisSerciceTest {
    @Resource
    RedisService redisService;

    @Test
    public void test(){
        List<String> list = new LinkedList<String>();
        list.add("qqq1");
        list.add("qqq2");
        list.add("qqq3");

        redisService.set("list1",list);

        Set<String> set = redisService.get("list1",Set.class);

        for(String obj:set){
            System.err.print(obj+"\t");
        }

    }

}
