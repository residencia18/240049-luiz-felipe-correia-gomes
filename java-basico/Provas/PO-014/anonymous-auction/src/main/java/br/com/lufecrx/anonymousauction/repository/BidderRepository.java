package br.com.lufecrx.anonymousauction.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.lufecrx.anonymousauction.model.Bidder;

public interface BidderRepository extends CrudRepository<Bidder, Integer> {

}
