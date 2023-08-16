  create table wanted.member (
    id                      bigint              not null            auto_increment,
    email                   varchar(255)        unique,
    password                varchar(255)        not null,
    name                    varchar(255),
    status                  varchar(255),
    created_at              datetime(6)         not null,
    updated_at              datetime(6),
    primary key (id)
  )
