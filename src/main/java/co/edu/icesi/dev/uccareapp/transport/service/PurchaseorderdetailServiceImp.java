package co.edu.icesi.dev.uccareapp.transport.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.dao.PurchaseorderdetailDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.PurchaseorderheaderDAO;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.PurchaseorderdetailPK;

@Service
public class PurchaseorderdetailServiceImp implements PurchaseorderdetailService {

    @Autowired
    private PurchaseorderdetailDAO purchaseorderdetaildao;

    @Autowired
    private PurchaseorderheaderDAO purchaseorderheaderdao;

    @Override
    public boolean savePurchaseorderdetail(Purchaseorderdetail purchaseorderdetail)
            throws NullPointerException, IllegalArgumentException {
        if (purchaseorderdetail == null || purchaseorderdetaildao
                .existsById(purchaseorderdetail.getId()))
            throw new NullPointerException("Purchaseorderdetail is null or already exists");

        if (!purchaseorderheaderdao
                .existsById(purchaseorderdetail.getId().getPurchaseorderid()))
            throw new NullPointerException("Purchaseorderheader doesn't exist");
        if (purchaseorderdetail.getOrderqty() < 1)
            throw new IllegalArgumentException("orderqty must be greater than 0");
        if (purchaseorderdetail.getUnitprice().signum() != 1)
            throw new IllegalArgumentException("unitprice must be greater than 0");

        purchaseorderdetaildao.save(purchaseorderdetail);

        return true;
    }

    @Override
    public boolean editPurchaseorderdetail(Purchaseorderdetail purchaseorderdetail)
            throws NullPointerException, IllegalArgumentException {
        if (purchaseorderdetail == null ||
                !purchaseorderdetaildao.existsById(purchaseorderdetail.getId()))
            throw new NullPointerException("Purchaseorderdetail is null or doesn't exist");

        if (!purchaseorderheaderdao
                .existsById(purchaseorderdetail.getPurchaseorderheader().getPurchaseorderid()))
            throw new NullPointerException("Purchaseorderheader doesn't exist");
        if (purchaseorderdetail.getOrderqty() < 1)
            throw new IllegalArgumentException("orderqty must be greater than 0");
        if (purchaseorderdetail.getUnitprice().signum() != 1)
            throw new IllegalArgumentException("unitprice must be greater than 0");

        Purchaseorderdetail editpurchaseorderdetail = purchaseorderdetaildao
                .findById(purchaseorderdetail.getId()).get();
        editpurchaseorderdetail.setDuedate(purchaseorderdetail.getDuedate());
        editpurchaseorderdetail.setModifieddate(purchaseorderdetail.getModifieddate());
        editpurchaseorderdetail.setOrderqty(purchaseorderdetail.getOrderqty());
        editpurchaseorderdetail.setProductid(purchaseorderdetail.getProductid());
        //editpurchaseorderdetail.setPurchaseorderheader(purchaseorderheaderdao
        //        .findById(purchaseorderdetail.getPurchaseorderheader().getPurchaseorderid()).get());
        editpurchaseorderdetail.setReceivedqty(purchaseorderdetail.getReceivedqty());
        editpurchaseorderdetail.setRejectedqty(purchaseorderdetail.getReceivedqty());
        editpurchaseorderdetail.setUnitprice(purchaseorderdetail.getUnitprice());

        purchaseorderdetaildao.save(editpurchaseorderdetail);

        return true;
    }

    @Override
    public Optional<Purchaseorderdetail> findById(PurchaseorderdetailPK id) {
        return purchaseorderdetaildao.findById(id);
    }

    @Override
    public Iterable<Purchaseorderdetail> findAll() {
        return purchaseorderdetaildao.findAll().iterator().hasNext()?
                purchaseorderdetaildao.findAll() : null;
    }

    @Override
    public void delete(Purchaseorderdetail purchaseorderdetail) {
        purchaseorderdetaildao.delete(purchaseorderdetail);
    }

}
