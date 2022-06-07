package co.edu.icesi.dev.uccareapp.transport;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.dev.uccareapp.transport.dao.BillofmaterialDAOImp;
import co.edu.icesi.dev.uccareapp.transport.dao.ProductDAOImp;
import co.edu.icesi.dev.uccareapp.transport.dao.ProductreviewDAOImp;
import co.edu.icesi.dev.uccareapp.transport.dao.PurchaseorderdetailDAOImp;
import co.edu.icesi.dev.uccareapp.transport.dao.PurchaseorderheaderDAOImp;
import co.edu.icesi.dev.uccareapp.transport.dao.ShipmethodDAOImp;
import co.edu.icesi.dev.uccareapp.transport.dao.UnitmeasureDAO;
import co.edu.icesi.dev.uccareapp.transport.dao.VendorDAOImp;
import co.edu.icesi.dev.uccareapp.transport.model.hr.Employee;
import co.edu.icesi.dev.uccareapp.transport.model.person.Businessentity;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderdetail;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.PurchaseorderdetailPK;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Purchaseorderheader;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Shipmethod;
import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Vendor;
import co.edu.icesi.dev.uccareapp.transport.model.prod.Billofmaterial;
import co.edu.icesi.dev.uccareapp.transport.model.prod.Product;
import co.edu.icesi.dev.uccareapp.transport.model.prod.Productreview;
import co.edu.icesi.dev.uccareapp.transport.repository.BusinessentityRepository;
import co.edu.icesi.dev.uccareapp.transport.repository.EmployeeRepository;

@SpringBootApplication
public class Application {
    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

    @Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public CommandLineRunner dummy(
        BusinessentityRepository businessentityrepository,
        EmployeeRepository employeerepository, 
        VendorDAOImp vendorDAO, 
        ShipmethodDAOImp shipmethodDAO,
        PurchaseorderheaderDAOImp purchaseorderheaderDAO,
        PurchaseorderdetailDAOImp purchaseorderdetailDAO, 
        ProductDAOImp productDAO,
        ProductreviewDAOImp productreviewDAO,
        BillofmaterialDAOImp billofmaterialDAO,
        UnitmeasureDAO unitmeasuredao) {
        
        return (args) -> {
            
            // Employees
            Employee employee1 = new Employee();
            employee1.setBusinessentityid(1);

            Employee employee2 = new Employee();
            employee2.setBusinessentityid(2);
            
            employeerepository.save(employee1);
            employeerepository.save(employee2);

            // Business
            Businessentity businessentity1 = new Businessentity();
            businessentity1.setBusinessentityid(1);
            
            Businessentity businessentity2 = new Businessentity();
            businessentity2.setBusinessentityid(2);
            
            businessentityrepository.save(businessentity1);
            businessentityrepository.save(businessentity2);
            
            // Vendors
            Vendor vendor1 = new Vendor();
            vendor1.setBusinessentityid(1);
            vendor1.setCreditrating(10);
            vendor1.setPurchasingwebserviceurl("https:");
            vendor1.setName("vendor");
            vendor1.setPreferredvendorstatus("active");
    
            Vendor vendor2 = new Vendor();
            vendor2.setBusinessentityid(2);
            vendor2.setCreditrating(20);
            vendor2.setPurchasingwebserviceurl("https:");
            vendor2.setName("vendor");
            vendor2.setPreferredvendorstatus("away");
    
            Vendor vendor3 = new Vendor();
            vendor3.setBusinessentityid(3);
            vendor3.setCreditrating(30);
            vendor3.setPurchasingwebserviceurl("https:");
            vendor3.setName("vendor");
            vendor3.setPreferredvendorstatus("active");
    
            vendorDAO.save(vendor1);
            vendorDAO.save(vendor2);
            vendorDAO.save(vendor3);

            // Shipmethods
            Shipmethod shipmethod = new Shipmethod();
            shipmethod.setShipmethodid(1);
            shipmethod.setShipbase(new BigDecimal("0.1"));
            shipmethod.setShiprate(new BigDecimal("0.1"));
            shipmethod.setName("FOUR");

            shipmethodDAO.save(shipmethod);

            // Headers
            Purchaseorderheader purchaseorderheader1 = new Purchaseorderheader();
            purchaseorderheader1.setPurchaseorderid(1);
            purchaseorderheader1.setEmployeeid(1);
            purchaseorderheader1.setOrderdate(LocalDate.now().plusDays(1));
            purchaseorderheader1.setSubtotal(new BigDecimal("0.1"));
            purchaseorderheader1.setShipmethod(shipmethod);
    
            Purchaseorderheader purchaseorderheader2 = new Purchaseorderheader();
            purchaseorderheader2.setPurchaseorderid(2);
            purchaseorderheader2.setEmployeeid(2);
            purchaseorderheader2.setOrderdate(LocalDate.now());
            purchaseorderheader2.setSubtotal(new BigDecimal("0.1"));
            purchaseorderheader2.setVendor(vendor1);
    
            Purchaseorderheader purchaseorderheader3 = new Purchaseorderheader();
            purchaseorderheader3.setPurchaseorderid(3);
            purchaseorderheader3.setEmployeeid(3);
            purchaseorderheader3.setOrderdate(LocalDate.now());
            purchaseorderheader3.setSubtotal(new BigDecimal("0.1"));
            purchaseorderheader3.setShipmethod(shipmethod);
    
            purchaseorderheaderDAO.save(purchaseorderheader1);
            purchaseorderheaderDAO.save(purchaseorderheader2);
            purchaseorderheaderDAO.save(purchaseorderheader3);

            // Details
            PurchaseorderdetailPK purchaseorderdetailPK1 = new PurchaseorderdetailPK();
            purchaseorderdetailPK1.setPurchaseorderid(1);
            purchaseorderdetailPK1.setPurchaseorderdetailid(1);

            PurchaseorderdetailPK purchaseorderdetailPK2 = new PurchaseorderdetailPK();
            purchaseorderdetailPK2.setPurchaseorderid(1);
            purchaseorderdetailPK2.setPurchaseorderdetailid(2);

            PurchaseorderdetailPK purchaseorderdetailPK3 = new PurchaseorderdetailPK();
            purchaseorderdetailPK3.setPurchaseorderid(2);
            purchaseorderdetailPK3.setPurchaseorderdetailid(1);

            Purchaseorderdetail purchaseorderdetail1 = new Purchaseorderdetail();
            purchaseorderdetail1.setId(purchaseorderdetailPK1);
            purchaseorderdetail1.setOrderqty(1);
            purchaseorderdetail1.setUnitprice(new BigDecimal("0.1"));
            purchaseorderdetail1.setProductid(1);
            purchaseorderdetail1.setDuedate(LocalDate.now().minusDays(3));

            Purchaseorderdetail purchaseorderdetail2 = new Purchaseorderdetail();
            purchaseorderdetail2.setId(purchaseorderdetailPK2);
            purchaseorderdetail2.setOrderqty(1);
            purchaseorderdetail2.setUnitprice(new BigDecimal("0.3"));
            purchaseorderdetail2.setProductid(1);
            purchaseorderdetail2.setDuedate(LocalDate.now().minusDays(1));

            Purchaseorderdetail purchaseorderdetail3 = new Purchaseorderdetail();
            purchaseorderdetail3.setId(purchaseorderdetailPK3);
            purchaseorderdetail3.setOrderqty(3);
            purchaseorderdetail3.setUnitprice(new BigDecimal("0.3"));
            purchaseorderdetail3.setProductid(1);
            purchaseorderdetail3.setDuedate(LocalDate.now().plusDays(1));

            purchaseorderdetailDAO.save(purchaseorderdetail1);
            purchaseorderdetailDAO.save(purchaseorderdetail2);
            purchaseorderdetailDAO.save(purchaseorderdetail3);

            // Products
            Product product1 = new Product();
            product1.setProductid(1);
            product1.setName("Tungsten");
            product1.setColor("Gray");
            product1.setDiscontinueddate(LocalDate.now());
            
            Product product2 = new Product();
            product2.setProductid(2);
            product2.setName("Aerogel");
            product2.setColor("White");
            product2.setDiscontinueddate(LocalDate.now());
            
            Product product3 = new Product();
            product3.setProductid(3);
            product3.setName("Osmium");
            product3.setColor("Gray");
            product3.setDiscontinueddate(LocalDate.now());

            productDAO.save(product1);
            productDAO.save(product2);
            productDAO.save(product3);
            
            // Product Reviews
            Productreview productreview1 = new Productreview();
            productreview1.setProductreviewid(1);
            productreview1.setComments("Good job on the tungsten, it sure is dense");
            productreview1.setEmailaddress("email@domain.com");
            productreview1.setRating(5);
            productreview1.setReviewdate(LocalDate.now());
            productreview1.setReviewername("Osman");
            productreview1.setProduct(product1);

            productreviewDAO.save(productreview1);

            // Bills of Material
           /* Unitmeasure unitmeasure = new Unitmeasure();
            unitmeasure.setUnitmeasurecode("uma");
            unitmeasure.setName(" atomic mass unit");
            
            unitmeasuredao.save(unitmeasure);*/

            Billofmaterial billofmaterial1 = new Billofmaterial();
            billofmaterial1.setBillofmaterialsid(1);
            billofmaterial1.setBomlevel(3);
            billofmaterial1.setStartdate(LocalDate.now().minusDays(30));
            billofmaterial1.setEnddate(LocalDate.now());
            billofmaterial1.setPerassemblyqty(new BigDecimal("0.3"));
            billofmaterial1.setProduct1(product1);
            billofmaterial1.setProduct2(product2);
           // billofmaterial1.setUnitmeasure(unitmeasure);

            billofmaterialDAO.save(billofmaterial1);
        };
    }
}


