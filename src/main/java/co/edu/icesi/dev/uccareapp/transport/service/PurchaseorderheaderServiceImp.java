package co.edu.icesi.dev.uccareapp.transport.service;

import java.time.LocalDate;
import java.util.Optional;

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

        purchaseorderheaderrepository.save(editpurchaseorderheader);
        
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

}
