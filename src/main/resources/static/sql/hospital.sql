/**
    특정 지역 + 진료 과목 ex) 경기도 수원시 피부과, 서울시 강남구 성형외과
 */
select road_name_address, hospital_name
from `likelion-db`.nation_wide_hospitals
where road_name_address like "%수원%"
  and hospital_name like "%피부과%";

/**
    business_type_name 보건소, 보건지소인 데이터
    in 연산자 사용
 */
SELECT business_type_name, hospital_name, road_name_address
FROM `likelion-db`.nation_wide_hospitals
where business_type_name in ('보건소', '보건지소');