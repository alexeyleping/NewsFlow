package com.example.news.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "flow")
public class Flow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "textflow")
    private String textFlow;

    @OneToOne
    @JoinColumn(name = "source_id", referencedColumnName = "id")
    private Source source;

    @OneToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    public Flow() {
    }

    public Flow(Long id, String textFlow, Source source, Subject subject) {
        this.id = id;
        this.textFlow = textFlow;
        this.source = source;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextFlow() {
        return textFlow;
    }

    public void setTextFlow(String textFlow) {
        this.textFlow = textFlow;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Subject getSubjectMatter() {
        return subject;
    }

    public void setSubjectMatter(Subject subject) {
        this.subject = subject;
    }
}
