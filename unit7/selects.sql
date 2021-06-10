получение ФИО всех клиентов, у которых есть автомобиль марки BMW
select uuid, first_name, last_name, middle_name, city
from client cl
inner join car c ON cl.id = c.client_id
where c.model_id IN (select cm.id from car_model cm where cm.brand = 'BMW')

получение автомобилей по данным клиента
select c.licence_plate, c.region
from car c
inner join client cl ON cl.id = c.client_id
where cl.first_name = 'Иван'
  and cl.last_name = 'Иванов'

select c.licence_plate, c.region
from car c
inner join client cl ON cl.id = c.client_id
where cl.uuid = 'a62eac80-2098-4647-af97-760ec4bb1ff1' удаление всех записей об автомобилях у определенного клиента
delete
from car c
where client_id in (SELECT cl.id FROM client cl where uuid = '901da328-c9ed-11eb-b8bc-0242ac130003');

изменение каких-либо данных в таблицах
UPDATE public.car
SET model_id = (select cm.id from car_model cm where brand = 'Volkswagen' and model = 'Golf')
WHERE client_id = (select cl.id FROM client cl WHERE cl.uuid = 'd6e5a5dc-89a9-4e2a-982d-8460f8d7280e');

получение количества автомобилей по каждому клиенту (на экран вывести id клиента, ФИО и количество автомобилей;
отсортировать результат по ФИО в алфавитном порядке)
select c.id,
       c.last_name,
       c.first_name,
       c.middle_name,
       (SELECT COUNT(*) FROM car c2 WHERE c.id = c2.client_id) as count_car
from client c
order by (last_name, first_name, middle_name)


переделать предыдущий запрос таким образом, чтобы на экран вывелись клиенты, у которых больше 2 машин

select c.id, c.last_name, c.first_name, c.middle_name, COUNT(*) as count_car
FROM car c2
         inner join client c on c2.client_id = c.id
GROUP BY c2.client_id, c.id, c.last_name, c.first_name, c.middle_name
having COUNT(*) > 1