package com.mg.dao.Role;

import com.mg.beans.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @Auther: fujian
 * @Date: 2018/7/23 12:01
 * @Description:
 */
@Component
public interface RoleRepository extends JpaRepository<RoleEntity,String> {
}
