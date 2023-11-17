package com.mycom.enjoy.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.enjoy.board.dto.BoardDto;

@Mapper
public interface BoardDao {
	int boardRegister(BoardDto dto);
	int boardUpdate(BoardDto dto);
	int boardDelete(int boardId);
	List<BoardDto> boardList();
	BoardDto boardDetail(int boardId);
}
