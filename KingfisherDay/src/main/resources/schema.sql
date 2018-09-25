create table QUESTION(
	QUESTION_ID VARCHAR2(2),
	QUESTION_TEXT VARCHAR2(200),
	OPTION_A VARCHAR2(100),
	OPTION_B VARCHAR2(100),
	OPTION_C VARCHAR2(100),
	OPTION_D VARCHAR2(100),
	OPTION_CORRECT VARCHAR2(100),
	CURRENT BOOLEAN	
);

create table EMPLOYEE(
	name VARCHAR2(100),
	email_id VARCHAR2(100),
	food_preference INTEGER,
	password VARCHAR2(1000),
	mobile VARCHAR2(50),
	photo VARCHAR2(10000)
);

create table EVENT(
	event_id number,
	event_name VARCHAR2(100),
	event_details VARCHAR2(2000),
	start VARCHAR2(10),
	end VARCHAR2(10),
	state number,
	ppt_path VARCHAR2(500),
	photo VARCHAR2(10000)
);

create table PHOTOGRAPHY_CONTEST_IMAGE(
	image_id number,
	owner VARCHAR2(100),
	image_name VARCHAR2(500),
	content VARCHAR2(10000)
);


CREATE TABLE PHOTOGRAPHY_CONTEST_RESPONSE (
  employee_email VARCHAR2(255) NOT NULL,
  image_id number NOT NULL,
  comment VARCHAR2(255) DEFAULT NULL,
  time_stamp datetime NOT NULL,
  vote number NOT NULL,
  PRIMARY KEY (employee_email,image_id)
);