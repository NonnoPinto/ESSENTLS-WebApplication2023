-- Insert data into Users Table

INSERT INTO public."Users"(
	email, password, "cardID", "registrationDate", name, surname, sex, "dateOfBirth", nationality, 
	"homeCountryAddress", "homeCountryUniversity", "periodOfStay", "phoneNumber", "paduaAddress", 
	"documentType", "documentNumber", "documentFile", "dietType", allergies)
	VALUES ('andre@gmail.com', 'andre', 'AN234', '2023-11-04',
			'Andre', 'Cmp', 'male', '1998-12-11', 'Italian', 
			'{"Province": "Venezia", "City": "Venice", "Street": "Via Po"}',
			'University of Padova', 1, '3412312111', 
			'{"Province": "Padova", "City": "Padova", "Street": "Via Roma"}',
			'ID', 'DG734', '/path/to/your/uploads/doc.pdf', 'No specific',
			ARRAY['Pet Allergy', 'Pollen Allergy', 'Drug Allergies']),
			
			('allesandro@gmail.com', 'alesandro', 'Al5234', '2023-09-04',
			'alesandro', 'alsa', 'male', '1999-10-11', 'Italian', 
			'{"Province": "Padova", "City": "Padova", "Street": "Via Rianta"}',
			'University of Padova', 2, '4583312111', 
			'{"Province": "Padova", "City": "Padova", "Street": "Via Rianta"}',
			'ID', 'DG834', '/path/to/your/uploads/documnent.pdf', 'No specific',
			ARRAY['Cold Allergy', 'Pollen Allergy', 'Smoking Allergies']),
			
			('alex@gmail.com', 'alex', 'AU264', '2023-01-08',
			'Alex', 'Perara', 'male', '1995-12-11', 'Brazilian', 
			'{"Province": "Rio", "City": "Rio", "Street": "Via Santos"}',
			'University of Padova', 1, '3412314511', 
			'{"Province": "Padova", "City": "Padova", "Street": "Via Ali"}',
			'Passport', 'IS736', '/path/to/your/uploads/Rio.pdf', 'Vegan',
			ARRAY['Pet Allergy', 'Mold Allergy', 'Insect Allergies']),
			
			('Imran@gmail.com', 'Imran', 'AN264', '2023-09-04',
			'Imran', 'Faruck', 'male', '1997-03-13', 'Bangladeshi', 
			'{"Province": "Chittagong", "City": "Chowkbazar", "Street": "Via Kulshi"}',
			'University of Madinah', 3, '3291292494', 
			'{"Province": "Padova", "City": "Padova", "Street": "Via Ottorero"}',
			'Passport', 'DG730', '/path/to/your/uploads/Imran.pdf', 'Halal',
			ARRAY['Pet Allergy', 'Alcohole Allergy', 'Drug Allergies']),
			
			('Laura@gmail.com', 'Laura', 'RR234', '2023-01-05',
			'Laura', 'LAU', 'female', '1999-12-11', 'Italian', 
			'{"Province": "Modana", "City": "Modana", "Street": "Via Pso"}',
			'University of Modana', 5, '3418888891', 
			'{"Province": "Padova", "City": "Padova", "Street": "Via Dat"}',
			'ID', 'IT678', '/path/to/your/uploads/Laura.pdf', 'No specific',
			ARRAY['Eye Allergy', 'Pollen Allergy', 'Drug Allergies']);


-- Insert into Event Table

INSERT INTO public."Events"(
	name, description, price, location, "maxParticipantsInternational", "maxParticipantsVolunteer",
	"eventStart", "eventEnd", "subscriptionStart", "subscriptionEnd", "withdrawalEnd", 
	"maxWaitingList", attributes, thumbnail, poster)
	VALUES ('Event One', 'This is Event One', 5.64, '{"Province": "Rio", "City": "Rio", "Street": "Via Santos"}',
			50, 110, '2022-01-01 10:30:00', '2022-01-01 12:30:00', 
			'2021-12-28 10:30:00', '2021-12-30 10:30:00', '2022-12-31 10:30:00',
			20, ARRAY['attributes one', 'attributes two', 'attributes three'],
			'file:///C:/myimages/thumbnail.jpg', 'file:///C:/myimages/poster.jpg'),
			
			('Event two', 'This is Event two', 15.64, '{"Province": "Padova", "City": "Padova", "Street": "Via po"}',
			30, 80, '2023-10-01 10:30:00', '2023-10-03 12:30:00', 
			'2023-09-01 10:30:00', '2023-09-20 10:30:00', '2023-09-30 10:30:00',
			20, ARRAY['attributes one', 'attributes two', 'attributes three'],
			'file:///C:/myimages/thumbnail.jpg', 'file:///C:/myimages/poster.jpg'),
			
			('Event Three', 'This is Event Three', 13.00, '{"Province": "Venezia", "City": "Mestre", "Street": "Via Piave"}',
			25, 80, '2023-05-01 10:30:00', '2023-05-10 12:30:00', 
			'2023-04-25 10:30:00', '2023-04-28 10:30:00', '2023-04-30 10:30:00',
			20, ARRAY['attributes one', 'attributes two', 'attributes three'],
			'file:///C:/myimages/thumbnail.jpg', 'file:///C:/myimages/poster.jpg'),
			
			('Event Four', 'This is Event Four', 15.00, '{"Province": "Rio", "City": "Rio", "Street": "Via Santos"}',
			150, 190, '2023-11-01 10:30:00', '2023-11-15 12:30:00', 
			'2023-09-01 10:30:00', '2023-10-01 10:30:00', '2023-10-15 10:30:00',
			40, ARRAY['attributes one', 'attributes two', 'attributes three'],
			'file:///C:/myimages/thumbnail.jpg', 'file:///C:/myimages/poster.jpg'),
			
			('Event Five', 'This is Event Five', 5.64, '{"Province": "Lazio", "City": "Roma", "Street": "Via Po"}',
			500, 1100, '2023-06-01 10:30:00', '2023-07-01 12:30:00', 
			'2023-04-28 10:30:00', '2023-05-15 10:30:00', '2023-05-27 10:30:00',
			200, ARRAY['attributes one', 'attributes two', 'attributes three'],
			'file:///C:/myimages/thumbnail.jpg', 'file:///C:/myimages/poster.jpg');



-- Insert into Payment Table

INSERT INTO public."Payments"(
	"userId", "eventId", method, amount, notes)
	VALUES (1, 3, 'Card', 20, 'For Event Three'),
	
	(2, 3, 'Cash', 50, 'For Card'),
	
	(3, 1, 'Bank', 50, 'For Event One'),
	
	(5, 4, 'Card', 100, 'For Card'),
	
	(4, 2, 'Card', 50, 'For Card');


-- Insert into Causes Table

INSERT INTO public."Causes"(
	name)
	VALUES ('Cause One'),
	('Cause Two'),
	('Cause Three'),
	('Cause Four'),
	('Cause Five');


-- Insert into Tags Table

INSERT INTO public."Tags"(
	name)
	VALUES ('Tag One'),
	('Tag Two'),
	('Tag Three'),
	('Tag Four'),
	('Tag Five');



-- Insert into Participants Table

INSERT INTO public."Participants"(
	"userId", "eventId", role, date, "attributeValues")
	VALUES ('1', '3', 'Organizer', '2023-05-27 10:30:00',
		   '{"attributeFirst": "One", "attributeSecond": "Two", "attributeThird": "Three"}'),
		   
		   ('2', '3', 'WaitingList', '2022-05-27 10:30:00',
		   '{"attributeFirst": "One", "attributeSecond": "Two", "attributeThird": "Three"}'),
		   
		   ('4', '4', 'Volunteer', '2023-08-20 10:30:00',
		   '{"attributeFirst": "One", "attributeSecond": "Two", "attributeThird": "Three"}'),
		   
		   ('3', '5', 'WaitingList', '2023-10-27 10:30:00',
		   '{"attributeFirst": "One", "attributeSecond": "Two", "attributeThird": "Three"}'),
		   
		   ('1', '5', 'Volunteer', '2023-11-25 10:30:00',
		   '{"attributeFirst": "One", "attributeSecond": "Two", "attributeThird": "Three"}');