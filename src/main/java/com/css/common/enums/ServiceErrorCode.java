package com.css.common.enums;

/**
 * 
 *TODO 服务错误码，及字典枚举
 * @author huangaho
 *2015-4-29下午12:56:52
 */
public enum ServiceErrorCode {
    /** 上传文件失败  */
    FAIL_UPFILE("1004", "文件上传失败"),
    /** 空文件  */
    EMPTY_FILE("1003", "空文件"),
    /** 参数错误 */
    INVALID_ARGUMENTS("error-004", "参数错误"),
    /**账号不存在**/
    ACCOUNT_NOT_EXIST("error-017","账号不存在"),
    ADMIN_ACCOUNT_NO_DELETE("error-018","超级管理员不能删除！"),
    /**用户登录失败，用户名或密码为空！*/
    ACCOUNT_OR_PASSWORD_NULL("error-3001","用户登录失败，用户名或密码不能为空！"),
    /**验证码错误**/
    CHECK_CODE_ERROR("error-3003","用户登录验证码输入错误！"),
    /**系统错误*/
    SYSTEM_ERROR("error-3000","系统错误"),
    /**无效令牌**/
    INVALID_TOKEN("error-3004", "无效令牌"),
    /**帐不唯一**/
    ACCOUNT_NOT_UNIQUE("","帐户名不唯一，请联系管理员"),
    /**密码错误**/
    PASSWORD_ERROR("error-018","密码错误"),
    /** 非法调用 */
    INVALID_INVOCATION("INVALID_INVOCATION", "非法调用"),
    /** 非法调用 */
    INVALID_USERNAMEPASSWORD("INVALID_USERNAMEPASSWORD", "无效的用户名或密码"),
    /** 增加索引异常 */
    ADD_INDEX_ERROR("ADD_INDEX_ERROR", "增加索引异常"),
    /** 删除索引异常 */
    DELETE_INDEX_ERROR("DELETE_INDEX_ERROR", "删除索引异常"),
    /** 搜索索引异常 */
    QUERY_INDEX_ERROR("DELETE_INDEX_ERROR", "搜索索引异常"),
    /** 其它未知错误 */
    OTHER_EXCEPTION("OTHER_EXCEPTION", "其它未知错误"),
    /** license校验失败 */
    LICENSE_VALIDATE_FAILED("LICENSE_VALIDATE_FAILED", "licence校验失败"),
    /** 数据已存在 */
    REPETITIONS_ARGUMENTS("0144", "该数据已经存在"),
    USERLOGINPARAMERR("10000", "用户名或密码不能为空!"),
    USERLOGINVALIDATIONERR("10001", "用户名或密码不正确！"),
    DATEFORMATERROR("10002", "日期格式错误！应如：yyyy-MM-dd"),
    DATABASEQUERYERROR("10004", "数据库查询错误"),
    DATAWRITESUCCESS("10005", "数据库写入成功"),
    DATADELETEERROR("10006", "数据库删除错误"),
    DATAWRITEERROR("10005", "数据库写入失败"),
    DATAUPDATEERROR("10007", "数据库更新错误"),
    INVALID_LIFEDIARY_CODE("10008", "无效的生活日记类别编码： "),
    FILE_UPLOAD_ERROR("10009", "文件上传失败"),
    FILE_CONTENT_NULL("10010", "文件内空为空"),
    DATAQUERYERROR("10011", "数据查询错误"),
    DATAQUERYSUCCESS("10012", "数据查询成功"),
    DATA_DELETE_ERROR("10013", "数据删除错误"),
    DATA_DELETE_SUCCESS("10014", "数据删除成功"),
    
    /**修改密码时验证旧密码未通过*/
    OLD_PWD_MISMATCH("OLD_PWD_MISMATCH", "旧密码错误"),
    RESOURCE_NOT_FOUND("OLD_PWD_MISMATCH", "资源文件未找到"),
    USER_MENU("usermenu","登录用户菜单"),
    USER_RESOURCE("userResource","登录用户资源"),
    SYSTEM_REOURCE_LIST("sysReourcelist","系统所有资源"),
    EVENT_AUDIT_YES("C10000002", "通过"),
    EVENT_AUDIT_NO("C10000001", "不通过"),
    EVENT_AUDIT_RETURN("C10000004", "重报"),
    EVENT_AUDIT_SUBMITED("C10000003", "送审"),
    ACCOUNT_STATUS_ENABLED("C10020001", "启用"),
    ACCOUNT_STATUS_DISABLE("C10020002", "禁用"),
    NO("C10010002","否"),
    /**是*/
    YES("C10010001","是"),
    EVENT_STATE_PROCESS("C10060002","正在处理"),
    EVENT_STATE_FINISH("C10060003","已結束");
    /** 编码 */
    private String             code;

    /** 描述 */
    private String             desc;

    private ServiceErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
