  create table wanted.board (
    id              bigint              not null        auto_increment,
    member_id       bigint              not null,
    content         varchar(255),
    title           varchar(255),
    name            varchar(255),
    created_at      datetime(6)         not null,
    updated_at      datetime(6),
    primary key (id)
  )
