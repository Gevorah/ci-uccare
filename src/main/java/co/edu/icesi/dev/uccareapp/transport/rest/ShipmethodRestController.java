package co.edu.icesi.dev.uccareapp.transport.rest;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Shipmethod;

public interface ShipmethodRestController {
    public void saveShipmethod(Shipmethod shipmethod);
	public void editShipmethod(Shipmethod shipmethod);
	public Shipmethod findById(Integer id);
	public Iterable<Shipmethod> findAll();
	public void delete(Shipmethod shipmethod);
}
