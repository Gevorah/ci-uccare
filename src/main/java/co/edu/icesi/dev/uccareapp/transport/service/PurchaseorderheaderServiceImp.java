package co.edu.icesi.dev.uccareapp.transport.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.dao.PurchaseorderheaderDAO;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;
import co.edu.icesi.dev.uccareapp.transport.repository.EmployeeRepository;

@Service
public class PurchaseorderheaderServiceImp implements PurchaseorderheaderService {

    @Autowired
    private PurchaseorderheaderDAO purchaseorderheaderrepository;
    
    @Autowired
    private EmployeeRepository employeerepository;

    @Override
    public boolean savePurchaseorderheader(Purchaseorderheader purchaseorderheader) throws NullPointerException, IllegalArgumentException {
        if (purchaseorderheader == null ||
                purchaseorderheaderrepository.existsById(purchaseorderheader.getPurchaseorderid()))
            throw new NullPointerException("Purchaseorderheader is null or already exists");

        if (!employeerepository.existsById(purchaseorderheader.getEmployeeid()))
            throw new NullPointerException("employeeid doesn't correspond to an existing Employee");
        if (!purchaseorderheader.getOrderdate().isEqual(LocalDate.now()))
            throw new IllegalArgumentException("orderdate must be actual");
        if (purchaseorderheader.getSubtotal().signum() != 1)
            throw new IllegalArgumentException("subtotal must be greater than 0");

        purchaseorderheaderrepository.save(purchaseorderheader);

        return true;
    }

    @Override
    public boolean editPurchaseorderheader(Purchaseorderheader purchaseorderheader) throws NullPointerException, IllegalArgumentException {
        if (purchaseorderheader == null ||
                !purchaseorderheaderrepository.existsById(purchaseorderheader.getPurchaseorderid()))
            throw new NullPointerException("Purchaseorderheader is null or doesn't exist");

        if (!employeerepository.existsById(purchaseorderheader.getEmployeeid()))
            throw new NullPointerException("employeeid doesn't correspond to an existing Employee");
        if (!purchaseorderheader.getOrderdate().isEqual(LocalDate.now()))
            throw new IllegalArgumentException("orderdate must be actual");
        if (purchaseorderheader.getSubtotal().signum() != 1)
            throw new IllegalArgumentException("subtotal must be greater than 0");

        Purchaseorderheader editpurchaseorderheader = purchaseorderheaderrepository.findById(purchaseorderheader.getPurchaseorderid()).get();
        
        editpurchaseorderheader.setEmployeeid(purchaseorderheader.getEmployeeid());
        editpurchaseorderheader.setFreight(purchaseorderheader.getFreight());
        editpurchaseorderheader.setModifieddate(LocalDate.now());
        editpurchaseorderheader.setOrderdate(purchaseorderheader.getOrderdate());
        editpurchaseorderheader.setRevisionnumber(purchaseorderheader.getRevisionnumber());
        editpurchaseorderheader.setShipdate(purchaseorderheader.getShipdate());
        editpurchaseorderheader.setStatus(purchaseorderheader.getStatus());
        editpurchaseorderheader.setSubtotal(purchaseorderheader.getSubtotal());
        editpurchaseorderheader.setTaxamt(purchaseorderheader.getTaxamt());
        editpurchaseorderheader.setVendor(purchaseorderheader.getVendor());

        purchaseorderheaderrepository.update(editpurchaseorderheader);
        
        return true;
    }

    @Override
    public Optional<Purchaseorderheader> findById(Integer id) {
        return purchaseorderheaderrepository.findById(id);
    }

    @Override
    public Iterable<Purchaseorderheader> findAll() {
        return purchaseorderheaderrepository.findAll().iterator().hasNext()?
                purchaseorderheaderrepository.findAll() : null;
    }

    @Override
    public void delete(Purchaseorderheader purchaseorderheader) {
        purchaseorderheaderrepository.delete(purchaseorderheader);
    }

    public Iterable<Object[]> findByDateRange(LocalDate start, LocalDate end) {

        return purchaseorderheaderrepository.findByDateRange(start, end);
    }

    public Iterable<Purchaseorderheader> findByTwoDetails() {
        
        return purchaseorderheaderrepository.findByTwoDetails();
    }
}
