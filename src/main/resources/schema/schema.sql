DROP TABLE IF EXISTS plan;

DROP SEQUENCE IF EXISTS plan_seq;

DROP TABLE IF EXISTS insurance_case;

DROP SEQUENCE IF EXISTS case_seq;

CREATE SEQUENCE IF NOT EXISTS case_seq;


CREATE TABLE insurance_case (
    id bigint DEFAULT nextval('case_seq') PRIMARY KEY,
    name varchar(255) NOT NULL
);


CREATE SEQUENCE IF NOT EXISTS plan_seq;

CREATE TABLE plan (
    id BIGINT PRIMARY KEY DEFAULT nextval('plan_seq'),
    name VARCHAR(255) NOT NULL,
    status VARCHAR(10) CHECK (status IN ('OPEN', 'CLOSED')),
    case_id BIGINT REFERENCES insurance_case(id)
);


