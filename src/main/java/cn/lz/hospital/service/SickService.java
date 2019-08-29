package cn.lz.hospital.service;

import cn.lz.hospital.domain.Doctor;
import cn.lz.hospital.domain.GhType;
import cn.lz.hospital.domain.MzPayable;

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
}
