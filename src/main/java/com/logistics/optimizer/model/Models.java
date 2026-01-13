package com.logistics.optimizer.model;
import java.time.LocalDate;
import java.util.List;

public record Truck(String id, int max_weight_lbs, int max_volume_cuft) {}
public record Order(String id, long payout_cents, int weight_lbs, int volume_cuft, String origin, String destination, LocalDate pickup_date, LocalDate delivery_date, boolean is_hazmat) {}
public record OptimizationRequest(Truck truck, List<Order> orders) {}
public record OptimizationResponse(String truck_id, List<String> selected_order_ids, long total_payout_cents, int total_weight_lbs, int total_volume_cuft, double utilization_weight_percent, double utilization_volume_percent) {}
