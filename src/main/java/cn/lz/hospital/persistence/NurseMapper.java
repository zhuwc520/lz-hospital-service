package cn.lz.hospital.persistence;

import cn.lz.hospital.domain.HBrList;
import cn.lz.hospital.domain.HHsList;
import cn.lz.hospital.domain.HYzList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NurseMapper {
    /**
     * 护士端登陆
     * @param id
     * @param password
     * @return
     */
    public Integer hLogin(@Param("id") Integer id,@Param("password") String password);

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
    public Integer hTwInput(@Param("zybm") String zybm,
                            @Param("temp") Integer temp,
                            @Param("pulse") Integer pulse,
                            @Param("blood1") Integer blood1,
                            @Param("blood2") Integer blood2,
                            @Param("cldate") String cldate,
                            @Param("cltime") Integer cltime);

    /**
     * 护士列表
     * @return
     */
    public List<HHsList> getHHsList();

    /**
     * 查房患者列表
     * @param keywords
     * @param id
     * @return
     */
    public List<HBrList> getHBrList(@Param("keywords") String keywords,@Param("id") Integer id);

    /**
     * 护士执行医嘱完成后通知HIS
     * @param zybm
     * @param xhstr
     * @param action_time
     * @return
     */
    public Integer hZxYz(@Param("id_bm") Integer id_bm, @Param("zybm") String zybm,@Param("xhstr") String xhstr,@Param("action_time") String action_time);

    /**
     * 医嘱列表
     * @param zybm
     * @param yztype
     * @param zt
     * @return
     */
    public List<HYzList> getHYzList(@Param("zybm") String zybm, @Param("yztype") String yztype, @Param("zt") String zt);
}
