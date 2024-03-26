package br.com.lufecrx.anonymousauction.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuctionDTO {

    private String title;

    private String description;

    private double initialPrice;

    private boolean active;

}
