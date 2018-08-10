package com.mg.beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName User
 * @Author fujian
 * @Date 2018-06-25 17:34
 **/
@Entity
@Table(name="T_USER")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String passWord;

    @Column(nullable = true, unique = true)
    private String nickName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = true)
    private Short sex;

    @Column(nullable = true, unique = true)
    private String phoneNum;

    @Column(nullable = false)
    private Date regTime;

    @Column(nullable = false)
    private Short status;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "T_USER_ROLE",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName ="roleID")})
    private List<RoleEntity> roleList ;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public List<RoleEntity> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleEntity> roleList) {
        this.roleList = roleList;
    }
}
