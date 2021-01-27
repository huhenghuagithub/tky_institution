package com.institution.transfer.dao.mapper;

import com.institution.transfer.dao.model.SubjectDimensionComparison;
import com.institution.transfer.vo.SubjectDimensionVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SubjectDimensionComparisonMapper extends Mapper<SubjectDimensionComparison> {

    List<SubjectDimensionVO> selectSubjectDimensionList();

}