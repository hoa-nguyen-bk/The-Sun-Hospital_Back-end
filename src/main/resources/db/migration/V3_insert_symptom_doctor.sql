USE the_sun_hospital;

-- Insert Specializations
INSERT INTO specialization (name, description) VALUES
                                                   ('Skin & Hair Issues', 'Specialization in dermatology and hair-related conditions'),
                                                   ('Bone & Joint Problems', 'Orthopedic care for bones and joints'),
                                                   ('Female Sexual Health', 'Gynecological and sexual health for women'),
                                                   ('Diet & Nutrition Counselling', 'Nutritional guidance and diet planning'),
                                                   ('Sports Related Injuries', 'Treatment for sports-related injuries and rehabilitation');

-- Insert Symptoms
INSERT INTO symptoms (name, description, specialization_id) VALUES
-- Skin & Hair Issues (specialization_id: 1)
('Pimples/Acne', 'Inflammatory skin condition', 1),
('Reddish Skin', 'Skin irritation or redness', 1),
('Rashes', 'Skin eruptions or irritations', 1),
('Spots on skin', 'Pigmented skin spots', 1),
('Itching', 'Persistent skin irritation', 1),
('Dark circles', 'Under-eye discoloration', 1),
('Hairfall', 'Excessive hair loss', 1),
('Dry scalp/Dandruff', 'Scalp flaking and dryness', 1),
-- Bone & Joint Problems (specialization_id: 2)
('Back pain', 'Chronic or acute back discomfort', 2),
('Neck pain', 'Pain in cervical region', 2),
('Lower backpain', 'Lumbar region pain', 2),
('Elbow pain', 'Pain in elbow joint', 2),
('Joint pains', 'Generalized joint discomfort', 2),
('Sprain', 'Ligament injury', 2),
('Arthritis', 'Joint inflammation', 2),
('Heel pain', 'Pain in heel area', 2),
('Fracture', 'Bone break', 2),
('Left Knee Pain', 'Pain in left knee', 2),
('Right Knee Pain', 'Pain in right knee', 2),
('Left Shoulder Pain', 'Pain in left shoulder', 2),
('Right Shoulder Pain', 'Pain in right shoulder', 2),
-- Female Sexual Health (specialization_id: 3)
('Decreased sexual drive', 'Reduced libido', 3),
('Painful Sexual Intercourse', 'Discomfort during intercourse', 3),
('Painful Vaginal Contractions', 'Vaginal muscle spasms', 3),
('Decreased Sexual Response', 'Reduced sexual arousal', 3),
-- Diet & Nutrition Counselling (specialization_id: 4)
('Underweight', 'Low body mass index', 4),
('Overweight', 'High body mass index', 4),
('Diet for Diabetes', 'Nutritional plan for diabetes', 4),
('Diet for PCOD/PCOS', 'Diet for polycystic ovary syndrome', 4),
('Diet for Hypertension/High BP', 'Diet for high blood pressure', 4),
-- Sports Related Injuries (specialization_id: 5)
('Wrist injury', 'Injury to wrist', 5),
('Back injury', 'Spinal or muscular back injury', 5),
('Hand injury', 'Injury to hand', 5),
('Shoulder injury', 'Injury to shoulder', 5),
('Neck injury', 'Injury to neck', 5),
('Elbow injury', 'Injury to elbow', 5),
('Knee injury', 'Injury to knee', 5),
('Hip injury', 'Injury to hip', 5),
('Return To Play', 'Rehabilitation for sports return', 5),
('Foot injury', 'Injury to foot', 5),
('Shin splints', 'Lower leg pain from overuse', 5),
('Ligament tear', 'Tear in ligament', 5),
('Achillies tendon injury', 'Injury to Achilles tendon', 5),
('Joint dislocation', 'Joint displacement', 5),
('Groin pull', 'Strain in groin area', 5),
('Hamstring injuries', 'Hamstring muscle strain', 5),
('Muscle strain', 'General muscle strain', 5),
('Workout injuries', 'Injuries from exercise', 5),
('Footwear related queries', 'Issues from improper footwear', 5);

-- Insert Clinics
INSERT INTO clinic (info, address, image, operate_times, link_map) VALUES
                                                                       ('Premier dermatology and hair care center', '123 5th Ave, New York, NY 10001', '/images/clinic1.jpg', '{"Mon-Fri": "9:00-17:00", "Sat": "10:00-14:00", "Sun": "Closed"}', 'https://maps.google.com/?q=123+5th+Ave,+New+York,+NY'),
                                                                       ('Orthopedic and joint care facility', '456 Wilshire Blvd, Los Angeles, CA 90017', '/images/clinic2.jpg', '{"Mon-Fri": "8:00-18:00", "Sat-Sun": "Closed"}', 'https://maps.google.com/?q=456+Wilshire+Blvd,+Los+Angeles,+CA'),
                                                                       ('Women’s health and gynecology clinic', '789 Michigan Ave, Chicago, IL 60611', '/images/clinic3.jpg', '{"Mon-Thu": "9:00-16:00", "Fri": "9:00-12:00", "Sat-Sun": "Closed"}', 'https://maps.google.com/?q=789+Michigan+Ave,+Chicago,+IL'),
                                                                       ('Nutrition and wellness center', '101 Main St, Houston, TX 77002', '/images/clinic4.jpg', '{"Mon-Fri": "10:00-18:00", "Sat": "10:00-15:00", "Sun": "Closed"}', 'https://maps.google.com/?q=101+Main+St,+Houston,+TX'),
                                                                       ('Sports injury and rehab clinic', '321 Ocean Dr, Miami, FL 33139', '/images/clinic5.jpg', '{"Mon-Fri": "8:00-17:00", "Sat": "9:00-13:00", "Sun": "Closed"}', 'https://maps.google.com/?q=321+Ocean+Dr,+Miami,+FL');

-- Insert Clinic-Specialization Relationships
INSERT INTO clinic_specialization (clinic_id, specialization_id) VALUES
                                                                     (1, 1), -- New York clinic -> Skin & Hair Issues
                                                                     (2, 2), -- Los Angeles clinic -> Bone & Joint Problems
                                                                     (3, 3), -- Chicago clinic -> Female Sexual Health
                                                                     (4, 4), -- Houston clinic -> Diet & Nutrition Counselling
                                                                     (5, 5), -- Miami clinic -> Sports Related Injuries
                                                                     (1, 4), -- New York clinic also offers Diet & Nutrition
                                                                     (2, 5), -- Los Angeles clinic also offers Sports Injuries
                                                                     (3, 1), -- Chicago clinic also offers Skin & Hair Issues
                                                                     (4, 1), -- Houston clinic also offers Skin & Hair Issues
                                                                     (5, 2); -- Miami clinic also offers Bone & Joint Problems

-- Insert Doctors (Users)
INSERT INTO user (specialization_id, clinic_id, first_name, last_name, date_of_birth, address, email, phone, role_id, medical_history, avatar, favourites, blood_group, password, years_experience, level, status, languages, awards, description) VALUES
                                                                                                                                                                                                                                                       (1, 1, 'Emily', 'Johnson', '1978-03-15', '456 7th St, New York, NY 10002', 'emily.johnson@clinic.com', '212-555-0101', 1, NULL, '/avatars/emily.jpg', '{}', 'A+', 'hashed_pass1', 15, 'Senior', 'Active', 'English, Spanish', 'Best Dermatologist 2023', 'Expert in acne and hair loss treatments'),
                                                                                                                                                                                                                                                       (1, 1, 'Michael', 'Chen', '1985-07-22', '789 8th Ave, New York, NY 10003', 'michael.chen@clinic.com', '212-555-0102', 1, NULL, '/avatars/michael.jpg', '{}', 'O+', 'hashed_pass2', 10, 'Mid-level', 'Active', 'English, Mandarin', NULL, 'Specializes in skin rashes and pigmentation'),
                                                                                                                                                                                                                                                       (2, 2, 'Sarah', 'Davis', '1975-11-30', '101 Sunset Blvd, Los Angeles, CA 90028', 'sarah.davis@clinic.com', '310-555-0201', 1, NULL, '/avatars/sarah.jpg', '{}', 'B-', 'hashed_pass3', 20, 'Senior', 'Active', 'English', 'Orthopedic Excellence 2024', 'Expert in joint replacement'),
                                                                                                                                                                                                                                                       (2, 2, 'James', 'Wilson', '1980-04-12', '202 Rodeo Dr, Los Angeles, CA 90035', 'james.wilson@clinic.com', '310-555-0202', 1, NULL, '/avatars/james.jpg', '{}', 'AB+', 'hashed_pass4', 12, 'Mid-level', 'Active', 'English, French', NULL, 'Focuses on back and neck pain'),
                                                                                                                                                                                                                                                       (3, 3, 'Laura', 'Martinez', '1979-09-05', '303 Wacker Dr, Chicago, IL 60606', 'laura.martinez@clinic.com', '312-555-0301', 1, NULL, '/avatars/laura.jpg', '{}', 'A-', 'hashed_pass5', 18, 'Senior', 'Active', 'English, Spanish', 'Women’s Health Award 2022', 'Specializes in sexual health'),
                                                                                                                                                                                                                                                       (3, 3, 'Anna', 'Taylor', '1988-01-25', '404 Lake Shore Dr, Chicago, IL 60605', 'anna.taylor@clinic.com', '312-555-0302', 1, NULL, '/avatars/anna.jpg', '{}', 'O-', 'hashed_pass6', 8, 'Junior', 'Active', 'English', NULL, 'Focuses on gynecological issues'),
                                                                                                                                                                                                                                                       (4, 4, 'David', 'Brown', '1982-06-18', '505 Texas Ave, Houston, TX 77003', 'david.brown@clinic.com', '713-555-0401', 1, NULL, '/avatars/david.jpg', '{}', 'B+', 'hashed_pass7', 14, 'Senior', 'Active', 'English', 'Nutritionist of the Year 2023', 'Expert in diabetes diet plans'),
                                                                                                                                                                                                                                                       (4, 4, 'Lisa', 'Nguyen', '1990-02-10', '606 Main St, Houston, TX 77004', 'lisa.nguyen@clinic.com', '713-555-0402', 1, NULL, '/avatars/lisa.jpg', '{}', 'A+', 'hashed_pass8', 6, 'Junior', 'Active', 'English, Vietnamese', NULL, 'Specializes in weight management'),
                                                                                                                                                                                                                                                       (5, 5, 'Robert', 'Smith', '1977-12-03', '707 Collins Ave, Miami, FL 33140', 'robert.smith@clinic.com', '305-555-0501', 1, NULL, '/avatars/robert.jpg', '{}', 'O+', 'hashed_pass9', 16, 'Senior', 'Active', 'English, Spanish', 'Sports Medicine Award 2024', 'Expert in sports rehabilitation'),
                                                                                                                                                                                                                                                       (5, 5, 'Karen', 'Lee', '1986-08-14', '808 Ocean Dr, Miami, FL 33141', 'karen.lee@clinic.com', '305-555-0502', 1, NULL, '/avatars/karen.jpg', '{}', 'AB-', 'hashed_pass10', 9, 'Mid-level', 'Active', 'English', NULL, 'Focuses on ligament injuries'),
                                                                                                                                                                                                                                                       (1, 3, 'Thomas', 'Garcia', '1983-05-20', '909 N State St, Chicago, IL 60610', 'thomas.garcia@clinic.com', '312-555-0303', 1, NULL, '/avatars/thomas.jpg', '{}', 'B-', 'hashed_pass11', 11, 'Mid-level', 'Active', 'English, Spanish', NULL, 'Specializes in dermatology'),
                                                                                                                                                                                                                                                       (2, 5, 'Jennifer', 'Adams', '1976-10-07', '1010 Lincoln Rd, Miami, FL 33139', 'jennifer.adams@clinic.com', '305-555-0503', 1, NULL, '/avatars/jennifer.jpg', '{}', 'A+', 'hashed_pass12', 19, 'Senior', 'Active', 'English', 'Orthopedic Innovator 2023', 'Expert in joint pain management'),
                                                                                                                                                                                                                                                       (4, 1, 'Steven', 'Patel', '1981-03-28', '1111 Broadway, New York, NY 10010', 'steven.patel@clinic.com', '212-555-0103', 1, NULL, '/avatars/steven.jpg', '{}', 'O-', 'hashed_pass13', 13, 'Senior', 'Active', 'English, Hindi', NULL, 'Nutrition and skin health expert'),
                                                                                                                                                                                                                                                       (5, 2, 'Michelle', 'Clark', '1989-11-11', '1212 Santa Monica Blvd, Los Angeles, CA 90025', 'michelle.clark@clinic.com', '310-555-0203', 1, NULL, '/avatars/michelle.jpg', '{}', 'A-', 'hashed_pass14', 7, 'Junior', 'Active', 'English', NULL, 'Focuses on sports injuries'),
                                                                                                                                                                                                                                                       (3, 1, 'Daniel', 'Rodriguez', '1984-04-16', '1313 Park Ave, New York, NY 10029', 'daniel.rodriguez@clinic.com', '212-555-0104', 1, NULL, '/avatars/daniel.jpg', '{}', 'B+', 'hashed_pass15', 10, 'Mid-level', 'Active', 'English, Spanish', NULL, 'Women’s health and dermatology'),
                                                                                                                                                                                                                                                       (1, 4, 'Rachel', 'Kim', '1987-07-09', '1414 Fannin St, Houston, TX 77005', 'rachel.kim@clinic.com', '713-555-0403', 1, NULL, '/avatars/rachel.jpg', '{}', 'AB+', 'hashed_pass16', 8, 'Junior', 'Active', 'English, Korean', NULL, 'Specializes in skin conditions'),
                                                                                                                                                                                                                                                       (2, 1, 'William', 'Thompson', '1979-01-31', '1515 3rd Ave, New York, NY 10028', 'william.thompson@clinic.com', '212-555-0105', 1, NULL, '/avatars/william.jpg', '{}', 'O+', 'hashed_pass17', 15, 'Senior', 'Active', 'English', 'Orthopedic Award 2022', 'Expert in arthritis treatment'),
                                                                                                                                                                                                                                                       (5, 3, 'Elizabeth', 'White', '1985-12-24', '1616 Dearborn St, Chicago, IL 60616', 'elizabeth.white@clinic.com', '312-555-0304', 1, NULL, '/avatars/elizabeth.jpg', '{}', 'A+', 'hashed_pass18', 9, 'Mid-level', 'Active', 'English', NULL, 'Focuses on sports rehabilitation'),
                                                                                                                                                                                                                                                       (4, 5, 'Christopher', 'Harris', '1980-09-17', '1717 Alton Rd, Miami, FL 33139', 'christopher.harris@clinic.com', '305-555-0504', 1, NULL, '/avatars/christopher.jpg', '{}', 'B-', 'hashed_pass19', 12, 'Mid-level', 'Active', 'English', NULL, 'Nutrition and sports health'),
                                                                                                                                                                                                                                                       (3, 2, 'Amanda', 'Lewis', '1978-06-02', '1818 Olympic Blvd, Los Angeles, CA 90021', 'amanda.lewis@clinic.com', '310-555-0204', 1, NULL, '/avatars/amanda.jpg', '{}', 'O-', 'hashed_pass20', 17, 'Senior', 'Active', 'English, Spanish', 'Gynecology Award 2023', 'Expert in women’s sexual health');