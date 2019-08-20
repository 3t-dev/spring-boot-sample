package com.example.demo.feature.order.manager;

import com.example.demo.feature.order.entity.OrderEntity;
import com.example.demo.feature.order.model.Order;
import com.example.demo.feature.order.repository.OrderRepostory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class DefaultOrderManagerImpl implements OrderManager {
    private static Logger logger = LoggerFactory.getLogger(DefaultOrderManagerImpl.class);

    private OrderRepostory repostory;

    @Autowired
    public DefaultOrderManagerImpl(OrderRepostory repostory) {
        this.repostory = repostory;
    }

    @Override
    @Transactional
    public Order saveOne(Order order) {
        logger.debug("save an order {}", order);
        OrderEntity entity = OrderEntity.builder()
                .code(order.getCode())
                .product(order.getProduct())
                .volume(order.getVolume())
                .createdDate(order.getCreatedDate())
                .updatedDate(order.getUpdatedDate())
                .build();

        repostory.save(entity); // we treat SQL Exception as unchecked exception

        Order savedOrder = order.toBuilder().id(entity.getId()).build();

        return savedOrder;
    }
}
