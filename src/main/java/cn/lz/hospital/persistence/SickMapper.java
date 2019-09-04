package cn.lz.hospital.persistence;

import cn.lz.hospital.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface SickMapper extends Mapper<KS> {
    /**
     * 就诊人card_no唯一性验证
     * @param card_no
     * @return
     */
    public Integer checkUniqueness(@Param("card_no") String card_no);
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
     * 查询医生列表by id
     * @param id
     * @return
     */
    public List<Doctor> getDoctorById(@Param("department_id") Integer id);

    /**
     * 挂号
     * @param name 姓名
     * @param sex 性别(0=女,1=男)
     * @param age 年龄
     * @param ghtype_id 挂号类别id
     * @param id_card 身份证
     * @param doctor_id 医生id
     * @param department_id 科室id
     * @param card_no 卡号
     *
     * @return
     */
    public Map<String,Object> insertRegistration(            @Param("name") String name,
                                                 @Param("sex") Integer sex,
                                                 @Param("age") Integer age,
                                                 @Param("card_no") String card_no,
                                                 @Param("doctor_id") Integer doctor_id,
                                                 @Param("department_id") Integer department_id,
                                                 @Param("ghtype_id") Integer ghtype_id,
                                                 @Param("id_card") String id_card
                                                 );

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
    public List<MzPayable> getMzPayableList(@Param("card_no") String card_no);

    /**
     * 住院缴费查询
     * @param card_no
     * @return
     */
    public List<ZyPayable> getZyPayable(@Param("card_no") String card_no);


    /**
     * 住院缴费
     * @param card_no
     * @param total
     * @return
     */
    public Integer insertZyPay(@Param("card_no")String card_no, @Param("total")BigDecimal total);

    /**
     * 住院预交费
     * @param card_no
     * @param total
     * @return
     */
    public Integer insertZyPrePay(@Param("card_no")String card_no, @Param("total")BigDecimal total);

    /**
     * 化验报告明细
     * @param tmh
     * @return
     */
    public List<HyBgDetail> hyBgDetail(@Param("tmh") String tmh);

    /**
     * 患者门诊缴费
     * @param card_no
     * @param total
     * @return
     */
    public Integer insertMzPay(@Param("card_no") String card_no,@Param("total") BigDecimal total);

    /**
     * 检查化验报告表头
     * @param tmh
     * @param type
     * @return
     */
    public List<JchyBgInfo> jchyBgInfo(@Param("tmh") String tmh,@Param("type") String type);

    /**
     * 检查报告列表
     * @param card_no
     * @return
     */
    public List<JchyBgList> jchyBgList(@Param("card_no") String card_no);

}
