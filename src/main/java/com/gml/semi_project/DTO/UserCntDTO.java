package com.gml.semi_project.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCntDTO {

    private Long totalUserCnt;
    private Long totalAdminCnt;
    private Long totalNewbieCnt;
    private Long totalVIPCnt;
    private Long totalBlacklistCnt;
}