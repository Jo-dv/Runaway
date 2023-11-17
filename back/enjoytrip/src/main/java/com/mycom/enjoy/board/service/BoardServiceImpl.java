package com.mycom.enjoy.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycom.enjoy.board.dao.BoardDao;
import com.mycom.enjoy.board.dto.BoardDto;
import com.mycom.enjoy.board.dto.BoardParamDto;

@Service
public class BoardServiceImpl implements BoardService{
	private final BoardDao dao;
	public BoardServiceImpl(BoardDao dao) {
		this.dao = dao;
	}

	@Override
	public List<BoardDto> boardList(BoardParamDto dto) {
		return dao.boardList(dto);
	}

	@Override
	public int boardInsert(BoardDto dto) {
		return dao.boardInsert(dto);
	}

	@Override
	public int boardUpdate(BoardDto dto) {
		return dao.boardUpdate(dto);
	}

	@Override
	public int boardDelete(int boardId) {
		return dao.boardDelete(boardId);
	}

	@Override
	public BoardDto boardDetail(int boardId) {
		return dao.boardDetail(boardId);
	}

}
