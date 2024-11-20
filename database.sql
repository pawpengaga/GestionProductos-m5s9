-- Creacion de la base de datos
CREATE DATABASE m5_gestion;

-- Creacion de las tablas
CREATE TABLE public.productos
(
    id serial NOT NULL,
    nombre character varying(50),
    precio double precision,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.productos
    OWNER to postgres;