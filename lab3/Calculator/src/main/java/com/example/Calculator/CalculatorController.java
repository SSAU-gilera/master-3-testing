package com.example.Calculator;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
public class CalculatorController {
    private final CalculatorFactory calculatorFactory;
    private final CalculatorRepository calculatorRepository;

    @Autowired
    public CalculatorController(CalculatorFactory calculatorFactory, CalculatorRepository calculatorRepository) {
        this.calculatorFactory = calculatorFactory;
        this.calculatorRepository = calculatorRepository;
    }

    @GetMapping("/calculate/{num1}/{base1}/{num2}/{base2}/{operation}")
    public ResponseEntity<String> calculate(
            @PathVariable("num1") String num1,
            @PathVariable("base1") String base1,
            @PathVariable("num2") String num2,
            @PathVariable("base2") String base2,
            @PathVariable("operation") String operation
    ) {
        Calculator calculator = calculatorFactory.createCalculator(base1, operation);
        String result = calculator.calculate(operation, base1, num1, num2);

        Random rn = new Random();
        int operationId = rn.nextInt(999999 - 100000 + 1) + 100000;

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());

        OperationEntity operationEntity = new OperationEntity();
        operationEntity.setOperationId(operationId);
        operationEntity.setFirstNum(num1);
        operationEntity.setFirstNumSystem(base1);
        operationEntity.setSecondNum(num2);
        operationEntity.setSecondNumSystem(base2);
        operationEntity.setDate(sqlDate);
        operationEntity.setTime(sqlTime);
        operationEntity.setOperationType(operation);
        operationEntity.setResult(result);

        calculatorRepository.save(operationEntity);

        return ResponseEntity.ok("Result: " + result);
    }

    @GetMapping("/getoperations")
    public ResponseEntity<List<OperationEntity>> getOperations( ) {
        List<OperationEntity> operationEntities = calculatorRepository.findAll();
        ResponseEntity<List<OperationEntity>> response;
        if (operationEntities == null || operationEntities.isEmpty()) {
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            response = new ResponseEntity<>(operationEntities, HttpStatus.OK);
        }
        return response;
    }

    @GetMapping("/getoperations/{operation}")
    public ResponseEntity<List<OperationEntity>> getOperationsByOperationType(@PathVariable("operation") String operationType) {
        List<OperationEntity> operationEntities = calculatorRepository.findAllByOperationType(operationType);

        ResponseEntity<List<OperationEntity>> response;
        if (operationEntities == null || operationEntities.isEmpty()) {
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            response = new ResponseEntity<>(operationEntities, HttpStatus.OK);
        }
        return response;
    }

    @GetMapping("/getoperations/{fromDate}/{toDate}/{base1}/{base2}/{operation}")
    public ResponseEntity<List<OperationEntity>> getOperations(
            @PathVariable("fromDate") String fromDate,
            @PathVariable("toDate") String toDate,
            @PathVariable("base1") String base1,
            @PathVariable("base2") String base2,
            @PathVariable("operation") String operation
    ) {
        Date from = Date.valueOf(fromDate);
        Date to = Date.valueOf(toDate);

        List<OperationEntity> operationEntities = calculatorRepository.findAllByDateBetweenAndOperationTypeAndFirstNumSystemAndSecondNumSystem(
                from, to, operation, base1, base2);

        ResponseEntity<List<OperationEntity>> response;
        if (operationEntities == null || operationEntities.isEmpty()) {
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            response = new ResponseEntity<>(operationEntities, HttpStatus.OK);
        }

        return response;
    }
}