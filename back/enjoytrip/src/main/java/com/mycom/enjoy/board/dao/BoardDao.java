package com.mycom.enjoy.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.enjoy.board.dto.BoardDto;
import com.mycom.enjoy.board.dto.BoardParamDto;

@Mapper
public interface BoardDao {
<<<<<<< HEAD

	int boardUpdate(BoardDto dto);
	int boardDelete(int boardId);
	//11/17 수정
	List<BoardDto> boardList(BoardParamDto boardParamDto);
	int boardInsert(BoardDto dto);
	
=======
	int boardInsert(BoardDto dto);
	int boardUpdate(BoardDto dto);
	int boardDelete(int boardId);
>>>>>>> e0d9626c367d0a7ef0decada53a08cac3195dc44
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
