package cn.lz.hospital.service;

import cn.lz.hospital.domain.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 护士端Service接口
 */
public interface NurseService {
    /**
     * 护士端登陆
     * @param id
     * @param password
     * @return
     */
    public NurseBean hLogin(Integer id, String password);

    /**
     * 填写体征检查数据
     * @param zybm
     * @param temp
     * @param pulse
     * @param blood1
     * @param blood2
     * @param cldate
     * @param cltime
     * @return
     */
    public Integer hTwInput(String zybm, BigDecimal temp, Integer pulse, Integer blood1, Integer blood2, String cldate, Integer cltime);

    /**
     * 护士列表
     * @return
     */
    public List<HHsList> getHHsList(String ks);

    /**
     * 查房患者列表
     * @param keywords
     * @param id
     * @param page
     * @return
     */
    public List<HBrList> getHBrList(String keywords,Integer id,Integer page);

    /**
     * 护士执行医嘱完成后通知HIS
     * @param zybm
     * @param xhstr
     * @param action_time
     * @return
     */
    public Integer hZxYz(Integer id_bm,String zybm,String xhstr,String action_time);

    /**
     * 医嘱列表
     * @param zybm
     * @param yztype
     * @param zt
     * @return
     */
    public List<HYzList> getHYzList(String zybm,String yztype,String zt);

    /**
     * 住院病人信息
     * @param zybm
     * @return
     */
    public List<HbrInfoBean> getHbrInfoList(String zybm);

}
