package model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.dao.CommentDAO;
import model.dto.CommentDTO;
import page.Paging;

public class CommentService {
	public Paging getPage(int pageNumber, int pageListLimit, int id) {
		Map<String, Integer> page = new HashMap<String, Integer>();
		page.put("start", (pageNumber - 1) * pageListLimit + 1);
		page.put("end", pageNumber * pageListLimit);
		page.put("bnum", id);
		
		CommentDAO dao = new CommentDAO();
		List<CommentDTO> data = dao.selectCmtPage(page);
		
		int totalRowCount = dao.selectTotalRowCount();
		int mod = totalRowCount % pageListLimit == 0 ? 0 : 1;
		int pageCount = (totalRowCount / pageListLimit) + mod;
		
		Paging paging = new Paging(data, pageNumber, pageCount, pageListLimit, 5, id);
		dao.close();
		return paging;
	}
}
