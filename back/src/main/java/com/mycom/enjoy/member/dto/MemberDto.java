package com.mycom.enjoy.member.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {
	private int memberId;
	private String memberName;
	private String memberEmail;
	private String memberPwd;
	private String memberPosition;
	private String memberGender;
	private String memberBirth;
	private String memberPhone;
	private int memberRegion;
}
