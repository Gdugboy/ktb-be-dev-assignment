package org.jerry.repository;

import org.jerry.entity.StockHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate; // 자바에서 날짜를 표현하는 클래스
import java.util.List; // List 자료구조 사용. 주식 거래 내역 여러 개 가져올 때

// JpaRepository를 상속받음. StockHistory 테이블을 관리하는 Repository, id 타입 Long
public interface StockHistoryRepository extends JpaRepository<StockHistory, String> {
    List<StockHistory> findByCompanyCodeAndTradeDateBetween(String companyCode, LocalDate startDate, LocalDate endDate);
}// 기업코드(company_code)와 날짜 범위(trade_date)를 기준으로 StockHistory 데이터 찾는 메서드
// 특정회사 주가 데이터를 원하는 날짜 범위 내에서 가져오는 기능.