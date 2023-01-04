package com.example.news.service;

import com.example.news.entity.Flow;
import com.example.news.entity.Source;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import com.opencsv.CSVWriter;

import java.io.IOException;

@Service
public class DischargeService {
    private final SourceService sourceService;
    private final FlowService flowService;
    private final SubjectService subjectService;

    public DischargeService(SourceService sourceService, FlowService flowService, SubjectService subjectService) {
        this.sourceService = sourceService;
        this.flowService = flowService;
        this.subjectService = subjectService;
    }

    @Scheduled(cron = "${interval-in-cron}")
    public void getQuantitySource() throws IOException {
        List<Source> listSource = sourceService.getAll();
        Set<Source> setSource = new HashSet<>(listSource);
        for (Source s : setSource) {
            List<Flow> flowList = flowService.findAllBySourceId(s.getId());
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
            try (
                Writer writer = Files.newBufferedWriter(Paths.get(s.getName()));
                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);
            )
            {
                String[] headerRecord = {s.getName()};
                csvWriter.writeNext(headerRecord);
                for (var entry : mapCountSubject.entrySet()){
                    List<String[]> str = new ArrayList<>();
                    str.add(new String[] {entry.getKey(), String.valueOf(entry.getValue())});
                    csvWriter.writeAll(str);
                }
            }
            System.out.println(mapCountSubject);
        }
    }
}
