package model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.dao.BookmarkDAO;
import model.dto.BookmarkDTO;
import page.Paging;

public class BookmarkService {

	public List<BookmarkDTO> getAll(BookmarkDTO dto) {
		BookmarkDAO dao = new BookmarkDAO();
		List<BookmarkDTO> data = dao.selectAll(dto);
		/* 실현할 비즈니스로직 있으면 구현 */
		dao.close();
		return data;
	}

	public boolean add(BookmarkDTO dto) {
		BookmarkDAO dao = new BookmarkDAO();
		int id = dao.getId();
		dto.setId(id);
		
		int rowCount = dao.insert(dto);
		/* dao가 아닌 비즈니스로직은 service에 구현해야하며,
		   반영된 행 rowCount 가 1이면 커밋 아니면 문제발생으로 만듦.
		   (visit만들때는 바로 확인하려고 dao에 구현하고 service에는 별다른 로직 없었음)*/
		if(rowCount == 1) {
			dao.commit();
			dao.close();
			return true;
		}
		dao.rollback();
		dao.close();
		return false;
	}
	
	public BookmarkDTO get(BookmarkDTO dto) {
		BookmarkDAO dao = new BookmarkDAO();
		BookmarkDTO data = dao.selectId(dto);
		return data;
	}

	public boolean update(BookmarkDTO dto) {
		BookmarkDAO dao = new BookmarkDAO();
		int rowCount = dao.update(dto);
		if(rowCount == 1) {
			dao.commit();
			dao.close();
			return true;
		}
		dao.rollback();
		dao.close();
		return false;
	}

	public boolean remove(BookmarkDTO dto) {
		BookmarkDAO dao = new BookmarkDAO();
		int count = dao.delete(dto);
		
		if(count == 1) {
			dao.commit();
			dao.close();
			return true;
		}
		dao.rollback();
		dao.close();
		return false;
	}

	public Paging getPage (BookmarkDTO dto, int pNum, int cnt) {
		Map<String, Object> page = new HashMap<String, Object>();
		//여러 타입일 경우 Object를 사용하던가 클래스 객체 하나 더 만들어서 쓰면 됨.
		page.put("start", (pNum - 1) * cnt + 1);
		page.put("end", pNum * cnt);
		page.put("userId", dto.getUserId() );
		
		BookmarkDAO dao = new BookmarkDAO();
		List<BookmarkDTO> dataList = dao.selectPage(page);
		
		int totalRowCount = dao.selectTotalRowCount(dto);
		int mod = totalRowCount % cnt == 0 ? 0 : 1;
		int pageCount = (totalRowCount / cnt) + mod;
		
		Paging paging = new Paging(dataList, pNum, pageCount, cnt, 5);
		
		dao.close();
		return paging;
	}

//	public List<Integer> getPageList(BookmarkDTO dto, int cnt) {
//		BookmarkDAO dao = new BookmarkDAO();
//		int totalRowCount = dao.selectTotalRowCount(dto);
//		int mod = totalRowCount % cnt == 0? 0 : 1;
//		int pageCount = (totalRowCount) / cnt + mod;
//		
//		List<Integer> pageList = new ArrayList<Integer>();
//		for(int i = 1; i <= pageCount; i++) {
//			pageList.add(i);
//		}
//		return pageList;
//	}

//	public int getLastPageNumber(BookmarkDTO dto, int cnt) {
//		BookmarkDAO dao = new BookmarkDAO();
//		int totalRowCount = dao.selectTotalRowCount(dto);
//		int mod = totalRowCount % cnt == 0 ? 0 : 1;
//		int pageCount = (totalRowCount / cnt) + mod;
//		dao.close();
//		return pageCount;
//	}
}
