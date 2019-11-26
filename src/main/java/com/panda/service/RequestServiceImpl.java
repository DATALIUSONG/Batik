package com.panda.service;

import javax.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.panda.dao.RequestDao;
import com.panda.pojo.Request;
import com.panda.pojo.RequestSyetemConf;
import com.panda.pojo.Approval;
import com.panda.pojo.RequestApproval;
@Service
@Transactional
public class RequestServiceImpl implements RequestService {

    @Resource
    RequestDao goodsDao;

    public void insert(Request user) {
        goodsDao.insertUser(user);
    }

    public void save(Request goods) {
        goodsDao.save(goods);
    }

    //部门列表查询
    public List<RequestSyetemConf> getDeptList() {
        System.out.println("-----service层被调用--------");
        List<RequestSyetemConf> result = goodsDao.getDeptList();
        return result;
    }

    //单位列表查询
    public List<RequestSyetemConf> getUnitList() {
        System.out.println("-----service层被调用--------");
        List<RequestSyetemConf> result = goodsDao.getUnitList();
        return result;
    }

    //期望供应商列表查询
    public List<RequestSyetemConf> getWishSupplier() {
        System.out.println("-----service层被调用--------");
        List<RequestSyetemConf> result = goodsDao.getWishSupplier();
        return result;
    }

    //固定供应商列表查询
    public List<RequestSyetemConf> getFixedSupplier() {
        System.out.println("-----service层被调用--------");
        List<RequestSyetemConf> result = goodsDao.getFixedSupplier();
        return result;
    }

    //需求库存列表查询
    public List<RequestSyetemConf> getRequestStock() {
        System.out.println("-----service层被调用--------");
        List<RequestSyetemConf> result = goodsDao.getRequestStock();
        return result;
    }

    public void delete(Request goods) {
        goodsDao.delete(goods);
    }

    public boolean deleteById(String goodsId) {
        int result = goodsDao.deleteById(goodsId);
        if (result < 0) {
            return false;
        }
        return true;
    }

    /**
     * @category 删除需求计划记录（更新需求计划中的需求计划状态）
     * @param goods
     */
    public void Delete(Request goods) {
        goodsDao.Delete(goods);
    }
    /**
     * @category 更新需求计划
     * @param goods
     */
    public void update(Request goods) {
        goodsDao.update(goods);
    }


    public Request queryById(String materialTrackingCode) {
        return goodsDao.queryById(materialTrackingCode);
    }

    /**
     * @category 查询需求计划表
     */
    public List<Request> queryAll() {
        return goodsDao.queryAll();
    }

    /**
     * @category 查询需求审批表
     */

    public List<RequestApproval> queryApproval(String requestPlanCode) {
        return goodsDao.queryApproval(requestPlanCode);
    }
    /**
     * @category 查询系统消息表
     */

    public List<Request> deleteQuery() {
        return goodsDao.deleteQuery();
    }

    /**
     * @category 查询系统消息表
     */

    public Request systemMassageQuery(String requestPlanCode) {
        return goodsDao.systemMassageQuery(requestPlanCode);
    }
    /**
     * @category 查询需求计划查询/修改表
     * @return
     */
    public List<Request> conditionQuery(String requestPlanCode, String requestPlanType, String requestDeportment, String requestDate, String requestMonth){
        return goodsDao.conditionQuery(requestPlanCode,requestPlanType,requestDeportment,requestDate,requestMonth);
    }

    public List<Request> queryAll(int pageNum) {
        return goodsDao.queryAll(pageNum);
    }

}
