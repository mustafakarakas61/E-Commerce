-- password: admin
insert into users(id, email, first_name, last_name, city, address, phone_number, post_index, activation_code, active, password, password_reset_code, provider)
    values(1, 'admin@gmail.com', 'Admin', 'Admin', null, null, null, null, null, true, 'iamaadmin', null, 'LOCAL');

insert into users(id, email, first_name, last_name, city, address, phone_number, post_index, activation_code, active, password, password_reset_code, provider)
    values(2, 'mustafa@gmail.com', 'Mustafa', 'Karakas', 'İstanbul', 'Bağcılar/İstanbul', '0534567890', '34200', null, true, '616161', null, 'LOCAL');

insert into users(id, email, first_name, last_name, city, address, phone_number, post_index, activation_code, active, password, password_reset_code, provider)
    values(3, 'batu@gmail.com', 'Batuhan', 'Yalcinturk', 'İstanbul', 'Beylikdüzü/İstanbul', '0531367890', '34100', null, true, 'mypass123', null, 'LOCAL');

insert into users(id, email, first_name, last_name, city, address, phone_number, post_index, activation_code, active, password, password_reset_code, provider)
    values(4, 'ahmet@gmail.com', 'Ahmet', 'Kupa', 'İzmir', 'Merkez/İzmir', '0547367890', '35250', null, true, 'mypassdoga', null, 'LOCAL');

insert into users(id, email, first_name, last_name, city, address, phone_number, post_index, activation_code, active, password, password_reset_code, provider)
    values(5, 'osman@gmail.com', 'Osman', 'Cayan', 'İstanbul', 'Beylikdüzü/İstanbul', '0531627890', '34100', null, true, 'mypassahmet', null, 'LOCAL');

insert into user_role (user_id, roles)
    values (1, 'ADMIN');

insert into user_role (user_id, roles)
    values (2, 'USER');

insert into user_role (user_id, roles)
    values (3, 'USER');

insert into user_role (user_id, roles)
    values (4, 'USER');

insert into user_role (user_id, roles)
    values (5, 'USER');
