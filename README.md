# Logistics Load Optimizer API 🚀

A high-performance Spring Boot API designed to solve the **Truck Load Optimization Problem**. It uses an advanced **Bitmask Dynamic Programming** algorithm to find the most profitable combination of orders within truck weight and volume constraints.

## ✨ Features
* **Optimal Selection:** Guarantees the maximum payout (cents) for any given set of orders.
* **Fast Processing:** Handles up to 22 orders efficiently using (2^n \cdot n)$ complexity.
* **Validation:** Checks for weight limits, volume limits, and date validity (pickup must be before delivery).
* **Docker Ready:** Includes Dockerfile and Docker Compose for easy deployment.

## 🛠️ Tech Stack
* **Java 21**
* **Spring Boot 3.4.1**
* **Maven** (Build Tool)
* **Docker** (Containerization)

## 🚀 Getting Started

### Prerequisites
* Java 21 or Docker installed.

### Run with Docker (Recommended)
```bash
docker-compose up --build
```

### Run Locally (Maven)
```bash
mvn clean package -DskipTests
java -jar target/load-optimizer-1.0.0.jar
```

## 🧪 Testing the API
You can test the endpoint using `curl`:

```bash
curl -X POST http://localhost:8080/api/v1/load-optimizer/optimize \
-H "Content-Type: application/json" \
-d '{
  "truck": {"id": "T1", "max_weight_lbs": 5000, "max_volume_cuft": 500},
  "orders": [
    {"id": "O1", "payout_cents": 1500, "weight_lbs": 2000, "volume_cuft": 150, "pickup_date": "2026-01-15", "delivery_date": "2026-01-17"},
    {"id": "O2", "payout_cents": 1200, "weight_lbs": 3500, "volume_cuft": 400, "pickup_date": "2026-01-15", "delivery_date": "2026-01-16"}
  ]
}'
```

## 📈 Performance
The algorithm uses **Bitmask DP**, which is significantly faster than a brute-force approach. It evaluates all ^n$ subsets while reusing sub-problem solutions, ensuring the API stays responsive under load.



