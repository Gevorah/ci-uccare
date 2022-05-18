package co.edu.icesi.dev.uccareapp.transport.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.dao.VendorDAO;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;
import co.edu.icesi.dev.uccareapp.transport.repository.BusinessentityRepository;

@Service
public class VendorServiceImp implements VendorService {

    @Autowired
    private VendorDAO vendorDAO;
    
    @Autowired
    private BusinessentityRepository businessentityrepository;
    
    @Override
    public boolean saveVendor(Vendor vendor) throws NullPointerException, IllegalArgumentException {
        if (vendor == null || vendorDAO.existsById(vendor.getBusinessentityid()) )
            throw new NullPointerException("Vendor is null or already exists");
        
        if(!businessentityrepository.existsById(vendor.getBusinessentityid()))
        	throw new NullPointerException("Businessentity doesn't exist");
        if (vendor.getCreditrating() < 0)
            throw new IllegalArgumentException("creditrating must be greater than 0");
        if (!vendor.getPurchasingwebserviceurl().startsWith("https"))
            throw new IllegalArgumentException("URL must start with 'https'");
        if (vendor.getName() == null)
            throw new NullPointerException("Fill the name field");
        
        vendorDAO.save(vendor);
        
        return true;
    }

    @Override
    public boolean editVendor(Vendor vendor) throws NullPointerException, IllegalArgumentException {
        if (vendor == null || !vendorDAO.existsById(vendor.getBusinessentityid()))
            throw new NullPointerException("Vendor is null or doesn't exist");
        
        if (vendor.getCreditrating() < 0)
            throw new IllegalArgumentException("creditrating must be greater than 0");
        if (!vendor.getPurchasingwebserviceurl().startsWith("https"))
            throw new IllegalArgumentException("URL must start with 'https'");
        if (vendor.getName() == null)
            throw new NullPointerException("Fill the name field");
        
        Vendor editvendor = vendorDAO.findById(vendor.getBusinessentityid()).get();
        
        editvendor.setAccountnumber(vendor.getAccountnumber());
        editvendor.setActiveflag(vendor.getActiveflag());
        editvendor.setCreditrating(vendor.getCreditrating());
        editvendor.setModifieddate(LocalDate.now());
        editvendor.setName(vendor.getName());
        editvendor.setPreferredvendorstatus(vendor.getPreferredvendorstatus());
        editvendor.setPurchasingwebserviceurl(vendor.getPurchasingwebserviceurl());
        
        vendorDAO.update(editvendor);

        return true;
    }

    @Override
    public Optional<Vendor> findById(Integer id) {
        return vendorDAO.findById(id);
    }

    @Override
    public Iterable<Vendor> findAll() {
        return vendorDAO.findAll().iterator().hasNext()?
                vendorDAO.findAll() : null;
    }

    @Override
    public void delete(Vendor vendor) {
        vendorDAO.delete(vendor);
    }

}
