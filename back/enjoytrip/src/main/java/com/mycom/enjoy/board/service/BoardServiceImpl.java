package com.mycom.enjoy.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycom.enjoy.board.dao.BoardDao;
import com.mycom.enjoy.board.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService{
	private final BoardDao dao;
	public BoardServiceImpl(BoardDao dao) {
		this.dao = dao;
	}

	@Override
	public List<BoardDto> boardList() {
		return dao.boardList();
	}

	@Override
	public int boardRegister(BoardDto dto) {
		return dao.boardRegister(dto);
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
