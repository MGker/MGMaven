import com.mg.Application;
import com.mg.beans.RoleEntity;
import com.mg.beans.UserEntity;
import com.mg.dao.Role.RoleRepository;
import com.mg.dao.User.UserRepository;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Auther: fujian
 * @Date: 2018/7/23 13:41
 * @Description:
 */
@SpringBootTest(classes=Application.class)
@RunWith(SpringRunner.class)
public class UserTest {
    @Resource
    UserRepository userRepository;
    @Resource
    RoleRepository roleRepository;
    @Test
   // @Transactional
    public void insertUser(){
        UserEntity u1 = new UserEntity();
        u1.setUserName("mgker1");
        u1.setNickName("萌客1");
        String password=new SimpleHash("MD5","123456",u1.getUserName(),2).toHex();
        u1.setPassWord(password);
        u1.setEmail("1234567@qq.com");
        u1.setRegTime(new Date());
        u1.setSex((short)1);
        u1.setStatus((short)1);

        userRepository.save(u1);
//        try {
//            throw new Exception();
//        } catch (Exception e) {
//            //e.printStackTrace();
//            System.err.println("出现异常");
//        }
    }

    @Test
    public void insertRole(){
        System.out.println("添加角色");
        RoleEntity r1 = new RoleEntity();
        r1.setRoleName("admin");
        r1.setComments("超级管理员");
        r1.setStatus((short)1);
        roleRepository.save(r1);
    }

    @Test
    public void getUser(){
        UserEntity u1 = userRepository.findOne("402886a464db0cae0164db0cd25e0000");
        List<RoleEntity> rolelist = u1.getRoleList();
        System.out.println("含有"+rolelist.size()+"个角色");
        for(RoleEntity role:rolelist){
            System.out.println(role.getRoleName());
            List<UserEntity> userList = role.getUserList();
            System.out.println(role.getRoleName()+"-00-"+userList.size());
        }



    }
}
