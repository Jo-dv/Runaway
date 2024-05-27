package com.mycom.enjoy.index.dto;

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

public class InfoDto {
	private int attractionTotal;
	private int memberTotal;
	private int articleTotal;
	private int mostCategory;
}
