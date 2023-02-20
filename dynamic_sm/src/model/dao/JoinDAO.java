package model.dao;

import org.apache.ibatis.session.SqlSession;

import database.connect.OracleConnection;
import model.dto.JoinDTO;

public class JoinDAO {

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
	
	public int insert(JoinDTO dto) {
		int count = session.insert("joinMapper.insert", dto);
		return count;
	}

	public JoinDTO selectUser(JoinDTO dto) {
		JoinDTO data = session.selectOne("joinMapper.selectUser", dto);
		return data;
	}

	public int update(JoinDTO dto) {
		int count = session.update("joinMapper.update", dto);
		return count;
	}

	public int dupIdChk(JoinDTO dto) {
		int count = session.selectOne("joinMapper.selectId", dto);
		return count;
	}

	public int delete(JoinDTO data) {
		int count = session.delete("joinMapper.deleteUser", data);
		return count;
	}

}
