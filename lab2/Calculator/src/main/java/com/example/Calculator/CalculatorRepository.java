package com.example.Calculator;


import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface CalculatorRepository extends JpaRepository<OperationEntity, Long> {
        List<OperationEntity> findAllByDateBetweenAndOperationTypeAndFirstNumSystemAndSecondNumSystem(
                Date from, Date to, String operationType, String firstNumSystem, String secondNumSystem
        );
}