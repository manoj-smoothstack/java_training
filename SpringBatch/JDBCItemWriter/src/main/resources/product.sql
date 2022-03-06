CREATE TABLE `product` (
  `product_id` int NOT NULL,
  `prod_name` varchar(45) NOT NULL,
  `price` decimal(15,0) NOT NULL,
  `unit` int NOT NULL,
  `prod_desc` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;