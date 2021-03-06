package cn.lz.hospital.service.impl;

import cn.lz.hospital.domain.*;
import cn.lz.hospital.persistence.SickMapper;
import cn.lz.hospital.service.SickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SickServiceImpl implements SickService {
    @Autowired
    private SickMapper sickMapper;

    /**
     * 就诊人card_no唯一性验证
     *
     * @param card_no
     * @return
     */
    @Override
    public Map<String, Object> checkUniqueness(String card_no) {
        Map<String, Object> retMap = new HashMap<>();
        Integer is_register = sickMapper.checkUniqueness(card_no);
        retMap.put("is_register", is_register);
        return retMap;
    }

    /**
     * 科室列表
     *
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> getAllById(Integer id) {
        List<KS> ksList = sickMapper.getAllById(id);
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("p_department", ksList);
        return retMap;
    }

    /**
     * 医生列表
     *
     * @return
     */
    @Override
    public List<Doctor> getDoctorAllList() {
        List<Doctor> doctorList = sickMapper.getDoctorAllList();
        return doctorList;
    }

    /**
     * 根据科室编号获取医生列表
     *
     * @param department_id 科室编号
     * @return
     */

    @Override
    public List<Doctor> getDoctorById(Integer department_id) {
        List<Doctor> doctors = sickMapper.getDoctorById(department_id);
        return doctors;
    }

    /**
     * 挂号
     *
     * @param name
     * @param sex
     * @param age
     * @param doctor_id
     * @param department_id
     * @param card_no
     * @return
     */
    @Override
    public Map<String, Object> insertRegistration(String name, Integer sex,
                                                  Integer age, String card_no,
                                                  Integer doctor_id, Integer department_id,
                                                  Integer ghtype_id, String id_card,String ghdate) {

        Map<String, Object> paramsMap = sickMapper.insertRegistration(name, sex,
                age, card_no, doctor_id, department_id, ghtype_id, id_card,ghdate);

        return paramsMap;
    }

    @Override
    public List<GhType> getGhTypeAllList() {
        List<GhType> ghTypeList = sickMapper.getGhTypeAllList();
        return ghTypeList;
    }

    @Override
    public List<MzPayable> getMzPayableList(String card_no) {
        List<MzPayable> mzPayableList = sickMapper.getMzPayableList(card_no);
        return mzPayableList;
    }

    @Override
    public List<ZyPayable> getZyPayable(String card_no) {
        List<ZyPayable> zyPayables = sickMapper.getZyPayable(card_no);
        return zyPayables;
    }

    /**
     * 住院缴费
     *
     * @param card_no
     * @param total
     * @return
     */
    @Override
    public Integer insertZyPay(String card_no, BigDecimal total) {
        return sickMapper.insertZyPay(card_no, total);
    }

    /**
     * 住院预交费
     *
     * @param card_no
     * @param total
     * @return
     */
    @Override
    public Integer insertZyPrePay(String card_no, BigDecimal total, BigDecimal zyyz) {
        return sickMapper.insertZyPrePay(card_no, total, zyyz);
    }

    @Override
    public List<HyBgDetail> hyBgDetail(String tmh) {
        return sickMapper.hyBgDetail(tmh);
    }

    @Override
    public Integer insertMzPay(String card_no, BigDecimal total) {
        return sickMapper.insertMzPay(card_no, total);
    }

    @Override
    public List<JchyBgInfo> jchyBgInfo(String tmh, String type) {

        return sickMapper.jchyBgInfo(tmh, type);
    }

    @Override
    public List<JchyBgList> jchyBgList(String card_no) {
        return sickMapper.jchyBgList(card_no);
    }

    @Override
    public HashMap<String, Object> queryCardNo(String id_card) {
        return sickMapper.queryCardNo(id_card);
    }

    @Override
    public List<ZyRecordBean> queryZyJlList(String card_no) {
        return sickMapper.queryZyJlList(card_no);
    }

    @Override
    public List<FyDetail> queryFyDetailList(String zybm) {
        return null;
    }

    @Override
    public List<FyDetail> queryFyDetailList(Map pm) {
        return sickMapper.queryFyDetailList(pm);
    }

}
