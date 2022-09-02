package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Hall;

public interface HallRepository extends CrudRepository<Hall> {
    Hall findHallBySerialNumber(Long serialNumber);
    boolean existsBySerialNumber(Long serialNumber);
}
