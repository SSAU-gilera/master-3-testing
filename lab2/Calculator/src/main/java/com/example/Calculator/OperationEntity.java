package com.example.Calculator;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "operations")
public class OperationEntity {

    @jakarta.persistence.Id
    @Id
    @Column(name = "operation_id")
    private Integer operationId;

    @Column(name = "first_num")
    private String firstNum;

    @Column(name = "first_num_system")
    private String firstNumSystem;

    @Column(name = "second_num")
    private String secondNum;

    @Column(name = "second_num_system")
    private String secondNumSystem;

    @Column(name = "date")
    private Date date;

    @Column(name = "time")
    private Time time;

    @Column(name = "operation_type")
    private String operationType;

    @Column(name = "result")
    private String result;

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public Integer getOperationId() {
        return operationId;
    }

    public String getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(String firstNum) {
        this.firstNum = firstNum;
    }

    public String getFirstNumSystem() {
        return firstNumSystem;
    }

    public void setFirstNumSystem(String firstNumSystem) {
        this.firstNumSystem = firstNumSystem;
    }

    public String getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(String secondNum) {
        this.secondNum = secondNum;
    }

    public String getSecondNumSystem() {
        return secondNumSystem;
    }

    public void setSecondNumSystem(String secondNumSystem) {
        this.secondNumSystem = secondNumSystem;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


}
