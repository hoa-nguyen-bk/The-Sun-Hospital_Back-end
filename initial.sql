-- Create database with full Unicode support
CREATE
DATABASE the_sun_hospital
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE
the_sun_hospital;

-- Table: role
CREATE TABLE role (
    id INT PRIMARY KEY,
    name       VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at DATETIME DEFAULT NULL
);

-- Table: specialization
CREATE TABLE specialization (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at  DATETIME DEFAULT NULL
);

-- Table: clinic
CREATE TABLE clinic (
    id INT PRIMARY KEY,
    info TEXT,
    address TEXT,
    image TEXT,
    operate_times JSON,
    link_map TEXT,
    created_at    DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at    DATETIME DEFAULT NULL
);

-- Table: clinic_specialization
CREATE TABLE clinic_specialization (
    id INT PRIMARY KEY,
    clinic_id INT,
    specialization_id INT,
    created_at        DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at        DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at        DATETIME DEFAULT NULL
);

-- Table: symptoms
CREATE TABLE symptoms (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    specialization_id INT,
    created_at        DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at        DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at        DATETIME DEFAULT NULL
);

-- Table: services
CREATE TABLE services (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    specialization_id INT,
    price      DECIMAL(10, 2),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at DATETIME DEFAULT NULL
);

-- Table: user
CREATE TABLE user (
    id INT PRIMARY KEY,
    specialization_id INT,
    clinic_id INT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    date_of_birth DATE,
    address TEXT,
    email       VARCHAR(255),
    phone       VARCHAR(255),
    role_id INT,
    medical_history TEXT,
    avatar TEXT,
    favourites  JSON,
    blood_group VARCHAR(10),
    password VARCHAR(255),
    years_experience INT,
    level VARCHAR(255),
    status VARCHAR(255),
    languages TEXT,
    awards TEXT,
    description TEXT,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at  DATETIME DEFAULT NULL
);

-- Table: appointment
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
    services   TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at DATETIME DEFAULT NULL
);

-- Table: time_allocation
CREATE TABLE time_allocation (
    id INT PRIMARY KEY,
    doctor_id INT,
    work_hours TEXT,
    availability JSON,
    status VARCHAR(255),
    date         DATE,
    created_at   DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at   DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at   DATETIME DEFAULT NULL
);

-- Table: review
CREATE TABLE review (
    id INT PRIMARY KEY,
    doctor_id INT,
    appointment_id INT,
    patient_id INT,
    star INT,
    recommended BOOLEAN,
    note       TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at DATETIME DEFAULT NULL
);

-- Foreign keys
ALTER TABLE clinic_specialization
ADD CONSTRAINT fk_clinic_specialization_clinic
FOREIGN KEY (clinic_id) REFERENCES clinic(id);

ALTER TABLE clinic_specialization
ADD CONSTRAINT fk_clinic_specialization_specialization
FOREIGN KEY (specialization_id) REFERENCES specialization(id);

ALTER TABLE symptoms
ADD CONSTRAINT fk_symptoms_specialization
FOREIGN KEY (specialization_id) REFERENCES specialization(id);

ALTER TABLE services
ADD CONSTRAINT fk_services_specialization
FOREIGN KEY (specialization_id) REFERENCES specialization(id);

ALTER TABLE user
ADD CONSTRAINT fk_user_role
FOREIGN KEY (role_id) REFERENCES role(id);

ALTER TABLE user
ADD CONSTRAINT fk_user_specialization
FOREIGN KEY (specialization_id) REFERENCES specialization(id);

ALTER TABLE user
ADD CONSTRAINT fk_user_clinic
FOREIGN KEY (clinic_id) REFERENCES clinic(id);

ALTER TABLE appointment
ADD CONSTRAINT fk_appointment_patient
FOREIGN KEY (patient_id) REFERENCES user(id);

ALTER TABLE appointment
ADD CONSTRAINT fk_appointment_doctor
FOREIGN KEY (doctor_id) REFERENCES user(id);

ALTER TABLE appointment
ADD CONSTRAINT fk_appointment_clinic
FOREIGN KEY (clinic_id) REFERENCES clinic(id);

ALTER TABLE time_allocation
ADD CONSTRAINT fk_time_allocation_doctor
FOREIGN KEY (doctor_id) REFERENCES user(id);

ALTER TABLE review
ADD CONSTRAINT fk_review_doctor
FOREIGN KEY (doctor_id) REFERENCES user(id);

ALTER TABLE review
ADD CONSTRAINT fk_review_appointment
FOREIGN KEY (appointment_id) REFERENCES appointment(id);

ALTER TABLE review
ADD CONSTRAINT fk_review_patient
FOREIGN KEY (patient_id) REFERENCES user(id);
