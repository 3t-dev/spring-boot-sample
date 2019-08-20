package com.example.demo.feature.order;

import com.example.demo.common.Constants;
import com.example.demo.exception.model.WrongLogicException;
import com.example.demo.feature.order.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.powermock.reflect.Whitebox;

@RunWith(JUnit4.class)
public class DefaultOrderModifyingUsecaseImplPrivateTests {
    @Test(expected = WrongLogicException.class)
    public void when_invalidInput_then_exception() throws Exception {
        Order order = Order.builder().volume(Constants.LOGIC_MIN_VOLUME - 1).build(); // wrong min volume logic

        DefaultOrderModifyingUsecaseImpl usecase = new DefaultOrderModifyingUsecaseImpl(null); // just test private function

        Whitebox.invokeMethod(usecase, "performLogicCheck", order);
    }
}
