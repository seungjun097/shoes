# shoesmall

# 수정사항
# member member_address 타입 변경-주소를 모두 넣기에 너무 짧음
# alter table member MODIFY member_address varchar2(200);
# member 아이디 수정은 삭제 and 수정시 session에 담긴 회원 정보도 함께 변경


#추가로 하면 좋은 것
# 1. member
# 비번 확인칸 만들기
# 아이디 비번 형식 검사 문자 숫자포함 체크

# 2. item
# 헤더의 검색기능 활성화. 페이지 이동 추가
# 리플달기 별점 추가


# 깃 이그노어 파일 적용 안될 때 캐쉬 비우기 git rm -r --cached .

#
create table cart(
    cart_key number not null primary key,
    member_key number references member(member_key) on delete set null,
    item_key number references item(item_key) on delete set null,
    item_size number not null,
    item_amount number not null,
    cart_add_date date default sysdate not null
);