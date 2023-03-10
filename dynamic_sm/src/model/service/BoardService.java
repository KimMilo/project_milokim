package model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.dao.BoardDAO;
import model.dao.CommentDAO;
import model.dto.BoardDTO;
import model.dto.CommentDTO;
import page.Paging;

public class BoardService {

	public Paging getPage(int pageNumber, int pageListLimit) {
		Map<String, Integer> page = new HashMap<String, Integer>();
		page.put("start", (pageNumber - 1) * pageListLimit + 1);
		page.put("end", pageNumber * pageListLimit);
		
		BoardDAO dao = new BoardDAO();
		List<BoardDTO> data = dao.selectPage(page);
		
		int totalRowCount = dao.selectTotalRowCount();
		int mod = totalRowCount % pageListLimit == 0 ? 0 : 1;
		int pageCount = (totalRowCount / pageListLimit) + mod;
		
		Paging paging = new Paging(data, pageNumber, pageCount, pageListLimit, 5);
		dao.close();
		return paging;
	}

	public BoardDTO detail(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		dao.update(dto);
		
		BoardDTO data = dao.selectDetail(dto);
		if(data != null) {
			dao.commit();
			dao.close();
			return data;
		}
		dao.rollback();
		dao.close();
		return data;
	}

	public int insertBoard(BoardDTO dto) {
		int result = -1;
		BoardDAO dao = new BoardDAO();
		result = dao.insertBoard(dto);
		if(result == 1) {
			dao.commit();
			dao.close();
			return result;
		}
		dao.rollback();
		dao.close();
		return result;
	}

	public int updateBoard(BoardDTO dto) {
		int result = -1;
		BoardDAO dao = new BoardDAO();
		result = dao.updateBoard(dto);
		if(result == 1) {
			dao.commit();
			dao.close();
		}
		dao.rollback();
		dao.close();
		return result;
	}

	public BoardDTO get(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		BoardDTO data = dao.selectId(dto);
		dao.close();
		return data;
	}

	public int deleteBoard(BoardDTO dto) {
		int result = -1;
		BoardDAO dao = new BoardDAO();
		result = dao.delete(dto);
		
		if(result == 1) {
			dao.commit();
			dao.close();
			return result;
		}
		dao.rollback();
		dao.close();
		return result;
	}

	public int deleteComment(CommentDTO dto) {
		int result = -1;
		CommentDAO dao = new CommentDAO();
		result = dao.deleteComment(dto);
		if(result == 1) {
			dao.commit();
			dao.close();
		}
		dao.rollback();
		dao.close();
		return result;
	}
	

}
