package com.metryfabricshopnew.service;

import com.metryfabricshopnew.domain.Basket;
import com.metryfabricshopnew.domain.Product;
import com.metryfabricshopnew.domain.User;
import com.metryfabricshopnew.dto.BasketDTO;
import com.metryfabricshopnew.dto.BasketDetailDTO;
import com.metryfabricshopnew.repository.BasketRepository;
import com.metryfabricshopnew.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    public BasketServiceImpl(BasketRepository basketRepository, ProductRepository productRepository, UserService userService) {
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    public Basket createBasket(User user, List<Long> productsIds) {
        Basket basket = new Basket();
        basket.setUser(user);
        List<Product> productList = getCollectRefProductsByIds(productsIds);
        basket.setProducts(productList);
        return basketRepository.save(basket);
    }

    private List<Product> getCollectRefProductsByIds(List<Long> productsIds) {
        return productsIds.stream()
                .map(productRepository::getOne)
                .collect(Collectors.toList());
    }

    @Override
    public void addProducts(Basket basket, List<Long> productsIds) {
        List<Product> products = basket.getProducts();
        List<Product> newProductList = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProductList.addAll(getCollectRefProductsByIds(productsIds));
        basket.setProducts(newProductList);
        basketRepository.save(basket);
    }

    @Override
    public BasketDTO getBasketByUser(String name) {
        User user = userService.findByName(name);
        if (user == null || user.getBasket() == null) {
            return new BasketDTO();
        }
        BasketDTO basketDTO = new BasketDTO();
        Map<Long, BasketDetailDTO> mapByproductId = new HashMap<>();

        List<Product> products = user.getBasket().getProducts();
        for (Product product : products) {
            BasketDetailDTO detail = mapByproductId.get(product.getId());
            if (detail == null) {
                mapByproductId.put(product.getId(), new BasketDetailDTO(product));
            } else {
                detail.setAmount(detail.getAmount().add(new BigDecimal(1.0)));
                detail.setSum(detail.getSum()+Double.valueOf(product.getPrice().toString()));
            }
        }
        basketDTO.setBasketDetails(new ArrayList<>(mapByproductId.values()));
        basketDTO.aggregate();

        return basketDTO;
    }
}
