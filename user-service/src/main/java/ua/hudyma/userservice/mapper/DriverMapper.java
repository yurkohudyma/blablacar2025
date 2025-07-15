package ua.hudyma.userservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.hudyma.userservice.domain.Driver;
import ua.hudyma.userservice.domain.ExperienceLevel;
import ua.hudyma.userservice.dto.DriverDto;

@Mapper
public interface DriverMapper {

    DriverMapper INSTANCE = Mappers.getMapper(DriverMapper.class);
    DriverDto toDto (Driver driver);
    Driver toEntity (DriverDto driverDto);

    default String map(ExperienceLevel level) {
        return level == null ? null : level.toString();
    }

    default ExperienceLevel map (String level){
        return level == null ? null : ExperienceLevel.fromValue(level);
    }
}
