create table source
    (
    id bigint primary key,
    name text
    );
create table subject
(
    id bigint primary key,
    name text
);
create table flow
(
    id bigint primary key,
    textflow text,
    source_id bigint REFERENCES source (id),
    subject_id bigint REFERENCES subject (id)
);

