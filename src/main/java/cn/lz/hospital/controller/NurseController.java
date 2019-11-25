package cn.lz.hospital.controller;

import cn.lz.hospital.bean.sys.OutMsgBean;
import cn.lz.hospital.controller.common.BaseController;
import cn.lz.hospital.domain.*;
import cn.lz.hospital.service.NurseService;
import cn.lz.hospital.utils.ValidateUtil;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import win.hupubao.common.utils.LoggerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName NurseController
 * @Description: TODO 护士端
 * @Author zhuwc
 * @Date 2019/10/9
 * @Version V1.0
 **/
@Controller
@RequestMapping("/nurse")
@Api(tags = "护士端")
public class NurseController extends BaseController {
    @Autowired
    private NurseService nurseService;

    @RequestMapping("/hLogin")
    @ApiOperation(httpMethod = "POST", value = "护士端登录接口", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "登陆id", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "password", value = "登陆密码", required = true, dataType = "string", paramType = "form")
    })
    public void hLogin(HttpServletRequest request, HttpServletResponse response) {
        OutMsgBean outMsgBean = null;
        try {
            Map<String, Object> paramsMap = new HashMap<>();
            Integer id = getInteger("id", null, paramsMap);
            String password = getString("password", null, paramsMap);
            LoggerUtils.info("接口[{}]，请求参数:{}", request.getRequestURI(), JSON.toJSONString(paramsMap));
            if (ValidateUtil.isEmpty(id) || ValidateUtil.isEmpty(password)) {
                outMsgBean = new OutMsgBean(-100, "参数不能为空");
                outJSONMsg(response, outMsgBean);
                return;
            }

            NurseBean nurseBean = nurseService.hLogin(id, password);
            if(nurseBean == null){
                outMsgBean = new OutMsgBean(-100,"登陆失败");
                outJSONMsg(response,outMsgBean);
                return;
            }
            if (nurseBean.getResult() == -1) {
                outMsgBean = new OutMsgBean(-100, "姓名密码验证不通过");
                outJSONMsg(response, outMsgBean);
                return;
            }
//            Map<String, Object> retMap = new HashMap<>();
//            retMap.put("result", nurseBean);
            outMsgBean = new OutMsgBean(nurseBean);
            LoggerUtils.info("接口[{}]，请求参数：{}，响应数据：{}", request.getRequestURI(), JSON.toJSONString(paramsMap), JSON.toJSONString(outMsgBean));
            outJSONMsg(response, outMsgBean);
        } catch (Exception e) {
            LoggerUtils.error("护士端登录，异常{}", e.getMessage());
            outMsgBean = new OutMsgBean(-100, "护士端登录，发送异常");
            outJSONMsg(response, outMsgBean);
        }
    }

    @RequestMapping("/hTwInput")
    @ApiOperation(httpMethod = "POST", value = "填写体征检查数据", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "zybm", value = "患者住院号", required = true, dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "temp", value = "患者体温", required = true, dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "pulse", value = "脉搏", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "blood1", value = "低压", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "blood2", value = "高压", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "cldate", value = "测量日期", required = true, dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "cltime", value = "测量时间", required = true, dataType = "int", paramType = "form")
    })
    public void hTwInput(HttpServletRequest request, HttpServletResponse response) {
        OutMsgBean outMsgBean = null;
        try {
            Map<String, Object> paramsMap = new HashMap<>();
            String zybm = getString("zybm", null, paramsMap);
            String temp = getString("temp", null, paramsMap);
            Integer pulse = getInteger("pulse", null, paramsMap);
            Integer blood1 = getInteger("blood1", null, paramsMap);
            Integer blood2 = getInteger("blood2", null, paramsMap);
            String cldate = getString("cldate", null, paramsMap);
            Integer cltime = getInteger("cltime", null, paramsMap);
            LoggerUtils.info("接口[{}]，请求参数:{}", request.getRequestURI(), JSON.toJSONString(paramsMap));
            if (ValidateUtil.isEmpty(zybm) || ValidateUtil.isEmpty(temp)
                    || ValidateUtil.isEmpty(pulse) || ValidateUtil.isEmpty(blood1)
                    || ValidateUtil.isEmpty(blood2) || ValidateUtil.isEmpty(cldate)
                    || ValidateUtil.isEmpty(cltime)) {
                outMsgBean = new OutMsgBean(-100, "参数不能为空");
                outJSONMsg(response, outMsgBean);
                return;
            }
            BigDecimal tempBig = new BigDecimal(temp);

            Integer result = nurseService.hTwInput(zybm, tempBig, pulse, blood1, blood2, cldate, cltime);
            if(result == null){
                outMsgBean = new OutMsgBean(-100,"填写体征检查数据失败");
                outJSONMsg(response,outMsgBean);
                return;
            }
            if(result == -2){
                outMsgBean = new OutMsgBean(-100,"该测量时间的体温数据已经存在");
                outJSONMsg(response,outMsgBean);
                return;
            }
            if(result == -1){
                outMsgBean = new OutMsgBean(-100,"保存体温数据失败");
                outJSONMsg(response,outMsgBean);
                return;
            }
            Map<String, Object> retMap = new HashMap<>();
            retMap.put("result", result);
            outMsgBean = new OutMsgBean(retMap);
            LoggerUtils.info("接口[{}]，请求参数：{}，响应数据：{}", request.getRequestURI(), JSON.toJSONString(paramsMap), JSON.toJSONString(outMsgBean));
            outJSONMsg(response, outMsgBean);
        } catch (Exception e) {
            LoggerUtils.error("填写体征检查数据，异常{}", e.getMessage());
            outMsgBean = new OutMsgBean(-100, "填写体征检查数据，发送异常");
            outJSONMsg(response, outMsgBean);
        }
    }

    @RequestMapping("/hHsList")
    @ApiOperation(httpMethod = "POST", value = "护士列表", produces = MediaType.APPLICATION_JSON_VALUE)
    public void hHsList(HttpServletRequest request, HttpServletResponse response) {
        OutMsgBean outMsgBean = null;
        try {


            Map<String, Object> paramsMap = new HashMap<>();
            String ks = getString("ks", "", paramsMap);
            List<HHsList> hHsLists = nurseService.getHHsList(ks);
            if (!ValidateUtil.checkListIsNotEmpty(hHsLists)) {
                outMsgBean = new OutMsgBean(-100, "查无数据");
                outJSONMsg(response, outMsgBean);
                return;
            }
            outMsgBean = new OutMsgBean(hHsLists);
            LoggerUtils.info("接口[{}]，响应数据：{}", request.getRequestURI(), JSON.toJSONString(outMsgBean));
            outJSONMsg(response, outMsgBean);
        } catch (Exception e) {
            LoggerUtils.error("护士列表，异常{}", e.getMessage());
            outMsgBean = new OutMsgBean(-100, "护士列表，发送异常");
            outJSONMsg(response, outMsgBean);
        }
    }

    @RequestMapping("/hBrList")
    @ApiOperation(httpMethod = "POST", value = "查房患者列表", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keywords", value = "搜索关键字。查询患者姓名，就诊号，card_no", required = false, dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "id", value = "护士id", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "page", value = "分页页码", required = true, dataType = "int", paramType = "form")
    })
    public void hBrList(HttpServletRequest request, HttpServletResponse response) {
        OutMsgBean outMsgBean = null;
        try {
            Map<String, Object> paramsMap = new HashMap<>();
            String keywords = getString("keywords", "", paramsMap);
            Integer id = getInteger("id", null, paramsMap);
            Integer page = getInteger("page", null, paramsMap);
            LoggerUtils.info("接口[{}]，请求参数:{}", request.getRequestURI(), JSON.toJSONString(paramsMap));
            if (ValidateUtil.isEmpty(id) || ValidateUtil.isEmpty(page)) {
                outMsgBean = new OutMsgBean(-100, "参数不能为空");
                outJSONMsg(response, outMsgBean);
                return;
            }
            List<HBrList> brLists = nurseService.getHBrList(keywords, id, page);
            if(!ValidateUtil.checkListIsNotEmpty(brLists)){
                outMsgBean = new OutMsgBean(-100,"查无数据");
                outJSONMsg(response,outMsgBean);
                return;
            }
            outMsgBean = new OutMsgBean(page, brLists);
            LoggerUtils.info("接口[{}]，请求参数：{}，响应数据：{}", request.getRequestURI(), JSON.toJSONString(paramsMap), JSON.toJSONString(outMsgBean));
            outJSONMsg(response, outMsgBean);
        } catch (Exception e) {
            LoggerUtils.error("查房患者列表，异常{}", e.getMessage());
            outMsgBean = new OutMsgBean(-100, "查房患者列表，发送异常");
            outJSONMsg(response, outMsgBean);
        }
    }

    @RequestMapping("/hZxYz")
    @ApiOperation(httpMethod = "POST", value = "护士执行医嘱完成后通知HIS", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id_bm", value = "护士编码", required = true, dataType = "int", paramType = "form"),
            @ApiImplicitParam(name = "zybm", value = "患者住院号", required = true, dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "xhstr", value = "执行医嘱的ID拼接字符串", required = true, dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "action_time", value = "时间戳,执行医嘱时间", required = true, dataType = "string", paramType = "form")
    })
    public void hZxYz(HttpServletRequest request, HttpServletResponse response) {
        OutMsgBean outMsgBean = null;
        try {
            Map<String, Object> paramsMap = new HashMap<>();
            Integer id_bm = getInteger("id_bm", null, paramsMap);
            String zybm = getString("zybm", null, paramsMap);
            String xhstr = getString("xhstr", null, paramsMap);
            String action_time = getString("action_time", null, paramsMap);
            LoggerUtils.info("接口[{}]，请求参数:{}", request.getRequestURI(), JSON.toJSONString(paramsMap));
            if (ValidateUtil.isEmpty(id_bm) || ValidateUtil.isEmpty(zybm) || ValidateUtil.isEmpty(xhstr) || ValidateUtil.isEmpty(action_time)) {
                outMsgBean = new OutMsgBean(-100, "参数不能为空");
                outJSONMsg(response, outMsgBean);
                return;
            }
            Integer result = nurseService.hZxYz(id_bm, zybm, xhstr, action_time);
            if(result == null){
                outMsgBean = new OutMsgBean(-100,"通知HIS失败");
                outJSONMsg(response,outMsgBean);
                return;
            }
            if (result == -5) {
                outMsgBean = new OutMsgBean(-100, "选择的行里面包含有长期医嘱和临时医嘱");
                outJSONMsg(response, outMsgBean);
                return;
            }
            if (result == -6) {
                outMsgBean = new OutMsgBean(-100, "选择的行里面包含有不同医生的医嘱");
                outJSONMsg(response, outMsgBean);
                return;
            }
            if (result == -7) {
                outMsgBean = new OutMsgBean(-100, "选择的行里面包含有不同执行科室的药品医嘱");
                outJSONMsg(response, outMsgBean);
                return;
            }
            if (result == -8) {
                outMsgBean = new OutMsgBean(-100, "选择的行里面包含有已经执行的医嘱");
                outJSONMsg(response, outMsgBean);
                return;
            }
            Map<String, Object> retMap = new HashMap<>();
            retMap.put("result", result);
            outMsgBean = new OutMsgBean(retMap);
            LoggerUtils.info("接口[{}]，请求参数：{}，响应数据：{}", request.getRequestURI(), JSON.toJSONString(paramsMap), JSON.toJSONString(outMsgBean));
            outJSONMsg(response, outMsgBean);
        } catch (Exception e) {
            LoggerUtils.error("护士执行医嘱完成后通知HIS，异常{}", e.getMessage());
            outMsgBean = new OutMsgBean(-100, "护士执行医嘱完成后通知HIS，发送异常");
            outJSONMsg(response, outMsgBean);
        }
    }

    @RequestMapping("/hYzList")
    @ApiOperation(httpMethod = "POST", value = "医嘱列表", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "zybm", value = "患者住院编号", required = true, dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "yztype", value = "医嘱类别（长期医嘱,临时医嘱）", required = true, dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "zt", value = "状态（所有,已执行，未执行，已停止）", required = true, dataType = "string", paramType = "form")
    })
    public void hYzList(HttpServletRequest request, HttpServletResponse response) {
        OutMsgBean outMsgBean = null;
        try {
            Map<String, Object> paramsMap = new HashMap<>();
            String zybm = getString("zybm", null, paramsMap);
            String yztype = getString("yztype", null, paramsMap);
            String zt = getString("zt", null, paramsMap);
            LoggerUtils.info("接口[{}]，请求参数:{}", request.getRequestURI(), JSON.toJSONString(paramsMap));
            if (ValidateUtil.isEmpty(zybm) || ValidateUtil.isEmpty(yztype) || ValidateUtil.isEmpty(zt)) {
                outMsgBean = new OutMsgBean(-100, "参数不能为空");
                outJSONMsg(response, outMsgBean);
                return;
            }
            List<HYzList> hYzLists = nurseService.getHYzList(zybm, yztype, zt);
            if (!ValidateUtil.checkListIsNotEmpty(hYzLists)) {
                outMsgBean = new OutMsgBean(-100, "查无数据");
                outJSONMsg(response, outMsgBean);
                return;
            }
            outMsgBean = new OutMsgBean(hYzLists);
            LoggerUtils.info("接口[{}]，请求参数：{}，响应数据：{}", request.getRequestURI(), JSON.toJSONString(paramsMap), JSON.toJSONString(outMsgBean));
            outJSONMsg(response, outMsgBean);
        } catch (Exception e) {
            LoggerUtils.error("医嘱列表，异常{}", e.getMessage());
            outMsgBean = new OutMsgBean(-100, "医嘱列表，发送异常");
            outJSONMsg(response, outMsgBean);
        }
    }

    //住院病人信息(pMobile_HBrInfo)
    @RequestMapping("/brInfo")
    @ApiOperation(httpMethod = "POST", value = "住院病人信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "zybm", value = "患者住院编号", required = true, dataType = "string", paramType = "form"),
    })
    public void brInfo(HttpServletRequest request, HttpServletResponse response) {
        OutMsgBean outMsgBean = null;
        try {
            Map<String, Object> paramsMap = new HashMap<>();
            String zybm = getString("zybm", null, paramsMap);
            LoggerUtils.info("接口[{}]，请求参数:{}", request.getRequestURI(), JSON.toJSONString(paramsMap));
            if (ValidateUtil.isEmpty(zybm)) {
                outMsgBean = new OutMsgBean(-100, "参数不能为空");
                outJSONMsg(response, outMsgBean);
                return;
            }
            List<HbrInfoBean> hbrInfoBeans = nurseService.getHbrInfoList(zybm);
            if (!ValidateUtil.checkListIsNotEmpty(hbrInfoBeans)) {
                outMsgBean = new OutMsgBean(-100, "查无数据");
                outJSONMsg(response, outMsgBean);
                return;
            }
            outMsgBean = new OutMsgBean(hbrInfoBeans);
            LoggerUtils.info("接口[{}]，请求参数：{}，响应数据：{}", request.getRequestURI(), JSON.toJSONString(paramsMap), JSON.toJSONString(outMsgBean));
            outJSONMsg(response, outMsgBean);
        } catch (Exception e) {
            LoggerUtils.error("住院病人信息，异常{}", e.getMessage());
            outMsgBean = new OutMsgBean(-100, "住院病人信息，发送异常");
            outJSONMsg(response, outMsgBean);
        }
    }


    
}
