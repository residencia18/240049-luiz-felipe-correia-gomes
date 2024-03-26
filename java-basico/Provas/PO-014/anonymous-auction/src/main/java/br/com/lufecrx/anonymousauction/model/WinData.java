package br.com.lufecrx.anonymousauction.model;

import br.com.lufecrx.anonymousauction.model.dto.AuctionDTO;
import br.com.lufecrx.anonymousauction.model.dto.BidderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WinData {
    
    private Integer id;

    private AuctionDTO auction;

    private BidderDTO bidder;

    private double amount;
}

