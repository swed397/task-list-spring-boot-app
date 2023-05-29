create table if not exists users
(
    id       bigserial primary key,
    name     varchar not null,
    username varchar not null unique,
    password varchar not null
);

create table if not exists roles
(
    id   bigserial primary key,
    role varchar not null unique
);


create table if not exists user_roles
(
    id      bigserial primary key,
    user_id bigint not null,
    role_id bigint not null,
    constraint fk_users_roles_users foreign key (user_id) references users (id),
    constraint fk_roles_users_users foreign key (role_id) references roles (id)
);