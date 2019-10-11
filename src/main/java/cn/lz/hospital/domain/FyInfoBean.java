package cn.lz.hospital.domain;

import java.util.List;

/**
 * Author: hr
 * Date: 2019-10-11 15:30
 * Content:
 */
public class FyInfoBean {

    private  List<FyDetail> fyDetails;

    private Integer pagecount;

    public List<FyDetail> getFyDetails() {
        return fyDetails;
    }

    public void setFyDetails(List<FyDetail> fyDetails) {
        this.fyDetails = fyDetails;
    }

    public Integer getPagecount() {
        return pagecount;
    }

    public void setPagecount(Integer pagecount) {
        this.pagecount = pagecount;
    }
}
