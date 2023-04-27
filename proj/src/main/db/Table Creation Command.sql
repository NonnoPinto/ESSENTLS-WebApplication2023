
-- Users Table Create Command  


CREATE TYPE gen AS ENUM ('female', 'male', 'others');
CREATE TYPE identity AS ENUM ('ID', 'Passport', 'Driver license');
CREATE TYPE diet AS ENUM ('No specific', 'Vegetarian', 'Vegan', 'Halal', 'Kosher', 'Pescatarian');

CREATE TABLE public."Users" (
 
id SERIAL PRIMARY KEY,
email VARCHAR(255) NOT NULL UNIQUE CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
password VARCHAR(255) NOT NULL,
"cardID" VARCHAR(50),
tier INT DEFAULT 0,
"registrationDate" DATE,
name VARCHAR(50),
surname VARCHAR(50),
sex gen,
"dateOfBirth" DATE, 
nationality VARCHAR(100),
"homeCountryAddress" json,
"homeCountryUniversity" VARCHAR(150),  
"periodOfStay" INT,
"phoneNumber" VARCHAR(50),
"paduaAddress" json, 
"documentType" identity,
"documentNumber" VARCHAR(50),
"documentFile" text,
"dietType" diet,
allergies text ARRAY,
"emailHash" VARCHAR(255),
"emailConfirmed" Boolean DEFAULT FALSE,
"documentBytes" bytea
);


-- Events Table Create Command 


CREATE TABLE public."Events" (

id SERIAL PRIMARY KEY,
name text NOT NULL,
description text,
price numeric(10,2) CHECK (price >= 0), 
visibility INT DEFAULT 3,
location json,
"maxParticipantsInternational" INT,
"maxParticipantsVolunteer" INT,
"eventStart" timestamp without time zone, 
"eventEnd" timestamp without time zone, 
"subscriptionStart" timestamp without time zone, 
"subscriptionEnd" timestamp without time zone, 
"withdrawalEnd" timestamp without time zone, 
"maxWaitingList" INT,
attributes text ARRAY,
thumbnail text,
poster text

);

-- Payments Table Create Command 

CREATE TYPE payMethod AS ENUM ('Cash', 'Card', 'Bank');

CREATE TABLE public."Payments" (

id SERIAL PRIMARY KEY,
"userId" INT NOT NULL,  
"eventId" INT,
method payMethod,
amount numeric(10,2) CHECK (amount >= 0) NOT NULL,
date DATE NOT NULL DEFAULT CURRENT_DATE,
notes text, 

CONSTRAINT fk_user 
	  FOREIGN KEY("userId") 
	  REFERENCES public."Users"(id)
	  ON DELETE SET NULL, 

CONSTRAINT fk_Event
      FOREIGN KEY("eventId") 
	  REFERENCES public."Events"(id)
	  ON DELETE SET NULL

);

-- Causes Table Create Command 



CREATE TABLE public."Causes" (

id SERIAL PRIMARY KEY,
name VARCHAR(255)

);

-- Tags Table Create Command 



CREATE TABLE public."Tags" (

name VARCHAR(255),
PRIMARY KEY (name)


);

-- Participants Table Create Command 

CREATE TYPE roleTypes AS ENUM ('Organizer', 'Participant', 'Volunteer', 'WaitingList');


CREATE TABLE public."Participants" (
"userId" INT,
"eventId" INT, 
role roleTypes,
date timestamp without time zone,
"attributeValues" json,

PRIMARY KEY ("userId", "eventId"),
CONSTRAINT fk_user 
	  FOREIGN KEY("userId") 
	  REFERENCES public."Users"(id)
	  ON DELETE SET NULL, 

CONSTRAINT fk_Event
      FOREIGN KEY("eventId") 
	  REFERENCES public."Events"(id)
	  ON DELETE SET NULL


);

CREATE TABLE public."EventTags" (
"eventId" INT,
tag VARCHAR(255),

PRIMARY KEY ("eventId", tag),
CONSTRAINT fk_Event
    FOREIGN KEY("eventId")
    REFERENCES public."Events"(id)
    ON DELETE SET NULL,

CONSTRAINT fk_Tag
    FOREIGN KEY(tag)
    REFERENCES public."Tags"(name)
    ON DELETE SET NULL
);

CREATE TABLE public."EventCauses" (
"eventId" INT,
"causeId" INT,

PRIMARY KEY ("eventId", "causeId"),
CONSTRAINT fk_Event
    FOREIGN KEY("eventId")
    REFERENCES public."Events"(id)
    ON DELETE SET NULL,

CONSTRAINT fk_Cause
    FOREIGN KEY("causeId")
    REFERENCES public."Causes"(id)
    ON DELETE SET NULL
);


