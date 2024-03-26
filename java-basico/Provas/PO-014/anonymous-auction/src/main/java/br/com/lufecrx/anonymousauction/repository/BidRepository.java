package br.com.lufecrx.anonymousauction.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.lufecrx.anonymousauction.model.Bid;

public interface BidRepository extends CrudRepository<Bid, Integer> {

    Iterable<Bid> findByAuctionId(Integer auctionId);

    Iterable<Bid> findByBidderId(Integer bidderId);    
}
