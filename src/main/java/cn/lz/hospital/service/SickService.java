package cn.lz.hospital.service;

import cn.lz.hospital.domain.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 患者端Service接口
 */
public interface SickService {
    /**
     * 就诊人card_no唯一性验证
     *
     * @param card_no
     * @return
     */
    public Map<String,Object> checkUniqueness(String card_no);

    /**
     * 科室列表
     *
     * @param id
     * @return
     */
    public Map<String, Object> getAllById(Integer id);

    /**
     * 医生列表
     *
     * @return
     */
    public List<Doctor> getDoctorAllList();

    /**
     * 根据科室id 获取医生列表
     * @param department_id
     * @return
     */
    public List<Doctor> getDoctorById(Integer department_id);

    /**
     * 挂号
     * @param name
     * @param sex
     * @param age
     * @param ghtype
     * @param doctor_id
     * @param department_id
     * @param card_no
     * @return
     */
    public Map<String,Object> insertRegistration(String name, Integer sex,
                                                 Integer age, String card_no,
                                                 Integer doctor_id, Integer department_id,
                                                 Integer ghtype_id,String id_card);

    /**
     * 挂号类别查询
     * @return
     */
    public List<GhType> getGhTypeAllList();

    /**
     * 门诊应缴费明细
     * @param card_no
     * @return
     */
    public List<MzPayable> getMzPayableList(String card_no);

    /**
     * 住院缴费明细
     * @param card_no
     * @return
     */
    public List<ZyPayable> getZyPayable(String card_no);



    /**
     * 住院缴费
     * @param zyBm
     * @param total
     * @return
     */
    public Integer insertZyPay(String zyBm, BigDecimal total);

    /**
     * 住院预交费
     * @param card_no
     * @param total
     * @return
     */
    public Integer insertZyPrePay(String card_no, BigDecimal total);

    /**
     * 化验报告明细
     * @param tmh
     * @return
     */
    public List<HyBgDetail> hyBgDetail(String tmh);

    /**
     * 患者门诊缴费
     * @param card_no
     * @param total
     * @return
     */
    public Integer insertMzPay(String card_no,BigDecimal total);

    /**
     * 检查化验报告表头
     * @param tmh
     * @param type
     * @return
     */
    public JchyBgInfo jchyBgInfo(String tmh,String type);

    /**
     * 检查化验报告列表
     * @param card_no
     * @return
     */
    public List<JchyBgList> jchyBgList(String card_no);
}
