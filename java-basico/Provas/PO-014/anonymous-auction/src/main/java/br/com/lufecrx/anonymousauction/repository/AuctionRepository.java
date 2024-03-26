package br.com.lufecrx.anonymousauction.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.lufecrx.anonymousauction.model.Auction;

public interface AuctionRepository extends CrudRepository<Auction, Integer> {

}
