package com.hrssc.service.impl;

import com.hrssc.entities.Position;
import com.hrssc.entities.Skill;
import com.hrssc.repository.PositionRepository;
import com.hrssc.repository.SkillRepository;
import com.hrssc.service.LoadCommonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("loadCommonInfoService")
public class LoadCommonInfoServiceImp implements LoadCommonInfoService {
    @Autowired
    PositionRepository positionRepository;

    @Autowired
    SkillRepository skillRepository;

    @Override
    public List<Skill> loadAllSkill() {
       return skillRepository.findAll();
    }

    @Override
    public List<Position> loadAllPosition() {
        return positionRepository.findAll();
    }
}
