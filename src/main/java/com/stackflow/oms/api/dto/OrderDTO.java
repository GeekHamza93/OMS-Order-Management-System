package com.stackflow.oms.api.dto;

import com.stackflow.oms.domain.Order.Status;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

public record OrderDTO(
        Long id,
        @NotNull Long customerId,
        @NotNull Status status,
        @NotNull @Min(0) BigDecimal amount,
        Instant createdAt
) {}