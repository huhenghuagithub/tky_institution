package com.institution.transfer.service;

import com.institution.transfer.common.model.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface DataMigrationService {

    Result selectSqlResultVO(Integer start,Integer end);

    /**
     * 下载SAP转6.0凭证数据
     * @param response
     * @param tableName
     * @param all
     * @param screen
     * @throws Exception
     */
    void downloadFile(HttpServletResponse response, String tableName, String all, String screen) throws Exception;

    /**
     * 下载公司用到的客商数据---需执行完downloadFile方法
     * @param response
     * @throws Exception
     */
    void downloadMerchantsFile(HttpServletResponse response) throws Exception;

    /**
     * 转换凭证数据内错误的客商名称
     * @param response
     * @throws Exception
     */
    void changeMerchantsName(HttpServletResponse response, MultipartFile multipartFile) throws Exception;

    /**
     * 生成目录的全路径
     * @throws Exception
     */
    void getSubjectFullPath() throws Exception;

}
