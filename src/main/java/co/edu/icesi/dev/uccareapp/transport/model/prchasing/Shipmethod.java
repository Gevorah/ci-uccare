package co.edu.icesi.dev.uccareapp.transport.model.prchasing;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * The persistent class for the shipmethod database table.
 *
 */
@Entity
@NamedQuery(name = "Shipmethod.findAll", query = "SELECT s FROM Shipmethod s")
public class Shipmethod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name = "SHIPMETHOD_SHIPMETHODID_GENERATOR", allocationSize = 1, sequenceName = "SHIPMETHOD_SEQ")
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHIPMETHOD_SHIPMETHODID_GENERATOR")
	private Integer shipmethodid;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifieddate;

	@Size(min = 4)
	private String name;

	private Integer rowguid;

	@Positive
	private BigDecimal shipbase;

	@Positive
	private BigDecimal shiprate;

	@JsonManagedReference(value = "shipmethod-purchaseorderheader")
	// bi-directional many-to-one association to Purchaseorderheader
	@OneToMany(mappedBy = "shipmethod")
	private List<Purchaseorderheader> purchaseorderheaders;

	public Shipmethod() {
	}

	public Purchaseorderheader addPurchaseorderheader(Purchaseorderheader purchaseorderheader) {
		getPurchaseorderheaders().add(purchaseorderheader);
		purchaseorderheader.setShipmethod(this);

		return purchaseorderheader;
	}

	public LocalDate getModifieddate() {
		return this.modifieddate;
	}

	public String getName() {
		return this.name;
	}

	public List<Purchaseorderheader> getPurchaseorderheaders() {
		return this.purchaseorderheaders;
	}

	public Integer getRowguid() {
		return this.rowguid;
	}

	public BigDecimal getShipbase() {
		return this.shipbase;
	}

	public Integer getShipmethodid() {
		return this.shipmethodid;
	}

	public BigDecimal getShiprate() {
		return this.shiprate;
	}

	public Purchaseorderheader removePurchaseorderheader(Purchaseorderheader purchaseorderheader) {
		getPurchaseorderheaders().remove(purchaseorderheader);
		purchaseorderheader.setShipmethod(null);

		return purchaseorderheader;
	}

	public void setModifieddate(LocalDate modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPurchaseorderheaders(List<Purchaseorderheader> purchaseorderheaders) {
		this.purchaseorderheaders = purchaseorderheaders;
	}

	public void setRowguid(Integer rowguid) {
		this.rowguid = rowguid;
	}

	public void setShipbase(BigDecimal shipbase) {
		this.shipbase = shipbase;
	}

	public void setShipmethodid(Integer shipmethodid) {
		this.shipmethodid = shipmethodid;
	}

	public void setShiprate(BigDecimal shiprate) {
		this.shiprate = shiprate;
	}

}