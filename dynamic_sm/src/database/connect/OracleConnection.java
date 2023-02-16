package database.connect;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class OracleConnection {

	public static SqlSession getSqlSession() {
		SqlSession sess = null;
		
		//src/main/java 다음 경로를 기재하면 됨.
		try(InputStream is = Resources.getResourceAsStream("resources/mybatis-config.xml")) {
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
			sess = factory.openSession(false);
			// false : 수동 commit , true : 자동 commit
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sess;
		
	}

}
