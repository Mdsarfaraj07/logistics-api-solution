package com.logistics.optimizer.model;
import java.util.List;
public record OptimizationRequest(Truck truck, List<Order> orders) {}
