package model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.dao.BoardDAO;
import model.dto.BoardDTO;
import model.dto.JoinDTO;
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
		int result = 0;
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
	

}
