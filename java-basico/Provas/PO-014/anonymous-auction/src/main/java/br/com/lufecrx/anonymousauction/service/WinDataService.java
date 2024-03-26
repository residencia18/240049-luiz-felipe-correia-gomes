package br.com.lufecrx.anonymousauction.service;

import br.com.lufecrx.anonymousauction.model.Bid;
import br.com.lufecrx.anonymousauction.model.WinData;

public class WinDataService {

    public static WinData from(Iterable<Bid> bids) {
        Bid highestBid = BidService.getHighestBidderByAuction(bids);

        return new WinData(
                null,
                AuctionService.from(highestBid.getAuction()),
                BidderService.from(highestBid.getBidder()),
                highestBid.getAmount());
    }
}
