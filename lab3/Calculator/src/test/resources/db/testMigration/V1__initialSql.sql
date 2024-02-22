CREATE TABLE IF NOT EXISTS public."operations"
(
    "operation_id" integer NOT NULL,
    first_num character varying COLLATE pg_catalog."default" NOT NULL,
    first_num_system character varying COLLATE pg_catalog."default" NOT NULL,
    second_num character varying COLLATE pg_catalog."default" NOT NULL,
    second_num_system character varying COLLATE pg_catalog."default" NOT NULL,
    date date NOT NULL,
    "time" time without time zone NOT NULL,
    operation_type character varying COLLATE pg_catalog."default" NOT NULL,
    result character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "operations_pkey" PRIMARY KEY ("operation_id")
    )