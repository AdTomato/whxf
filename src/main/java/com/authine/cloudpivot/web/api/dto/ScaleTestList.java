package com.authine.cloudpivot.web.api.dto;

import com.authine.cloudpivot.web.api.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *  量表测评内容和量表测评结果集合
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScaleTestList extends ScaleTest {

    private List<ScaleTestDetail> scaleTestDetails;

    private List<ScaleTestResult> scaleTestResults;

  //  private List<ScaleTestAcore> scaleTestAcores;




}
