package com.hrssc.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.hrssc.domain.jacksonview.MiscView;
import com.hrssc.entities.Position;
import com.hrssc.entities.Skill;
import com.hrssc.service.LoadCommonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/load-form-info")
public class LoadFormInfoController {

    @Autowired
    LoadCommonInfoService loadCommonInfoService;

    @JsonView(MiscView.ShortView.class)
    @GetMapping("/skills")
    public List<Skill> getAllSkills(){
       return loadCommonInfoService.loadAllSkill();
    }

    @JsonView(MiscView.FormInfo.class)
    @GetMapping("/positions")
    public List<Position> getAllPositions(){
        return loadCommonInfoService.loadAllPosition();
    }

    @JsonView(MiscView.ShortView.class)
    @GetMapping("/get-skill-by-position/{positionId}")
    public List<Skill> getSkillByPosition(@PathVariable(value = "positionId") int positionId){
       return loadCommonInfoService.loadSkillByPosition(positionId);
    }

}
