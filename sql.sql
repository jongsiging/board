select * from BOARD
select * from COM_CODE
select * from user_info
insert into USER_INFO 
(USER_ID
, USER_PW
, USER_NAME
, USER_PHONE1
, USER_PHONE2
, USER_PHONE3 
, USER_ADDR1
, USER_ADDR2 
, USER_COMPANY
, CREATOR
, CREATE_TIME
, MODIFIER
, MODIFIED_TIME)
values ('m001'
, '1111'
, 'kim'
, '010'
, '8079'
, '8050'
, 'seoul'
, 'korea'
, 'next'
, 'kim'
, sysdate
, 'kim'
, sysdate)
update user_info set USER_PW = '1111' where USER_ID = 'm002'