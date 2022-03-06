package com.pc.sz.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 焱宣
 * @Description
 * @create 2022-02-09 11:24
 */

@ApiModel(value = "用户对象BO",description = "从客户端，由用户传入的数据封装在此entity中")
public class UserBo {
    @ApiModelProperty(value="用户名",name="username",example = "imooc")
    private String userName;
    private String passWord;
    private String confirmPassword;


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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
