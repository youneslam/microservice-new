package com.dev;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author DELL
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderPlacedEvent {
    private String orderNumber;
}

