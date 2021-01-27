package com.institution.transfer.dao.mapper;


import com.institution.transfer.dao.model.SapSourceData;
import com.institution.transfer.vo.SqlResultVO;
import com.institution.transfer.vo.VoucherVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SapSourceDataMapper extends Mapper<SapSourceData> {

    Integer selectMaxId();

    List<Map<String,String>> selectCompanyCodes(@Param("tableName")String tableName);

    List<SqlResultVO> selectSqlResultVOByStartAndEnd(@Param("start") Integer start,@Param("end") Integer end
            ,@Param("sapCompanyCode") String sapCompanyCode,@Param("tableName") String tableName);

    List<Map<String,Object>> selectVoucherType(@Param("sapCompanyCode")String sapCompanyCode
            ,@Param("tableName")String tableName);

}