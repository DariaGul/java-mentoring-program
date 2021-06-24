insert into public.car_model (brand, model)
values ('Volkswagen', 'Golf');
insert into public.car_model (brand, model)
values ('Volkswagen', 'Polo');
insert into public.car_model (brand, model)
values ('Porsche', '911-carrera');
insert into public.car_model (brand, model)
values ('Subaru', 'IMPREZA');
insert into public.car_model (brand, model)
values ('Subaru', 'WRX');
insert into public.car_model (brand, model)
values ('Mitsubishi', 'GALANT');
insert into public.car_model (brand, model)
values ('Mitsubishi', 'COLT');
insert into public.car_model (brand, model)
values ('BMW', 'GRAN COUPE');
insert into public.car_model (brand, model)
values ('BMW', 'SEDAN');

insert into public.car (licence_plate, region, model_id, client_id)
values ('а001ро', 62, 1, 1);
insert into public.car (licence_plate, region, model_id, client_id)
values ('а001ро', 96, 2, 2);
insert into public.car (licence_plate, region, model_id, client_id)
values ('о166оо', 11, 9, 3);
insert into public.car (licence_plate, region, model_id, client_id)
values ('х767кр', 96, 3, 4);
insert into public.car (licence_plate, region, model_id, client_id)
values ('м112мм', 96, 8, 5);
insert into public.car (licence_plate, region, model_id, client_id)
values ('р909оо', 77, 8, 6);
insert into public.car (licence_plate, region, model_id, client_id)
values ('р111оо', 62, 9, 7);
insert into public.car (licence_plate, region, model_id, client_id)
values ('р111оо', 22, 9, 8);
insert into public.car (licence_plate, region, model_id, client_id)
values ('о121оо', 22, 8, 8);
insert into public.car (licence_plate, region, model_id, client_id)
values ('о121оо', 50, 9, 9);

insert into public.client (first_name, last_name, city, uuid)
values ('Джорно', 'Джованна', 'Неаполь', '901da328-c9ed-11eb-b8bc-0242ac130003');
insert into public.client (first_name, last_name, city, uuid)
values ('Джорно', 'Джованна', 'Неаполь', 'abc5ac4b-b22b-4bbe-8388-237ba8642700');
insert into public.client (first_name, last_name, city, uuid)
values ('Нимдок', 'Бенни', 'АМ', '5e1c857c-edce-4284-8ab7-6727874e9fc8');
insert into public.client (first_name, last_name, city, uuid)
values ('Джейн', 'Доу', 'Детроит', 'd6e5a5dc-89a9-4e2a-982d-8460f8d7280e');
insert into public.client (first_name, last_name, city, uuid)
values ('Кевин', 'Флинн', 'Система', '3858e828-1034-4310-bdb5-af9f6415f203');
insert into public.client (first_name, last_name, city, uuid)
values ('Джордж', 'Миллер', 'Осака', 'a2b8376d-63f5-43d9-ab07-cd9d7f069940');
insert into public.client (first_name, last_name, city, uuid)
values ('Дэвид', 'Уорт', 'Куб', '61bcf947-fb19-4a8d-bf18-8b29dbde99a3');
insert into public.client (first_name, last_name, city, uuid)
values ('Иван', 'Иванов', 'Рязань', 'a62eac80-2098-4647-af97-760ec4bb1ff1');
insert into public.client (first_name, last_name, city, uuid)
values ('Иван', 'Петров', 'Рязань', '18a7b5ef-6d90-451a-bcf3-1dc203737a3f');
insert into public.client (first_name, last_name, city, uuid)
values ('Джорджи', 'Денбро', 'Мен', 'a032f7fc-1b4a-46c6-9302-fe1c5ce3656b');
insert into public.client (first_name, last_name, city, uuid)
values ('Билл', 'Денбро', 'Мен', 'f3b65a03-3f59-4060-b55c-5de5a0e0a543');
insert into public.client (first_name, last_name, city, uuid)
values ('Уилл', 'Байерс', 'Хоукинс', 'a877e177-5a14-4889-bce3-f5b15113854b');
insert into public.client (first_name, last_name, city, uuid)
values ('Алексей', 'Кузнецов', 'Москва', '6da172ab-02bb-4b78-9bb0-acb26cd3a789');
insert into public.client (first_name, last_name, city, uuid)
values ('Андрей', 'Андреев', 'Москва', 'b1f03c60-24a8-49a2-aa4c-94658006aa44');

insert into public.insurance ("number", start_date, end_date, car_id)
values ('1234567890qqq', to_date('2014-08-28', 'YYYY-MM-DD'), to_date('2018-08-28', 'YYYY-MM-DD'), 1);
insert into public.insurance ("number", start_date, end_date, car_id)
values ('1234567891qqq', to_date('2018-01-28', 'YYYY-MM-DD'), to_date('2022-01-28', 'YYYY-MM-DD'), 2);
insert into public.insurance ("number", start_date, end_date, car_id)
values ('1234567892qqq', to_date('2010-09-11', 'YYYY-MM-DD'), to_date('2014-09-11', 'YYYY-MM-DD'), 3);
insert into public.insurance ("number", start_date, end_date, car_id)
values ('1234567893qqq', to_date('2018-01-28', 'YYYY-MM-DD'), to_date('2022-01-28', 'YYYY-MM-DD'), 4);
insert into public.insurance ("number", start_date, end_date, car_id)
values ('1234567894qqq', to_date('2020-01-28', 'YYYY-MM-DD'), to_date('2024-01-28', 'YYYY-MM-DD'), 5);
insert into public.insurance ("number", start_date, end_date, car_id)
values ('1234567895qqq', to_date('2019-07-28', 'YYYY-MM-DD'), to_date('2023-07-28', 'YYYY-MM-DD'), 6);
insert into public.insurance ("number", start_date, end_date, car_id)
values ('1234567896qqq', to_date('2019-05-24', 'YYYY-MM-DD'), to_date('2023-05-24', 'YYYY-MM-DD'), 7);
insert into public.insurance ("number", start_date, end_date, car_id)
values ('1234567897qqq', to_date('2010-05-24', 'YYYY-MM-DD'), to_date('2014-05-24', 'YYYY-MM-DD'), 8);
insert into public.insurance ("number", start_date, end_date, car_id)
values ('1234567898qqq', to_date('2012-05-24', 'YYYY-MM-DD'), to_date('2016-05-24', 'YYYY-MM-DD'), 9);


insert into public.client_insurance (client_id, insurance_id)
values (13, 1);
insert into public.client_insurance (client_id, insurance_id)
values (14, 1);
insert into public.client_insurance (client_id, insurance_id)
values (15, 5);
insert into public.client_insurance (client_id, insurance_id)
values (16, 7);
insert into public.client_insurance (client_id, insurance_id)
values (17, 8);
