package com.shaojun.networkdemo.network.service.models;

import java.io.Serializable;

public class User implements Serializable {

	public String sex;
	public String userType;
	public String accountName;
	public String userToken;
	public String email;
	public String userIDCard;
	public String avatarUrl;    //头像url
    public String userStatus;    //已认证状态不允许修改displayName和userIDCard
	public String userName;
	public String birthDate;
	public String displayName;
	public String mobile;

	@Override
	public String toString() {
		return super.toString();
	}
}