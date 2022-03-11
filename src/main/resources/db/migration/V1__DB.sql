create sequence basket_seq start 1 increment 1;
create sequence category_seq start 1 increment 1;
create sequence order_details_seq start 1 increment 1;
create sequence order_seq start 1 increment 1;
create sequence product_seq start 1 increment 1;
create sequence user_seq start 1 increment 1;

create table baskets (
    id int8 not null,
    user_id int8,
    primary key (id)
);

create table baskets_products (
    basket_id int8 not null,
    product_id int8 not null
);

create table categories (
    id int8 not null,
    title varchar(255),
    primary key (id)
);

create table orders (
    id int8 not null,
    address varchar(255),
    created timestamp,
    status varchar(255),
    sum numeric(19, 2),
    update timestamp,
    user_id int8,
    primary key (id)
);

create table orders_details (
    id int8 not null,
    amount numeric(19, 2),
    price numeric(19, 2),
    order_id int8,
    product_id int8,
    details_id int8 not null,
    primary key (id)
);

create table products (
    id int8 not null,
    price numeric(19, 2),
    title varchar(255),
    primary key (id)
);

create table products_categories (
    product_id int8 not null,
    category_id int8 not null
);

create table users (
    id int8 not null,
    archive boolean not null,
    email varchar(255),
    name varchar(255),
    password varchar(255),
    role varchar(255),
    basket_id int8,
    primary key (id)
);

alter table if exists orders_details
add constraint UK_kk6y3pyhjt6kajomtjbhsoajo unique (details_id);

alter table if exists baskets
add constraint FK87s17cinc4wkx0taas5nh0s8h foreign key (user_id) references users;

alter table if exists baskets_products
add constraint FK81mtboy4cahxvtv86apcin43c foreign key (product_id) references products;

alter table if exists baskets_products
add constraint FKribumfhgwuqeoqhq8gs88smee foreign key (basket_id) references baskets;

alter table if exists orders
add constraint FK32ql8ubntj5uh44ph9659tiih foreign key (user_id) references users;

alter table if exists orders_details
add constraint FK5o977kj2vptwo70fu7w7so9fe foreign key (order_id) references orders;

alter table if exists orders_details
add constraint FKs0r9x49croribb4j6tah648gt foreign key (product_id) references products;

alter table if exists orders_details
add constraint FKgvp1k7a3ubdboj3yhnawd5m1p foreign key (details_id) references orders_details;

alter table if exists products_categories
add constraint FKqt6m2o5dly3luqcm00f5t4h2p foreign key (category_id) references categories;

alter table if exists products_categories
add constraint FKtj1vdea8qwerbjqie4xldl1el foreign key (product_id) references products;

alter table if exists users
add constraint FKommxbvym8k63qbwomj0ikj8us foreign key (basket_id) references baskets;