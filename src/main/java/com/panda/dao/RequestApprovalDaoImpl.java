package com.panda.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.panda.pojo.Approval;
import com.panda.pojo.Purchase;
import com.panda.pojo.Request;
import com.panda.pojo.RequestApproval;

@Repository
public class RequestApprovalDaoImpl implements RequestApprovalDao{

	@Resource
	SessionFactory sessionFactory;
	
	/**
	 * @category 获取当前的session
	 * @return
	 */
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<Approval> qureyByRequestCode(String requestPlanCode) {
		return getCurrentSession().createQuery(
                "from " + Approval.class.getName() + " where requestPlanCode='" + requestPlanCode + "'").list();
	}

	@SuppressWarnings({ "unchecked" })
	public List<Request> getUnapproval() {
		return getCurrentSession().createQuery("from " + Request.class.getName()).list();
	}
	
	public Request queryByRequestPlanCode(String requestPlanCode) {
		return (Request)getCurrentSession().createQuery(
				"from " + Request.class.getName() + " where requestPlanCode = '"+ requestPlanCode + "'").uniqueResult();
	}

	public Boolean approvalPass(String requestPlanCode, int status) {
		Session session = getCurrentSession();
		status++;
		String sql = "update t_panda_request t set t.approvalStatus = '" + status +"'"
				+ "where t.requestPlanCode = '" + requestPlanCode +"'";
		SQLQuery query = session.createSQLQuery(sql);
		int updateNum = query.executeUpdate();
		if (updateNum != 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public Boolean approvalBack(String purchasePlanCode) {
		Session session = getCurrentSession();
		String sql = "update t_panda_request t set t.approvalStatus = '1' "
				+ "where t.requestPlanCode = '" + purchasePlanCode +"'";
		SQLQuery query = session.createSQLQuery(sql);
		int updateNum = query.executeUpdate();
		if (updateNum != 0) {
			return true;
		}else {
			return false;
		}
	}

	public void approvalSave(RequestApproval approval) {
		getCurrentSession().save(approval);
		
	}

	public String getApprovalOpinion(String requestPlanCode) {
		String hql = "from " + RequestApproval.class.getName() + " where requestPlanCode='" + requestPlanCode + "'";
		RequestApproval approval = (RequestApproval) getCurrentSession().createQuery(hql).uniqueResult();
		return approval.getApprovalExplain();
	}
}
