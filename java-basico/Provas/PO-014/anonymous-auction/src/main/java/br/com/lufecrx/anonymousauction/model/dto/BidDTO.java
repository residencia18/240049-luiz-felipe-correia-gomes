package br.com.lufecrx.anonymousauction.model.dto;

import br.com.lufecrx.anonymousauction.model.Auction;
import br.com.lufecrx.anonymousauction.model.Bidder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidDTO {

    private Auction auction;

    private Bidder bidder;

    private double amount;
    
}
