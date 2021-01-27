package com.institution.transfer.service;

import javax.servlet.http.HttpServletResponse;

public interface ScreenDataService {

    void downloadFile(HttpServletResponse response, String tableName) throws Exception;

}
