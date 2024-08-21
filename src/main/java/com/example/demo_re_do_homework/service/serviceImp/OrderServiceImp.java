package com.example.demo_re_do_homework.service.serviceImp;

import com.example.demo_re_do_homework.dto.dtoOrder.dtoOrderRequest.DTOOrderRequest;
import com.example.demo_re_do_homework.dto.dtoOrder.dtoOrderResponse.DTOOrderProductResponse;
import com.example.demo_re_do_homework.entity.Customer;
import com.example.demo_re_do_homework.entity.Order;
import com.example.demo_re_do_homework.entity.Product;
import com.example.demo_re_do_homework.entity.ProductOrder;
import com.example.demo_re_do_homework.entity.enums.Status;
import com.example.demo_re_do_homework.repository.CustomerRepository;
import com.example.demo_re_do_homework.repository.OrderRepository;
import com.example.demo_re_do_homework.repository.ProductRepository;
import com.example.demo_re_do_homework.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderServiceImp implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderServiceImp(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public DTOOrderProductResponse saveOrder(List<DTOOrderRequest> dtoOrderRequest, Long customerId) {
        // Step 1: Fetch the customer by ID
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (Objects.isNull(customer)) {
            // Handle the case where the customer is not found
            return null;
        }

        // Step 2: Create a new Order and associate it with the customer
        Order newOrder = new Order();
        newOrder.setCustomer(customer);
        newOrder.setOrderDate(LocalDate.from(LocalDateTime.now()));
        newOrder.setStatus(Status.PENDING);
        newOrder.setTotalAmount(0.0F);

        // Step 3: Create a list to hold ProductOrder entities
        List<ProductOrder> productOrders = new ArrayList<>();
        double totalAmount = 0.0;

        // Step 4: Loop through each DTOOrderRequest to map products and quantities
        for (DTOOrderRequest dtoOrderRequests : dtoOrderRequest) {
            Product product = productRepository.findById(dtoOrderRequests.getProductId()).orElse(null);
            if (Objects.isNull(product)) {
                continue;
            }
            // Create a new ProductOrder and set its fields
            ProductOrder productOrder = new ProductOrder();
            productOrder.setOrder(newOrder);
            productOrder.setProduct(product);
            productOrder.setQuantity(dtoOrderRequests.getQuantity());

            // Add to the list of ProductOrder
            productOrders.add(productOrder);

            // Update the order's total amount
            totalAmount += product.getUnitPrice() * dtoOrderRequests.getQuantity();
        }

        // Step 5: Set the calculated total amount to the order
        newOrder.setTotalAmount((float) totalAmount);

        // Step 6: Save the order and associated productOrders to the database
        newOrder.setProductOrder(productOrders);
        orderRepository.save(newOrder);

        // Step 7: Convert the saved order to a DTOOrderProductResponse
        DTOOrderProductResponse dtoOrderProductResponse = new DTOOrderProductResponse();
        dtoOrderProductResponse.responseOrderProductTODTO(newOrder);

        return dtoOrderProductResponse;
    }

    @Override
    public DTOOrderProductResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (!Objects.isNull(order)){
            DTOOrderProductResponse dtoOrderProductResponse = new DTOOrderProductResponse();
            dtoOrderProductResponse.responseOrderProductTODTO(order);
            return dtoOrderProductResponse;
        }
        return null;
    }

    @Override
    public DTOOrderProductResponse getOrderByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (Objects.isNull(customer)){
            return null;
        }
        Order order = orderRepository.findById(customerId).orElse(null);
        DTOOrderProductResponse dtoOrderProductResponse = new DTOOrderProductResponse();
        dtoOrderProductResponse.responseOrderProductTODTO(Objects.requireNonNull(order));
        return dtoOrderProductResponse;
    }

    @Override
    public DTOOrderProductResponse updateOrderStatus(Status status, Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (!Objects.isNull(order)){
            order.setStatus(status);
            Order savedOrderStatus = orderRepository.save(order);
            DTOOrderProductResponse dtoOrderProductResponse = new DTOOrderProductResponse();
            dtoOrderProductResponse.responseOrderProductTODTO(savedOrderStatus);
            return dtoOrderProductResponse;
        }
        return null;
    }


}
