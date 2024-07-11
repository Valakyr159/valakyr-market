package com.valakyr.market.persistence;

import com.valakyr.market.domain.Purchase;
import com.valakyr.market.domain.repository.PurchaseRepository;
import com.valakyr.market.persistence.crud.CompraCrudRepository;
import com.valakyr.market.persistence.entity.Compra;
import com.valakyr.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchase((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchase(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra((purchase));
        compra.getProductos().forEach((producto -> producto.setCompra(compra)));
        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}
