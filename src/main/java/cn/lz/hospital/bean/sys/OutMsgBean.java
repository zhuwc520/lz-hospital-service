package cn.lz.hospital.bean.sys;

import java.io.Serializable;

/**
 * @Description: 公共响应对应
 * @Author: zhuwc
 * @CreateDate: 2018/12/7 18:26
 * @Version: 1.0
 */
public class OutMsgBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer error_code = 200;
    private String error_msg = "成功";
    private Integer page;
    private Object data;

    public OutMsgBean() {
    }


    public OutMsgBean(Integer error_code, String error_msg, Object data) {
        this.error_code = error_code;
        this.error_msg = error_msg;
        this.data = data;
    }

    public OutMsgBean(Object data) {
        this.data = data;
    }
    public OutMsgBean(Integer page,Object data){
        this.page = page;
        this.data = data;
    }
    public OutMsgBean(Integer error_code, String error_msg) {
        this.error_code = error_code;
        this.error_msg = error_msg;
    }

    public Integer getError_code() {
        return error_code;
    }

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
