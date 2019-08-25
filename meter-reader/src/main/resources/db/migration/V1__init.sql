CREATE SCHEMA IF NOT EXISTS meter;

CREATE SEQUENCE IF NOT EXISTS meter.meter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS meter.meter (
  id                bigint                          NOT NULL DEFAULT nextval('meter.meter_id_seq'::regclass),
  meter_id          uuid,
  initial_reading   numeric(10,3),
  current_reading   numeric(10, 3),
  active            boolean,
  created           timestamp without time zone,
  modified          timestamp without time zone,
  CONSTRAINT meter_pkey PRIMARY KEY (id),
  CONSTRAINT meter_ukey UNIQUE (meter_id)
);

CREATE INDEX IF NOT EXISTS idx_meter
    ON meter.meter USING btree
        (meter_id, active)
    TABLESPACE pg_default;

CREATE SEQUENCE IF NOT EXISTS meter.meter_reading_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS meter.meter_reading (
    id              bigint                          NOT NULL DEFAULT nextval('meter.meter_id_seq'::regclass),
    meter_id        uuid,
    created         timestamp without time zone,
    modified        timestamp without time zone,
    from_time       timestamp without time zone,
    to_time         timestamp without time zone,
    reading_value   numeric(10, 3),
    CONSTRAINT meter_reading_pkey PRIMARY KEY (id),
    CONSTRAINT fk_meter FOREIGN KEY (meter_id)
        REFERENCES meter.meter (meter_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);





