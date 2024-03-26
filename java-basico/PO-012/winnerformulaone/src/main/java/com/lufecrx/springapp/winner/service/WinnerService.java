package com.lufecrx.springapp.winner.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lufecrx.springapp.winner.entity.Winner;

import jakarta.annotation.PostConstruct;

@Service
public class WinnerService {
    private List<Winner> winners = new ArrayList<>();

    @PostConstruct
    public void init() {
        try {
            String csvFile = "src/main/resources/pilotos.csv";
            String line = "";
            String delimiter = ";";

            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                br.readLine(); // Ler a primeira linha (cabecalho)

                while ((line = br.readLine()) != null) {
                    String[] data = line.split(delimiter);
                    String country = data[0];
                    String pilotName = data[1];
                    int victories = Integer.parseInt(data[2]);

                    winners.add(new Winner(country, pilotName, victories));
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o ficheiro");
        }
    }

    public List<Winner> getAllWinners() {
        return winners;
    }

    public List<Winner> getWinnersByCountry(String country) {
        List<Winner> winnersByCountry = new ArrayList<>();

        for (Winner winner : winners) {
            if (winner.getCountry().equals(country)) {
                winnersByCountry.add(winner);
            }
        }

        return winnersByCountry;
    }

    //Retornar  o Top 5 vencedores (em ordem decrescente)
    public List<Winner> getTopWinners(int top) {
        List<Winner> topWinners = new ArrayList<>(winners);
        topWinners.sort(Comparator.comparingInt(Winner::getVictories).reversed());
        
        return topWinners.subList(0, Math.min(top, topWinners.size()));
    }

    // Retornar o número de vitórias por país (em ordem decrescente) 
    public Map<String, Integer> calculateVictoriesByCountry() {
        // Mapa para armazenar o número de vitórias por país
        Map<String, Integer> victoriesByCountry = new HashMap<>();

        // Contar o número de vitórias para cada país
        for (Winner winner : winners) {
            String country = winner.getCountry();
            int victories = winner.getVictories();
            victoriesByCountry.put(country, victoriesByCountry.getOrDefault(country, 0) + victories);
        }

        return victoriesByCountry;
    }

    // Retornar a média de vitórias por país (número de vitórias dividido pelo número de vencedores, em ordem decrescente)
    public Map<String, Double> calculateAverageVictoriesByCountry() {
        // Mapa para armazenar a média de vitórias por pais
        Map<String, Double> averageVictoriesByCountry = new HashMap<>();

        // Mapa com o número de vitórias de cada país
        Map<String, Integer> victoriesByCountry = calculateVictoriesByCountry();
        
        // Calcular a média de vitórias para cada pais
        for (Map.Entry<String, Integer> entry : victoriesByCountry.entrySet()) {
            String country = entry.getKey();
            int totalVictories = entry.getValue();

            // Calcular a média de vitórias para cada pais
            double averageVictories = (double) totalVictories / winners.stream().filter(winner -> winner.getCountry().equals(country)).count();
            averageVictoriesByCountry.put(country, averageVictories);
        }

        return averageVictoriesByCountry;
    }


    // Ordenar a média de vitórias por pais (em ordem decrescente)
    public Map<String, Double> getAverageVictoriesByCountry() {
        Map<String, Double> averageVictoriesByCountry = calculateAverageVictoriesByCountry();

        List<Map.Entry<String, Double>> sortedList = new ArrayList<>(averageVictoriesByCountry.entrySet());
        sortedList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        Map<String, Double> sortedAverageVictoriesByCountry = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : sortedList) {
            sortedAverageVictoriesByCountry.put(entry.getKey(), entry.getValue());
        }

        return sortedAverageVictoriesByCountry;
    }

    // Ordenar o numero de vitórias por pais (em ordem decrescente)
    public Map<String, Integer> getVictoriesByCountry() {
        Map<String, Integer> victoriesByCountry = calculateVictoriesByCountry();

        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(victoriesByCountry.entrySet());
        sortedList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        Map<String, Integer> sortedVictoriesByCountry = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : sortedList) {
            sortedVictoriesByCountry.put(entry.getKey(), entry.getValue());
        }

        return sortedVictoriesByCountry;
    }


    
}
