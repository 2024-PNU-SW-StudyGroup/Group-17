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
	"created_date"	timestamp	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"modified_date"	timestamp	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"user_status"	varchar(1)		NOT NULL
);

COMMENT ON COLUMN "User"."password" IS '암호화할 것을 고려해서 넉넉하게 잡음';

COMMENT ON COLUMN "User"."join_type" IS 'INAPP: 자체 이메일 가입, KAKAO: 카카오가입';

COMMENT ON COLUMN "User"."grade" IS '필요한가? 나무키우기로 할 거면 레벨로 설정해야 되려나?';

COMMENT ON COLUMN "User"."eco_points" IS '포인트에 따라 나무 이미지 다르게?';

COMMENT ON COLUMN "User"."role" IS '회원';

COMMENT ON COLUMN "User"."user_status" IS 'Y: 활성화 회원, N: 탈퇴 회원';

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
	"operation_hours"	varchar(255)		NULL,
	"open_hour"	TIME		NOT NULL,
	"close_hour"	TIME		NULL,
	"closed_days"	varchar(255)		NULL,
	"store_rating"	int(1)	DEFAULT 0	NOT NULL,
	"favorite_count"	int(11)	DEFAULT 0	NOT NULL,
	"review_count"	int(11)	DEFAULT 0	NOT NULL,
	"ceo_name"	varchar(20)		NOT NULL,
	"company_name"	varchar(20)		NOT NULL,
	"company_address"	varchar(255)		NOT NULL,
	"business_number"	varchar(12)		NOT NULL,
	"country_of_origin"	varchar(255)		NULL,
	"created_at"	TIMESTAMP	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"updated_at"	TIMESTAMP	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"status"	varchar(1)	DEFAULT Y	NOT NULL
);

COMMENT ON COLUMN "Store"."pickup_time" IS '일단 19:00~22:00 형식으로 받는다고 생각함';

COMMENT ON COLUMN "Store"."operation_hours" IS '월~일 별로 오픈, 마감시간을 따로 받아야 하나?';

COMMENT ON COLUMN "Store"."store_rating" IS '1~5점';

COMMENT ON COLUMN "Store"."ceo_name" IS '어';

COMMENT ON COLUMN "Store"."company_name" IS '쩌';

COMMENT ON COLUMN "Store"."company_address" IS '지';

COMMENT ON COLUMN "Store"."business_number" IS '이';

COMMENT ON COLUMN "Store"."country_of_origin" IS '건?';

COMMENT ON COLUMN "Store"."updated_at" IS '이후 수정 시간을 자동으로 갱신하려면 트리거 추가헤야 함';

COMMENT ON COLUMN "Store"."status" IS 'Y: 활성화, N: 삭제';

CREATE TABLE "Menu" (
	"menu_id"	bigint(20)		NOT NULL,
	"store_id"	bigint(20)		NOT NULL,
	"menu_category"	varchar(100)		NOT NULL,
	"menu_name"	varchar(255)		NOT NULL,
	"menu_price"	int(11)		NOT NULL,
	"menu_discount_price"	int(11)		NOT NULL,
	"menu_picture"	text		NULL,
	"popularity"	tinyint(1)	DEFAULT 0	NOT NULL,
	"created_date"	timestamp	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"modified_date"	timestamp	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"status"	varchar(255)		NOT NULL
);

COMMENT ON COLUMN "Menu"."status" IS '판매 상태(판매 중, 품절 등)';

CREATE TABLE "MenuOption" (
	"menu_option_id"	bigint(20)		NOT NULL,
	"menu_id"	bigint(20)		NOT NULL,
	"menu_detail_name"	varchar(255)		NULL,
	"menu_content"	varchar(255)		NULL,
	"menu_pickup_time"	time		NULL,
	"created_date"	timestmap	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"modified_date"	timestamp	DEFAULT CURRENT_TIMESTAMP	NOT NULL
);

COMMENT ON COLUMN "MenuOption"."menu_pickup_time" IS 'type을 뭘로 해야 되지??';

CREATE TABLE "Order" (
	"order_id"	bigint(20)		NOT NULL,
	"user_id"	bigint(20)		NOT NULL,
	"order_time"	varchar(255)		NOT NULL,
	"payment_method"	varchar(255)		NOT NULL,
	"total_price"	int(11)		NOT NULL,
	"create_date"	timestamp	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"modified_date"	timestamp	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"status"	varchar(255)		NOT NULL
);

COMMENT ON COLUMN "Order"."status" IS '주문 완료, 픽업 완료';

CREATE TABLE "Cart" (
	"cart_id"	bigint(20)		NOT NULL,
	"user_id"	bigint(20)		NOT NULL,
	"menu_id"	bigint(20)		NOT NULL,
	"cart_quantity"	int(11)		NOT NULL,
	"created_time"	timestamp	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"modified_time"	timestamp	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"status"	varchar(255)		NOT NULL
);

COMMENT ON COLUMN "Cart"."cart_quantity" IS '장바구니에 담은 메뉴의 수량';

COMMENT ON COLUMN "Cart"."status" IS '일반';

CREATE TABLE "StoreImage" (
	"store_image_id"	bigint(20)		NOT NULL,
	"store_id"	bigint(20)		NOT NULL,
	"store_picture_url"	text		NOT NULL,
	"created_time"	timestamp	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"modified_time"	timestamp	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"status"	varchar(255)		NOT NULL
);

COMMENT ON COLUMN "StoreImage"."status" IS '일반';

CREATE TABLE "Review" (
	"review_id"	bigint(20)		NOT NULL,
	"user_id"	bigint(20)		NOT NULL,
	"store_id"	bigint(20)		NOT NULL,
	"review_rating"	int(1)		NOT NULL,
	"review_content"	varchar(255)		NULL,
	"review_picture_url"	text		NULL,
	"created_time"	timestamp	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"modified_time"	timestamp	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"status"	varchar(255)		NOT NULL
);

COMMENT ON COLUMN "Review"."review_rating" IS '1~5점';

COMMENT ON COLUMN "Review"."status" IS '일반';

CREATE TABLE "Favorite" (
	"user_id"	bigint(20)		NOT NULL,
	"store_id"	bigint(20)		NOT NULL,
	"created_time"	timestamp	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"modified_time"	timestamp	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"status"	varchar(1)		NOT NULL
);

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

CREATE TABLE "ReviewImage" (
	"review_image_id"	bigint(20)		NOT NULL,
	"review_id"	bigint(20)		NOT NULL,
	"review_picture_url"	text		NOT NULL,
	"created_time"	timestamp	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"modified_time"	timestamp	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"status"	varchar(255)		NOT NULL
);

COMMENT ON COLUMN "ReviewImage"."status" IS '일반';

CREATE TABLE "MenuImage" (
	"menu_image_id"	bigint(20)		NOT NULL,
	"menu_id"	bigint(20)		NOT NULL,
	"menu_picture_url"	text		NOT NULL,
	"created_time"	timestamp	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"modified_time"	timestamp	DEFAULT CURRENT_TIMESTAMP	NOT NULL,
	"status"	varchar(255)		NOT NULL
);

COMMENT ON COLUMN "MenuImage"."status" IS '일반';

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

ALTER TABLE "StoreImage" ADD CONSTRAINT "PK_STOREIMAGE" PRIMARY KEY (
	"store_image_id"
);

ALTER TABLE "Review" ADD CONSTRAINT "PK_REVIEW" PRIMARY KEY (
	"review_id"
);

ALTER TABLE "Coupon" ADD CONSTRAINT "PK_COUPON" PRIMARY KEY (
	"coupon_id"
);

ALTER TABLE "ReviewImage" ADD CONSTRAINT "PK_REVIEWIMAGE" PRIMARY KEY (
	"review_image_id"
);

ALTER TABLE "MenuImage" ADD CONSTRAINT "PK_MENUIMAGE" PRIMARY KEY (
	"menu_image_id"
);

ALTER TABLE "Order_Menu" ADD CONSTRAINT "PK_ORDER_MENU" PRIMARY KEY (
	"order_menu_id"
);

