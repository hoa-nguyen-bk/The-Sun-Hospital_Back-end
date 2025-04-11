INSERT INTO specialization (id, name, description) VALUES
                                                       (1, 'Cardiology', 'Heart-related specialization'),
                                                       (2, 'Dermatology', 'Skin-related specialization');

INSERT INTO clinic (id, info, address, image, operate_times, link_map)
VALUES (
           1,
           'Main clinic in city center',
           '123 Nguyen Trai, District 1, HCMC',
           'clinic1.jpg',
           JSON_OBJECT('monday', '08:00-17:00', 'tuesday', '08:00-17:00'),
           'https://maps.google.com/?q=123+Nguyen+Trai'
       );

INSERT INTO clinic_specialization (id, clinic_id, specialization_id)
VALUES (1, 1, 1), (2, 1, 2);
INSERT INTO role (id, name) VALUES
                                (1, 'ROLE_ADMIN'),
                                (2, 'ROLE_DOCTOR'),
                                (3, 'ROLE_PATIENT');

-- Admin
INSERT INTO user (id, first_name, last_name, email, password, role_id)
VALUES (1, 'Admin', 'User', 'admin@sunhospital.com', 'admin123', 1);





-- Doctor
INSERT INTO user (
    id, first_name, last_name, email, password, role_id, specialization_id, clinic_id, years_experience, level
)
VALUES (
           2, 'Dr.', 'Strange', 'strange@sunhospital.com', 'doctor123', 2, 1, 1, 10, 'Senior'
       );

-- Patient
INSERT INTO user (
    id, first_name, last_name, email, password, role_id, phone, date_of_birth
)
VALUES (
           3, 'John', 'Doe', 'john.doe@example.com', 'patient123', 3, '0912345678', '1990-01-01'
       );

INSERT INTO appointment (
    id, patient_id, doctor_id, book_time, status, clinic_id, total, code, status_request
)
VALUES (
           1, 3, 2, '2025-04-08 10:00:00', 'Scheduled', 1, 500000.00, 'APT001', 'Pending'
       );

INSERT INTO time_allocation (
    id, doctor_id, work_hours, availability, status, date
)
VALUES (
           1, 2, '08:00-17:00', JSON_ARRAY('08:00', '09:00', '10:00'), 'Available', '2025-04-08'
       );

INSERT INTO review (
    id, doctor_id, appointment_id, patient_id, star, recommended, note
)
VALUES (
           1, 2, 1, 3, 5, TRUE, 'Very professional and friendly.'
       );
