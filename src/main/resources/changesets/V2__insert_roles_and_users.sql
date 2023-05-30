insert into roles (role)
values ('ROLE_ADMIN'),
       ('ROLE_USER');

insert into users(name, username, password)
values ('admin', 'admin@gmail.com', '$2a$10$H3RYB46fQegjw2PZo0xeu.4R0wIY0ZDVvCLVHw1uD4DepmslJgn7e'),
       ('John', 'john@gmail.com', '$2a$10$vxgNoLa0CGICQ3NQBeX5EubFSj17UpbQlbwdZcOaCek/nNZmfxrfm');

insert into user_roles(user_id, role_id)
values (1, 1),
       (2, 2);



