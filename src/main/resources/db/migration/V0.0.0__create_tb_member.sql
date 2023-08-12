  create table member (
    id                      bigint               not null            auto_increment,
    created_at              datetime(6),
    updated_at              datetime(6),
    email                   varchar(255),
    password                varchar(255),
    status                  varchar(255),
    primary key (id)
  )
