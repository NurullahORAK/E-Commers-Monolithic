package org.orak.ecommers.repository;

import org.orak.ecommers.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket,Integer> {
}
