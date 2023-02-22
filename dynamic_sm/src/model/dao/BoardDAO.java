package model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import database.connect.OracleConnection;
import model.dto.BoardDTO;
import model.dto.JoinDTO;

public class BoardDAO {
	
	private SqlSession session = OracleConnection.getSqlSession();
	
	public void commit() {
		session.commit();
	}
	
	public void rollback() {
		session.rollback();
	}
	
	public void close() {
		session.close();
	}

	public List<BoardDTO> selectPage(Map<String, Integer> page) {
		List<BoardDTO> dataList = session.selectList("boardMapper.selectPage", page);
		return dataList;
	}

	public int selectTotalRowCount() {
		int count = session.selectOne("boardMapper.selectTotalRowCount");
		return count;
	}

	public int update(BoardDTO dto) {
		int data = session.update("boardMapper.update", dto);
		return data;
	}

	public BoardDTO selectDetail(BoardDTO dto) {
		BoardDTO data = session.selectOne("boardMapper.selectDetail", dto);
		return data;
	}

	public int insertBoard(BoardDTO dto) {
		int count = session.insert("boardMapper.insertBoard", dto);
		return count;
	}

	public int updateBoard(BoardDTO dto) {
		int count = session.update("boardMapper.updateBoard", dto);
		return count;
	}

	public BoardDTO selectId(BoardDTO dto) {
		BoardDTO data = session.selectOne("boardMapper.selectId", dto);
		return data;
	}

	public int delete(BoardDTO dto) {
		int count = session.delete("boardMapper.delete", dto);
		return count;
	}
}
