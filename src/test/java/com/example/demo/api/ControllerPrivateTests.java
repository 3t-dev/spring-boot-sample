package com.example.demo.api;

import com.example.demo.api.dto.OrderRequestDto;
import com.example.demo.exception.model.InvalidInputException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.powermock.reflect.Whitebox;

@RunWith(JUnit4.class)
public class ControllerPrivateTests {
    @Test(expected = InvalidInputException.class)
    public void when_invalidInput_then_exception() throws Exception {
        OrderRequestDto dto = OrderRequestDto.builder().code("").build(); // empty code

        OrderController controller = new OrderController(null); // just test private function

        Whitebox.invokeMethod(controller, "validateDto", dto);
    }
}
