package com.example.demo.feature.order;

import com.example.demo.exception.model.WrongLogicException;
import com.example.demo.feature.order.manager.OrderManager;
import com.example.demo.feature.order.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.example.demo.common.Constants.ERROR_CODE_WRONG_LOGIC;
import static com.example.demo.common.Constants.LOGIC_MIN_VOLUME;

@Component
public class DefaultOrderModifyingUsecaseImpl implements OrderModifyingUsecase {
    private static Logger logger = LoggerFactory.getLogger(DefaultOrderModifyingUsecaseImpl.class);

    private OrderManager manager;

    @Autowired
    public DefaultOrderModifyingUsecaseImpl(OrderManager manager) {
        this.manager = manager;
    }

    @Override
    public Order addNewOrder(Order order) {
        logger.info("add new order {}", order);
        performLogicCheck(order);

        order.setCreatedDate(new Date());
        order.setUpdatedDate(new Date());

        return manager.saveOne(order);
    }

    private void performLogicCheck(Order order) {
        // perform validation logic
        // eg: volume of order should be greater than 1000
        if (order.getVolume() < LOGIC_MIN_VOLUME) {
            throw new WrongLogicException(ERROR_CODE_WRONG_LOGIC, "volume of order should be greater than 1000");
        }
    }
}
