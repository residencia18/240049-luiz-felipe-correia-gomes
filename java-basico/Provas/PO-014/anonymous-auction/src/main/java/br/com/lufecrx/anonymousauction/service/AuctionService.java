package br.com.lufecrx.anonymousauction.service;

import java.util.ArrayList;
import java.util.List;

import br.com.lufecrx.anonymousauction.model.Auction;
import br.com.lufecrx.anonymousauction.model.dto.AuctionDTO;

public class AuctionService {

    // AuctionDTO to Auction
    public static Auction fromDto(AuctionDTO dto) {
        return new Auction(null, 
                dto.getTitle(), 
                dto.getDescription(), 
                dto.getInitialPrice(), 
                dto.isActive(),
                null);
    }

    public static List<Auction> fromDto(Iterable<AuctionDTO> dtos) {
        List<Auction> auctions = new ArrayList<>();
        for (AuctionDTO dto : dtos) {
            auctions.add(fromDto(dto));
        }
        return auctions;
    }
    
    // Auction to AuctionDTO
    public static AuctionDTO from(Auction auction) {
        return new AuctionDTO(auction.getTitle(),
                auction.getDescription(),
                auction.getInitialPrice(),
                auction.isActive());
    }

    public static List<AuctionDTO> from(Iterable<Auction> auctions) {
        List<AuctionDTO> dtos = new ArrayList<>();
        for (Auction auction : auctions) {
            dtos.add(from(auction));
        }
        return dtos;
    }
}
