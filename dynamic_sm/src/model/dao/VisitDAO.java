package model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import database.connect.OracleConnection;
import model.dto.VisitDTO;

public class VisitDAO {

	/*
	 * Mybatis 관련 라이브러리 필요
	 * 	 1. WEB-INF/lib에 ojdbc 파일 넣기
	 * 	 2. WEB-INF/lib에 mybatis 파일 넣기 (사용자->.m2->mybatis 찾아보기(기존에 했기 때문에 있을거임))
	 * 
	 * --> 결론적으로 lib폴더에 사용할 라이브러리를 넣으면 자동으로 인식함.
	 */
	
	private SqlSession session = OracleConnection.getSqlSession();
	
	public int insert(VisitDTO data) {
		//String res = session.selectOne("visitMapper.connectTest");
		//System.out.println(res);
		//return 0;
		int res = session.insert("visitMapper.insert", data);
		return res;
	}
	public void close() {
		session.close();
	}
	public void commit() {
		session.commit();
	}
	public void rollback() {
		session.rollback();
	}

	public List<VisitDTO> select() {
		List<VisitDTO> dataList = session.selectList("visitMapper.select");
		return dataList;
	}
	
	public List<VisitDTO> selectNickname(VisitDTO data) {
		List<VisitDTO> dataList = session.selectList("visitMapper.selectUserId", data);
		return dataList;
	}
	
	public VisitDTO selectId(VisitDTO dto) {
		VisitDTO data = session.selectOne("visitMapper.selectId", dto);
		return data;
	}

	public int update(VisitDTO dto) {
		int count = session.update("visitMapper.update", dto);
		return count;
	}
	
	public int delete(VisitDTO dto) {
		int count = session.delete("visitMapper.delete", dto);
		return count;
	}

	public int getId() { 
		int id = session.selectOne("visitMapper.getId");
		return id;
	}
	public List<VisitDTO> selectPage(Map<String, Integer> page) {
		List<VisitDTO> dataList = session.selectList("visitMapper.selectPage", page);
		return dataList;
	}
	
	public int selectTotalRowCount() {
		int count = session.selectOne("visitMapper.totalRowCount");
		return count;
	}
}
