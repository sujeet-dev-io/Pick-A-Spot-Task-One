# Pick-A-Spot-Task-One
A Spring Boot based backend application to find the most suitable slot for a shipping container in a yard. Implements smart slot selection based on size, cooling requirement, distance, and availability. Includes REST API, DTOs, and proper error handling.
# Pick a Spot - Container Slot Matching API

This is a Spring Boot project that determines the best slot in a shipping yard to place a container based on its size, cold storage needs, and location.

## Features
- Match containers to available slots.
- Supports size and cold storage compatibility.
- Handles occupied slot conditions.
- Returns best suitable slot or an error if none available.

---

## 🔧 How to Run This Project Locally

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/pick-a-spot.git
cd pick-a-spot
```

### 2. Build the Project
Make sure you have Java 17+ and Maven installed.
```bash
./mvnw clean install
```

### 3. Run the Application
```bash
./mvnw spring-boot:run
```
The app will start on `http://localhost:8080`

---

## 📮 API Endpoint

### `POST /api/v1/pickSpot`

### Request Body Example (No Suitable Slot Case)
```json
{
  "container": {
    "id": "C1",
    "size": "big",
    "needsCold": false,
    "x": 1,
    "y": 1
  },
  "yardMap": [
    {
      "x": 1,
      "y": 2,
      "sizeCap": "small",
      "hasColdUnit": false,
      "occupied": false
    },
    {
      "x": 2,
      "y": 2,
      "sizeCap": "small",
      "hasColdUnit": true,
      "occupied": false
    }
  ]
}
```
### Response
```json
{
  "error": "no suitable slot"
}
```

### Request Body Example (Valid Slot Found)
```json
{
  "container": {
    "id": "C1",
    "size": "small",
    "needsCold": false,
    "x": 1,
    "y": 1
  },
  "yardMap": [
    {
      "x": 1,
      "y": 2,
      "sizeCap": "big",
      "hasColdUnit": false,
      "occupied": false
    },
    {
      "x": 2,
      "y": 2,
      "sizeCap": "big",
      "hasColdUnit": false,
      "occupied": false
    }
  ]
}
```
### Response
```json
{
  "containerId": "C1",
  "targetX": 1,
  "targetY": 2
}
```

---

## 🧪 Testing
Use Postman or any API tool to send the above request to:
```
http://localhost:8080/api/v1/pickSpot
```
Ensure the correct response is received based on the container and yardMap conditions.

---

## 📌 Note
- Big containers can't go in small slots.
- Cold containers need slots with cold units.
- Occupied slots are ignored.

---

## 📜 License
This project is open-source and free to use.

