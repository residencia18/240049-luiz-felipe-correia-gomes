package com.lufecrx.springapp.winner.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lufecrx.springapp.winner.entity.Winner;
import com.lufecrx.springapp.winner.service.WinnerService;

@RestController
public class WinnerController {
    @Autowired 
    private WinnerService winnerService;

    @GetMapping("/todos")
    public List<Winner> listAllWinners() {
        return winnerService.getAllWinners();
    }

    @GetMapping("/brasileiros")
    public List<Winner> listWinnersByCountry() {
        return winnerService.getWinnersByCountry("Brasil");
    }

    @GetMapping("/top5")
    public List<Winner> listTop5Winners() {
        return winnerService.getTopWinners(5);
    }

    @GetMapping("/top10")
    public List<Winner> listTop10Winners() {
        return winnerService.getTopWinners(10);
    }

    @GetMapping("/porpais")
    public Map<String, Integer> listVictoriesByCountry() {
        return winnerService.getVictoriesByCountry();
    }

    @GetMapping("/mediaporpais")
    public Map<String, Double> listAverageVictoriesByCountry() {
        return winnerService.getAverageVictoriesByCountry();
    }
}
