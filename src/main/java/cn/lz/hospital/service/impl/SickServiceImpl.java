package cn.lz.hospital.service.impl;

import cn.lz.hospital.domain.Doctor;
import cn.lz.hospital.domain.GhType;
import cn.lz.hospital.domain.KS;
import cn.lz.hospital.persistence.SickMapper;
import cn.lz.hospital.service.SickService;
import cn.lz.hospital.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SickServiceImpl implements SickService {
    @Autowired
    private SickMapper sickMapper;

    /**
     * 就诊人card_no唯一性验证
     * @param card_no
     * @return
     */
    @Override
    public int checkUniqueness(String card_no) {
        return  sickMapper.checkUniqueness(card_no);
    }

    /**
     * 科室列表
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> getAllById(Integer id) {
        List<KS> ksList =sickMapper.getAllById(id);
        Map<String,Object> retMap = new HashMap<>();
        retMap.put("p_department",ksList);
        return retMap;
    }

    /**
     * 医生列表
     * @return
     */
    @Override
    public List<Doctor> getDoctorAllList() {
        List<Doctor> doctorList = sickMapper.getDoctorAllList();
        return doctorList;
    }

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
    @Override
    public Map<String,Object> insertRegistration(String name, Integer sex,
                                     Integer age, String ghtype,
                                     Integer doctor_id, Integer department_id,
                                     String card_no) {
        Map<String,Object> retMap = sickMapper.insertRegistration(name,sex,
                age,ghtype,doctor_id,department_id,card_no);
        if(ValidateUtil.checkMapIsNotEmpty(retMap)){
            return retMap;
        }
        return null;
    }

    @Override
    public List<GhType> getGhTypeAllList() {
        List<GhType> ghTypeList = sickMapper.getGhTypeAllList();
        return ghTypeList;
    }
}
