package io.gary.bestshop.product.messaging;


import io.gary.bestshop.messaging.event.order.OrderCompletedEvent;
import io.gary.bestshop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventListener {

    private final ProductService productService;

    @StreamListener(MessagingChannels.ORDER_COMPLETED_INPUT)
    public void handleOrderCompletedEvent(OrderCompletedEvent event) {

        log.info("Processing event: {}", event);

        productService.increasePurchaseCount(event.getOrder().getProductId());
    }
}
