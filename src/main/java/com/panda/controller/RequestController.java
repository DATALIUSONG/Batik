package com.panda.controller;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.panda.pojo.Request;
import com.panda.pojo.RequestSyetemConf;
import com.panda.service.RequestService;
import com.panda.pojo.Approval;
import com.panda.pojo.RequestApproval;

@Controller
public class RequestController {

    @Resource
    RequestService goodsService;
    /**
     * @category 新增需求计划表
     * @param map
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("request/save")
    public @ResponseBody String save(@RequestParam Map<String, Object> map, HttpServletRequest request,
                                     HttpServletResponse response,  HttpSession session) {

        response.setHeader("Access-Control-Allow-Origin", "*");// 解决跨域问题
        // 1、获取页面传递来的参数

        String requestPlanType = map.get("requestPlanType").toString();
        String requestPlanName = map.get("requestPlanName").toString();
        String requestRemarks = map.get("requestRemarks").toString();
        String requestDeportment = map.get("requestDeportment").toString();
        String requestPerson = map.get("requestPerson").toString();
        String requestStatus = map.get("requestStatus").toString();
        String approvalStatus = map.get("approvalStatus").toString();
        //表
        String classificationCode = map.get("classificationCode").toString();
        String classificationName = map.get("classificationName").toString();
        String materialCode = map.get("materialCode").toString();
        String materialName = map.get("materialName").toString();
        String type = map.get("type").toString();
        String specification = map.get("specification").toString();
        String unit = map.get("unit").toString();
        String quantity = map.get("quantity").toString();
        String requestMonth = map.get("requestMonth").toString();
        String requestDate = map.get("requestDate").toString();
        String sureSource = map.get("sureSource").toString();
        String wishSupplier = map.get("wishSupplier").toString();
        String fixedSupplier = map.get("fixedSupplier").toString();
        String remarks = map.get("remarks").toString();
        String CreatePerson = map.get("userCname").toString();

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String createTime = dateFormat.format(date);

        // 2、新建商品对象，并赋值
        Request goods = new Request();

        goods.setRequestPlanType(requestPlanType);
        goods.setRequestPlanName(requestPlanName);
        goods.setRequestRemarks( requestRemarks);
        goods.setRequestDeportment(requestDeportment);
        goods.setRequestPerson(requestPerson);
        goods.setRequestStatus(requestStatus);
        goods.setApprovalStatus(approvalStatus);
        goods.setClassificationCode(classificationCode);
        goods.setClassificationName(classificationName);
        goods.setMaterialCode(materialCode);
        goods.setMaterialName(materialName);
        goods.setType(type);
        goods.setSpecification(specification);
        goods.setUnit(unit);
        goods.setQuantity(quantity);
        goods.setRequestMonth(requestMonth);
        goods.setRequestDate(requestDate);
        goods.setSureSource(sureSource);
        goods.setWishSupplier(wishSupplier);
        goods.setFixedSupplier(fixedSupplier);
        goods.setRemarks(remarks);
        goods.setCreatePerson(CreatePerson);
        goods.setCreateTime(createTime);
        // 3、执行新增操作，即将新品保存到MySQL数据库中
        goodsService.save(goods);

        // 4、组装结果数据返回给页面
        Map<String, String> result = new HashMap<String, String>();
        result.put("message", "新增成功");
        result.put("status", "1");

        return JSON.toJSONString(result);
    }

    /**
     * @category 部门列表查询
     * @param request
     * @param response
     * @return
     */

    @RequestMapping("request/getdeptlist")
    public @ResponseBody String getDeptList(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");// 解决跨域问题
        List<RequestSyetemConf> result = goodsService.getDeptList();
        Map<String, Object> map = new HashMap<String, Object>();
        if (null == result || 0 == result.size()) {
            map.put("message", "没有数据");
            map.put("status", "0");
            map.put("data", "没有数据");
            return JSON.toJSONString(map);
        }

        map.put("message", "查询成功");
        map.put("status", "1");
        map.put("data", result);
        System.out.println("前台传递过来的数据：" + JSON.toJSONString(map));
        return JSON.toJSONString(map);
    }

    /**
     * @category 单位列表查询
     * @param request
     * @param response
     * @return
     */

    @RequestMapping("/request/getunitlist")
    public @ResponseBody String getUnitList(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");// 解决跨域问题
        List<RequestSyetemConf> result = goodsService.getUnitList();
        Map<String, Object> map = new HashMap<String, Object>();
        if (null == result || 0 == result.size()) {
            map.put("message", "没有数据");
            map.put("status", "0");
            map.put("data", "没有数据");
            return JSON.toJSONString(map);
        }

        map.put("message", "查询成功");
        map.put("status", "1");
        map.put("data", result);
        return JSON.toJSONString(map);
    }

    /**
     * @category 期望供应商列表查询
     * @param request
     * @param response
     * @return
     */

    @RequestMapping("/request/getwishSupplier")
    public @ResponseBody String getWishSupplier(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");// 解决跨域问题
        List<RequestSyetemConf> result = goodsService.getWishSupplier();
        Map<String, Object> map = new HashMap<String, Object>();
        if (null == result || 0 == result.size()) {
            map.put("message", "没有数据");
            map.put("status", "0");
            map.put("data", "没有数据");
            return JSON.toJSONString(map);
        }

        map.put("message", "查询成功");
        map.put("status", "1");
        map.put("data", result);
        return JSON.toJSONString(map);
    }

    /**
     * @category 固定供应商列表查询
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/request/getFixedSupplier")
    public @ResponseBody String getFixedSupplier(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");// 解决跨域问题
        List<RequestSyetemConf> result = goodsService.getFixedSupplier();
        Map<String, Object> map = new HashMap<String, Object>();
        if (null == result || 0 == result.size()) {
            map.put("message", "没有数据");
            map.put("status", "0");
            map.put("data", "没有数据");
            return JSON.toJSONString(map);
        }

        map.put("message", "查询成功");
        map.put("status", "1");
        map.put("data", result);
        return JSON.toJSONString(map);
    }

    /**
     * @category 需求库存列表查询
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/request/getrequeststock")
    public @ResponseBody String getRequestStock(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");// 解决跨域问题
        List<RequestSyetemConf> result = goodsService.getRequestStock();
        Map<String, Object> map = new HashMap<String, Object>();
        if (null == result || 0 == result.size()) {
            map.put("message", "没有数据");
            map.put("status", "0");
            map.put("data", "没有数据");
            return JSON.toJSONString(map);
        }

        map.put("message", "查询成功");
        map.put("status", "1");
        map.put("data", result);
        return JSON.toJSONString(map);
    }
    //-----------------------------------------------------------------------------
    /**
     * @category 根据id删除商品
     * @param map
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/goods/deleteById")
    public @ResponseBody String deleteById(@RequestParam Map<String, Object> map, HttpServletRequest request,
                                           HttpServletResponse response) {
        // 1、获取页面传递来的参数
        String goodsId = map.get("goodsId").toString();
        // 2、执行删除操作，即将MySQL数据库中商品删除
        boolean isDel = goodsService.deleteById(goodsId);
        // 3、组装结果数据返回给页面
        Map<String, String> result = new HashMap<String, String>();
        if (isDel) {
            result.put("message", "删除成功");
            result.put("status", "1");
            return JSON.toJSONString(result);
        }

        result.put("message", "删除失败");
        result.put("status", "0");
        return JSON.toJSONString(result);
    }

    /**
     * @category 更新或修改需求计划表
     * @param map
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/goods/update")
    public @ResponseBody String update(@RequestParam Map<String, Object> map, HttpServletRequest request,
                                       HttpServletResponse response) {
        // 1、获取页面传递来的参数
         System.out.println("前台传递过来的数据：" + JSON.toJSONString(map));
        String id = map.get("id").toString();
        String requestPlanType = map.get("requestPlanType").toString();
        String requestPlanName = map.get("requestPlanName").toString();
        String requestRemarks = map.get("requestRemarks").toString();
        String requestDeportment = map.get("requestDeportment").toString();
        String requestPerson = map.get("requestPerson").toString();
        String requestStatus = map.get("requestStatus").toString();
        String approvalStatus = map.get("approvalStatus").toString();
        //表
        String classificationCode = map.get("classificationCode").toString();
        String classificationName = map.get("classificationName").toString();
        String materialCode = map.get("materialCode").toString();
        String materialName = map.get("materialName").toString();
        String type = map.get("type").toString();
        String specification = map.get("specification").toString();
        String unit = map.get("unit").toString();
        String quantity = map.get("quantity").toString();
        String requestMonth = map.get("requestMonth").toString();
        String requestDate = map.get("requestDate").toString();
        String sureSource = map.get("sureSource").toString();
        String wishSupplier = map.get("wishSupplier").toString();
        String fixedSupplier = map.get("fixedSupplier").toString();
        String remarks = map.get("remarks").toString();
        String modifyReason = map.get("modifyReason").toString();
        String modifyPerson = map.get("userCname").toString();

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String updateTime = dateFormat.format(date);


        // 2、新建商品对象，并赋值
        Request goods = goodsService.queryById(id);
        System.out.println("通过前台ID查询到的数据：" + JSON.toJSONString(goods));

        goods.setRequestPlanType(requestPlanType);
        goods.setRequestPlanName(requestPlanName);
        goods.setRequestPlanName( requestRemarks);
        goods.setRequestDeportment(requestDeportment);
        goods.setRequestPerson(requestPerson);
        goods.setRequestStatus(requestStatus);
        goods.setApprovalStatus(approvalStatus);
        goods.setClassificationCode(classificationCode);
        goods.setClassificationName(classificationName);
        goods.setMaterialCode(materialCode);
        goods.setMaterialName(materialName);
        goods.setType(type);
        goods.setSpecification(specification);
        goods.setUnit(unit);
        goods.setQuantity(quantity);
        goods.setRequestMonth(requestMonth);
        goods.setRequestDate(requestDate);
        goods.setSureSource(sureSource);
        goods.setWishSupplier(wishSupplier);
        goods.setFixedSupplier(fixedSupplier);
        goods.setRemarks(remarks);
        goods.setModifyReason(modifyReason);
        goods.setModifyPerson(modifyPerson);
        goods.setUpdateTime(updateTime);


        // 3、执行更新操作，即将商品更新到MySQL数据库中
        goodsService.update(goods);

        // 4、组装结果数据返回给页面
        Map<String, String> result = new HashMap<String, String>();
        result.put("message", "更新成功");
        result.put("status", "1");
        return JSON.toJSONString(result);
    }

    /**
     * @category 查询所有的需求计划表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/goods/queryAll")
    public @ResponseBody String queryAll(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");// 解决跨域问题
        //System.out.println("我已经进入query页面,接下来开始查询数据");
        // 1、执行查询操作，即将MySQL数据库中商品查询出来
        List<Request> list = goodsService.queryAll();

        // 2、组装结果数据返回给页面
        Map<String, Object> result = new HashMap<String, Object>();
        if (list == null || list.size() == 0) {
            result.put("message", "目前还没有任何需求计划");
            result.put("status", "0");
            return JSON.toJSONString(result);
        }

        result.put("message", "查询成功");
        result.put("status", "1");
        result.put("data", list);

        return JSON.toJSONString(result);
    }

    /**
     * @category 查询所有的系统消息表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/systemMassageQuery")
    public @ResponseBody String systemMassageQuery(@RequestParam Map<String, String> map, HttpServletRequest request,
                                          HttpServletResponse response) {
        // 解决跨域问题
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 0、获取页面传递过来的参数
        String requestPlanCode = map.get("requestPlanCode");
        // 1、执行查询操作，即将MySQL数据库中商品查询出来
        Request goods = goodsService.systemMassageQuery(requestPlanCode);
        // 2、组装结果数据返回给页面
        Map<String, Object> result = new HashMap<String, Object>();
        if (goods == null) {
            result.put("message", "没有该商品");
            result.put("status", "0");
            return JSON.toJSONString(result);
        }
        result.put("message", "查询成功");
        result.put("status", "1");
        result.put("data", goods);
        return JSON.toJSONString(result);
    }



    /**
     * @category 查询所有的需求审批表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/goods/queryApproval")
    public @ResponseBody String queryApproval(@RequestParam Map<String, String> map, HttpServletRequest request,
                                          HttpServletResponse response) {
        // 解决跨域问题
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 0、获取页面传递过来的参数
        //System.out.println("前台传递过来的数据：" + JSON.toJSONString(map));
        String requestPlanCode = map.get("requestPlanCode");
        // 1、执行查询操作，即将MySQL数据库中商品查询出来
        List<RequestApproval>  approval = goodsService.queryApproval(requestPlanCode);
        // 2、组装结果数据返回给页面

        Map<String, Object> result = new HashMap<String, Object>();
        if (approval == null) {
            result.put("message", "目前还没有任何需求计划");
            result.put("status", "0");
            return JSON.toJSONString(result);
        }

        result.put("message", "查询成功");
        result.put("status", "1");
        result.put("data", approval);
        return JSON.toJSONString(result);
    }

    /**
     * @category 查询需求计划查询/修改表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/request/conditionQuery")
    public @ResponseBody String conditionQuery(@RequestParam Map<String, String> map, HttpServletRequest request,
                                              HttpServletResponse response) {
        // 解决跨域问题
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 0、获取页面传递过来的参数

        String requestPlanCode = map.get("requestPlanCode");
        String requestPlanType = map.get("requestPlanType");
        String requestDeportment = map.get("requestDeportment");
        String requestDate = map.get("requestDate");
        String requestMonth = map.get("requestMonth");

        // 1、执行查询操作，即将MySQL数据库中商品查询出来
        List<Request>  conditionquery = goodsService.conditionQuery(requestPlanCode,requestPlanType,requestDeportment,requestDate,requestMonth);
        // 2、组装结果数据返回给页面

        Map<String, Object> result = new HashMap<String, Object>();
        if (conditionquery == null) {
            result.put("message", "目前还没有任何需求计划");
            result.put("status", "0");
            return JSON.toJSONString(result);
        }

        result.put("message", "查询成功");
        result.put("status", "1");
        result.put("data", conditionquery);
        System.out.println("打印返回前端数据：" + JSON.toJSONString(result));
        return JSON.toJSONString(result);
    }
    /**
     * @category 删除需求计划，实际就是对需求计划状态做update操作
     * @param map
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/goods/delete")
    public @ResponseBody String delete(@RequestParam Map<String, Object> map, HttpServletRequest request,
                                       HttpServletResponse response) {
        // 1、获取页面传递来的参数
        //System.out.println("前台传递过来的数据：" + JSON.toJSONString(map));

        String requestStatus = map.get("requestStatus").toString();
        String materialTrackingCode = map.get("materialTrackingCode").toString();

        // 2、新建对象，并赋值

        Request goods = goodsService.queryById(materialTrackingCode);

        goods.setRequestStatus(requestStatus);
        // 3、执行删除（更新)操作，即将商品更新到MySQL数据库中

        goodsService.Delete(goods);

        // 4、组装结果数据返回给页面
        Map<String, String> result = new HashMap<String, String>();
        result.put("message", "删除成功");
        result.put("status", "1");

        System.out.println(JSON.toJSONString(result));
        return JSON.toJSONString(result);
    }

    /**
     * @category 删除计划查询
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deletequery")
    public @ResponseBody String deleteQuery(@RequestParam Map<String, String> map, HttpServletRequest request,
                                                   HttpServletResponse response) {
        // 解决跨域问题
        response.setHeader("Access-Control-Allow-Origin", "*");

        // 1、执行查询操作，即将MySQL数据库中商品查询出来
        List<Request> list = goodsService.deleteQuery();
        // 2、组装结果数据返回给页面
        Map<String, Object> result = new HashMap<String, Object>();
        if (list == null) {
            result.put("message", "没有该商品");
            result.put("status", "0");
            return JSON.toJSONString(result);
        }
        result.put("message", "查询成功");
        result.put("status", "1");
        result.put("data", list);
        return JSON.toJSONString(result);
    }

    /**
     * @category 查询所有的商品
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/goods/queryById")
    public @ResponseBody String queryById(@RequestParam Map<String, String> map, HttpServletRequest request,
                                          HttpServletResponse response) {
        // 解决跨域问题
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 0、获取页面传递过来的参数

        String materialTrackingCode = map.get("materialTrackingCode");
        // 1、执行查询操作，即将MySQL数据库中商品查询出来
        Request goods = goodsService.queryById(materialTrackingCode);
        // 2、组装结果数据返回给页面
        Map<String, Object> result = new HashMap<String, Object>();
        if (goods == null) {
            result.put("message", "没有该商品");
            result.put("status", "0");
            return JSON.toJSONString(result);
        }
        result.put("message", "查询成功");
        result.put("status", "1");
        result.put("data", goods);
        System.out.println("sql值："+JSON.toJSONString(result));
        return JSON.toJSONString(result);
    }

}
