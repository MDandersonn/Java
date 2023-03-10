package kr.co.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.main.OracleConnection;
import kr.co.vo.AccountVO;
public class AccountDAO {
	
	private SqlSession sess = OracleConnection.getSqlSession();

	public List<AccountVO> selectAccountRequest() {
		List<AccountVO> resultSet = sess.selectList("test.reqAccount");
		return resultSet;
	}

	public boolean insertAccountRequest(AccountVO vo) {
		int result = sess.insert("test.insertReqAccount", vo);
		if(result == 1) {
			sess.commit();
			return true;
		} else {
			sess.rollback();
			return false;
		}
	}

	public boolean duplicationCheck(AccountVO vo) {
		int result = sess.selectOne("test.dupReqAccountNickname", vo);
		if(result == 1) {
			return true; 
		}
		
		result = sess.selectOne("test.dupReqAccountEmail", vo);
		if(result == 1) {
			return true;
		}
		return false;
	}

	public List<AccountVO> selectAccountList() {
		List<AccountVO> result = sess.selectList("test.selectAccountList");
		return result;
	}

	public void updateAccountRequest(int id) {
		int result = sess.update("test.updateAccountRequest", id);
		if(result == 1) {
			AccountVO account = sess.selectOne("test.selectAccountRequestId", id);
			result = sess.insert("test.insertAccount", account);
			if(result == 1) {
				sess.commit();
			} else {
				sess.rollback();
			}
		}
		sess.rollback();
	}

	public AccountVO selectAccount(AccountVO data) {
		AccountVO result = sess.selectOne("test.selectAccount", data);
		
		if(result != null) {
			int cnt = sess.update("test.updateAccountLoginDate", data);
			if(cnt == 1) {
				cnt = sess.insert("test.insertAccountLogLoginDate", data);
				if(cnt == 1) {
					sess.commit();
				} else {
					sess.rollback();
				}
			} else {
				sess.rollback();
			}
		}
		return result;
	}

	public void insertAccountLogoutLog(AccountVO user) {
		int result = sess.insert("test.insertAccountLogoutLog", user);
		if(result == 1) {
			sess.commit();
		} else {
			sess.rollback();
		}
	}

}