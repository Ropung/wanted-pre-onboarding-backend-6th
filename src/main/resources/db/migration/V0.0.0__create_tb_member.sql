  create table wanted.member (
    id                      bigint               not null            auto_increment,
    email                   varchar(255),
    password                varchar(255),
    name                    varchar(255),
    status                  varchar(255),
    created_at              datetime(6),
    updated_at              datetime(6),
    primary key (id)
  )
