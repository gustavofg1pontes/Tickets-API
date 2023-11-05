CREATE TABLE cv_events
(
    id           uuid      not null primary key,
    name         varchar   not null,
    date         timestamp not null,
    max_guests   integer   not null,
    sold_tickets integer   not null default 0
);

comment on table cv_events is 'Tabela para armazenar eventos';