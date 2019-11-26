package com.panda.dao;

import java.util.List;

import javax.annotation.Resource;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.panda.pojo.Approval;
import com.panda.pojo.Inventory;
import com.panda.pojo.Purchase;
import com.panda.pojo.Request;

@Repository
public class PurchaseDaoImpl implements PurchaseDao{
	
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
	public List<Purchase> queryAll() {
		return getCurrentSession().createQuery("from " + Purchase.class.getName()).list();
	}

	public void updatePlanName(String planCode, String planName) {
		Session session = getCurrentSession();
		String sql = "update t_panda_purchase t  set t.purchasePlanName = '" 
		+ planName + "' where t.requestPlanCode = '" + planCode + "'";
		SQLQuery query =session.createSQLQuery(sql);
		query.executeUpdate();
	}
	
	public Request queryByRequestPlanCode(String requestPlanCode) {
		Object object = getCurrentSession().createQuery("from " + Request.class.getName() + " where requestPlanCode ='" + requestPlanCode + "'").uniqueResult();
		if (object == null) {
			return null;
		}
		return (Request)object;
	}

	@SuppressWarnings("unchecked")
	public List<Request> queryUpdate() {
		return getCurrentSession().createQuery("from " + Request.class.getName()).list();
	}

	public Boolean requestBack(String planCode) {
		Session session = getCurrentSession();
		String sql = "update t_panda_request t set t.requestStatus = '自由'"
				+ ", t.approvalStatus = '未提交' where requestPlanCode = '" + planCode + "'";
		SQLQuery query = session.createSQLQuery(sql);
		int updateNum = query.executeUpdate();
		if (updateNum != 0) {
			return true;
		}else {
			return false;
		}
		
	}

	public void savePurchase(Purchase purchase) {
		getCurrentSession().save(purchase);
	}
	
	public void updateRequestApprovalStatus(String requestPlanCode) {
		Session session = getCurrentSession();
		String sql = "update t_panda_request t set t.approvalStatus = '10'"
				+ " where requestPlanCode = '" + requestPlanCode + "'";
		SQLQuery query = session.createSQLQuery(sql);
		query.executeUpdate();
	}
	
	public Boolean createPurchasePlan(String requestPlanCode) {
		Session session = getCurrentSession();
		String sql = "update t_panda_purchase t set t.approvalStatus = '7'"
				+ " where requestPlanCode = '" + requestPlanCode + "'";
		SQLQuery query = session.createSQLQuery(sql);
		int updateNum = query.executeUpdate();
		if (updateNum != 0) {
			return true;
		}else {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Purchase> getUnwritePurchase() {
		return getCurrentSession().createQuery("from " + Purchase.class.getName() ).list();
	}	

	public Boolean purchaseBack(String requestPlanCode) {
		SQLQuery query = getCurrentSession().createSQLQuery("delete from t_panda_purchase where requestPlanCode ='" + requestPlanCode + "'");
		int i = query.executeUpdate();
		if (i == 0) {
			return false;
		}
		return true;
	}

	public Inventory queryByMaterialCode(String materialCode) {
		return (Inventory)getCurrentSession().createQuery("from " + Inventory.class.getName() + " where materialCode ='" + materialCode + "'").uniqueResult();
	}

	public Purchase getPrintData(String purchasePlanCode) {
		return (Purchase)getCurrentSession().createQuery("from " + Purchase.class.getName() + " where purchasePlanCode = '" + purchasePlanCode + "'").uniqueResult();
	}

	public String getPurchasePlanCode(String requestPlanCode) {
		String hql = "from " + Purchase.class.getName() + " where requestPlanCode='" + requestPlanCode + "'";
		Purchase purchase = (Purchase)getCurrentSession().createQuery(hql).uniqueResult();
		return purchase.getPurchasePlanCode();
	}
	 /**
     * @category 删除需求计划记录（更新需求计划中的需求计划状态）
     * @param goods
     */
    public void Delete(Request goods) {
        getCurrentSession().update(goods);
    }
	
	 /**
     * @param materialTrackingCode
     * @category 根据物料编码来查询
     */
    public Request queryById(String materialTrackingCode) {
        Object object = getCurrentSession().createQuery("from " + Request.class.getName() + " where materialTrackingCode='" + materialTrackingCode + "'")
                .uniqueResult();
        if (object == null) {
            return null;
        }
        return (Request) object;
    }
}
