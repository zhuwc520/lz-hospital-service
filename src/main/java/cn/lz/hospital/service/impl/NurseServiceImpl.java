package cn.lz.hospital.service.impl;

import cn.lz.hospital.domain.*;
import cn.lz.hospital.persistence.NurseMapper;
import cn.lz.hospital.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName NurseServiceImpl
 * @Description: TODO 护士端Service接口实现
 * @Author zhuwc
 * @Date 2019/10/9
 * @Version V1.0
 **/
@Service
public class NurseServiceImpl implements NurseService {
    @Autowired
    private NurseMapper nurseMapper;

    @Override
    public NurseBean hLogin(Integer id, String password) {
        NurseBean nurseBean = nurseMapper.hLogin(id, password);
        return nurseBean;
    }

    @Override
    public Integer hTwInput(String zybm, BigDecimal temp, Integer pulse, Integer blood1, Integer blood2, String cldate, Integer cltime) {
        Integer result = nurseMapper.hTwInput(zybm, temp, pulse, blood1, blood2, cldate, cltime);
        return result;
    }

    @Override
    public List<HHsList> getHHsList(String ks) {
        List<HHsList> hHsLists = nurseMapper.getHHsList(ks);
        return hHsLists;
    }

    @Override
    public List<HBrList> getHBrList(String keywords, Integer id, Integer page) {
        List<HBrList> hBrLists = nurseMapper.getHBrList(keywords, id);
        return hBrLists;
    }

    @Override
    public Integer hZxYz(Integer id_bm, String zybm, String xhstr, String action_time) {
        Integer result = nurseMapper.hZxYz(id_bm, zybm, xhstr, action_time);
        return result;
    }

    @Override
    public List<HYzList> getHYzList(String zybm, String yztype, String zt) {
        List<HYzList> hYzLists = nurseMapper.getHYzList(zybm, yztype, zt);
        return hYzLists;
    }



    @Override
    public List<HbrInfoBean> getHbrInfoList(String zybm) {
        return nurseMapper.getHbrInfoList(zybm);
    }

    @Override
    public List<HTwBean> querHtwBeanList(String zybm, String cldate) {
        List<HTwBean> hTwBeans = nurseMapper.querHtwBeanList(zybm,cldate);
        return hTwBeans;
    }
}
