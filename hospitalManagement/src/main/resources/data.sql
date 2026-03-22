 INSERT INTO patient(name,birth_date ,email, gender,blood_group)
 VALUES
 ('abc', '2005-05-01','abc@gmail.com', 'Male','A_POSITIVE'),
 ('def', '2004-03-01','def@gmail.com', 'Male','O_POSITIVE'),
 ('ghi',' 2006-06-04','gghi@gmail.com', 'Male','B_POSITIVE'),
 ('mahesh','2002-01-02','mahesh@gmail.com','Male','AB_NEGITIVE'),
 ('rahul','2001-04-03','rahul@gmail.com','Male','A_NEGITIVE');

 INSERT INTO doctor (name,specialization,email)
 VALUES
        ('Dr.Rakesh Metha','Cardiologist','rakesh.metha@gmail.com'),
        ('Dr. Sneha kapoor','Dermotology','sneha.kapoor@gmail.com'),
        ('Dr.Arjun Nair','Orthopedics','arjun.nair@example.com');


 INSERT INTO appointment (appointment_time , reason , doctor_id,patient_id)
 VALUES
    ('2025-05-01 10:30:00','General checkup',1,2),
    ('2025-05-02 11:00:00','Skin Rash',2,2),
    ('2025-05-03 11:30:00','Knee Pain',3,3),
    ('2025-05-04  9:45:00','Follow up visit',1,1),
    ('2025-05-05 14:00:00','consultant',1,4),
    ('2025-05-06 16:15:00','Allergy treatment',2,5);

