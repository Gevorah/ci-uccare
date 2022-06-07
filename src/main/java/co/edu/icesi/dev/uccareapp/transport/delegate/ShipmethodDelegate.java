package co.edu.icesi.dev.uccareapp.transport.delegate;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Shipmethod;

public interface ShipmethodDelegate {
    public Shipmethod saveShipmethod(Shipmethod shipmethod);
	public void editShipmethod(Shipmethod shipmethod);
	public Shipmethod findById(Integer id);
	public Iterable<Shipmethod> findAll();
	public void delete(Shipmethod shipmethod);
}