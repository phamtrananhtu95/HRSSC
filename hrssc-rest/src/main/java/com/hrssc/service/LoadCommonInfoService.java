package com.hrssc.service;

import com.hrssc.entities.Position;
import com.hrssc.entities.Skill;

import java.util.List;

public interface LoadCommonInfoService {

    List<Skill> loadAllSkill();

    List<Position> loadAllPosition();
}