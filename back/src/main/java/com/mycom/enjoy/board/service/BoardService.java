package com.mycom.enjoy.board.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.enjoy.board.dto.BoardDto;
import com.mycom.enjoy.board.dto.BoardParamDto;
import com.mycom.enjoy.board.dto.BoardResultDto;

public interface BoardService {
	BoardResultDto boardDetail(BoardParamDto boardParamDto);

	BoardResultDto boardDelete(int boardId);

	BoardResultDto boardUpdate(BoardDto dto);

	BoardResultDto boardInsert(BoardDto dto);

	BoardResultDto boardList(BoardParamDto boardParamDto);

	// int boardListTotalCount();
	BoardResultDto boardListSearchWord(BoardParamDto boardParamDto);
}
