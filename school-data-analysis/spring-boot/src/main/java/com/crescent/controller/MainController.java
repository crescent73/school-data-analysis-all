package com.crescent.controller;

import com.crescent.bean.BirthplaceAnalysis;
import com.crescent.bean.GenderAnalysis;
import com.crescent.bean.LevelAnalysis;
import com.crescent.bean.NationAnalysis;
import com.crescent.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/main")
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapping("/nation_analysis")
    List<NationAnalysis> nationAnalysis(){
        return mainService.nationAnalysis();
    }

    @RequestMapping("/birthplace_analysis")
    List<BirthplaceAnalysis> birthplaceAnalysis(){
        return mainService.birthplaceAnalysis();
    }

    @RequestMapping("/gender_analysis")
    List<GenderAnalysis> genderAnalysis(){
        return mainService.genderAnalysis();
    }

    @RequestMapping("/level_analysis")
    List<LevelAnalysis> levelAnalysis(){
        return mainService.levelAnalysis();
    }
}
