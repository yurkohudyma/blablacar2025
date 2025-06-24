package ua.hudyma.tripservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.hudyma.tripservice.domain.City;

@Repository
public interface CityRepository extends JpaRepository <City, String> {
}
