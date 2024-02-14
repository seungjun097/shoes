package service;

import java.util.List;

import domain.board.BoardDAO;
import domain.board.BoardDTO;

public class BoardService {
	
	BoardDAO boardDao;
		
	public BoardService() {
		this.boardDao = new BoardDAO();
	}
	
	public List<BoardDTO> boardList() {
		return boardDao.boardlist();
	}
	
	public int insertWrite(BoardDTO dto) {
		return boardDao.insertWrite(dto);
	}
	public void updateVisitCount(String board_key) {
		boardDao.updateVisitCount(board_key);
	}
	public BoardDTO selectView(String baord_key) {
		return boardDao.selectView(baord_key);
	}
	public int updateEdit(BoardDTO dto) {
		return boardDao.updateEdit(dto);
	}
	public int deletePost(String board_key) {
		return boardDao.deletePost(board_key);
	}
}
