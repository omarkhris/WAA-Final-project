package com.miu.waafinalproject.SecureOnlineAuctionSystem.services.Bid;

import com.miu.waafinalproject.SecureOnlineAuctionSystem.dto.*;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.enums.RolesEnum;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.exceptions.CustomerCanOnlyBidExceptions;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.exceptions.CustomerOrProductNotFoundException;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.exceptions.DepositBiddingLessThanStartingPriceException;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Bid;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Customer;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Deposit;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.model.Product;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.BidRepo;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.CustomerRepo;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.repository.ProductRepo;
import com.miu.waafinalproject.SecureOnlineAuctionSystem.services.Customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BidService implements IBidService {
    private final BidRepo bidRepo;
    private final CustomerRepo customerRepo;
    private final ProductRepo productRepository;
    private final CustomerService customerService;

    public Bid createBid(BidAddUpdateDto dto) {
        // Fetch the customer and product from the provided IDs
        Customer customer = customerRepo.findById(dto.getCustomerId()).orElse(null);
        Product product = productRepository.findById(dto.getProductId()).orElse(null);

        if (customerService.isEligibleToBid(dto) == false) {
            throw new DepositBiddingLessThanStartingPriceException("Deposit amount is less than starting price");
        } else {
            if (customer != null && product != null) {
                if (customer.getUsers().getRoles() == RolesEnum.CUSTOMER) {
                    // Check if the customer meets deposit requirements, etc., here
                    // You can use the hasRequiredDeposit method we discussed earlier

                    Bid bid = new Bid();
                    bid.setCustomer(customer);
                    bid.setBidAmount(dto.getNewBidAmount());
                    bid.setBidDate(new Date());
                    bid.setProduct(product);
                    bidRepo.save(bid);

                    return bid;
                } else {
                    throw new CustomerCanOnlyBidExceptions("Only customers can bid");
                }
            } else {
                // Handle cases where the customer or product with the provided IDs was not found
                throw new CustomerOrProductNotFoundException("Customer or product not found");
            }
        }
    }

    public CustomerBidResultDto getHighestBidForProduct(Long productId) {
        return bidRepo.findHighestBidForProduct(productId);
    }

    public Double getHighestBidForCustomerAndProduct(Long customerId, Long productId) {
        Optional<Double> highestBid = bidRepo.findHighestBidByCustomerAndProduct(customerId, productId);
        return highestBid.orElse(0.0); // Default value if there are no bids
    }

    public Bid getHighestBidder(Long productId) {
        List<Bid> bids = bidRepo.findBidsByProductIdOrderByBidAmountDesc(productId);
        if (!bids.isEmpty()) {
            Bid highestBid = bids.get(0);
            return highestBid;
            // Now, 'highestBid' contains the highest bid for the specified product.
        } else {
            throw new CustomerOrProductNotFoundException("Customer or product not found");
        }
    }

    public BidHistoryDto getBidHistoryForCustomer(Long customerId) {
        // Find customer's bids by customer ID
        List<Bid> customerBids = bidRepo.findByCustomerCustomerIDOrderByBidDate(customerId);

        Customer customer = customerRepo.findById(customerId).orElse(null);

        if (customer == null) {
            // Handle the case where the customer is not found
            return null;
        }

        BidHistoryDto bidHistory = new BidHistoryDto();
        bidHistory.setCustomerId(customerId);
        bidHistory.setUsername(customer.getUsers().getEmail());

        List<BidProductHistoryDto> productHistoryList = new ArrayList<>();

        for (Bid bid : customerBids) {
            BidProductHistoryDto productHistory = new BidProductHistoryDto();
            productHistory.setProductName(bid.getProduct().getName());
            productHistory.setBidDate(bid.getBidDate());
            productHistory.setAmount(bid.getBidAmount());

            productHistoryList.add(productHistory);
        }

        bidHistory.setProductHistory(productHistoryList);

        return bidHistory;
    }

    public List<BidDto> findBidsByProductID(Long productId) {
        List<Bid> bids = bidRepo.findByProductProductID(productId);

        return bids.stream()
                .map(bid -> {
                    BidDto bidDto = new BidDto();
                    bidDto.setBidId(bid.getBidID());
                    bidDto.setBidAmount(bid.getBidAmount());
                    bidDto.setCustomerId(bid.getCustomer().getCustomerID());
                    bidDto.setUsername(bid.getCustomer().getUsers().getEmail());

                    // Create a ProductDto and set its properties here
                    ProductDto productDto = new ProductDto();
                    productDto.setProductID(bid.getProduct().getProductID());
                    productDto.setName(bid.getProduct().getName());
                    productDto.setStartingPrice(bid.getProduct().getStartingPrice());
                    productDto.setDescription(bid.getProduct().getDescription());
                    productDto.setStartingPrice(bid.getProduct().getStartingPrice());
                    productDto.setDeposit(bid.getProduct().getDeposit());
                    productDto.setBidDueDate(bid.getProduct().getBidDueDate());
                    productDto.setBiddingPaymentDueDate(bid.getProduct().getBiddingPaymentDueDate());
                    productDto.setReleased(bid.getProduct().isReleased());
                    productDto.setSold(bid.getProduct().isSold());
                    // Set other product properties in the ProductDto

                    bidDto.setProduct(productDto);
                    return bidDto;
                })
                .collect(Collectors.toList());
    }
}
