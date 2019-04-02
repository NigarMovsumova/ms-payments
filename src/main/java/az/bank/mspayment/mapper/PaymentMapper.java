package az.bank.mspayment.mapper;

import az.bank.mspayment.model.dto.PaymentDto;
import az.bank.mspayment.model.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class PaymentMapper {
    public static final PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    public abstract PaymentDto entityToDto(PaymentEntity entity);

    public abstract PaymentEntity dtoToEntity(PaymentDto dto);

    public abstract List<PaymentEntity> dtoListToEntityList(List<PaymentDto> dto);

    public abstract List<PaymentDto> entityListToDtoList(List<PaymentEntity> entity);
}
