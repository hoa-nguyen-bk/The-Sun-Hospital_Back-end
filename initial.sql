CREATE DATABASE the_sun_hospital;
use the_sun_hospital;
CREATE TABLE role (
    id INT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE specialization (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    description TEXT
);

CREATE TABLE clinic (
    id INT PRIMARY KEY,
    info TEXT,
    address TEXT,
    image TEXT,
    operate_times TEXT,
    link_map TEXT,
    work_hours TEXT
);

CREATE TABLE clinic_specialization (
    id INT PRIMARY KEY,
    clinic_id INT,
    specialization_id INT
);

CREATE TABLE symptoms (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    specialization_id INT
);

CREATE TABLE services (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    specialization_id INT,
    price DECIMAL(10, 2)
);

CREATE TABLE user (
    id INT PRIMARY KEY,
    specialization_id INT,
    clinic_id INT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    date_of_birth DATE,
    address TEXT,
    contact VARCHAR(255),
    role_id INT,
    medical_history TEXT,
    avatar TEXT,
    favourites TEXT,
    blood_group VARCHAR(10),
    password VARCHAR(255),
    contact_information TEXT,
    years_experience INT,
    level VARCHAR(255),
    status VARCHAR(255),
    languages TEXT,
    awards TEXT,
    description TEXT
);

CREATE TABLE appointment (
    id INT PRIMARY KEY,
    patient_id INT,
    doctor_id INT,
    book_time DATETIME,
    status VARCHAR(255),
    specialization TEXT,
    symptoms TEXT,
    note TEXT,
    attachment TEXT,
    total DECIMAL(10, 2),
    discount DECIMAL(10, 2),
    code VARCHAR(255),
    status_request VARCHAR(255),
    clinic_id INT,
    services TEXT
);

CREATE TABLE time_allocation (
    id INT PRIMARY KEY,
    doctor_id INT,
    work_hours TEXT,
    availability TEXT,
    status VARCHAR(255),
    date DATE
);

CREATE TABLE review (
    id INT PRIMARY KEY,
    doctor_id INT,
    appointment_id INT,
    patient_id INT,
    star INT,
    recommended BOOLEAN,
    note TEXT
);

-- clinic_specialization
ALTER TABLE clinic_specialization
ADD CONSTRAINT fk_clinic_specialization_clinic
FOREIGN KEY (clinic_id) REFERENCES clinic(id);

ALTER TABLE clinic_specialization
ADD CONSTRAINT fk_clinic_specialization_specialization
FOREIGN KEY (specialization_id) REFERENCES specialization(id);

-- symptoms
ALTER TABLE symptoms
ADD CONSTRAINT fk_symptoms_specialization
FOREIGN KEY (specialization_id) REFERENCES specialization(id);

-- services
ALTER TABLE services
ADD CONSTRAINT fk_services_specialization
FOREIGN KEY (specialization_id) REFERENCES specialization(id);

-- user
ALTER TABLE user
ADD CONSTRAINT fk_user_role
FOREIGN KEY (role_id) REFERENCES role(id);

ALTER TABLE user
ADD CONSTRAINT fk_user_specialization
FOREIGN KEY (specialization_id) REFERENCES specialization(id);

ALTER TABLE user
ADD CONSTRAINT fk_user_clinic
FOREIGN KEY (clinic_id) REFERENCES clinic(id);

-- appointment
ALTER TABLE appointment
ADD CONSTRAINT fk_appointment_patient
FOREIGN KEY (patient_id) REFERENCES user(id);

ALTER TABLE appointment
ADD CONSTRAINT fk_appointment_doctor
FOREIGN KEY (doctor_id) REFERENCES user(id);

ALTER TABLE appointment
ADD CONSTRAINT fk_appointment_clinic
FOREIGN KEY (clinic_id) REFERENCES clinic(id);

-- time_allocation
ALTER TABLE time_allocation
ADD CONSTRAINT fk_time_allocation_doctor
FOREIGN KEY (doctor_id) REFERENCES user(id);

-- review
ALTER TABLE review
ADD CONSTRAINT fk_review_doctor
FOREIGN KEY (doctor_id) REFERENCES user(id);

ALTER TABLE review
ADD CONSTRAINT fk_review_appointment
FOREIGN KEY (appointment_id) REFERENCES appointment(id);

ALTER TABLE review
ADD CONSTRAINT fk_review_patient
FOREIGN KEY (patient_id) REFERENCES user(id);
