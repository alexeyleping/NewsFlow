create schema news;
create table news.source
    (
    id bigint primary key,
    name text
    );
create table news.subjectmatter
(
    id bigint primary key,
    name text
);
create table news.flow
(
    id bigint primary key,
    textflow text,
    source_id bigint REFERENCES source (id),
    subjectmatter_id bigint REFERENCES subjectmatter (id)
);

