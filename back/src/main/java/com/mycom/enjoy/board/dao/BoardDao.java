package com.mycom.enjoy.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.enjoy.board.dto.BoardDto;
import com.mycom.enjoy.board.dto.BoardParamDto;

@Mapper
public interface BoardDao {
	int boardInsert(BoardDto dto);
	int boardUpdate(BoardDto dto);
	int boardDelete(int boardId);
	BoardDto boardDetail(int boardId);
	List<BoardDto> boardList(BoardParamDto boardParamDto);
	//구현 필요 ( 11/17 ) 
	int boardListTotalCount();
	//boardUserRead
	//map-@param
	int boardUserReadInsert(@Param("boardId") int boardId, @Param("memberId") int memberId);
	int boardReadCountUpdate(int boardId);
	int boardUserReadCount(BoardParamDto boardParamDto);
	int boardReadCountDelete(int boardId);
	
	//검색
	List<BoardDto> boardListSearchWord(BoardParamDto boardParamDto); //검색어
	int boardListSearchWordTotalCount(BoardParamDto boardParamDto); //검색어 가진 총 건수
	
	
	
	
}
