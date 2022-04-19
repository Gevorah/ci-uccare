package co.edu.icesi.dev.uccareapp.transport.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;

@Repository
public interface VendorRepository extends CrudRepository<Vendor, Integer> {
}
