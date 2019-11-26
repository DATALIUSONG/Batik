package com.panda.dao;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSON;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.hibernate.Query;

import com.panda.pojo.Request;
import com.panda.pojo.RequestSyetemConf;
import com.panda.pojo.Approval;
import com.panda.pojo.RequestApproval;

@Repository
public class RequestDaoImpl implements RequestDao {

    @Resource
    SessionFactory sessionFactory;

    /**
     * @category 获取当前连接数据库的session对象
     * @return
     */
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * @param user
     * @category 注册
     */
    public void insertUser(Request user) {
        Session session = getSession();
        session.save(user);
    }

    /**
     * @param goods
     * @category 新增或保存商品
     */
    public void save(Request goods) {
        Session session = getSession();
        Serializable id = session.save(goods);
        System.out.println("hibernate save方法返回结果：" + id);
    }

    /**
     * @category 部门列表查询
     */
    public List<RequestSyetemConf> getDeptList() {
        Session session = getSession();
        String queryString = "select requestDepartment from t_panda_systemconf;";
        return session.createSQLQuery(queryString).list();

    }

    /**
     * @category 单位列表查询
     */
    public List<RequestSyetemConf> getUnitList() {
        Session session = sessionFactory.getCurrentSession();
        String queryString = "select t1.*,t2.goodsName from t_mr_result t1 LEFT JOIN t_goods t2 ON (t1.goodsId=t2.id) ORDER BY t1.goodsViewCount DESC;";//"SELECT t.goodsId AS \"商品编号\",SUM(t.goodsViewCount) AS \"总数\" FROM t_mr_result t GROUP BY t.goodsId ORDER BY SUM(t.goodsViewCount);";
        Query query = session.createSQLQuery(queryString);
        List<RequestSyetemConf> list = query.list();
        return list;
    }


    /**
     * @category 期望供应商列表查询
     */
    public List<RequestSyetemConf> getWishSupplier() {
        Session session = sessionFactory.getCurrentSession();
        String queryString = "select t1.*,t2.goodsName from t_mr_result t1 LEFT JOIN t_goods t2 ON (t1.goodsId=t2.id) ORDER BY t1.goodsViewCount DESC;";//"SELECT t.goodsId AS \"商品编号\",SUM(t.goodsViewCount) AS \"总数\" FROM t_mr_result t GROUP BY t.goodsId ORDER BY SUM(t.goodsViewCount);";
        Query query = session.createSQLQuery(queryString);
        List<RequestSyetemConf> list = query.list();
        return list;
    }

    /**
     * @category 固定供应商列表查询
     */
    public List<RequestSyetemConf> getFixedSupplier() {
        Session session = sessionFactory.getCurrentSession();
        String queryString = "select t1.*,t2.goodsName from t_mr_result t1 LEFT JOIN t_goods t2 ON (t1.goodsId=t2.id) ORDER BY t1.goodsViewCount DESC;";//"SELECT t.goodsId AS \"商品编号\",SUM(t.goodsViewCount) AS \"总数\" FROM t_mr_result t GROUP BY t.goodsId ORDER BY SUM(t.goodsViewCount);";
        Query query = session.createSQLQuery(queryString);
        List<RequestSyetemConf> list = query.list();
        return list;
    }

    /**
     * @category 需求库存列表查询
     */
    public List<RequestSyetemConf> getRequestStock() {
        Session session = sessionFactory.getCurrentSession();
        String queryString = "select t1.*,t2.goodsName from t_mr_result t1 LEFT JOIN t_goods t2 ON (t1.goodsId=t2.id) ORDER BY t1.goodsViewCount DESC;";//"SELECT t.goodsId AS \"商品编号\",SUM(t.goodsViewCount) AS \"总数\" FROM t_mr_result t GROUP BY t.goodsId ORDER BY SUM(t.goodsViewCount);";
        Query query = session.createSQLQuery(queryString);
        List<RequestSyetemConf> list = query.list();
        return list;
    }

    /**
     * @param goods
     * @category 删除商品
     */
    public void delete(Request goods) {
        getSession().delete(goods);
    }

    /**
     * @category 根据id删除商品
     */
    public int deleteById(String goodsId) {
        SQLQuery query = getSession().createSQLQuery("delete from t_goods where id='" + goodsId + "'");
        int ret = query.executeUpdate();
        return ret;
    }

    /**
     * @category 删除需求计划记录（更新需求计划中的需求计划状态）
     * @param goods
     */
    public void Delete(Request goods) {
        getSession().update(goods);
    }

    /**
     * @param goods
     * @category 更新商品
     */
    public void update(Request goods) {
        getSession().update(goods);
    }

    /**
     * @param materialTrackingCode
     * @category 根据物料编码来查询
     */
    public Request queryById(String materialTrackingCode) {
        Object object = getSession().createQuery("from " + Request.class.getName() + " where materialTrackingCode='" + materialTrackingCode + "'")
                .uniqueResult();
        if (object == null) {
            return null;
        }
        return (Request) object;
    }

    /**
     * @category 查询需求计划表
     */
    @SuppressWarnings("unchecked")
    public List<Request> queryAll() {
        return getSession().createQuery("from " + Request.class.getName() + " order by createTime desc ").list();
    }

    /**
     * @category 查询需求审批表
     */
    public List<RequestApproval> queryApproval(String requestPlanCode) {
        return getSession().createQuery("from " + RequestApproval.class.getName() + " where requestPlanCode='" + requestPlanCode + "'").list();

    }

    /**
     * @category 查询系统消息表
     */
    public Request systemMassageQuery(String requestPlanCode) {
        Object object = getSession().createQuery("from " + Request.class.getName() + " where requestPlanCode='" + requestPlanCode + "'")
                .uniqueResult();
        if (object == null) {
            return null;
        }
        return (Request) object;
    }

    /**
     * @category 查询系统消息表
     */
    public List<Request> deleteQuery() {
       return getSession().createQuery("from " + Request.class.getName() + " where requestStatus='" + "自由" + "'").list();


    }

    /**
     * @category 查询需求计划查询/修改表
     * @return
     */
   public List<Request> conditionQuery(String requestPlanCode, String requestPlanType, String requestDeportment, String requestDate, String requestMonth){

       Session session = getSession();
       String sql = "select * from t_panda_request where requestPlanCode = '" + requestPlanCode + "' or requestPlanType = '" + requestPlanType + "' or requestDeportment = '" + requestDeportment + "' or requestDate ='" + requestDate + "' or requestMonth = '" + requestMonth + "'";
      return session.createSQLQuery(sql).list();
    }
    /**
     * @category 分页查询
     */
    @SuppressWarnings("unchecked")
    public List<Request> queryAll(int pageNum) {
        // 1.创建Criteria对象
        Criteria criteria = getSession().createCriteria(Request.class);
        // 2.排序
        criteria.addOrder(Order.asc("goodsPrice"));// 按照商品价格升序
        // 3.分页
        criteria.setFirstResult(pageNum - 1);// 从什么位置开始，默认为0
        criteria.setMaxResults(10);// 最多检出的条数
        // 4.执行SQL
        List<Request> list = criteria.list();
        return list;
    }

}
