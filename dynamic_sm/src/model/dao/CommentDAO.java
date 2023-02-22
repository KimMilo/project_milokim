package model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import database.connect.OracleConnection;
import model.dto.CommentDTO;

public class CommentDAO {
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
	
	public List<CommentDTO> selectCmtPage(Map<String, Integer> page) {
		List<CommentDTO> dataList = session.selectList("commentMapper.selectCmtPage", page);
		return dataList;
	}
	
	public int selectTotalRowCount() {
		int count = session.selectOne("commentMapper.selectTotalRowCount");
		return count;
	}

	public int insertComment(CommentDTO dto) {
		int count = session.insert("commentMapper.insert", dto);
		return count;
	}

	public int deleteComment(CommentDTO dto) {
		int count = session.delete("commentMapper.delete", dto);
		return count;
	}

	public int pushComment(CommentDTO dto) {
		int count = session.update("commentMapper.pushComment", dto);
		return count;		
	}

}
