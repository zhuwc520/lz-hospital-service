package cn.lz.hospital.controller;

import cn.lz.hospital.bean.sys.OutMsgBean;
import cn.lz.hospital.controller.common.BaseController;
import cn.lz.hospital.domain.Doctor;
import cn.lz.hospital.domain.GhType;
import cn.lz.hospital.domain.MzPayable;
import cn.lz.hospital.domain.ZyPayable;
import cn.lz.hospital.service.SickService;
import cn.lz.hospital.utils.ValidateUtil;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import win.hupubao.common.utils.LoggerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 患者端接口
 */
@Controller
@RequestMapping("/sick")
@Api(tags = "患者端")
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
    @ApiOperation(httpMethod = "POST", value = "就诊人card_no唯一性验证", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "card_no",value = "就诊卡号",required = true,dataType = "string",paramType = "form")
    public void checkUniqueness(HttpServletRequest request,
                                HttpServletResponse response) {
        OutMsgBean outMsgBean = null;
        try {
            Map<String, Object> paramsMap = new HashMap<>();
            String card_no = getString("card_no", null, paramsMap);
            LoggerUtils.info("接口[{}]，请求参数:", request.getRequestURI(), JSON.toJSONString(paramsMap));
            if (ValidateUtil.isEmpty(card_no)) {
                outMsgBean = new OutMsgBean(-100, "参数不能为空");
                outJSONMsg(response, outMsgBean);
                return;
            }
            Map<String, Object> retMap  = sickService.checkUniqueness(card_no);
            if (!ValidateUtil.checkMapIsNotEmpty(retMap)) {
                outMsgBean = new OutMsgBean(-100, "读取信息请求失败");
                outJSONMsg(response, outMsgBean);
                return;
            }
            outMsgBean = new OutMsgBean(200, "处理完成", retMap);
            LoggerUtils.info("接口[{}]，请求参数：{}，响应数据：{}", request.getRequestURI(), JSON.toJSONString(paramsMap), JSON.toJSONString(outMsgBean));
            outJSONMsg(response, outMsgBean);
        } catch (Exception e) {
            LoggerUtils.error("就诊人card_no唯一性验证，异常{}", e.getMessage());
            outMsgBean = new OutMsgBean(-100, "就诊人card_no唯一性验证，发送异常");
            outJSONMsg(response, outMsgBean);
        }


    }

    /**
     * 科室列表
     *
     * @param request
     * @param response
     */
    @RequestMapping("/getKsList")
    @ApiOperation(httpMethod = "POST", value = "科室列表", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "id",value = "科室id",required = true,dataType = "int",paramType = "form")
    public void getKsList(HttpServletRequest request, HttpServletResponse response) {
        OutMsgBean outMsgBean = null;
        try {
            Map<String, Object> paramsMap = new HashMap<>();
            Integer id = getInteger("id", null, paramsMap);
            LoggerUtils.info("接口[{}]，请求参数:", request.getRequestURI(), JSON.toJSONString(paramsMap));
            Map<String, Object> retMap = sickService.getAllById(id);
            outMsgBean = new OutMsgBean(retMap);
            outJSONMsg(response, outMsgBean);
            LoggerUtils.info("接口[{}]，返回数据:", request.getRequestURI(), JSON.toJSONString(outMsgBean));
        } catch (Exception e) {
            LoggerUtils.error("科室列表查询异常===》{}", e.getMessage());
            outMsgBean = new OutMsgBean(-100, "查询科室列表异常");
            outJSONMsg(response, outMsgBean);
        }

    }


//    /**
//     * 医生列表
//     *
//     * @param request
//     * @param response
//     */
//    @RequestMapping("/getDoctorList")
//    public void getDoctorList(HttpServletRequest request, HttpServletResponse response) {
//        OutMsgBean outMsgBean = null;
//        try {
//            List<Doctor> doctorList = sickService.getDoctorAllList();
//            if (!ValidateUtil.checkListIsNotEmpty(doctorList)) {
//                outMsgBean = new OutMsgBean(-100, "查无数据");
//                outJSONMsg(response, outMsgBean);
//                return;
//            }
//            outMsgBean = new OutMsgBean(doctorList);
//            outJSONMsg(response, outMsgBean);
//            LoggerUtils.info("接口[{}]，返回数据:", request.getRequestURI(), JSON.toJSONString(outMsgBean));
//        } catch (Exception e) {
//            LoggerUtils.error("医生列表查询异常===》{}", e.getMessage());
//            outMsgBean = new OutMsgBean(-100, "医生查询列表发生异常");
//            outJSONMsg(response, outMsgBean);
//        }
//
//    }

    /**
     * 根据科室id查找医生
     *
     * @param request
     * @param response
     */
    @RequestMapping("/getDoctorListByid")
    @ApiOperation(httpMethod = "POST", value = "根据科室id查找医生", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "department_id",value = "科室id",required = true,dataType = "int",paramType = "form")
    public void getDoctorListByid(int department_id, HttpServletRequest request, HttpServletResponse response) {
        OutMsgBean outMsgBean = null;
        try {
            List<Doctor> doctorList = sickService.getDoctorById(department_id);
            if (!ValidateUtil.checkListIsNotEmpty(doctorList)) {
                outMsgBean = new OutMsgBean(-100, "查无数据");
                outJSONMsg(response, outMsgBean);
                return;
            }
            outMsgBean = new OutMsgBean(doctorList);
            outJSONMsg(response, outMsgBean);
            LoggerUtils.info("接口[{}]，返回数据:", request.getRequestURI(), JSON.toJSONString(outMsgBean));
        } catch (Exception e) {
            LoggerUtils.error("医生列表查询异常===》{}", e.getMessage());
            outMsgBean = new OutMsgBean(-100, "医生查询列表发生异常");
            outJSONMsg(response, outMsgBean);
        }

    }


    /**
     * 挂号
     *
     * @param request
     * @param response
     */
    @RequestMapping("/ghInsert")
    @ApiOperation(httpMethod = "POST", value = "挂号", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "名称",required = true,dataType = "string",paramType = "form"),
            @ApiImplicitParam(name = "sex",value = "性别(1=男,0=女)",required = true,dataType = "int",paramType = "form"),
            @ApiImplicitParam(name = "age",value = "年龄",required = true,dataType = "int",paramType = "form"),
            @ApiImplicitParam(name = "ghtype_id",value = "挂号类别id",required = true,dataType = "int",paramType = "form"),
            @ApiImplicitParam(name = "doctor_id",value = "医生id",required = true,dataType = "int",paramType = "form"),
            @ApiImplicitParam(name = "department_id",value = "科室id",required = true,dataType = "int",paramType = "form"),
            @ApiImplicitParam(name = "card_no",value = "就诊卡号",required = true,dataType = "string",paramType = "form"),
            @ApiImplicitParam(name = "id_card",value = "身份证",required = true,dataType = "string",paramType = "form")
    })
    public void ghInsert(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> paramsMap = new HashMap<>();
        OutMsgBean outMsgBean = null;
        try {
            String name = getString("name", null, paramsMap);
            Integer sex = getInteger("sex", 1, paramsMap);
            Integer age = getInteger("age", null, paramsMap);
            Integer ghtype_id = getInteger("ghtype_id", null, paramsMap);
            Integer doctor_id = getInteger("doctor_id", null, paramsMap);
            Integer department_id = getInteger("department_id", null, paramsMap);
            String card_no = getString("card_no", null, paramsMap);
            String id_card = getString("id_card", null, paramsMap);
            LoggerUtils.info("接口[{}]，请求参数:", request.getRequestURI(), JSON.toJSONString(paramsMap));
            if (ValidateUtil.isEmpty(name) || ValidateUtil.isEmpty(sex)
                    || ValidateUtil.isEmpty(age) || ValidateUtil.isEmpty(ghtype_id)
                    || ValidateUtil.isEmpty(doctor_id) || ValidateUtil.isEmpty(department_id)
                    || ValidateUtil.isEmpty(card_no)||ValidateUtil.isEmpty(id_card)) {
                outMsgBean = new OutMsgBean(-100, "参数不能为空");
                outJSONMsg(response, outMsgBean);
                return;
            }
            Map<String, Object> retMap = sickService.insertRegistration(name, sex, age, card_no, doctor_id, department_id,ghtype_id, id_card);
            if (!ValidateUtil.checkMapIsNotEmpty(retMap)) {
                outMsgBean = new OutMsgBean(-100, "查无数据");
                outJSONMsg(response, outMsgBean);
                return;
            }
            outMsgBean = new OutMsgBean(retMap);
            outJSONMsg(response, outMsgBean);
            LoggerUtils.info("接口[{}]，返回数据:", request.getRequestURI(), JSON.toJSONString(outMsgBean));
        } catch (Exception e) {
            LoggerUtils.error("挂号异常===》{}", e.getMessage());
            outMsgBean = new OutMsgBean(-100, "挂号异常发生异常");
            outJSONMsg(response, outMsgBean);
        }

    }

    /**
     * 挂号列表查询
     *
     * @param request
     * @param response
     */

    @RequestMapping("/getGhTypeList")
    @ApiOperation(httpMethod = "POST", value = "挂号列表查询", produces = MediaType.APPLICATION_JSON_VALUE)
    public void getGhTypeList(HttpServletRequest request, HttpServletResponse response) {
        OutMsgBean outMsgBean = null;
        try {
            List<GhType> ghTypeList = sickService.getGhTypeAllList();
            if (!ValidateUtil.checkListIsNotEmpty(ghTypeList)) {
                outMsgBean = new OutMsgBean(-100, "查无数据");
                outJSONMsg(response, outMsgBean);
                return;
            }
            outMsgBean = new OutMsgBean(ghTypeList);
            outJSONMsg(response, outMsgBean);
            LoggerUtils.info("接口[{}]，返回数据:", request.getRequestURI(), JSON.toJSONString(outMsgBean));
        } catch (Exception e) {
            LoggerUtils.error("挂号列表查询异常===》{}", e.getMessage());
            outMsgBean = new OutMsgBean(-100, "挂号列表查询发生异常");
            outJSONMsg(response, outMsgBean);
        }

    }

    /**
     * 门诊缴费查询
     * @param request
     * @param response
     */
    @RequestMapping("/getMzPayableList")
    @ApiOperation(httpMethod = "POST", value = "门诊缴费查询", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "card_no",value = "就诊卡号",required = true,dataType = "string",paramType = "form")
    public void getMzPayableList(HttpServletRequest request, HttpServletResponse response){
        OutMsgBean outMsgBean = null;
        Map<String, Object> paramsMap = new HashMap<>();
        try{
            String card_no = getString("card_no", null, paramsMap);
            List<MzPayable> mzPayableList = sickService.getMzPayableList(card_no);

            if (!ValidateUtil.checkListIsNotEmpty(mzPayableList)) {
                outMsgBean = new OutMsgBean(-100, "查无数据");
                outJSONMsg(response, outMsgBean);
                return;
            }
            outMsgBean = new OutMsgBean(mzPayableList);
            outJSONMsg(response, outMsgBean);
            LoggerUtils.info("接口[{}]，返回数据:", request.getRequestURI(), JSON.toJSONString(outMsgBean));

        }catch (Exception e){
            LoggerUtils.error("门诊缴费查询===》{}", e.getMessage());
            outMsgBean = new OutMsgBean(-100, "门诊缴费查询发生异常");
            outJSONMsg(response, outMsgBean);
        }
    }


    /**
     * 住院缴费查询
     * @param request
     * @param response
     */
    @RequestMapping("/getZyPayableList")
    @ApiOperation(httpMethod = "POST", value = "住院缴费查询", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "card_no",value = "就诊卡号",required = true,dataType = "string",paramType = "form")
    public void getZyPayableList(HttpServletRequest request, HttpServletResponse response){
        OutMsgBean outMsgBean = null;
        Map<String, Object> paramsMap = new HashMap<>();
        try{
            String card_no = getString("card_no", null, paramsMap);
            List<ZyPayable> zyPayables = sickService.getZyPayable(card_no);

            if (!ValidateUtil.checkListIsNotEmpty(zyPayables)) {
                outMsgBean = new OutMsgBean(-100, "查无数据");
                outJSONMsg(response, outMsgBean);
                return;
            }
            outMsgBean = new OutMsgBean(zyPayables);
            outJSONMsg(response, outMsgBean);
            LoggerUtils.info("接口[{}]，返回数据:", request.getRequestURI(), JSON.toJSONString(outMsgBean));

        }catch (Exception e){
            LoggerUtils.error("住院缴费查询===》{}", e.getMessage());
            outMsgBean = new OutMsgBean(-100, "住院缴费查询发生异常");
            outJSONMsg(response, outMsgBean);
        }
    }


    /**
     * 住院缴费
     * @param request
     * @param response
     */
    @RequestMapping("/insertZyPay")
    @ApiOperation(httpMethod = "POST", value = "住院缴费", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "zyBm", value = "住院编号", required = true, dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "total", value = "总金额", required = true, dataType = "string", paramType = "form")
    }
    )
    public void insertZyPay(HttpServletRequest request, HttpServletResponse response){
        OutMsgBean outMsgBean = null;
        Map<String, Object> paramsMap = new HashMap<>();
        try{
            String zyBm = getString("zyBm", null, paramsMap);
            String total = getString("total","0.00",paramsMap);
            BigDecimal totalBig = new BigDecimal(total);
            Integer result = sickService.insertZyPay(zyBm,totalBig);
            if (result == null){
                outMsgBean = new OutMsgBean(-100, "缴费异常");
                outJSONMsg(response, outMsgBean);
                return;
            }
            if (result == 2){
                outMsgBean = new OutMsgBean(-100, "金额不符");
                outJSONMsg(response, outMsgBean);
                return;
            }
            if (result == -1){
                outMsgBean = new OutMsgBean(-100, "缴费失败");
                outJSONMsg(response, outMsgBean);
                return;
            }

            outMsgBean = new OutMsgBean(result);
            outJSONMsg(response, outMsgBean);
            LoggerUtils.info("接口[{}]，返回数据:", request.getRequestURI(), JSON.toJSONString(outMsgBean));

        }catch (Exception e){
            LoggerUtils.error("住院缴费===》{}", e.getMessage());
            outMsgBean = new OutMsgBean(-100, "住院缴费发生异常");
            outJSONMsg(response, outMsgBean);
        }
    }

    /**
     * 预存交费
     * @param request
     * @param response
     */
    @RequestMapping("/insertZyPrePay")
    @ApiOperation(httpMethod = "POST", value = "预存交费", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({

            @ApiImplicitParam(name = "card_no", value = "就诊卡号", required = true, dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "total", value = "总金额", required = true, dataType = "string", paramType = "form")
    }
    )
    public void insertZyPrePay(HttpServletRequest request, HttpServletResponse response){
        OutMsgBean outMsgBean = null;
        Map<String, Object> paramsMap = new HashMap<>();
        try{
            String card_no = getString("card_no", null, paramsMap);
            String total = getString("total","0.00",paramsMap);
            BigDecimal totalBig = new BigDecimal(total);
            Integer result = sickService.insertZyPrePay(card_no,totalBig);
            if (result == null){
                outMsgBean = new OutMsgBean(-100, "缴费异常");
                outJSONMsg(response, outMsgBean);
                return;
            }
            if (result == -1){
                outMsgBean = new OutMsgBean(-100, "缴费失败");
                outJSONMsg(response, outMsgBean);
                return;
            }

            outMsgBean = new OutMsgBean(result);
            outJSONMsg(response, outMsgBean);
            LoggerUtils.info("接口[{}]，返回数据:", request.getRequestURI(), JSON.toJSONString(outMsgBean));

        }catch (Exception e){
            LoggerUtils.error("住院缴费===》{}", e.getMessage());
            outMsgBean = new OutMsgBean(-100, "住院缴费发生异常");
            outJSONMsg(response, outMsgBean);
        }
    }
}
