package pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.tecsup.hexagonal.app.domain.model.Customer;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.request.CustomerRequest;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.response.CustomerResponse;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.entity.CustomerEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(
            target = "createdAt",
            source = "createdAt"
    )
    Customer toDomain(CustomerEntity customerEntity);

    // Request to Domain (for new users)
    default  Customer toDomain(CustomerRequest customerRequest){
        return  Customer.create(
            null,
            customerRequest.getName(),
            customerRequest.getEmail(),
                customerRequest.getDocument()
        );
    };

    CustomerEntity toEntity(Customer customer);
    // Domain to Response
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "document", source = "document")
    CustomerResponse toResponse(Customer customer);

    // List of CustomerEntity -> List of Customer
    List<Customer> toDomainList(List<CustomerEntity> entities);

    // List of Customer -> List of CustomerResponse
    List<CustomerResponse> toResponseList(List<Customer> customers);
}
