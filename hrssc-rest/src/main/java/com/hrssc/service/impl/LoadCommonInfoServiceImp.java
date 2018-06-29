package com.hrssc.service.impl;

import com.hrssc.entities.Position;
import com.hrssc.entities.Skill;
import com.hrssc.repository.PositionRepository;
import com.hrssc.repository.SkillRepository;
import com.hrssc.service.LoadCommonInfoService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Service("loadCommonInfoService")
public class LoadCommonInfoServiceImp implements LoadCommonInfoService {
    @Autowired
    PositionRepository positionRepository;

    @Autowired
    SkillRepository skillRepository;
    Comparator<Skill> skillComparator = new Comparator<Skill>() {
        @Override
        public int compare(Skill o1, Skill o2) {
            if(o1.getTitle().compareTo(o2.getTitle()) > 0){
                return 1;
            }
            if(o1.getTitle().compareTo(o2.getTitle()) < 0){
                return -1;
            }
            return 0;
        }
    };
    @Override
    public List<Skill> loadAllSkill() {
        List<Skill> skillList = skillRepository.findDistinctSkill();
        skillList.sort(skillComparator);

       return skillList;
    }

    @Override
    public List<Position> loadAllPosition() {
        List<Position> positionList = positionRepository.findAll();
        for(Position position: positionList){
            List<Skill> skillList = (List<Skill>)position.getSkillsById();
            skillList.sort(skillComparator);
        }
        return positionList;
    }

    @Override
    public List<Skill> loadSkillByPosition(int positionId){
        List<Skill> skillList = skillRepository.findByPositionId(positionId);
        skillList.sort(skillComparator);
        return skillList;
    }
}
