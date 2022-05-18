package co.edu.icesi.dev.uccareapp.transport.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.dao.ShipmethodDAO;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Shipmethod;

@Service
public class ShipmethodServiceImp implements ShipmethodService {

    @Autowired
    private ShipmethodDAO shipmethodDAO;

    @Override
    public boolean saveShipmethod(Shipmethod shipmethod) throws NullPointerException, IllegalArgumentException {
        if (shipmethod == null || shipmethodDAO.existsById(shipmethod.getShipmethodid()))
            throw new NullPointerException("Shipmethod is null or already exists");

        if (shipmethod.getShipbase().signum() != 1)
            throw new IllegalArgumentException("shipbase must be greater than 0");
        if (shipmethod.getShiprate().signum() != 1)
            throw new IllegalArgumentException("shiprate must be greater than 0");
        if (shipmethod.getName().length() < 4)
            throw new IllegalArgumentException("name must have at least 4 chars");

        shipmethodDAO.save(shipmethod);
        
        return true;
    }

    @Override
    public boolean editShipmethod(Shipmethod shipmethod) throws NullPointerException, IllegalArgumentException {
        if (shipmethod == null || !shipmethodDAO.existsById(shipmethod.getShipmethodid()))
            throw new NullPointerException("Shipmethod is null or doesn't exists");

        if (shipmethod.getShipbase().signum() != 1)
            throw new IllegalArgumentException("shipbase must be greater than 0");
        if (shipmethod.getShiprate().signum() != 1)
            throw new IllegalArgumentException("shiprate must be greater than 0");
        if (shipmethod.getName().length() < 4)
            throw new IllegalArgumentException("name must have at least 4 chars");

        Shipmethod editshipmethod = shipmethodDAO.findById(shipmethod.getShipmethodid()).get();
        editshipmethod.setModifieddate(LocalDate.now());
        editshipmethod.setName(shipmethod.getName());
        editshipmethod.setRowguid(shipmethod.getRowguid());
        editshipmethod.setShipbase(shipmethod.getShipbase());
        editshipmethod.setShiprate(shipmethod.getShipbase());

        shipmethodDAO.update(editshipmethod);
        
        return true;
    }

    @Override
    public Optional<Shipmethod> findById(Integer id) {
        return shipmethodDAO.findById(id);
    }

    @Override
    public Iterable<Shipmethod> findAll() {
        return shipmethodDAO.findAll().iterator().hasNext()?
                shipmethodDAO.findAll() : null;
    }

    @Override
    public void delete(Shipmethod shipmethod) {
        shipmethodDAO.delete(shipmethod);
    }

}
