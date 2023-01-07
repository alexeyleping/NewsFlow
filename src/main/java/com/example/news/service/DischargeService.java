package com.example.news.service;

import com.example.news.entity.Flow;
import com.example.news.entity.Source;
import com.example.news.filters.TokenFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import com.opencsv.CSVWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class DischargeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenFilter.class);
    private final SourceService sourceService;
    private final FlowService flowService;

    ExecutorService threadPool = Executors.newFixedThreadPool(8);
    public DischargeService(SourceService sourceService, FlowService flowService) {
        this.sourceService = sourceService;
        this.flowService = flowService;
    }

    @Scheduled(cron = "${upload.interval.cron}")
    @Async
    public void getQuantitySource(){
        LOGGER.info("Начинаем выгрузку CSV... " + LocalDateTime.now());
        List<Source> listSource = sourceService.getAll();
        Set<Source> setSource = new HashSet<>(listSource);
        for (Source s : setSource) {
            LOGGER.info("Начинаем выгрузку для источника " + s.getName() + " " + LocalDateTime.now());
            threadPool.execute(() -> doFlow(s.getId(), s.getName()));
    }
    }


    public Runnable doFlow(Long idSource, String nameSource){
        List<Flow> flowList = flowService.findAllBySourceId(idSource);
        Map<String, Integer> mapCountSubject = new HashMap<>();
        for (Flow f : flowList
        ) {
            if (!mapCountSubject.containsKey(f.getSubject())) {
                mapCountSubject.put(f.getSubject().getName(), 0);
            }
        }
        for (var entry : mapCountSubject.entrySet()) {
            int i = 0;
            for (Flow f : flowList) {
                if (Objects.equals(f.getSubject().getName(), entry.getKey())) {
                    i++;
                    entry.setValue(i);
                }
            }
        }
        doTask(nameSource, mapCountSubject);
        LOGGER.info("Закончили выгрузку " + LocalDateTime.now());
        return null;
    }

    public void doTask(String nameFile, Map<String, Integer> mapCountSubject){
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(nameFile));
                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
        )
        {
            String[] headerRecord = {nameFile};
            csvWriter.writeNext(headerRecord);
            for (var entry : mapCountSubject.entrySet()){
                List<String[]> str = new ArrayList<>();
                str.add(new String[] {entry.getKey(), String.valueOf(entry.getValue())});
                csvWriter.writeAll(str);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
