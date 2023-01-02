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
    @JoinColumn(name = "subjectmatter_id", referencedColumnName = "id")
    private SubjectMatter subjectMatter;

    public Flow() {
    }

    public Flow(Long id, String textFlow, Source source, SubjectMatter subjectMatter) {
        this.id = id;
        this.textFlow = textFlow;
        this.source = source;
        this.subjectMatter = subjectMatter;
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

    public SubjectMatter getSubjectMatter() {
        return subjectMatter;
    }

    public void setSubjectMatter(SubjectMatter subjectMatter) {
        this.subjectMatter = subjectMatter;
    }
}
