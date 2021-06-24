CREATE TABLE public.car_model
(
    id    serial      NOT NULL,
    brand varchar(50) NOT NULL,
    model varchar(50) NOT NULL,
    CONSTRAINT car_model_brand_check CHECK (brand <> ''),
    CONSTRAINT car_model_model_check CHECK (model <> ''),
    CONSTRAINT car_model_pkey PRIMARY KEY (id),
    UNIQUE (brand, model)
);

CREATE TABLE public.car
(
    id            serial     NOT NULL,
    licence_plate varchar(6) NOT NULL,
    region        int        NOT NULL,
    model_id      int REFERENCES car_model ON DELETE CASCADE,
    client_id     int REFERENCES client ON DELETE CASCADE,
    CONSTRAINT car_licence_plate_region UNIQUE (licence_plate, region),
    CONSTRAINT car_pkey PRIMARY KEY (id),
    CONSTRAINT car_region_check CHECK (region >= 1 AND region <= 777),
    FOREIGN KEY (model_id) REFERENCES car_model (id),
    FOREIGN KEY (client_id) REFERENCES client (id)
);

CREATE TABLE public.client
(
    id          serial      NOT NULL,
    uuid        UUID        NOT NULL,
    first_name  varchar(50) NOT NULL,
    last_name   varchar(50) NOT NULL,
    middle_name varchar(50),
    city        varchar(50),
    CONSTRAINT client_first_name_check CHECK (first_name <> ''),
    CONSTRAINT client_last_name_check CHECK (last_name <> ''),
    CONSTRAINT client_pkey PRIMARY KEY (id)
);

CREATE TABLE public.insurance
(
    id         serial      NOT NULL,
    "number"   varchar(20) NOT NULL,
    start_date timestamp   NOT NULL,
    end_date   timestamp   NOT NULL,
    car_id     int REFERENCES car ON DELETE CASCADE,
    CONSTRAINT insurance_number_key UNIQUE (number),
    CONSTRAINT insurance_pkey PRIMARY KEY (id),
    FOREIGN KEY (car_id) REFERENCES car (id)
);

CREATE TABLE public.client_insurance
(
    id           serial NOT NULL,
    client_id    int,
    insurance_id int,
    CONSTRAINT client_insurance_pkey PRIMARY KEY (id)
);