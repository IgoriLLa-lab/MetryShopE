insert into products (id, price, title)
values (1, 1100.0, 'futer clear'),
       (2, 1100.0, 'futer red'),
       (3, 1600.0, 'futer green'),
       (4, 800.0, 'futer blue'),
       (5, 2500.0, 'futer white'),
       (6, 9000.0, 'futer black');

alter sequence product_seq restart with 6;