package cn.lz.hospital.controller;

import cn.lz.hospital.bean.sys.OutMsgBean;
import cn.lz.hospital.controller.common.BaseController;
import cn.lz.hospital.domain.Doctor;
import cn.lz.hospital.domain.GhType;
import cn.lz.hospital.service.SickService;
import cn.lz.hospital.utils.ValidateUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import win.hupubao.common.utils.LoggerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 患者端接口
 */
@Controller
@RequestMapping("/sick")
public class SickController extends BaseController {
    @Autowired
    private SickService sickService;

    /**
     * 就诊人card_no唯一性验证
     *
     * @param request
     * @param response
     */
    @RequestMapping("/checkUniqueness")
    public void checkUniqueness(HttpServletRequest request,
                                HttpServletResponse response) {
        OutMsgBean outMsgBean = null;
        Map<String, Object> paramsMap = new HashMap<>();
        String card_no = getString("card_no", null, paramsMap);
        LoggerUtils.info("接口[{}]，请求参数:", request.getRequestURI(), JSON.toJSONString(paramsMap));
        if (ValidateUtil.isEmpty(card_no)) {
            outMsgBean = new OutMsgBean(-100, "参数不能为空");
            outJSONMsg(response, outMsgBean);
            return;
        }
        Object relsut = sickService.checkUniqueness(card_no);
        if (relsut == null){
            outMsgBean = new OutMsgBean(-100, "读取信息请求失败");
            outJSONMsg(response, outMsgBean);
            return;
        }
        //        Integer ret = sickService.checkUniqueness(card_no);
        Integer ret = (Integer) relsut;
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("is_register", ret);
        outMsgBean = new OutMsgBean(200, "处理完成", retMap);
        LoggerUtils.info("接口[{}]，请求参数：{}，响应数据：{}", request.getRequestURI(), JSON.toJSONString(paramsMap), JSON.toJSONString(outMsgBean));
        outJSONMsg(response, outMsgBean);
    }

    /**
     * 科室列表
     *
     * @param request
     * @param response
     */
    @RequestMapping("/getKsList")
    public void getKsList(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> paramsMap = new HashMap<>();
        Integer id = getInteger("id", null, paramsMap);
        LoggerUtils.info("接口[{}]，请求参数:", request.getRequestURI(), JSON.toJSONString(paramsMap));
        Map<String, Object> retMap = sickService.getAllById(id);
        OutMsgBean outMsgBean = new OutMsgBean(retMap);
        outJSONMsg(response, outMsgBean);
        LoggerUtils.info("接口[{}]，返回数据:", request.getRequestURI(), JSON.toJSONString(outMsgBean));
    }


    /**
     * 医生列表
     *
     * @param request
     * @param response
     */
    @RequestMapping("/getDoctorList")
    public void getDoctorList(HttpServletRequest request, HttpServletResponse response) {
        OutMsgBean outMsgBean = null;
        List<Doctor> doctorList = sickService.getDoctorAllList();
        if (!ValidateUtil.checkListIsNotEmpty(doctorList)) {
            outMsgBean = new OutMsgBean(-100, "查无数据");
            outJSONMsg(response, outMsgBean);
            return;
        }
        outMsgBean = new OutMsgBean(doctorList);
        outJSONMsg(response, outMsgBean);
        LoggerUtils.info("接口[{}]，返回数据:", request.getRequestURI(), JSON.toJSONString(outMsgBean));
    }

    /**
     * 根据科室id查找医生
     *
     * @param request
     * @param response
     */
    @RequestMapping("/getDoctorListByid")
    public void getDoctorListByid(int department_id,HttpServletRequest request, HttpServletResponse response) {
        OutMsgBean outMsgBean = null;
        List<Doctor> doctorList = sickService.getDoctorById(department_id);
        if (!ValidateUtil.checkListIsNotEmpty(doctorList)) {
            outMsgBean = new OutMsgBean(-100, "查无数据");
            outJSONMsg(response, outMsgBean);
            return;
        }
        outMsgBean = new OutMsgBean(doctorList);
        outJSONMsg(response, outMsgBean);
        LoggerUtils.info("接口[{}]，返回数据:", request.getRequestURI(), JSON.toJSONString(outMsgBean));
    }



    /**
     * 挂号
     *
     * @param request
     * @param response
     */
    @RequestMapping("/ghInsert")
    public void ghInsert(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> paramsMap = new HashMap<>();
        OutMsgBean outMsgBean = null;
        String name = getString("name", null, paramsMap);
        Integer sex = getInteger("sex", 1, paramsMap);
        Integer age = getInteger("age", null, paramsMap);
        String ghtype = getString("ghtype", null, paramsMap);
        Integer doctor_id = getInteger("doctor_id", null, paramsMap);
        Integer department_id = getInteger("department_id", null, paramsMap);
        String card_no = getString("card_no", null, paramsMap);
        LoggerUtils.info("接口[{}]，请求参数:", request.getRequestURI(), JSON.toJSONString(paramsMap));
        if (ValidateUtil.isEmpty(name) || ValidateUtil.isEmpty(sex)
                || ValidateUtil.isEmpty(age) || ValidateUtil.isEmpty(ghtype)
                || ValidateUtil.isEmpty(doctor_id) || ValidateUtil.isEmpty(department_id)
                || ValidateUtil.isEmpty(card_no)) {
            outMsgBean = new OutMsgBean(-100, "参数不能为空");
            outJSONMsg(response, outMsgBean);
            return;
        }
        Map<String, Object> retMap = sickService.insertRegistration(name, sex, age, ghtype, doctor_id, department_id, card_no);
        if (!ValidateUtil.checkMapIsNotEmpty(retMap)) {
            outMsgBean = new OutMsgBean(-100, "查无数据");
            outJSONMsg(response, outMsgBean);
            return;
        }
        outMsgBean = new OutMsgBean(retMap);
        outJSONMsg(response, outMsgBean);
        LoggerUtils.info("接口[{}]，返回数据:", request.getRequestURI(), JSON.toJSONString(outMsgBean));
    }

    /**
     * 挂号列表查询
     *
     * @param request
     * @param response
     */
    @RequestMapping("/getGhTypeList")
    public void getGhTypeList(HttpServletRequest request, HttpServletResponse response) {
        OutMsgBean outMsgBean = null;
        List<GhType> ghTypeList = sickService.getGhTypeAllList();
        if (!ValidateUtil.checkListIsNotEmpty(ghTypeList)) {
            outMsgBean = new OutMsgBean(-100, "查无数据");
            outJSONMsg(response, outMsgBean);
            return;
        }
        outMsgBean = new OutMsgBean(ghTypeList);
        outJSONMsg(response, outMsgBean);
        LoggerUtils.info("接口[{}]，返回数据:", request.getRequestURI(), JSON.toJSONString(outMsgBean));
    }
}
