package br.com.lufecrx.anonymousauction.service;

import java.util.ArrayList;
import java.util.List;

import br.com.lufecrx.anonymousauction.model.Bidder;
import br.com.lufecrx.anonymousauction.model.dto.BidderDTO;

public class BidderService {

    // BidderDTO to Bidder
    public static Bidder fromDto(BidderDTO dto) {
        return new Bidder(null, dto.getName(), dto.getCpf(), null);
    }
    
    // Bidder to BidderDTO
    public static BidderDTO from(Bidder bidder) {
        return new BidderDTO(bidder.getName(), null);
    }

    public static List<BidderDTO> from(Iterable<Bidder> bidders) {
        List <BidderDTO> dtos = new ArrayList<>();
        for (Bidder bidder : bidders) {
            dtos.add(from(bidder));
        }
        return dtos;
    }

    public static BidderDTO fromWithCPF(Bidder bidder) {
        return new BidderDTO(bidder.getName(), bidder.getCpf());
    }

    public static List<BidderDTO> fromWithCPF(Iterable<Bidder> bidders) {
        List <BidderDTO> dtos = new ArrayList<>();
        for (Bidder bidder : bidders) {
            dtos.add(fromWithCPF(bidder));
        }
        return dtos;
    }    
}
