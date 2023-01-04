package com.example.news.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private final DischargeService dischargeService;

    public CommandLineAppStartupRunner(DischargeService dischargeService) {
        this.dischargeService = dischargeService;
    }

    @Override
    public void run(String...args) throws Exception {
        dischargeService.getQuantitySource();

    }
}
