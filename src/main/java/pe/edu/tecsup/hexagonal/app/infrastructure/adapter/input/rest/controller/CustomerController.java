package pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.tecsup.hexagonal.app.application.port.input.CustomerUseCase;
import pe.edu.tecsup.hexagonal.app.domain.model.Customer;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.common.ApiResponse;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.common.ApiResponseFactory;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.request.CustomerRequest;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.response.CustomerResponse;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.mapper.CustomerMapper;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    private final CustomerUseCase customerUseCase;
    private final CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(@RequestBody CustomerRequest customerRequest){
        Customer newCustomer = this.customerMapper.toDomain(customerRequest);
        Customer createCustomer = this.customerUseCase.create(newCustomer);
        if(createCustomer==null){
            return ResponseEntity.badRequest().body(ApiResponseFactory.error(
                    "Not exits Customer",
                    HttpStatus.BAD_REQUEST.value()
            ));
        }
        CustomerResponse customerResponse = this.customerMapper.toResponse(createCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponseFactory.success(
                        "Customer created successfully",
                        HttpStatus.CREATED.value(),
                        customerResponse
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> findAllCustomer(){
        List<Customer> foundCustomer = this.customerUseCase.findAll();
        return ResponseEntity.ok().body(ApiResponseFactory.success(
                "Customers found",
                200,
                this.customerMapper.toResponseList(foundCustomer)
        ));
    }
}
