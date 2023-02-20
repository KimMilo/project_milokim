package model.service;

import model.dao.JoinDAO;
import model.dto.JoinDTO;

public class JoinService {

	public int add(JoinDTO dto) {
		JoinDAO dao = new JoinDAO();
		
		JoinDTO data = dao.selectUser(dto);
		
		if(data == null) {
			int count = dao.insert(dto);
			if(count == 1) {
				dao.commit();
				dao.close();
				return 1;
			}
			dao.rollback();
			dao.close();
			return -1;
		}
		dao.close();
		return -2;
	}

	public JoinDTO login(JoinDTO dto) {
		JoinDAO dao = new JoinDAO();
		JoinDTO data = dao.selectUser(dto);
		
		if(data == null) {
			dao.close();
			return data;
		} else {
			if(data.getPassword().equals(dto.getPassword())) {
				dao.close();
				return data;
			} else {
				dao.close();
				return null;
			}
		}
	}

	public JoinDTO update(JoinDTO data, JoinDTO updateData, String password) {
		JoinDAO dao = new JoinDAO();
		JoinDTO nowData = dao.selectUser(data);
		if(nowData.getPassword().equals(password)) {
			nowData.setPassword(updateData.getPassword());
			nowData.setEmail(updateData.getEmail());
			int count = dao.update(nowData);
			if(count == 1) {
				dao.commit();
				dao.close();
				return nowData;
			}
			return null;
		}
		return null;
		
	}

	public int dupIdChk(JoinDTO dto) {
		int result = 0;
		JoinDAO dao = new JoinDAO();
		result = dao.dupIdChk(dto);
		dao.close();
		return result;
	}

	public int deleteUser(JoinDTO dto, JoinDTO data) {
		int result = 0;
		JoinDAO dao = new JoinDAO();
		if(dto.getPassword().equals(data.getPassword())) {
			result = dao.delete(data);
			if(result == 1) {
				dao.commit();
				dao.close();
				return result;
			} else {
				dao.rollback();
				dao.close();
				return result;
			}
		}
		dao.close();
		return result;
	}
}
