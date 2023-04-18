-- Insert data into Users Table

INSERT INTO public."Users"(
	email, password, cardid, registrationdate, name, 
	surname, sex, dateofbirth, nationality, homecountryaddress, 
	homecountryuniversity, periodofstay, phonenumber, paduaaddress, 
	documenttype, documentnumber, documentfile, diettype, 
	allergies)
	VALUES ('andre@gmail.com', 'andre', 'AN234', '2023-11-04',
			'Andre', 'Cmp', 'male', '1998-12-11', 'Italian', 
			'{"Province": "Venezia", "City": "Venice", "Street": "Via Po"}',
			'University of Padova', '5 Years', '3412312111', 
			'{"Province": "Padova", "City": "Padova", "Street": "Via Roma"}',
			'Italian Student', 'DG734', '/path/to/your/uploads/doc.pdf', 
			ARRAY['A Therapeutic Diet', 'A Maintenance Diet', 'An Experimental Diet'],
			ARRAY['Pet Allergy', 'Pollen Allergy', 'Drug Allergies']);


INSERT INTO public."Users"(
	email, password, cardid, registrationdate, name, 
	surname, sex, dateofbirth, nationality, homecountryaddress, 
	homecountryuniversity, periodofstay, phonenumber, paduaaddress, 
	documenttype, documentnumber, documentfile, diettype, 
	allergies)
	VALUES ('allesandro@gmail.com', 'alesandro', 'Al5234', '2023-09-04',
			'alesandro', 'alsa', 'male', '1999-10-11', 'Italian', 
			'{"Province": "Padova", "City": "Padova", "Street": "Via Rianta"}',
			'University of Padova', '5 Years', '4583312111', 
			'{"Province": "Padova", "City": "Padova", "Street": "Via Rianta"}',
			'Italian Student', 'DG834', '/path/to/your/uploads/documnent.pdf', 
			ARRAY['A Experimental Diet', 'A Maintenance Diet', 'An Therapeutic Diet'],
			ARRAY['Cold Allergy', 'Pollen Allergy', 'Smoking Allergies']);
			
INSERT INTO public."Users"(
	email, password, cardid, registrationdate, name, 
	surname, sex, dateofbirth, nationality, homecountryaddress, 
	homecountryuniversity, periodofstay, phonenumber, paduaaddress, 
	documenttype, documentnumber, documentfile, diettype, 
	allergies)
	VALUES ('alex@gmail.com', 'alex', 'AU264', '2023-01-08',
			'Alex', 'Perara', 'male', '1995-12-11', 'Brazilian', 
			'{"Province": "Rio", "City": "Rio", "Street": "Via Santos"}',
			'University of Padova', '2 Years', '3412314511', 
			'{"Province": "Padova", "City": "Padova", "Street": "Via Ali"}',
			'International Student', 'IS736', '/path/to/your/uploads/Rio.pdf', 
			ARRAY['high fat', 'A Maintenance Diet', 'low carb'],
			ARRAY['Pet Allergy', 'Mold Allergy', 'Insect Allergies']);
			
			
INSERT INTO public."Users"(
	email, password, cardid, registrationdate, name, 
	surname, sex, dateofbirth, nationality, homecountryaddress, 
	homecountryuniversity, periodofstay, phonenumber, paduaaddress, 
	documenttype, documentnumber, documentfile, diettype, 
	allergies)
	VALUES ('Imran@gmail.com', 'Imran', 'AN264', '2023-09-04',
			'Imran', 'Faruck', 'male', '1997-03-13', 'Bangladeshi', 
			'{"Province": "Chittagong", "City": "Chowkbazar", "Street": "Via Kulshi"}',
			'University of Madinah', '2 Years', '3291292494', 
			'{"Province": "Padova", "City": "Padova", "Street": "Via Ottorero"}',
			'International Student', 'DG730', '/path/to/your/uploads/Imran.pdf', 
			ARRAY['Atkins Diet', 'Zone Diet', 'An Experimental Diet'],
			ARRAY['Pet Allergy', 'Alcohole Allergy', 'Drug Allergies']);
			
INSERT INTO public."Users"(
	email, password, cardid, registrationdate, name, 
	surname, sex, dateofbirth, nationality, homecountryaddress, 
	homecountryuniversity, periodofstay, phonenumber, paduaaddress, 
	documenttype, documentnumber, documentfile, diettype, 
	allergies)
	VALUES ('Laura@gmail.com', 'Laura', 'RR234', '2023-01-05',
			'Laura', 'LAU', 'female', '1999-12-11', 'Italian', 
			'{"Province": "Modana", "City": "Modana", "Street": "Via Pso"}',
			'University of Modana', '5 Years', '3418888891', 
			'{"Province": "Padova", "City": "Padova", "Street": "Via Dat"}',
			'Italian Student', 'IT678', '/path/to/your/uploads/Laura.pdf', 
			ARRAY['whole-food Diet', 'Mediterranean Diet', 'An Experimental Diet'],
			ARRAY['Eye Allergy', 'Pollen Allergy', 'Drug Allergies']);


-- Insert into Event Table

INSERT INTO public."Events"(
	name, description, price, location, maxparticipantsinternational, 
	maxparticipantsvolunteer, eventstart, eventend, subscriptionstart, 
	subscriptionend, withdrawalend, maxwaitinglist, attributes, 
	thumbnail, poster)
	VALUES ('Event One', 'This is Event One', 5.64, '{"Province": "Rio", "City": "Rio", "Street": "Via Santos"}',
			50, 110, '2022-01-01 10:30:00', '2022-01-01 12:30:00', 
			'2021-12-28 10:30:00', '2021-12-30 10:30:00', '2022-12-31 10:30:00',
			20, ARRAY['attributes one', 'attributes two', 'attributes three'],
			'file:///C:/myimages/thumbnail.jpg', 'file:///C:/myimages/poster.jpg');


INSERT INTO public."Events"(
	name, description, price, location, maxparticipantsinternational, 
	maxparticipantsvolunteer, eventstart, eventend, subscriptionstart, 
	subscriptionend, withdrawalend, maxwaitinglist, attributes, 
	thumbnail, poster)
	VALUES ('Event two', 'This is Event two', 15.64, '{"Province": "Padova", "City": "Padova", "Street": "Via po"}',
			30, 80, '2023-10-01 10:30:00', '2023-10-03 12:30:00', 
			'2023-09-01 10:30:00', '2023-09-20 10:30:00', '2023-09-30 10:30:00',
			20, ARRAY['attributes one', 'attributes two', 'attributes three'],
			'file:///C:/myimages/thumbnail.jpg', 'file:///C:/myimages/poster.jpg');
			
INSERT INTO public."Events"(
	name, description, price, location, maxparticipantsinternational, 
	maxparticipantsvolunteer, eventstart, eventend, subscriptionstart, 
	subscriptionend, withdrawalend, maxwaitinglist, attributes, 
	thumbnail, poster)
	VALUES ('Event Three', 'This is Event Three', 13.00, '{"Province": "Venezia", "City": "Mestre", "Street": "Via Piave"}',
			25, 80, '2023-05-01 10:30:00', '2023-05-10 12:30:00', 
			'2023-04-25 10:30:00', '2023-04-28 10:30:00', '2023-04-30 10:30:00',
			20, ARRAY['attributes one', 'attributes two', 'attributes three'],
			'file:///C:/myimages/thumbnail.jpg', 'file:///C:/myimages/poster.jpg');
			

INSERT INTO public."Events"(
	name, description, price, location, maxparticipantsinternational, 
	maxparticipantsvolunteer, eventstart, eventend, subscriptionstart, 
	subscriptionend, withdrawalend, maxwaitinglist, attributes, 
	thumbnail, poster)
	VALUES ('Event Four', 'This is Event Four', 15.00, '{"Province": "Rio", "City": "Rio", "Street": "Via Santos"}',
			150, 190, '2023-11-01 10:30:00', '2023-11-15 12:30:00', 
			'2023-09-01 10:30:00', '2023-10-01 10:30:00', '2023-10-15 10:30:00',
			40, ARRAY['attributes one', 'attributes two', 'attributes three'],
			'file:///C:/myimages/thumbnail.jpg', 'file:///C:/myimages/poster.jpg');
			
INSERT INTO public."Events"(
	name, description, price, location, maxparticipantsinternational, 
	maxparticipantsvolunteer, eventstart, eventend, subscriptionstart, 
	subscriptionend, withdrawalend, maxwaitinglist, attributes, 
	thumbnail, poster)
	VALUES ('Event Five', 'This is Event Five', 5.64, '{"Province": "Lazio", "City": "Roma", "Street": "Via Po"}',
			500, 1100, '2023-06-01 10:30:00', '2023-07-01 12:30:00', 
			'2023-04-28 10:30:00', '2023-05-15 10:30:00', '2023-05-27 10:30:00',
			200, ARRAY['attributes one', 'attributes two', 'attributes three'],
			'file:///C:/myimages/thumbnail.jpg', 'file:///C:/myimages/poster.jpg');



-- Insert into Payment Table

INSERT INTO public."Payments"(
	userid, eventid, method, amount, notes)
	VALUES (1, 3, 'Online', 20, 'For Event Three');
	
INSERT INTO public."Payments"(
	userid, method, amount, notes)
	VALUES (2, 'Cash', 50, 'For Card');
	
INSERT INTO public."Payments"(
	userid, eventid, method, amount, notes)
	VALUES (3, 1, 'Online', 50, 'For Event One');
	
INSERT INTO public."Payments"(
	userid, method, amount, notes)
	VALUES (5, 'Online', 100, 'For Card');
	
INSERT INTO public."Payments"(
	userid, method, amount, notes)
	VALUES (4, 'Online', 50, 'For Card');


-- Insert into Causes Table

INSERT INTO public."Causes"(
	name)
	VALUES ('Cause One');
	
INSERT INTO public."Causes"(
	name)
	VALUES ('Cause Two');
	
INSERT INTO public."Causes"(
	name)
	VALUES ('Cause Three');
	
INSERT INTO public."Causes"(
	name)
	VALUES ('Cause Four');
	
INSERT INTO public."Causes"(
	name)
	VALUES ('Cause Five');


-- Insert into Tags Table

INSERT INTO public."Tags"(
	name)
	VALUES ('Tag One');

INSERT INTO public."Tags"(
	name)
	VALUES ('Tag Two');
	
INSERT INTO public."Tags"(
	name)
	VALUES ('Tag Three');
	
INSERT INTO public."Tags"(
	name)
	VALUES ('Tag Four');
	
INSERT INTO public."Tags"(
	name)
	VALUES ('Tag Five');



-- Insert into Participants Table

INSERT INTO public."Participants"(
	userid, eventid, role, date, attributevalues)
	VALUES ('1', '3', 'admin', '2023-05-27 10:30:00',
		   '{"attributeFirst": "One", "attributeSecond": "Two", "attributeThird": "Three"}');
		   
INSERT INTO public."Participants"(
	userid, eventid, role, date, attributevalues)
	VALUES ('2', '3', 'volontiers', '2022-05-27 10:30:00',
		   '{"attributeFirst": "One", "attributeSecond": "Two", "attributeThird": "Three"}');
		   
INSERT INTO public."Participants"(
	userid, eventid, role, date, attributevalues)
	VALUES ('4', '4', 'Member', '2023-08-20 10:30:00',
		   '{"attributeFirst": "One", "attributeSecond": "Two", "attributeThird": "Three"}');
		   
INSERT INTO public."Participants"(
	userid, eventid, role, date, attributevalues)
	VALUES ('3', '5', 'volontiers', '2023-10-27 10:30:00',
		   '{"attributeFirst": "One", "attributeSecond": "Two", "attributeThird": "Three"}');
		   		   
INSERT INTO public."Participants"(
	userid, eventid, role, date, attributevalues)
	VALUES ('1', '5', 'admin', '2023-11-25 10:30:00',
		   '{"attributeFirst": "One", "attributeSecond": "Two", "attributeThird": "Three"}');