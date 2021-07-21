CREATE
INDEX CONCURRENTLY IF NOT EXISTS car_licence_plate_region ON public.car(licence_plate, region);
CREATE
INDEX CONCURRENTLY IF NOT EXISTS car_brand ON public.car_model (brand);
CREATE
INDEX CONCURRENTLY IF NOT EXISTS car_brand_model ON public.car_model (brand, model);
CREATE
INDEX CONCURRENTLY IF NOT EXISTS insurance_number ON public.insurance (number);
CREATE
INDEX CONCURRENTLY IF NOT EXISTS insurance_end_date ON public.insurance (end_date);