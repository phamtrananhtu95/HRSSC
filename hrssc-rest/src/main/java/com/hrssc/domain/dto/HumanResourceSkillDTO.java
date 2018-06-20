package com.hrssc.domain.dto;

import com.hrssc.entities.ResourceSkills;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Thien on 6/20/2018.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HumanResourceSkillDTO {

    private String fullname;
    private int status;
    private String email;
    private String tel;
    private Long availableDate;
    private Long availableDuration;
    private List<ResourceSkills> resourceSkills;
    private int companyId;
    private int positionId;
    private int userId;


}
