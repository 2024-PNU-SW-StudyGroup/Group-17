DROP TABLE "User";

CREATE TABLE "User" (
	"user_id"	bigint(20)		NOT NULL,
	"password"	varchar(255)		NOT NULL,
	"user_name"	varchar(100)		NOT NULL,
	"user_phone"	varchar(11)		NOT NULL,
	"email"	varchar(100)		NOT NULL,
	"join_type"	varchar(10)		NOT NULL,
	"grade"	varchar(100)		NOT NULL,
	"eco_points"	int(100)	DEFAULT 0	NOT NULL,
	"total_discount"	int(10)	DEFAULT 0	NOT NULL,
	"role"	varchar(100)		NOT NULL,
	"user_status"	varchar(1)		NOT NULL
);

COMMENT ON COLUMN "User"."password" IS '암호화할 것을 고려해서 넉넉하게 잡음';

COMMENT ON COLUMN "User"."join_type" IS 'INAPP: 자체 이메일 가입, KAKAO: 카카오가입';

COMMENT ON COLUMN "User"."grade" IS '필요한가? 나무키우기로 할 거면 레벨로 설정해야 되려나?';

COMMENT ON COLUMN "User"."eco_points" IS '포인트에 따라 나무 이미지 다르게?';

COMMENT ON COLUMN "User"."role" IS '회원';

COMMENT ON COLUMN "User"."user_status" IS 'Y: 활성화 회원, N: 탈퇴 회원';

DROP TABLE "Store";

CREATE TABLE "Store" (
	"store_id"	bigint(20)		NOT NULL,
	"store_name"	varchar(255)		NOT NULL,
	"category"	varchar(20)		NOT NULL,
	"store_address"	varchar(255)		NOT NULL,
	"store_picture_url"	text		NULL,
	"store_phone"	varchar(20)		NOT NULL,
	"notice"	varchar(255)		NULL,
	"store_content"	varchar(255)		NULL,
	"pickup_time"	varchar(255)		NOT NULL,
	"closed_days"	varchar(255)		NULL,
	"store_rating"	int(1)	DEFAULT 0	NOT NULL,
	"favorite_count"	int(11)	DEFAULT 0	NOT NULL,
	"review_count"	int(11)	DEFAULT 0	NOT NULL,
	"ceo_name"	varchar(20)		NOT NULL,
	"company_name"	varchar(20)		NOT NULL,
	"company_address"	varchar(255)		NOT NULL,
	"business_number"	varchar(12)		NOT NULL,
	"country_of_origin"	varchar(255)		NULL,
	"status"	varchar(1)	DEFAULT Y	NOT NULL,
	"Field"	VARCHAR(255)		NULL
);

COMMENT ON COLUMN "Store"."pickup_time" IS '일단 19:00~22:00 형식으로 받는다고 생각함';

COMMENT ON COLUMN "Store"."store_rating" IS '1~5점';

COMMENT ON COLUMN "Store"."ceo_name" IS '어';

COMMENT ON COLUMN "Store"."company_name" IS '쩌';

COMMENT ON COLUMN "Store"."company_address" IS '지';

COMMENT ON COLUMN "Store"."business_number" IS '이';

COMMENT ON COLUMN "Store"."country_of_origin" IS '건?';

COMMENT ON COLUMN "Store"."status" IS 'Y: 활성화, N: 삭제';

DROP TABLE "Menu";

CREATE TABLE "Menu" (
	"menu_id"	bigint(20)		NOT NULL,
	"store_id"	bigint(20)		NOT NULL,
	"menu_category"	varchar(100)		NOT NULL,
	"menu_name"	varchar(255)		NOT NULL,
	"menu_price"	int(11)		NOT NULL,
	"menu_discount_price"	int(11)		NOT NULL,
	"menu_picture_url"	varchar(255)		NULL,
	"popularity"	tinyint(1)	DEFAULT 0	NOT NULL,
	"status"	varchar(255)		NOT NULL
);

COMMENT ON COLUMN "Menu"."status" IS '판매 상태(판매 중, 품절 등)';

DROP TABLE "MenuOption";

CREATE TABLE "MenuOption" (
	"menu_option_id"	bigint(20)		NOT NULL,
	"menu_id"	bigint(20)		NOT NULL,
	"menu_detail_name"	varchar(255)		NULL,
	"menu_content"	varchar(255)		NULL,
	"menu_pickup_time"	time		NULL
);

COMMENT ON COLUMN "MenuOption"."menu_pickup_time" IS 'type을 뭘로 해야 되지??';

DROP TABLE "Order";

CREATE TABLE "Order" (
	"order_id"	bigint(20)		NOT NULL,
	"user_id"	bigint(20)		NOT NULL,
	"order_time"	varchar(255)		NOT NULL,
	"payment_method"	varchar(255)		NOT NULL,
	"total_price"	int(11)		NOT NULL,
	"status"	varchar(255)		NOT NULL
);

COMMENT ON COLUMN "Order"."status" IS '주문 완료, 픽업 완료';

DROP TABLE "Cart";

CREATE TABLE "Cart" (
	"cart_id"	bigint(20)		NOT NULL,
	"user_id"	bigint(20)		NOT NULL,
	"menu_id"	bigint(20)		NOT NULL,
	"cart_quantity"	int(11)		NOT NULL,
	"status"	varchar(255)		NOT NULL
);

COMMENT ON COLUMN "Cart"."cart_quantity" IS '장바구니에 담은 메뉴의 수량';

COMMENT ON COLUMN "Cart"."status" IS '일반';

DROP TABLE "Review";

CREATE TABLE "Review" (
	"review_id"	bigint(20)		NOT NULL,
	"user_id"	bigint(20)		NOT NULL,
	"store_id"	bigint(20)		NOT NULL,
	"review_rating"	int(1)		NOT NULL,
	"review_content"	varchar(255)		NULL,
	"review_picture_url"	varchar(255)		NULL,
	"status"	varchar(255)		NOT NULL
);

COMMENT ON COLUMN "Review"."review_rating" IS '1~5점';

COMMENT ON COLUMN "Review"."status" IS '일반';

DROP TABLE "Favorite";

CREATE TABLE "Favorite" (
	"user_id"	bigint(20)		NOT NULL,
	"store_id"	bigint(20)		NOT NULL,
	"status"	varchar(1)		NOT NULL
);

DROP TABLE "Coupon";

CREATE TABLE "Coupon" (
	"coupon_id"	bigint(20)		NOT NULL,
	"coupon_name"	varchar(255)		NOT NULL,
	"coupon_content"	varchar(255)		NOT NULL,
	"deduce_price"	int(11)		NOT NULL,
	"min_order_price"	int(11)		NOT NULL,
	"created_time"	timestamp	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"modified_time"	timestamp	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"status"	varchar(1)		NOT NULL
);

COMMENT ON COLUMN "Coupon"."status" IS 'Y: 가능, N: 기간 만료';

DROP TABLE "Order_Menu";

CREATE TABLE "Order_Menu" (
	"order_menu_id"	bigint(20)		NOT NULL,
	"order_id"	bigint(20)		NOT NULL,
	"menu_id2"	bigint(20)		NOT NULL,
	"quantity"	int(11)		NOT NULL,
	"unit_price"	int(11)		NOT NULL,
	"total_price"	int(11)		NOT NULL,
	"options"	varchar(255)		NULL
);

COMMENT ON COLUMN "Order_Menu"."quantity" IS '주문한 메뉴의 수량';

COMMENT ON COLUMN "Order_Menu"."unit_price" IS '주문 당시 메뉴의 가격';

COMMENT ON COLUMN "Order_Menu"."total_price" IS '주문 당시 총 가격';

COMMENT ON COLUMN "Order_Menu"."options" IS '메뉴 옵션이 있을 경우';

DROP TABLE "operation_hours";

CREATE TABLE "operation_hours" (
	"operation_id"	bigint9(20)		NOT NULL,
	"store_id"	bigint(20)		NOT NULL,
	"mon_open"	DATE		NULL,
	"mon_close"	DATE		NULL,
	"tue_open"	DATE		NULL,
	"tue_close"	DATE		NULL,
	"wed_open"	DATE		NULL,
	"wed_close"	DATE		NULL,
	"thu_open"	DATE		NULL,
	"thu_close"	DATE		NULL,
	"fri_open"	DATE		NULL,
	"fri_close"	DATE		NULL,
	"sat_open"	DATE		NULL,
	"sat_close"	DATE		NULL,
	"sun_open"	DATE		NULL,
	"sun_close"	DATE		NULL
);

COMMENT ON COLUMN "operation_hours"."mon_open" IS '입력 안 하면 휴무일로 간주';

ALTER TABLE "User" ADD CONSTRAINT "PK_USER" PRIMARY KEY (
	"user_id"
);

ALTER TABLE "Store" ADD CONSTRAINT "PK_STORE" PRIMARY KEY (
	"store_id"
);

ALTER TABLE "Menu" ADD CONSTRAINT "PK_MENU" PRIMARY KEY (
	"menu_id"
);

ALTER TABLE "MenuOption" ADD CONSTRAINT "PK_MENUOPTION" PRIMARY KEY (
	"menu_option_id"
);

ALTER TABLE "Order" ADD CONSTRAINT "PK_ORDER" PRIMARY KEY (
	"order_id"
);

ALTER TABLE "Cart" ADD CONSTRAINT "PK_CART" PRIMARY KEY (
	"cart_id"
);

ALTER TABLE "Review" ADD CONSTRAINT "PK_REVIEW" PRIMARY KEY (
	"review_id"
);

ALTER TABLE "Coupon" ADD CONSTRAINT "PK_COUPON" PRIMARY KEY (
	"coupon_id"
);

ALTER TABLE "Order_Menu" ADD CONSTRAINT "PK_ORDER_MENU" PRIMARY KEY (
	"order_menu_id"
);

ALTER TABLE "operation_hours" ADD CONSTRAINT "PK_OPERATION_HOURS" PRIMARY KEY (
	"operation_id"
);

ALTER TABLE "Menu" ADD CONSTRAINT "FK_Store_TO_Menu_1" FOREIGN KEY (
	"store_id"
)
REFERENCES "Store" (
	"store_id"
);

ALTER TABLE "MenuOption" ADD CONSTRAINT "FK_Menu_TO_MenuOption_1" FOREIGN KEY (
	"menu_id"
)
REFERENCES "Menu" (
	"menu_id"
);

ALTER TABLE "Order" ADD CONSTRAINT "FK_User_TO_Order_1" FOREIGN KEY (
	"user_id"
)
REFERENCES "User" (
	"user_id"
);

ALTER TABLE "Cart" ADD CONSTRAINT "FK_User_TO_Cart_1" FOREIGN KEY (
	"user_id"
)
REFERENCES "User" (
	"user_id"
);

ALTER TABLE "Cart" ADD CONSTRAINT "FK_Menu_TO_Cart_1" FOREIGN KEY (
	"menu_id"
)
REFERENCES "Menu" (
	"menu_id"
);

ALTER TABLE "Review" ADD CONSTRAINT "FK_User_TO_Review_1" FOREIGN KEY (
	"user_id"
)
REFERENCES "User" (
	"user_id"
);

ALTER TABLE "Review" ADD CONSTRAINT "FK_Store_TO_Review_1" FOREIGN KEY (
	"store_id"
)
REFERENCES "Store" (
	"store_id"
);

ALTER TABLE "Favorite" ADD CONSTRAINT "FK_User_TO_Favorite_1" FOREIGN KEY (
	"user_id"
)
REFERENCES "User" (
	"user_id"
);

ALTER TABLE "Favorite" ADD CONSTRAINT "FK_Store_TO_Favorite_1" FOREIGN KEY (
	"store_id"
)
REFERENCES "Store" (
	"store_id"
);

ALTER TABLE "Order_Menu" ADD CONSTRAINT "FK_Order_TO_Order_Menu_1" FOREIGN KEY (
	"order_id"
)
REFERENCES "Order" (
	"order_id"
);

ALTER TABLE "Order_Menu" ADD CONSTRAINT "FK_Menu_TO_Order_Menu_1" FOREIGN KEY (
	"menu_id2"
)
REFERENCES "Menu" (
	"menu_id"
);

ALTER TABLE "operation_hours" ADD CONSTRAINT "FK_Store_TO_operation_hours_1" FOREIGN KEY (
	"store_id"
)
REFERENCES "Store" (
	"store_id"
);

