package com.mycom.enjoy.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.enjoy.board.dao.BoardDao;
import com.mycom.enjoy.board.dto.BoardDto;
import com.mycom.enjoy.board.dto.BoardParamDto;
import com.mycom.enjoy.board.dto.BoardResultDto;

@Service
public class BoardServiceImpl implements BoardService {
	private final BoardDao dao;

	public BoardServiceImpl(BoardDao dao) {
		this.dao = dao;
	}

	private final int SUCCESS = 1;
	private final int FAIL = -1;

	@Override
	@Transactional
	public BoardResultDto boardInsert(BoardDto dto) {
		BoardResultDto boardResultDto = new BoardResultDto();
		try {
			dao.boardInsert(dto);
			boardResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}

	@Override
	@Transactional
	public BoardResultDto boardUpdate(BoardDto dto) {
		BoardResultDto boardResultDto = new BoardResultDto();
		try {
			dao.boardUpdate(dto);
			boardResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}

	@Override
	@Transactional
	public BoardResultDto boardDelete(int boardId) {
		BoardResultDto boardResultDto = new BoardResultDto();
		try {
			dao.boardReadCountDelete(boardId);
			dao.boardDelete(boardId);
			boardResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}

	@Override
	@Transactional
	public BoardResultDto boardDetail(BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto = new BoardResultDto();
		try {
			int userReadCnt = dao.boardUserReadCount(boardParamDto);
			if (userReadCnt == 0) {
				dao.boardUserReadInsert(boardParamDto.getBoardId(), boardParamDto.getMemberId());
				dao.boardReadCountUpdate(boardParamDto.getBoardId());
			}

			BoardDto dto = dao.boardDetail(boardParamDto.getBoardId());
			boardResultDto.setDto(dto);
			boardResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}

	@Override
	public BoardResultDto boardList(BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto = new BoardResultDto();

		try {
			List<BoardDto> list = dao.boardList(boardParamDto);
			int count = dao.boardListTotalCount();
			boardResultDto.setList(list);
			boardResultDto.setCount(count);
			boardResultDto.setResult(SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}

		return boardResultDto;
	}

	@Override
	public BoardResultDto boardListSearchWord(BoardParamDto boardParamDto) {
		BoardResultDto boardResultDto = new BoardResultDto();

		try {
			List<BoardDto> list = dao.boardListSearchWord(boardParamDto);
			int count = dao.boardListSearchWordTotalCount(boardParamDto);

			boardResultDto.setList(list);
			boardResultDto.setCount(count);
			boardResultDto.setResult(SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}

		return boardResultDto;
	}

}
