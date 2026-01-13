package com.logistics.optimizer.model;
import java.time.LocalDate;
public record Order(String id, long payout_cents, int weight_lbs, int volume_cuft, String origin, String destination, LocalDate pickup_date, LocalDate delivery_date, boolean is_hazmat) {}
