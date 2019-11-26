package com.panda.service;

import java.util.List;
import com.panda.pojo.RequestSyetemConf;
import com.panda.pojo.Request;

import org.springframework.stereotype.Controller;
import com.panda.pojo.RequestApproval;
import com.panda.pojo.Approval;
@Controller
public interface RequestService {


    /**
     * @category 注册
     * @param user
     */
    public void insert(Request user);

    /**
     * @category 新增或保存
     * @param goods
     */
    void save(Request goods);

    /**
     * @category 部门列表查询
     */
    public List<RequestSyetemConf> getDeptList();

    /**
     * @category 单位列表查询
     */
    public List<RequestSyetemConf> getUnitList();

    /**
     * @category 期望供应商列表查询
     */
    public List<RequestSyetemConf> getWishSupplier();

    /**
     * @category 固定供应商列表查询
     */
    public List<RequestSyetemConf> getFixedSupplier();


    /**
     * @category 需求库存列表查询
     */
    public List<RequestSyetemConf> getRequestStock();

    /**
     * @category 删除商品
     * @param goods
     */
    void delete(Request goods);

    /**
     * @category 删除商品
     */
    boolean deleteById(String goodsId);


    /**
     * @category 删除需求计划记录（更新需求计划中的需求计划状态）
     * @param goods
     */
    void Delete(Request goods);
    /**
     * @category 更新需求计划
     * @param goods
     */
    void update(Request goods);

    /**
     * @category 根据materialTrackingCode来查询
     * @param materialTrackingCode
     * @return
     */
    Request queryById(String materialTrackingCode);

    /**
     * @category 查询需求计划表
     * @return
     */
    List<Request> queryAll();

    /**
     * @category 查询系统消息表
     * @return
     */
    Request systemMassageQuery(String requestPlanCode);

    /**
     * @category 删除查询表
     * @return
     */
    List<Request> deleteQuery();
    /**
     * @category 查询需求审批表
     * @return
     */
    List<RequestApproval> queryApproval(String requestPlanCode);

    /**
     * @category 查询需求计划查询/修改表
     * @return
     */
    List<Request> conditionQuery(String requestPlanCode, String requestPlanType, String requestDeportment, String requestDate, String requestMonth);
    /**
     * @category 分页查询
     * @param pageNum 当前页码数
     * @return
     */
    List<Request> queryAll(int pageNum);
}
