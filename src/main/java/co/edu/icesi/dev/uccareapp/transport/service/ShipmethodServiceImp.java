package co.edu.icesi.dev.uccareapp.transport.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Shipmethod;
import co.edu.icesi.dev.uccareapp.transport.repository.ShipmethodRepository;

@Service
public class ShipmethodServiceImp implements ShipmethodService {

    @Autowired
    private ShipmethodRepository shipmethodrepository;

    @Override
    public boolean saveShipmethod(Shipmethod shipmethod) throws NullPointerException, IllegalArgumentException {
        if (shipmethod == null || shipmethodrepository.existsById(shipmethod.getShipmethodid()))
            throw new NullPointerException("Shipmethod is null or already exists");

        if (shipmethod.getShipbase().signum() != 1)
            throw new IllegalArgumentException("shipbase must be greater than 0");
        if (shipmethod.getShiprate().signum() != 1)
            throw new IllegalArgumentException("shiprate must be greater than 0");
        if (shipmethod.getName().length() < 4)
            throw new IllegalArgumentException("name must have at least 4 chars");

        shipmethodrepository.save(shipmethod);
        
        return true;
    }

    @Override
    public boolean editShipmethod(Shipmethod shipmethod) throws NullPointerException, IllegalArgumentException {
        if (shipmethod == null || !shipmethodrepository.existsById(shipmethod.getShipmethodid()))
            throw new NullPointerException("Shipmethod is null or doesn't exists");

        if (shipmethod.getShipbase().signum() != 1)
            throw new IllegalArgumentException("shipbase must be greater than 0");
        if (shipmethod.getShiprate().signum() != 1)
            throw new IllegalArgumentException("shiprate must be greater than 0");
        if (shipmethod.getName().length() < 4)
            throw new IllegalArgumentException("name must have at least 4 chars");

        Shipmethod editshipmethod = shipmethodrepository.findById(shipmethod.getShipmethodid()).get();
        editshipmethod.setModifieddate(LocalDate.now());
        editshipmethod.setName(shipmethod.getName());
        editshipmethod.setRowguid(shipmethod.getRowguid());
        editshipmethod.setShipbase(shipmethod.getShipbase());
        editshipmethod.setShiprate(shipmethod.getShipbase());

        shipmethodrepository.save(editshipmethod);
        
        return true;
    }

    @Override
    public Optional<Shipmethod> findById(Integer id) {
        return shipmethodrepository.findById(id);
    }

    @Override
    public Iterable<Shipmethod> findAll() {
        return shipmethodrepository.findAll().iterator().hasNext()?
                shipmethodrepository.findAll() : null;
    }

    @Override
    public void delete(Shipmethod shipmethod) {
        shipmethodrepository.delete(shipmethod);
    }

}
