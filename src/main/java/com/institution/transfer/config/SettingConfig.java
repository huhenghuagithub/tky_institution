package com.institution.transfer.config;

import com.institution.transfer.constants.DimensionCorrespondenceConstants;
import com.institution.transfer.dao.mapper.*;
import com.institution.transfer.dao.model.*;
import com.institution.transfer.vo.DimensionMessageVO;
import com.institution.transfer.vo.SubjectDimensionVO;
import com.institution.transfer.vo.VoucherDetailsVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * 初始化赋值
 */
@Component
public class SettingConfig implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SapSourceDataMapper sapSourceDataMapper;

    @Resource
    private SubjectCodeComparisonMapper subjectCodeComparisonMapper;

    @Resource
    private SubjectDimensionComparisonMapper subjectDimensionComparisonMapper;

    @Resource
    private DimensionValueComparisonMapper dimensionValueComparisonMapper;

    @Resource
    private XtSubjectInfoMapper xtSubjectInfoMapper;

    @Resource
    private CustomerCodeComparisonMapper customerCodeComparisonMapper;

    @Resource
    private SupplierCodeComparisonMapper supplierCodeComparisonMapper;

    @Resource
    private ConditionOneSourceDataMapper conditionOneSourceDataMapper;

    @Resource
    private MerchantsConversionMapper merchantsConversionMapper;

    @Override
    public void run(String... args) throws Exception {

        logger.error("项目启动完成，开始初始化所需静态资源=================");

        //6.0维度值
        //DimensionCorrespondenceConstants.subjectDimensionMap=new HashMap<>();
        List<DimensionValueComparison> dimensionValueComparisons = dimensionValueComparisonMapper.selectAll();
        for (DimensionValueComparison dimensionValueComparison : dimensionValueComparisons) {
            DimensionCorrespondenceConstants.dimensionCorrespondenceMap.put(dimensionValueComparison.getId(),dimensionValueComparison.getSexDimensionValue());
        }

        //SAP科目与6.0科目对应关系
        DimensionCorrespondenceConstants.subjectAndFunctionalScopeMap=new HashMap<>();
        List<SubjectCodeComparison> subjectCodeComparisons = subjectCodeComparisonMapper.selectAll();
        for (SubjectCodeComparison subjectCodeComparison : subjectCodeComparisons) {
            DimensionCorrespondenceConstants.subjectAndFunctionalScopeMap.put(
                    subjectCodeComparison.getSapSubjectCode()+
                            (StringUtils.isEmpty(subjectCodeComparison.getSapFunctionalScopeCode())?"":subjectCodeComparison.getSapFunctionalScopeCode())
                    ,subjectCodeComparison.getSexSubjectCode());
        }

        //6.0科目与维度对应
        DimensionCorrespondenceConstants.subjectDimensionMap=new HashMap<>();
        List<SubjectDimensionVO> subjectDimensionVOS = subjectDimensionComparisonMapper.selectSubjectDimensionList();
        for (SubjectDimensionVO subjectDimensionVO : subjectDimensionVOS) {
            List<DimensionMessageVO> dimensionMessageVOS = DimensionCorrespondenceConstants.subjectDimensionMap.get(subjectDimensionVO.getSexSubjectCode());
            if (null == dimensionMessageVOS || dimensionMessageVOS.isEmpty()){
                dimensionMessageVOS=new ArrayList<>();
                dimensionMessageVOS.add(subjectDimensionVO.getDimensionMessageVOS());
                DimensionCorrespondenceConstants.subjectDimensionMap.put(subjectDimensionVO.getSexSubjectCode(),dimensionMessageVOS);
            }else {
                dimensionMessageVOS.add(subjectDimensionVO.getDimensionMessageVOS());
                DimensionCorrespondenceConstants.subjectDimensionMap.put(subjectDimensionVO.getSexSubjectCode(),dimensionMessageVOS);
            }
        }

        //6.0科目全路径
        DimensionCorrespondenceConstants.sexSubjectFullPathMap=new HashMap<>();
        List<XtSubjectInfo> xtSubjectInfos = xtSubjectInfoMapper.selectAll();
        for (XtSubjectInfo xtSubjectInfo : xtSubjectInfos) {
            DimensionCorrespondenceConstants.sexSubjectFullPathMap.put(xtSubjectInfo.getXtSubjectCode(),xtSubjectInfo.getXtFullPath());
        }

        //客户对应关系
        DimensionCorrespondenceConstants.customerCodeComparisonMap=new HashMap<>();
        List<CustomerCodeComparison> customerCodeComparisons = customerCodeComparisonMapper.selectAll();
        for (CustomerCodeComparison customerCodeComparison : customerCodeComparisons) {
            DimensionCorrespondenceConstants.customerCodeComparisonMap.put(customerCodeComparison.getSapMerchantsCode(),customerCodeComparison.getSexMerchantsName());
        }

        //供应商对应关系
        DimensionCorrespondenceConstants.supplierCodeComparisonMap=new HashMap<>();
        List<SupplierCodeComparison> supplierCodeComparisons = supplierCodeComparisonMapper.selectAll();
        for (SupplierCodeComparison supplierCodeComparison : supplierCodeComparisons) {
                DimensionCorrespondenceConstants.supplierCodeComparisonMap.put(supplierCodeComparison.getSapMerchantsCode(),supplierCodeComparison.getSexMerchantsName());
        }

        //能导入凭证编号1001与1002----SAP公司编码+凭证号
        DimensionCorrespondenceConstants.conditionOneDataSet=new HashSet<>();
        List<ConditionOneSourceData> conditionOneSourceData = conditionOneSourceDataMapper.selectAll();
        for (ConditionOneSourceData conditionOneSourceDatum : conditionOneSourceData) {
            DimensionCorrespondenceConstants.conditionOneDataSet.add(conditionOneSourceDatum.getSapCompanyCodeVoucherCode());
        }

        DimensionCorrespondenceConstants.merchantsConversionMap=new HashMap<>();
        List<MerchantsConversion> merchantsConversions = merchantsConversionMapper.selectAll();
        for (MerchantsConversion merchantsConversion : merchantsConversions) {
            DimensionCorrespondenceConstants.merchantsConversionMap.put(merchantsConversion.getOldMerchantsName(),merchantsConversion.getNewMerchantsName());
        }

        logger.error("项目启动完成，初始化所需静态资源完成=================");

    }
}
