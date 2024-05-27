package com.mycom.enjoy.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginResultDto {
	private int memberId;
	private String memberName;
	private String memberEmail;
	private String memberPwd;
	private String memberPosition;
	private String memberGender;
	private String memberBirth;
	private String memberPhone;
	private int memberRegion;
	private String sidoName;
}
