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
public class MemberUpdatePwdParams {
	private String memberPwd;
	private String memberNewPwd;
	private int memberId;
}
