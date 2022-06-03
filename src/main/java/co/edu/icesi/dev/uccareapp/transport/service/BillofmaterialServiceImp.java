package co.edu.icesi.dev.uccareapp.transport.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.dao.BillofmaterialDAO;
import co.edu.icesi.dev.uccareapp.transport.model.prod.Billofmaterial;

@Service
public class BillofmaterialServiceImp implements BillofmaterialService {

    @Autowired
    private BillofmaterialDAO billofmaterialDAO;

    @Override
    public boolean saveBillofmaterial(Billofmaterial billofmaterial) {
         if (billofmaterial == null || billofmaterialDAO
                .existsById(billofmaterial.getBillofmaterialsid()))
            throw new NullPointerException("Billofmaterial is null or already exists");

            billofmaterialDAO.save(billofmaterial);

            return true;
    }

    @Override
    public boolean editBillofmaterial(Billofmaterial billofmaterial) {
        if (billofmaterial == null || !billofmaterialDAO
            .existsById(billofmaterial.getBillofmaterialsid()))
        throw new NullPointerException("Billofmaterial is null or already exists");

        Billofmaterial editBillofmaterial= billofmaterialDAO.findById(billofmaterial.getBillofmaterialsid()).get();
        editBillofmaterial.setBomlevel(billofmaterial.getBomlevel());
        editBillofmaterial.setEnddate(billofmaterial.getEnddate());
        editBillofmaterial.setModifieddate(billofmaterial.getModifieddate());
        editBillofmaterial.setPerassemblyqty(billofmaterial.getPerassemblyqty());
        editBillofmaterial.setProduct1(billofmaterial.getProduct1());
        editBillofmaterial.setProduct2(billofmaterial.getProduct2());
        editBillofmaterial.setStartdate(billofmaterial.getStartdate());
        editBillofmaterial.setUnitmeasure(billofmaterial.getUnitmeasure());

        billofmaterialDAO.update(editBillofmaterial);
        return true;
    }

    @Override
    public Optional<Billofmaterial> findById(Integer id) {
        return billofmaterialDAO.findById(id);
    }

    @Override
    public Iterable<Billofmaterial> findAll() {
        return billofmaterialDAO.findAll().iterator().hasNext()?
                billofmaterialDAO.findAll() : null;
    }

    @Override
    public void delete(Billofmaterial billofmaterial) {
        billofmaterialDAO.delete(billofmaterial);
        
    }
    
}
