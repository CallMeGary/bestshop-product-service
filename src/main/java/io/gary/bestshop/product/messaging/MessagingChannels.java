package io.gary.bestshop.product.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MessagingChannels {

    String PRODUCT_CREATED_OUTPUT = "productCreatedOutput";
    @Output(PRODUCT_CREATED_OUTPUT)
    MessageChannel productCreatedOutput();


    String PRODUCT_UPDATED_OUTPUT = "productUpdatedOutput";
    @Output(PRODUCT_UPDATED_OUTPUT)
    MessageChannel productUpdatedOutput();


    String PRODUCT_DELETED_OUTPUT = "productDeletedOutput";
    @Output(PRODUCT_DELETED_OUTPUT)
    MessageChannel productDeletedOutput();


    String PRODUCT_REVIEW_ADDED_OUTPUT = "productReviewAddedOutput";
    @Output(PRODUCT_REVIEW_ADDED_OUTPUT)
    MessageChannel productReviewAddedOutput();


    String PRODUCT_CREATED_INPUT = "productCreatedInput";
    @Input(PRODUCT_CREATED_INPUT)
    MessageChannel productCreatedInput();


    String PRODUCT_UPDATED_INPUT = "productUpdatedInput";
    @Input(PRODUCT_UPDATED_INPUT)
    MessageChannel productUpdatedInput();


    String PRODUCT_DELETED_INPUT = "productDeletedInput";
    @Input(PRODUCT_DELETED_INPUT)
    MessageChannel productDeletedInput();


    String PRODUCT_REVIEW_ADDED_INPUT = "productReviewAddedInput";
    @Input(PRODUCT_REVIEW_ADDED_INPUT)
    MessageChannel productReviewAddedInput();


    String ORDER_COMPLETED_INPUT = "orderCompletedInput";
    @Input(ORDER_COMPLETED_INPUT)
    MessageChannel orderCompletedInput();
}
