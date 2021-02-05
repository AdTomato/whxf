package com.authine.cloudpivot.web.api.jiaqinxinxi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Ke LongHai
 * @Date 2021/2/5 14:08
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LerveDto {

    //干部
    private List<CadreLeave> cadreLeavelist;
    //消防员
    private List<FiremenLeave> firemenLeaveList;
    //专职队员
    private List<ProfessionalPlayers> professionalPlayersList;
    //文员
    private List<ClerkLeave> clerkLeaveList;

}
