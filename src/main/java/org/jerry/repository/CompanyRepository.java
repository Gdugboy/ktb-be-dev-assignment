package org.jerry.repository;
// 이 파일이 repository 패키지 안에 있음

import jakarta.persistence.Entity;
import org.jerry.entity.Company; // Company 엔티티와 매핑
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// JpaRepository(스프링 제공 인터페이스), DB에서 CRUD가능, SQL작성 필요 X
import java.util.Optional;
// 데이터 없을 경우 null 반환이 아닌, Optional을 반환해서 null 체크 안전하게

@Repository
// Company 엔티티의 데이터 관리, id 필드 타입이 Long
// 인터페이스 = 틀, CompanyRepository는 Company 데이터를 다룰 기능을 정의
// Company 엔티티(Company 테이블)을 관리하는 Repository, Company의 id 필드가 long 타입
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByCompanyCode(String companyCode);
} // 기업 종목 코드로 Company를 찾는 메서드