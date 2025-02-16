package org.jerry.controller;

import org.jerry.entity.StockHistory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.jerry.service.StockService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController // 🔹 이 클래스가 'API 컨트롤러' 역할을 한다고 스프링에게 알림
@RequestMapping("/api/stocks") // 🔹 모든 API 엔드포인트의 기본 URL을 "/api/stocks"로 설정
public class StockController {

    private final StockService stockService; // 서비스 계층을 사용하기 위한 필드

    // 생성자 주입 (Spring이 자동으로 StockService를 넣어줌)
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    // 🔹 특정 기업의 주가 데이터를 조회하는 API
    @GetMapping
    public ResponseEntity<?> getStockHistory(
            @RequestParam("companyCode") String companyCode, // 🔹 기업 종목 코드 (필수)
            @RequestParam("startDate") String startDateStr, // 🔹 조회 시작 날짜 (문자열 형태)
            @RequestParam("endDate") String endDateStr, // 🔹 조회 종료 날짜 (문자열 형태)
            @RequestHeader(value = "x-api-key", required = false) String headerApiKey, // 🔹 헤더에서 API 키 받기
            @RequestParam(value = "apikey", required = false) String paramApiKey // 🔹 쿼리 파라미터에서 API 키 받기
    ) {
        // 🔹 API 키 검증 (헤더 OR 쿼리 파라미터에서 받은 값이 유효한지 체크)
        String requestApiKey = (headerApiKey != null) ? headerApiKey : paramApiKey;
        if (requestApiKey == null || !stockService.isValidApiKey(requestApiKey)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("403 Forbidden: 잘못된 API 키입니다.");
        }

        try {
            // 🔹 날짜 변환 (String -> LocalDate)
            LocalDate startDate = LocalDate.parse(startDateStr);
            LocalDate endDate = LocalDate.parse(endDateStr);

            // 🔹 서비스 계층을 호출하여 주가 데이터 조회
            List<StockHistory> stockHistoryList = stockService.getStockHistory(companyCode, startDate, endDate);

            return ResponseEntity.ok(stockHistoryList); // 🔹 정상 응답 (200 OK)
        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("400 Bad Request: 날짜 형식이 올바르지 않습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // 🔹 기업 코드가 없는 경우
        }
    }
}