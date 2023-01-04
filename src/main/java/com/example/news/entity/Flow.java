package com.example.news.entity;

import jakarta.persistence.*;

import java.util.Objects;

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

    @ManyToOne
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flow flow = (Flow) o;
        return Objects.equals(id, flow.id) && Objects.equals(textFlow, flow.textFlow) && Objects.equals(source, flow.source) && Objects.equals(subject, flow.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, textFlow, source, subject);
    }

    @Override
    public String toString() {
        return "Flow{" +
                "id=" + id +
                ", textFlow='" + textFlow + '\'' +
                ", source=" + source +
                ", subject=" + subject +
                '}';
    }
}
