create table if not exists status
(
    id   bigserial primary key,
    name varchar not null
);

create table if not exists tasks
(
    id              bigserial primary key,
    title           varchar,
    description     varchar,
    status_id       bigint,
    expiration_date timestamp,
    constraint fk_task_status_id foreign key (status_id) references status (id)
);

create table if not exists users_tasks
(
    user_id bigint not null,
    task_id bigint not null,
    primary key (user_id, task_id),
    constraint fk_users_tasks_users foreign key (user_id) references users (id) on delete cascade on update no action,
    constraint fk_users_tasks_tasks foreign key (task_id) references tasks (id) on delete cascade on update no action
);




