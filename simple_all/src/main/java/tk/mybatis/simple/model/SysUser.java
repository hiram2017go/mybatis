package tk.mybatis.simple.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SysUser implements Serializable {

    private static final long serialVersionUID = -328602757171077630L;

    /*用户id*/
    private Long id;

    /*用户名*/
    private String userName;

    /*用户密码*/
    private String userPassword;

    /*用户邮箱*/
    private String userEmail;

    /*用户简介*/
    private String userInfo;

    /*头像*/
    private byte[] headImg;

    /*创建时间*/
    private Date createTime;

    /*用户角色的集合*/
    private List<SysRole> sysRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public byte[] getHeadImg() {
        return headImg;
    }

    public void setHeadImg(byte[] headImg) {
        this.headImg = headImg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<SysRole> getSysRole() {
        return sysRole;
    }

    public void setSysRole(List<SysRole> sysRole) {
        this.sysRole = sysRole;
    }
}
