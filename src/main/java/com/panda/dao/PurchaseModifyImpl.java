package com.panda.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.panda.pojo.PurchaseModify;
import com.panda.pojo.User;

@Repository
public class PurchaseModifyImpl implements PurchaseModifyDao{
	
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
	public List<PurchaseModify> queryByPlanCode(String purchasePlanCode) {
		return getCurrentSession().createQuery(
                "from " + PurchaseModify.class.getName() + " where purchasePlanCode='" + purchasePlanCode + "'").list();
		 
	}

}
