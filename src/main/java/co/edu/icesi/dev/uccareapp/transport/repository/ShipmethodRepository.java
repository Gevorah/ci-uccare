package co.edu.icesi.dev.uccareapp.transport.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Shipmethod;

@Repository
public interface ShipmethodRepository extends CrudRepository<Shipmethod, Integer> {
}
