--------------------------------------------------------
-- 	CREATE TABLE STRUCTURE WITHOUT PK 
--------------------------------------------------------

CREATE TABLE "WEBSTORE"."T_GENERATOR" 
   (	
   "GEN_KEY" VARCHAR2(255 BYTE) NOT NULL,   
	"GEN_VALUE" NUMBER NOT NULL
   );
   
create table users (
        id int(11) NOT NULL AUTO_INCREMENT,
        username varchar(255) not null,
 	password varchar(255) not null,
	createdate datetime not null,
        image longblob,
        name varchar(255) not null,
	surname varchar(255) not null,
        auth int ,
        primary key (id)
    );

CREATE TABLE "WEBSTORE"."T_PRODUCT_TYPE" 
(
	"ID" NUMBER NOT NULL,
	"NAME" VARCHAR2 (500 BYTE) NOT NULL
);

CREATE TABLE "WEBSTORE"."T_ORDER_TYPE" 
(
	"ID" NUMBER NOT NULL,
	"NAME" VARCHAR2 (50 BYTE) NOT NULL
);

CREATE TABLE "WEBSTORE"."T_ROLE" 
(
	"ID" NUMBER NOT NULL,
	"NAME" VARCHAR2 (50 BYTE) NOT NULL
);
//pas incluse dans la base
CREATE TABLE "WEBSTORE"."T_CURRENCY" 
(
	"ID" NUMBER NOT NULL,
	"NAME" VARCHAR2 (50 BYTE) NOT NULL,
	"RATE" NUMBER NOT NULL
);


CREATE TABLE "WEBSTORE"."T_USER" 
(
	"ID" NUMBER NOT NULL,
	"USERNAME" VARCHAR2 (30 BYTE) NOT NULL,
	"PASSWORD" VARCHAR2(50 BYTE) NOT NULL,
	"ROLE_ID" NUMBER NOT NULL
);

CREATE TABLE "WEBSTORE"."T_PRODUCT" 
(
	"ID" NUMBER NOT NULL,
	"NAME" VARCHAR2 (50 BYTE) NOT NULL,
	"DESCRIPTION" VARCHAR2 (500 BYTE), 
	"PRODUCT_TYPE_ID" NUMBER NOT NULL,
	"CURRENCY_ID" NUMBER NOT NULL,
	"SINGLE_PRICE" NUMBER NOT NULL,
	"QUANTITY" NUMBER NOT NULL,
	"PICTURE_NAME" VARCHAR2 (50 BYTE),
	"ACTIVE" NUMBER NOT NULL
);

CREATE TABLE "WEBSTORE"."T_CUSTOMER" 
(
	"ID" NUMBER NOT NULL,
	"NAME" VARCHAR2 (50 BYTE) NOT NULL,
	"BIRTH_DATE" DATE,
	"ADDRESS" VARCHAR2 (100 BYTE),
	"USER_ID" NUMBER NOT NULL,
	"ACTIVE" NUMBER NOT NULL
);
//non incluse 
CREATE TABLE "WEBSTORE"."T_ORDER" 
(
	"ID" NUMBER NOT NULL,
	"CUSTOMER_ID" NUMBER NOT NULL,
	"ORDER_TYPE_ID" NUMBER NOT NULL,
	"TOTAL_PRICE" NUMBER,
	"TOTAL_QUANTITY" NUMBER,
	"PURCHASE_DATE" TIMESTAMP,
	"COMMENT" VARCHAR2 (200 BYTE)
);


CREATE TABLE "WEBSTORE"."T_ORDER_DETAILS" 
(
	"ID" NUMBER NOT NULL,
	"ORDER_ID" NUMBER,
	"PRODUCT_ID" NUMBER NOT NULL,
	"QUANTITY" NUMBER NOT NULL,
	"PRICE" NUMBER NOT NULL,
	"CURRENCY_ID" NUMBER NOT NULL
);


--------------------------------------------------------
-- 	CREATE TABLE PRIMARY KEYS 
--------------------------------------------------------

ALTER TABLE "WEBSTORE"."T_USER" ADD CONSTRAINT "T_USER_PK" PRIMARY KEY ("ID");
ALTER TABLE "WEBSTORE"."T_ROLE" ADD CONSTRAINT "T_ROLE_PK" PRIMARY KEY ("ID");
ALTER TABLE "WEBSTORE"."T_PRODUCT" ADD CONSTRAINT "T_PRODUCT_PK" PRIMARY KEY ("ID");
ALTER TABLE "WEBSTORE"."T_PRODUCT_TYPE" ADD CONSTRAINT "T_PRODUCT_TYPE_PK" PRIMARY KEY ("ID");
ALTER TABLE "WEBSTORE"."T_CUSTOMER" ADD CONSTRAINT "T_CUSTOMER_PK" PRIMARY KEY ("ID");
ALTER TABLE "WEBSTORE"."T_ORDER_TYPE" ADD CONSTRAINT "T_ORDER_TYPE_PK" PRIMARY KEY ("ID");
ALTER TABLE "WEBSTORE"."T_ORDER" ADD CONSTRAINT "T_ORDER_PK" PRIMARY KEY ("ID");
ALTER TABLE "WEBSTORE"."T_ORDER_DETAILS" ADD CONSTRAINT "T_ORDER_DETAILS_PK" PRIMARY KEY ("ID");
ALTER TABLE "WEBSTORE"."T_CURRENCY" ADD CONSTRAINT "T_CURRENCY_PK" PRIMARY KEY ("ID");



--------------------------------------------------------
-- 	CREATE TABLE FOREIGN KEYS 
--------------------------------------------------------

ALTER TABLE "WEBSTORE"."T_USER" ADD CONSTRAINT "T_USER_ROLE_FK1" FOREIGN KEY ("ROLE_ID")
	  REFERENCES "WEBSTORE"."T_ROLE" ("ID") ENABLE;
	  
ALTER TABLE "WEBSTORE"."T_PRODUCT" ADD CONSTRAINT "T_PRODUCT_PRODUCT_TYPE_FK1" FOREIGN KEY ("PRODUCT_TYPE_ID")
	  REFERENCES "WEBSTORE"."T_PRODUCT_TYPE" ("ID") ENABLE;
ALTER TABLE "WEBSTORE"."T_PRODUCT" ADD CONSTRAINT "T_PRODUCT_CURRENCY_FK1" FOREIGN KEY ("CURRENCY_ID")
	  REFERENCES "WEBSTORE"."T_CURRENCY" ("ID") ENABLE;

ALTER TABLE "WEBSTORE"."T_CUSTOMER" ADD CONSTRAINT "T_CUSTOMER_ROLE_FK1" FOREIGN KEY ("USER_ID")
	  REFERENCES "WEBSTORE"."T_USER" ("ID") ENABLE;


ALTER TABLE "WEBSTORE"."T_ORDER" ADD CONSTRAINT "T_ORDER_CUSTOMER_FK1" FOREIGN KEY ("CUSTOMER_ID")
	  REFERENCES "WEBSTORE"."T_CUSTOMER" ("ID") ENABLE;
ALTER TABLE "WEBSTORE"."T_ORDER" ADD CONSTRAINT "T_ORDER_ORDER_TYPE_FK1" FOREIGN KEY ("ORDER_TYPE_ID")
	  REFERENCES "WEBSTORE"."T_ORDER_TYPE" ("ID") ENABLE;


ALTER TABLE "WEBSTORE"."T_ORDER_DETAILS" ADD CONSTRAINT "T_ORDER_DETAILS_ORDER_FK1" FOREIGN KEY ("ORDER_ID")
	  REFERENCES "WEBSTORE"."T_ORDER" ("ID") ENABLE;
ALTER TABLE "WEBSTORE"."T_ORDER_DETAILS" ADD CONSTRAINT "T_ORDER_DETAILS_PRODUCT__FK1" FOREIGN KEY ("PRODUCT_ID")
	  REFERENCES "WEBSTORE"."T_PRODUCT" ("ID") ENABLE;
ALTER TABLE "WEBSTORE"."T_ORDER_DETAILS" ADD CONSTRAINT "T_ORDER_DETAILS_CURRENCY_FK1" FOREIGN KEY ("CURRENCY_ID")
	  REFERENCES "WEBSTORE"."T_CURRENCY" ("ID") ENABLE;

--------------------------------------------------------
-- 	INSERT DATA 
--------------------------------------------------------
Insert into WEBSTORE.T_CURRENCY (ID,NAME,RATE) values (1,'BGN',1.00);

Insert into WEBSTORE.T_ROLE (ID,NAME) values (1,'ROLE_ADMIN');
Insert into WEBSTORE.T_ROLE (ID,NAME) values (2,'ROLE_USER');

Insert into WEBSTORE.T_ORDER_TYPE (ID,NAME) values (0,'supply');
Insert into WEBSTORE.T_ORDER_TYPE (ID,NAME) values (1,'sell');
Insert into WEBSTORE.T_ORDER_TYPE (ID,NAME) values (2,'refund');
Insert into WEBSTORE.T_ORDER_TYPE (ID,NAME) values (3,'refunded');


Insert into WEBSTORE.T_USER (ID,USERNAME,PASSWORD,ROLE_ID) values (1,'admin','96e79218965eb72c92a549dd5a330112',1);
Insert into WEBSTORE.T_USER (ID,USERNAME,PASSWORD,ROLE_ID) values (2,'user','e3ceb5881a0a1fdaad01296d7554868d',2);

Insert into WEBSTORE.T_CUSTOMER (ID,NAME,BIRTH_DATE,ADDRESS,USER_ID,ACTIVE) values (14,'Init Customer',to_date('2000-01-01','RRRR-MM-DD'),'Init CustomerAddress',2,1);
Insert into WEBSTORE.T_CUSTOMER (ID,NAME,BIRTH_DATE,ADDRESS,USER_ID,ACTIVE) values (15,'Administrator',to_date('2000-01-01','RRRR-MM-DD'),'Init Administrtor Address',1,1);


Insert into WEBSTORE.T_PRODUCT_TYPE (ID,NAME) values (1,'electronics,photo,cameras');
Insert into WEBSTORE.T_PRODUCT_TYPE (ID,NAME) values (2,'electronics,photo,video');
Insert into WEBSTORE.T_PRODUCT_TYPE (ID,NAME) values (3,'electronics,tv,lcd');
Insert into WEBSTORE.T_PRODUCT_TYPE (ID,NAME) values (4,'electronics,pc,notebook');
Insert into WEBSTORE.T_PRODUCT_TYPE (ID,NAME) values (5,'electornics,pc,desktop');
Insert into WEBSTORE.T_PRODUCT_TYPE (ID,NAME) values (6,'electronics,mobile,smartphones');


Insert into WEBSTORE.T_PRODUCT (ID,NAME,DESCRIPTION,PRODUCT_TYPE_ID,CURRENCY_ID,SINGLE_PRICE,QUANTITY,PICTURE_NAME,ACTIVE) values (7,'ASUS N752 ~ 256GB SSD + 1TB','CPU: i7, HDD: 256SSD + 1TB , Video: Gefore  NVIDIA FX 6500',1,1,2200,20,'pic42.jpg',0);
Insert into WEBSTORE.T_PRODUCT (ID,NAME,DESCRIPTION,PRODUCT_TYPE_ID,CURRENCY_ID,SINGLE_PRICE,QUANTITY,PICTURE_NAME,ACTIVE) values (1,'Nikon D5200 24.1MP Digital SLR Camera','Equipped with a 24.1 megapixel DX-format sensor,Powered with EXPEED 3 engine,Sensitivity range from ISO 100 to ISO 6400 and the best in class 39 point AF system',1,1,500,9,'pic1.jpg',1);
Insert into WEBSTORE.T_PRODUCT (ID,NAME,DESCRIPTION,PRODUCT_TYPE_ID,CURRENCY_ID,SINGLE_PRICE,QUANTITY,PICTURE_NAME,ACTIVE) values (2,'Canon IXUS 275 HS 20.2 MP','Ultra-slim IXUS and Canon quality in your pocket,Get closer to the moments that matter with a 12x zoom,Capture stunning photos and movies with 20.2 MP and Intelligent IS,Share easily and shoot remotely using Wi-Fi and NFC,Play with your creativity using Creative shot',2,1,300,9,'pic2.jpg',1);
Insert into WEBSTORE.T_PRODUCT (ID,NAME,DESCRIPTION,PRODUCT_TYPE_ID,CURRENCY_ID,SINGLE_PRICE,QUANTITY,PICTURE_NAME,ACTIVE) values (3,'Sony DSC W830 Cyber-shot 20.1 MP','Super HAD CCD sensor with 20.1 effective megapixels,720p MP4 movie mode the camera shoots 1280 x 720 high definition movies at 30 fps,8x optical zoom Carl Zeiss Vario Tessar lens,Equipped with sweep panorama, intelligent auto and picture effect,2.7-inch (230K dots) clear photo LCD display',3,1,2000,30,'pic3.jpg',1);
Insert into WEBSTORE.T_PRODUCT (ID,NAME,DESCRIPTION,PRODUCT_TYPE_ID,CURRENCY_ID,SINGLE_PRICE,QUANTITY,PICTURE_NAME,ACTIVE) values (4,'Nikon Coolpix S2900 20.1MP','20.1 megapixels camera,5x optical zoom,2.7 inch TFT LCD, and 5-level brightness adjustment,Control : TTL auto flash with monitor preflashes,Shutter type : Mechanical and CCD electronic shutter',1,1,200,29,'pic4.jpg',1);
Insert into WEBSTORE.T_PRODUCT (ID,NAME,DESCRIPTION,PRODUCT_TYPE_ID,CURRENCY_ID,SINGLE_PRICE,QUANTITY,PICTURE_NAME,ACTIVE) values (5,'Notebook Dell E1505','Notebook 12',4,1,1000,10,'pic43.jpg',1);
Insert into WEBSTORE.T_PRODUCT (ID,NAME,DESCRIPTION,PRODUCT_TYPE_ID,CURRENCY_ID,SINGLE_PRICE,QUANTITY,PICTURE_NAME,ACTIVE) values (8,'Samsung Galaxy S7','3G Data Capable, Air Gesture, Bluetooth Enabled, Fingerprint Sensor, Global Ready, GPS, Internet Browser, Music Player, Near Field Communication, QWERTY Keyboard, Speakerphone',1,1,1500,10,'pic51.jpg',1);
Insert into WEBSTORE.T_PRODUCT (ID,NAME,DESCRIPTION,PRODUCT_TYPE_ID,CURRENCY_ID,SINGLE_PRICE,QUANTITY,PICTURE_NAME,ACTIVE) values (6,'Acer Predator 15 G9-591','Memory 8GB, CPU: Intel I7 Core, HDD: 1TB + 512GB SSD, Video: NVidia Geforce 4GB',4,1,2000,10,'pic41.jpg',1);


Insert into WEBSTORE.T_GENERATOR (GEN_KEY,GEN_VALUE) values ('WEBSTORE.T_CUSTOMER',34);
Insert into WEBSTORE.T_GENERATOR (GEN_KEY,GEN_VALUE) values ('WEBSTORE.T_USER',23);
Insert into WEBSTORE.T_GENERATOR (GEN_KEY,GEN_VALUE) values ('WEBSTORE.T_ORDER_DETAILS',108);
Insert into WEBSTORE.T_GENERATOR (GEN_KEY,GEN_VALUE) values ('WEBSTORE.T_ORDER',65);
Insert into WEBSTORE.T_GENERATOR (GEN_KEY,GEN_VALUE) values ('WEBSTORE.T_PRODUCT',12);

   
--------------------------------------------------------
-- 	SET ORACLE SESSION TO COMPARE INSENSITIVE DATA LIKE 'aAA' == 'AAA' 
--------------------------------------------------------
   
ALTER SESSION SET NLS_COMP=LINGUISTIC;