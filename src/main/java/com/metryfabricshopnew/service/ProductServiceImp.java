package com.metryfabricshopnew.service;

import com.metryfabricshopnew.domain.Basket;
import com.metryfabricshopnew.domain.User;
import com.metryfabricshopnew.dto.ProductDTO;
import com.metryfabricshopnew.mapper.ProductMapper;
import com.metryfabricshopnew.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    private final ProductMapper mapper = ProductMapper.MAPPER;

    private final ProductRepository productRepository;
    private final UserService userService;
    private final BasketService basketService;

    public ProductServiceImp(ProductRepository productRepository, UserService userService, BasketService basketService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.basketService = basketService;
    }

    @Override
    public List<ProductDTO> getAll() {
        return mapper.fromProductList(productRepository.findAll());
    }

    @Override
    @Transactional
    public void addToUserBasket(Long productId, String username) {
        User user = userService.findByName(username);
        if (user == null){
            throw new RuntimeException("User " + username + "not found");
        }

        Basket basket = user.getBasket();
        if (basket == null){
            Basket newBasket = basketService.createBasket(user, Collections.singletonList(productId));
            user.setBasket(newBasket);
            userService.save(user);
        } else {
            basketService.addProducts(basket, Collections.singletonList(productId));
        }
    }


}
