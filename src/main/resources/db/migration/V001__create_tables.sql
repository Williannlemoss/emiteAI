create table if not exists transport (
                                         id  bigserial not null,
                                         created_at timestamp,
                                         is_sent boolean default false,
                                         trip_completed timestamp,
                                         updated_at timestamp,
                                         primary key (id)
);

create table if not exists transport_order (
                                               id  bigserial not null,
                                               created_at timestamp,
                                               is_sent boolean default false,
                                               updated_at timestamp,
                                               transport_id int8,
                                               primary key (id),
                                               CONSTRAINT fk_transport_order
                                                   FOREIGN KEY(transport_id)
                                                       REFERENCES transport(id)
);

create table if not exists purchase (
                                        id  bigserial not null,
                                        created_at timestamp,
                                        is_sent boolean default false,
                                        total_order_amount float8,
                                        updated_at timestamp,
                                        transport_order_id int8,
                                        primary key (id),
                                        CONSTRAINT fk_transport_order
                                            FOREIGN KEY(transport_order_id)
                                                REFERENCES transport_order(id)
);

create table if not exists product (
                                       id  bigserial not null,
                                       created_at timestamp,
                                       name varchar(255),
                                       price float8,
                                       updated_at timestamp,
                                       purchase_id int8,
                                       primary key (id),
                                       CONSTRAINT fk_purchase
                                           FOREIGN KEY(purchase_id)
                                               REFERENCES purchase(id)
);







