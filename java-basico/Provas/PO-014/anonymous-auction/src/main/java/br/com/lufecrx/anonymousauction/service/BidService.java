package br.com.lufecrx.anonymousauction.service;

import java.util.ArrayList;
import java.util.List;

import br.com.lufecrx.anonymousauction.model.Bid;
import br.com.lufecrx.anonymousauction.model.dto.BidDTO;

public class BidService {
    // BidDTO to Bid
    public static Bid fromDto(BidDTO dto) {
        return new Bid(null, 
            dto.getAuction(), 
            dto.getBidder(), 
            dto.getAmount());
    }
    
    // Bid to BidDTO
    public static BidDTO from(Bid bid) {
        return new BidDTO(bid.getAuction(), 
            bid.getBidder(),
            bid.getAmount());
    }

    public static List<BidDTO> from(Iterable<Bid> bids) {
        List <BidDTO> dtos = new ArrayList<>();
        for (Bid bid : bids) {
            dtos.add(from(bid));
        }
        return dtos;
    }    

    // Get highest bid by auction
    public static Bid getHighestBidderByAuction(Iterable<Bid> bids) {
        Bid highestBid = bids.iterator().next();

        for (Bid bid : bids) {
            if (bid.getAmount() > highestBid.getAmount()) {
                highestBid = bid;
            }
        }
        return highestBid;
    }
}
