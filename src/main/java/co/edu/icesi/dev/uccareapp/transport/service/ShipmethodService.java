package co.edu.icesi.dev.uccareapp.transport.service;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.model.prchasing.Shipmethod;

public interface ShipmethodService {
	public boolean saveShipmethod(Shipmethod shipmethod);
	public boolean editShipmethod(Shipmethod shipmethod);
	public Optional<Shipmethod> findById(Integer id);
	public Iterable<Shipmethod> findAll();
	public void delete(Shipmethod shipmethod);
}
