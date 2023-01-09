package com.example.news.service;

import com.example.news.entity.Flow;
import com.example.news.entity.Source;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import com.opencsv.CSVWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DischargeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DischargeService.class);
    private final SourceService sourceService;
    private final FlowService flowService;

    ExecutorService threadPool = Executors.newFixedThreadPool(8);
    public DischargeService(SourceService sourceService, FlowService flowService) {
        this.sourceService = sourceService;
        this.flowService = flowService;
    }

    @Scheduled(cron = "${upload.interval.cron}")
    public void getQuantitySource(){
        LOGGER.info("Начинаем выгрузку CSV...");
        List<Source> listSource = sourceService.getAll();
        Set<Source> setSource = new HashSet<>(listSource);
        for (Source s : setSource) {
            LOGGER.info("Начинаем выгрузку для источника " + s.getName());
            threadPool.execute(() -> doFlow(s.getId(), s.getName()));
    }
    }
    public Runnable doFlow(Long idSource, String nameSource){
        List<Flow> flowList = flowService.findAllBySourceId(idSource);
        Map<String, Long> mapCountSubject = flowList.stream()
                .map(n -> n.getSubject().getName())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        doTask(nameSource, mapCountSubject);
        LOGGER.info("Закончили выгрузку!");
        return null;
    }

    public void doTask(String nameFile, Map<String, Long> mapCountSubject){
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
