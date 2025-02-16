package org.jerry.service;

import org.jerry.entity.Company;
import org.jerry.entity.StockHistory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.jerry.repository.CompanyRepository;
import org.jerry.repository.StockHistoryRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service // 이 클래스가 '서비스' 계층이라는 것을 스프링에게 알림
public class StockService {

    private final CompanyRepository companyRepository; // 기업 정보를 관리하는 Repository
    private final StockHistoryRepository stockHistoryRepository; // 주가 정보를 관리하는 Repository
    private final String apiKey; // API 키를 저장할 변수

    // 생성자 주입 (Spring이 자동으로 Repository와 API Key를 넣어줌)
    public StockService(CompanyRepository companyRepository, StockHistoryRepository stockHistoryRepository,
                        @Value("${api.key}") String apiKey) {
        this.companyRepository = companyRepository;
        this.stockHistoryRepository = stockHistoryRepository;
        this.apiKey = apiKey; // application.yml에서 설정한 API 키를 가져옴
    }

    // 🔹 API 키 검증 메서드
    public boolean isValidApiKey(String requestApiKey) {
        return apiKey.equals(requestApiKey); // API 키가 맞는지 확인
    }

    // 🔹 특정 기업의 주가 데이터를 조회하는 메서드
    public List<StockHistory> getStockHistory(String companyCode, LocalDate startDate, LocalDate endDate) {
        // ✅ `companyCode`가 `company` 엔티티에 존재하는지 확인
        Optional<Company> company = companyRepository.findByCompanyCode(companyCode);

        if (company.isEmpty()) { // 기업 코드가 없으면 예외 발생
            throw new IllegalArgumentException("존재하지 않는 기업 코드입니다: " + companyCode);
        }

        // ✅ `StockHistory`에서 `company_code`를 직접 사용하도록 변경
        return stockHistoryRepository.findByCompanyCodeAndTradeDateBetween(companyCode, startDate, endDate);
    }
}