# 화요일이라는 데이터 베이스가 존재하면 삭제
DROP DATABASE IF EXISTS `Hwa`;
# 화요일 데이터 베이스 생성
CREATE DATABASE `Hwa`;
# 데이터베이스 화요일 실행
USE `Hwa`;

# care 테이블 생성 / id 정수, 음수 없이, 공백 없이, 값 자동 증가 + pk로 설정
CREATE TABLE `care` (
	`id`	INT	UNSIGNED NOT NULL AUTO_INCREMENT,
	`care`	VARCHAR(10)	NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE `type` (
	`id`	INT	UNSIGNED NOT NULL AUTO_INCREMENT,
	`type`	VARCHAR(10)	NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE `member` (
	`id`	INT	UNSIGNED NOT NULL AUTO_INCREMENT,
	`member_email`	VARCHAR(40)	NOT NULL,
	`member_pwd`	VARCHAR(40)	NOT NULL,
	`member_birth`	VARCHAR(40)	NOT NULL,
	`member_gender`	VARCHAR(40) NOT NULL,
	`member_name`	VARCHAR(40)	NOT NULL,
	PRIMARY KEY(id)
);


# member_id, care_id, type_id는 product에서 참조하기 위해 fk로 설정
CREATE TABLE `product` (
	`id`	INT	UNSIGNED NOT NULL AUTO_INCREMENT,
	`care_id`	INT	UNSIGNED,
	`type_id`	INT	UNSIGNED,
	`product_name`	VARCHAR(100)	NOT NULL,
	`product_brand`	VARCHAR(100)	NOT NULL,
	`product_capacity`	VARCHAR(100)	NOT NULL,
	`product_price`	VARCHAR(100)	NOT NULL,
	`product_explanation`	TEXT	NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(care_id) REFERENCES `care`(id),
	FOREIGN KEY(type_id) REFERENCES `type`(id)
);

CREATE TABLE `review` (
	`id`	INT	UNSIGNED NOT NULL  AUTO_INCREMENT,
	`member_id`	INT	UNSIGNED,
	`product_id` INT UNSIGNED,
	`review`	TEXT	NULL,
	`grade`	INT	NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(member_id) REFERENCES `member`(id),
	FOREIGN KEY(product_id) REFERENCES `product`(id)
);

# care 테이블에 값 입력
INSERT INTO `care` SET care = 'skin';
INSERT INTO `care` SET care = 'body';
INSERT INTO `care` SET care = 'SPF';
INSERT INTO `care` SET care = 'hair';
INSERT INTO `care` SET care = 'cleansing';

INSERT INTO `type` SET `type` = 'combi';
INSERT INTO `type` SET `type` = 'dry';
INSERT INTO `type` SET `type` = 'oily';
INSERT INTO `type` SET `type` = 'sensitive';
INSERT INTO `type` SET `type` = 'neuatral';

INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','1','1025 독도 로션','라운드랩','200','20000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','1','1025 독도 토너','라운드랩','200','15000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','1','E 로션','아토팜','300','50000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','1','다이브인 저분자 히말루론산 세럼','토리든','50','22000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','1','다이브인 저분자 히말루론산 토너','토리든','300','21000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','1','더 심플 데일리 로션','싸이닉','145','18000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','1','레티노타이트닝 비타민A 레니톨 앰플','닥터오라클','30','29000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','1','세라마이드 아토 로션','일리윤','350','21900', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','1','아쿠아 스쿠알란 세럼','에스네이처','50','21000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','1','아쿠아 오아시스 토너','에스네이처','300','25000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','1','아쿠아 콜라겐 펩타이드 트리플 젤 에센스','에스네이처','50','43000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','1','어성초 케어 세럼','다자연','30','20000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','1','인텐시브 리파이닝 에멀젼','라보드레','50','33000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','1','캐롯 카로틴 카밍 워터 패드','스킨푸드','250','26000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','1','파워10 감초줄렌 젤리패드','잇츠스킨','120','25000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','2','1025 독도 로션','라운드랩','200','20000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','2','1025 독도 토너','라운드랩','200','15000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','2','E 로션','아토팜','300','50000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','2','다이브인 저분자 히말루론산 세럼','토리든','50','22000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','2','다이브인 저분자 히말루론산 토너','토리든','300','21000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','2','더 심플 데일리 로션','싸이닉','145','18000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','2','레티노타이트닝 비타민A 레니톨 앰플','닥터오라클','30','29000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','2','세라마이드 아토 로션','일리윤','350','21900', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','2','아미노산 앰플','딕셔니스트','30','38000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','2','아쿠아 오아시스 토너','에스네이처','300','25000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','2','아쿠아 콜라겐 펩타이드 트리플 젤 에센스','에스네이처','50','43000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','2','아토베리어 365 로션','에스트라','150','31000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','2','캐롯 카로틴 카밍 워터 패드','스킨푸드','250','26000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','2','파워10 감초줄렌 젤리패드','잇츠스킨','120','25000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','2','화이트 트러플 더블 세럼 앤 크림','달바','70','78000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','3','1025 독도 로션','라운드랩','200','20000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','3','1025 독도 토너','라운드랩','500','29800', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','3','DMT로션','피지오겔','200','22700', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','3','구달 청귤 비타C 잡티케어 세럼','구달','30','22400', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','3','닥터지 로얄 블랙 스네일 퍼스트 에센스','닥터지','165','24700', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','3','드라마티컬리 디퍼런트 모이스춰라이징 로션/젤','크리니크','125','55000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','3','바이오더마 하이드라비오 에센스로션','바이오더마','200','33000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','3','아누아 어성초 80 수분 진정 앰플','아누아','30','29200', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','3','아벤느 이드랑스 에센스-인-로션','아벤느','100','30000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','3','아토베리어 365 로션','에스트라','150','31000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','3','어성초 77 수딩 토너','아누아','500','32000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','3','어성초 카밍 토너 스킨부스터','아비브','200','27300', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','3','웰라쥬 리얼 히알루로닉 블루100 앰플','웰라쥬','100','22400', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','3','유세린 하이알루론 어드벤스드 AOX 에센스','유세린','30','48000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','3','차앤박(CNP) 프로폴리스 액티브 앰플','차앤박','30','37500', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','3','토리든 다이브인 세럼','토리든','50','24500', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','3','트릭세라 뉴트리-플루이드 로션','아벤느','200','29700', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','3','티트리시카 수딩토너','브링그린','510','17600', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','3','프리메라 오가니언스 워터리 에센스','프리메라','230','24000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','3','하이드라비오 토너','바이오더마','500','28500', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','4','1025 독도 로션','라운드랩','200','20000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','4','1025 독도 토너','라운드랩','200','15000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','4','E 로션','아토팜','300','50000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','4','노스카나인 트러블 세럼','파티온','30','24000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','4','다이브인 저분자 히말루론산 세럼','토리든','50','22000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','4','다이브인 저분자 히말루론산 토너','토리든','300','21000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','4','더 심플 데일리 로션','싸이닉','145','18000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','4','레티노타이트닝 비타민A 레티놀 앰플','닥터오라클','30','29000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','4','세라마이드 아토 로션','일리윤','350','21900', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','4','아쿠아 스쿠알란 세럼','에스네이처','50','21000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','4','아쿠아 오아시스 토너','에스네이처','300','25000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','4','아쿠아 콜라겐 펩타이드 트리플 젤 에센스','에스네이처','50','43000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','4','아토베리어 365 로션','에스트라','150','31000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','4','캐롯 카로틴 카밍 워터 패드','스킨푸드','250','26000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','4','파워10 감초줄렌 젤리패드','잇츠스킨','120','25000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','5','1025 독도 로션','라운드랩','200','20000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','5','1025 독도 토너','라운드랩','200','15000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','5','다이브인 저분자 히말루론산 토너','토리든','300','21000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','5','다이브인 저분자 히알루론산 세럼','토리든','50','22000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','5','더 심플 데일리 로션','싸이닉','145','18000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','5','레드 블레미쉬 멀티 플루이드','닥터지','100','95000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','5','레티노타이트닝 비타민A 레티놀 앰플','닥터오라클','30','29000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','5','비파다 바이옴 앰플 토너','마녀공장','400','25000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','5','세라마이드 아토 로션','일리윤','350','21900', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','5','아쿠아 스쿠알란 세럼','에스네이처','50','21000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','5','아쿠아 오아시스 토너','에스네이처','300','25000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','5','아쿠아 콜라겐 펩타이드 트리플 젤 에센스','에스네이처','50','43000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','5','어성초 77 수딩 토너','아누아','500','32000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','5','인텐시브 리파이닝 에멀젼','라보드레','50','33000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '1','5','화이트 트러플 더블 세럼 앤 크림','달바','70','78000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','1','마일드 세라마이드 워시','나드','500','18000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','1','세라마이드 아토 6.0 탑투로 워시','일리윤','500','16500', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','1','약산성 어성초 바디워시','닥터올가','500','33000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','1','여드름 기능성 바디워시','블랑네이처','500','45000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','1','탑투토 워시','아토팜','460','35000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','2','마일드 세라마이드 워시','나드','500','18000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','2','세라마이드 아토 6.0 탑투로 워시','일리윤','500','16500', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','2','아토워시&샴푸 블루라벨','편강율','290','16000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','2','약산성 어성초 바디워시','닥터올가','500','33000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','2','탑투토 워시','아토팜','460','35000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','3','바이워시 (4종택1)','아비노','532','12900', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','3','세라마이드 아토 6.0 탑투로 워시','일리윤','500','16500', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','3','신양유 바디워시','레이비','1150','15800', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','3','클리어 아크네 바디워시','낫포유','500','16900', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','3','퍼퓸 바디워시','밀크바오밥','500','14800', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','4','마일드 세라마이드 워시','나드','500','18000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','4','세라마이드 아토 6.0 탑투로 워시','일리윤','500','16500', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','4','약산성 어성초 바디워시','닥터올가','500','33000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','4','여드름 기능성 바디워시','블랑네이처','500','45000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','4','탑투토 워시','아토팜','460','35000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','5','마일드 세라마이드 워시','나드','500','18000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','5','세라마이드 아토 6.0 탑투로 워시','일리윤','500','16500', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','5','아토워시&샴푸 블루라벨','편강율','290','16000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','5','약산성 어성초 바디워시','닥터올가','500','33000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '2','5','탑투토 워시','아토팜','460','35000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','1','그린 마일드 업 선 플러스[SPF50+/PA++++]','닥터지','50','29000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','1','다이브인 무기자차 마일드 선크림 [SPF50+/PA++++]','토리든','60','23000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','1','선뮤즈 모이스처 선크림 [SPF50+/PA++++]','비플레인','50','26000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','1','엔조이 슈퍼 마일드 선 에센스 [SPF50+/PA++++]','싸이닉','50','15000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','1','자작나무 수분 선크림[SPF50+/PA+++++]','라운드랩','50','25000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','2','그린 마일드 업 선 플러스[SPF50+/PA++++]','닥터지','50','29000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','2','다이브인 무기자차 마일드 선크림 [SPF50+/PA++++]','토리든','60','23000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','2','워터풀 선크림[SPF50+/PA++++][에센스]','달바','50','34000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','2','워터풀 선크림[SPF50+/PA++++][톤업]','달바','50','34000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','2','자작나무 수분 선크림[SPF50+/PA+++++]','라운드랩','50','25000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','3','그린 마일드 업 선 플러스[SPF50+/PA++++]','닥터지','50','29000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','3','레이저 썬스크린','셀퓨전씨','35','29400', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','3','맑은 어성초 진정 수분 선크림','구달','50','16200', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','3','워터풀 톤업 선크림','달바','50','23800', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','3','톤업프로텍션선SPF42','차앤박','50','27500', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','4','그린 마일드 업 선 플러스[SPF50+/PA++++]','닥터지','50','29000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','4','다이브인 무기자차 마일드 선크림[SPF50+/PA++++]','토리든','60','23000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','4','선뮤즈 모이스처 선크림 [SPF50+/PA++++]','비플레인','50','26000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','4','워터풀 선크림[SPF50+/PA++++][톤업]','달바','50','34000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','4','자작나무 수분 선크림[SPF50+/PA+++++]','라운드랩','50','25000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','5','그린 마일드 업 선 플러스[SPF50+/PA++++]','닥터지','50','29000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','5','다이브인 무기자차 마일드 선크림[SPF50+/PA++++]','토리든','60','23000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','5','워터풀 선크림[SPF50+/PA++++][에센스]','달바','50','34000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','5','워터풀 선크림[SPF50+/PA++++][톤업]','달바','50','34000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '3','5','자작나무 수분 선크림[SPF50+/PA+++++]','라운드랩','50','25000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '4','1','두피가 편한 샴푸','이솔','500','18800', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '4','1','바이옥실 안티 헤어로스 샴푸','마녀공장','480','33000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '4','1','센시티브 두피 케어 샴푸','나드','500','24000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '4','1','아르간 스템셀 에이지 디파잉 샴푸','안달로우내추럴스','340','18000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '4','1','탈모증상완화 두피강화 샴푸','라보에이치','400','24000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','1','1025 독도 클렌져','라운드랩','150','13000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','1','녹두 약산성 클렌징폼','비플레인','80','14000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','1','다이브인 저분자 히알루론산 클렌징폼','토리든','150','15000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','1','세이프 미 릴리프 모이스처 클렌징폼','메이크프렘','150','16000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','1','아쿠아 라이스 약산성 클렌징폼','에스네이처','160','19000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','2','1025 독도 클렌져','라운드랩','150','13000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','2','녹두 약산성 클렌징폼','비플레인','80','14000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','2','다이브인 저분자 히알루론산 클렌징폼','토리든','150','15000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','2','세이프 미 릴리프 모이스처 클렌징폼','메이크프렘','150','16000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','2','아쿠아 라이스 약산성 클렌징폼','에스네이처','160','19000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','3','1025 독도 클렌저','라운드랩','150','13000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','3','센카 퍼펙트 휩 페이셜 워시','센카','120','11900', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','3','아크네 폼 클렌저 어성초 폼 대용량','아비브','250','16800', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','3','약산성 레드 블레미쉬 클리어 수딩폼','닥터지','225','15300', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','3','클린잇제로 포어 클래리파잉 폼 클렌저','바닐라코','300','11200', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','4','1025 독도 클렌져','라운드랩','150','13000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','4','녹두 약산성 클렌징폼','비플레인','80','14000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','4','다이브인 저분자 히알루론산 클렌징폼','토리든','150','15000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','4','세이프 미 릴리프 모이스처 클렌징폼','메이크프렘','150','16000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','4','아쿠아 라이스 약산성 클렌징폼','에스네이처','160','19000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','5','1025 독도 클렌저','라운드랩','150','13000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','5','녹두 약산성 클렌징폼','비플레인','80','14000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','5','세이프 미 릴리프 모이스처 클렌징 폼','메이크프렘','150','16000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','5','아쿠아 라이스 약산성 클렌징폼','에스네이처','160','19000', 'product_explanation');
INSERT INTO product ( care_id, type_id, product_name, product_brand, product_capacity, product_price, product_explanation ) VALUES ( '5','5','퓨어&딥 클렌징 폼','마녀공장','100','14000', 'product_explanation');

INSERT INTO `member` (member_email , member_pwd, member_birth, member_gender, member_name) VALUES ( 'scy@gmail.com', 'scy', '2001.01.28', '여', '송채');
INSERT INTO `member` (member_email , member_pwd, member_birth, member_gender, member_name) VALUES ( 'khh@gmail.com', 'khh', '2001.01.28', '남', '김호');
INSERT INTO `member` (member_email , member_pwd, member_birth, member_gender, member_name) VALUES ( 'ohw@gmail.com', 'ohw', '2001.01.28', '남', '오길');
INSERT INTO `member` (member_email , member_pwd, member_birth, member_gender, member_name) VALUES ( 'his@gmail.com', 'his', '2001.01.28', '남', '황인');
INSERT INTO `member` (member_email , member_pwd, member_birth, member_gender, member_name) VALUES ( 'kde@gmail.com', 'kde', '2001.01.28', '여', '김다');
INSERT INTO `member` (member_email , member_pwd, member_birth, member_gender, member_name) VALUES ( 'sjh@gmail.com', 'sjh', '2001.01.28', '여', '송주');
INSERT INTO `member` (member_email , member_pwd, member_birth, member_gender, member_name) VALUES ( 'koy@gmail.com', 'koy', '2001.01.28', '남', '고우');
INSERT INTO `member` (member_email , member_pwd, member_birth, member_gender, member_name) VALUES ( 'pcg@gmail.com', 'pcg', '2001.01.28', '남', '박창');
INSERT INTO `member` (member_email , member_pwd, member_birth, member_gender, member_name) VALUES ( 'ksy@gmail.com', 'ksy', '2001.01.28', '남', '김상');

INSERT INTO review ( member_id, product_id, review, grade ) VALUES ( '3', '100', '좋아요', '1');
INSERT INTO review ( member_id, product_id, review, grade ) VALUES ( '4', '101', '싫어요', '2');
INSERT INTO review ( member_id, product_id, review, grade ) VALUES ( '5', '102', '별로에요', '3');
INSERT INTO review ( member_id, product_id, review, grade ) VALUES ( '6', '103', '그저그래요', '4');
INSERT INTO review ( member_id, product_id, review, grade ) VALUES ( '7', '104', '완벽해요', '3');

# 테이블 확인
SELECT * FROM review;
SELECT * FROM `member`;
SELECT * FROM product;
SELECT * FROM care;
SELECT * FROM `type`;

# product 테이블의 product.name을 as를 사용해 건성 + 스킨케어 제품으로 나오게 바꾸고 값은 product.type_id = 2 이면서 product.care_id = 1인 값을 불러 옴.
# 해당 type_id와 care_id 는 위에서 설정 했음
SELECT product.product_name AS `건성 + 스킨케어 제품` FROM product WHERE product.type_id = 2 AND product.care_id = 1;
SELECT product.product_name FROM product WHERE product.type_id = 2 AND product.care_id = 1;

# 리뷰에서 '좋'이 들어간 걸 찾기
SELECT * FROM review WHERE review LIKE '좋%';

SHOW DATABASES;
USE Hwa;
SHOW TABLES;
SELECT * FROM product;
SELECT * FROM care;


desc `member`;

##SELECT A.*, M.`member_name` AS extra_writerName
##FROM product AS A
##INNER JOIN `member` AS M
##ORDER BY A.id DESC;

SELECT * FROM product;
SELECT * FROM care;
SELECT * FROM `type`;

SELECT product.id, care.id, `type`.id, product.product_name, product.product_brand, product.product_capacity, product.product_price, product.product_explanation
FROM product
JOIN `care` on product.id = `care`.id
JOIN `type` on product.id = `type`.id;

DESC review;
SELECT * FROM review;

DELETE FROM review
WHERE review.product_id = 161;

SELECT * FROM `member`;
SELECT * FROM `review`;


SELECT P.id AS '상품번호', P.product_name AS '상품이름', M.member_name AS '작성자', R.review AS '내용', R.grade AS '별점'
FROM `review` AS R
INNER JOIN `member` AS M
ON R.`member_id` = M.id
INNER JOIN `product` AS P
ON R.`product_id` = P.id
WHERE M.`member_name` LIKE CONCAT('%', '채', '%')
AND R.product_id = 3;
