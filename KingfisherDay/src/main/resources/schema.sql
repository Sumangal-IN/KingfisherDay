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

create table FLAG(
	KEY VARCHAR2(100),
	VALUE BOOLEAN
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
	event_id VARCHAR2(2),
	event_name VARCHAR2(50),
	event_details VARCHAR2(200),
	start timestamp,
	end timestamp,
	photo VARCHAR2(10000),
	current boolean
);
