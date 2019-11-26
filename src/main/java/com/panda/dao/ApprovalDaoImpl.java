package com.panda.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.panda.pojo.Approval;
import com.panda.pojo.Purchase;
import com.panda.pojo.PurchaseModify;

@Repository
public class ApprovalDaoImpl implements ApprovalDao{
	
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
	public List<Purchase> getUnapproval() {
		return getCurrentSession().createQuery("from " + Purchase.class.getName()).list();
	}
	
	public Purchase queryByPurchasePlanCode(String purchasePlanCode) {
		return (Purchase)getCurrentSession().createQuery(
				"from " + Purchase.class.getName() + " where purchasePlanCode = '"+ purchasePlanCode + "'").uniqueResult();
	}

	public Boolean approvalPass(String purchasePlanCode, int status) {
		Session session = getCurrentSession();
		status++;
		String sql = "update t_panda_purchase t set t.approvalStatus = '" + status +"'"
				+ "where t.purchasePlanCode = '" + purchasePlanCode +"'";
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
		String sql = "update t_panda_purchase t set t.approvalStatus = '6' "
				+ "where t.purchasePlanCode = '" + purchasePlanCode +"'";
		SQLQuery query = session.createSQLQuery(sql);
		int updateNum = query.executeUpdate();
		if (updateNum != 0) {
			return true;
		}else {
			return false;
		}
	}

	public void approvalSave(Approval approval) {
		getCurrentSession().save(approval);
		
	}

	public String getApprovalOpinion(String requestPlanCode) {
		String hql = "from " + Approval.class.getName() + " where requestPlanCode='" + requestPlanCode + "'";
		Approval approval = (Approval) getCurrentSession().createQuery(hql).uniqueResult();
		return approval.getApprovalExplain();
	}

	
}
