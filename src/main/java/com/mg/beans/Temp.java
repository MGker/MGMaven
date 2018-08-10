package com.mg.beans;

import javax.persistence.Column;

/**
 * @Auther: fujian
 * @Date: 2018/7/11 15:48
 * @Description:
 */
public class Temp {
    private String roleName;
    private String description;
    private Integer status;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Temp{" +
                "roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
