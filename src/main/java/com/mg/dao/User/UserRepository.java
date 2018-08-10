package com.mg.dao.User;

import com.mg.beans.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @Auther: fujian
 * @Date: 2018/7/11 14:02
 * @Description:
 */
@Component
public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByUserName(String userName);

    UserEntity findByUserNameOrEmail(String username, String email);
}
