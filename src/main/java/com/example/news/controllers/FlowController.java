package com.example.news.controllers;

import com.example.news.entity.Flow;
import com.example.news.service.FlowService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flow")
public class FlowController {
    private final FlowService flowService;

    public FlowController(FlowService flowService) {
        this.flowService = flowService;
    }

    @GetMapping("/{id}")
    public Flow getSource(@PathVariable Long id){
        return flowService.getFlow(id);
    }

    @GetMapping
    public Page<Flow> getFlowByParams(@RequestParam(required = false) Long source,
                                      @RequestParam(required = false) Long subjectMatter,
                                      @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                      @RequestParam(value = "page", defaultValue = "0") Integer page) {
        return flowService.getFlowAnyParams(source, subjectMatter, limit, page);
    }
}