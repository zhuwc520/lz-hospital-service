package cn.lz.hospital.persistence;

import cn.lz.hospital.domain.Doctor;
import cn.lz.hospital.domain.GhType;
import cn.lz.hospital.domain.KS;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface SickMapper extends Mapper<KS> {
    /**
     * 就诊人card_no唯一性验证
     * @param card_no
     * @return
     */
    public int checkUniqueness(@Param("card_no") String card_no);
    /**
     * 科室列表查询
     * @param id
     * @return
     */
    public List<KS> getAllById(@Param("id") Integer id);

    /**
     * 查询医生列表
     * @return
     */
    public List<Doctor> getDoctorAllList();

    /**
     * 挂号
     * @param name 姓名
     * @param sex 性别(0=女,1=男)
     * @param age 年龄
     * @param ghtype 挂号类别编码
     * @param doctor_id 医生id
     * @param department_id 科室id
     * @param card_no 身份证号
     * @return
     */
    public Map<String,Object> insertRegistration(@Param("name") String name,
                                                 @Param("sex") Integer sex,
                                                 @Param("age") Integer age,
                                                 @Param("ghtype") String ghtype,
                                                 @Param("doctor_id") Integer doctor_id,
                                                 @Param("department_id") Integer department_id,
                                                 @Param("card_no") String card_no);

    /**
     * 挂号类别查询
     * @return
     */
    public List<GhType> getGhTypeAllList();
}
