CREATE TABLE patient (
   id INT NOT NULL AUTO_INCREMENT,
   first_name VARCHAR(20),
   middle_name VARCHAR(20),
   last_name VARCHAR(20),
   age INT,
   email VARCHAR(50),
   consent_given BOOLEAN,
   blood_type VARCHAR(50),
   preexisting_conditions VARCHAR(5000),
   policy_number INT,
   registration_date DATE,
   date_of_birth DATE,
   insurer_id VARCHAR(50),
   ssn VARCHAR(50)
);

CREATE TABLE patient_preexisting_conditions (
   patient_id INT NOT NULL AUTO_INCREMENT,
   preexisting_conditions VARCHAR(5000)
);