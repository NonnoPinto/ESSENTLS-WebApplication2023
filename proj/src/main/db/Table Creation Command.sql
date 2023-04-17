
-- Users Table Create Command  

CREATE TYPE gen AS ENUM ('female', 'male', 'others');

CREATE TABLE public."Users" (
 
id SERIAL PRIMARY KEY,
email VARCHAR(255) NOT NULL UNIQUE CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
password VARCHAR(255) NOT NULL,
cardID VARCHAR(50),
tier INT DEFAULT 0,
registrationDate DATE,
name VARCHAR(50),
surname VARCHAR(50),
sex gen,
dateOfBirth DATE, 
nationality VARCHAR(100),
homeCountryAddress json,
homeCountryUniversity VARCHAR(150),  
periodOfStay VARCHAR(50),
phoneNumber VARCHAR(50),
paduaAddress json, 
documentType VARCHAR(50),
documentNumber VARCHAR(50),
documentFile text,
dietType text ARRAY,
allergies text ARRAY,
emailHash text,
emailConfirmed Boolean DEFAULT FALSE
);


-- Events Table Create Command 

-- String imageUrl = "file:///C:/myimages/image.jpg"

CREATE TABLE public."Events" (

id SERIAL PRIMARY KEY,
name text NOT NULL,
description text,
price numeric(10,2) CHECK (price >= 0), 
visibility INT DEFAULT 3,
location json,
maxParticipantsInternational INT,
maxParticipantsVolunteer INT,
eventStart timestamp without time zone, 
eventEnd timestamp without time zone, 
subscriptionStart timestamp without time zone, 
subscriptionEnd timestamp without time zone, 
withdrawalEnd timestamp without time zone, 
maxWaitingList INT,
attributes text ARRAY,
thumbnail text,
poster text

);

-- Payments Table Create Command 


CREATE TABLE public."Payments" (

id SERIAL PRIMARY KEY,
userId INT NOT NULL,  
eventId INT,
method text NOT NULL,
amount numeric(10,2) CHECK (amount >= 0) NOT NULL,
date DATE NOT NULL DEFAULT CURRENT_DATE,
notes text, 

CONSTRAINT fk_user 
	  FOREIGN KEY(userId) 
	  REFERENCES public."Users"(id)
	  ON DELETE SET NULL, 

CONSTRAINT fk_Event
      FOREIGN KEY(eventId) 
	  REFERENCES public."Events"(id)
	  ON DELETE SET NULL

);

-- Causes Table Create Command 



CREATE TABLE public."Causes" (

id SERIAL PRIMARY KEY,
name text NOT NULL

);

-- Tags Table Create Command 



CREATE TABLE public."Tags" (

name text,
PRIMARY KEY (name)


);

-- Participants Table Create Command 



CREATE TABLE public."Participants" (
userId INT,
eventId INT, 
role text,
date timestamp without time zone,
attributeValues json,

PRIMARY KEY (userId, eventId),
CONSTRAINT fk_user 
	  FOREIGN KEY(userId) 
	  REFERENCES public."Users"(id)
	  ON DELETE SET NULL, 

CONSTRAINT fk_Event
      FOREIGN KEY(eventId) 
	  REFERENCES public."Events"(id)
	  ON DELETE SET NULL


);


