package org.jerry.entity;
// Company.java 는 org.jerry.entity 패키지 안에 속해있음.
// entity는 DB 테이블과 매핑되는 클래스가 들어가는 패키지

import jakarta.persistence.*;

@Entity
@Table(name = "company")
public class Company {

    @Id // 🔹 기본키를 companyCode로 변경
    @Column(name = "company_code", nullable = false, unique = true)
    private String companyCode;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    public Company() {}

    public Company(String companyCode, String companyName) {
        this.companyCode = companyCode;
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
