package pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.tecsup.hexagonal.app.domain.model.Transaction;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.request.TransactionRequest;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.input.rest.dto.response.TransactionResponse;
import pe.edu.tecsup.hexagonal.app.infrastructure.adapter.output.persistence.entity.TransactionEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionEntity toEntity(Transaction transaction);

    Transaction toDomain(TransactionEntity entity);

    List<TransactionEntity> toEntityList(List<Transaction> transactions);

    List<Transaction> toDomainList(List<TransactionEntity> entities);

    // =========================================
    // REQUEST -> DOMAIN
    // =========================================
    default Transaction toDomain(TransactionRequest request){
        return Transaction.createTransfer(
          null,
          request.getSourceAccountId(),
          request.getDestinationAccountId(),
          request.getAmount(),
          null,
          request.getDescription()
        );
    };

    // =========================================
    // DOMAIN -> RESPONSE
    // =========================================


    default TransactionResponse toResponse(Transaction transaction){
        return TransactionResponse.builder()
                .id(transaction.getId())
                .status(transaction.getStatus().name())
                .createAt(transaction.getCreatedAt().toString())
                .build();
    };
}
