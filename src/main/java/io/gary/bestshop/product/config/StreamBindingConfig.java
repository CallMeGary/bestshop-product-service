package io.gary.bestshop.product.config;

import io.gary.bestshop.product.messaging.MessagingChannels;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(MessagingChannels.class)
public class StreamBindingConfig {

}
