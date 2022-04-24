package co.edu.icesi.dev.uccareapp.transport.service;

import java.sql.Timestamp;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;
import co.edu.icesi.dev.uccareapp.transport.repository.EmployeeRepository;
import co.edu.icesi.dev.uccareapp.transport.repository.PurchaseorderheaderRepository;
import co.edu.icesi.dev.uccareapp.transport.repository.VendorRepository;

@Service
public class PurchaseorderheaderServiceImp implements PurchaseorderheaderService {

    private PurchaseorderheaderRepository purchaseorderheaderrepository;
    private VendorRepository vendorrepository;
    private EmployeeRepository employeerepository;

    @Autowired
    public PurchaseorderheaderServiceImp(PurchaseorderheaderRepository purchaseorderheaderrepository, VendorRepository vendorrepository, EmployeeRepository employeerepository) {
        this.purchaseorderheaderrepository = purchaseorderheaderrepository;
        this.vendorrepository = vendorrepository;
        this.employeerepository = employeerepository;
    }

    @Override
    public boolean savePurchaseorderheader(Purchaseorderheader purchaseorderheader) throws NullPointerException, IllegalArgumentException {
        if (purchaseorderheader == null ||
                purchaseorderheaderrepository.existsById(purchaseorderheader.getPurchaseorderid()))
            throw new NullPointerException("Purchaseorderheader is null or already exists");

        if (!employeerepository.existsById(purchaseorderheader.getEmployeeid()))
            throw new NullPointerException("employeeid doesn't correspond to an existing Employee");
        if (purchaseorderheader.getOrderdate().before(Timestamp.from(Instant.now().minusSeconds(100))) ||
                purchaseorderheader.getOrderdate().after(Timestamp.from(Instant.now().plusSeconds(100))))
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
        if (purchaseorderheader.getOrderdate().before(Timestamp.from(Instant.now().minusMillis(10000))) ||
                purchaseorderheader.getOrderdate().after(Timestamp.from(Instant.now().plusMillis(10000))))
            throw new IllegalArgumentException("orderdate must be actual");
        if (purchaseorderheader.getSubtotal().signum() != 1)
            throw new IllegalArgumentException("subtotal must be greater than 0");

        Purchaseorderheader editpurchaseorderheader = purchaseorderheaderrepository.findById(purchaseorderheader.getPurchaseorderid()).get();
        
        editpurchaseorderheader.setEmployeeid(purchaseorderheader.getEmployeeid());
        editpurchaseorderheader.setFreight(purchaseorderheader.getFreight());
        editpurchaseorderheader.setModifieddate(Timestamp.from(Instant.now()));
        editpurchaseorderheader.setOrderdate(purchaseorderheader.getOrderdate());
        editpurchaseorderheader.setRevisionnumber(purchaseorderheader.getRevisionnumber());
        editpurchaseorderheader.setShipdate(purchaseorderheader.getShipdate());
        editpurchaseorderheader.setStatus(purchaseorderheader.getStatus());
        editpurchaseorderheader.setSubtotal(purchaseorderheader.getSubtotal());
        editpurchaseorderheader.setTaxamt(purchaseorderheader.getTaxamt());
        editpurchaseorderheader.setVendor(purchaseorderheader.getVendor());

        purchaseorderheaderrepository.save(editpurchaseorderheader);
        
        return true;
    }

}
