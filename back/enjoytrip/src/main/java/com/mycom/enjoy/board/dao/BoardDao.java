package com.mycom.enjoy.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.enjoy.board.dto.BoardDto;
import com.mycom.enjoy.board.dto.BoardParamDto;

@Mapper
public interface BoardDao {

	int boardUpdate(BoardDto dto);
	int boardDelete(int boardId);
	//11/17 수정
	List<BoardDto> boardList(BoardParamDto boardParamDto);
	int boardInsert(BoardDto dto);
	
	BoardDto boardDetail(int boardId);
}
