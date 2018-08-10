package com.mg.beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Auther: fujian
 * @Date: 2018/7/23 10:14
 * @Description:
 */
@Entity
@Table(name="T_ROLE")
public class RoleEntity implements Serializable {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String roleID;
    @Column(nullable = false, unique = true)
    private String roleName;
    @Column(nullable=true)
    private String comments;
    @Column(nullable = false)
    private Short status;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "roleList")
    private List<UserEntity> userList;

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public List<UserEntity> getUserList() {
        return userList;
    }

    public void setUserList(List<UserEntity> userList) {
        this.userList = userList;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
