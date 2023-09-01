# Ecommerce App with Spring Boot Microservices

Welcome to the Ecommerce App, a powerful solution built using Java and Spring Boot microservices. This application is designed to streamline the management of shopping carts and cart items, providing a seamless shopping experience for your users.

## Overview

The Ecommerce App is a collection of microservices, each serving a specific purpose:


## Minimum Requirements

Before you begin, ensure you meet the following requirements:

- **JDK (Java Development Kit)**: Version 1.8 or higher is required to compile and run this project. You can download the latest JDK from the [Oracle website](https://www.oracle.com/java/technologies/javase-downloads.html) or use an open-source alternative like OpenJDK.

- **Maven**: Maven is used for managing project dependencies and building the project. Make sure you have Maven installed. You can download it from the [Maven website](https://maven.apache.org/download.cgi).

## Services


### 1. Discovery Server

- **Description**: The Discovery Server operates on its default port, 8761, and utilizes the Eureka Discovery Server to facilitate service registration.
  
### 2. API Gateway

- **Description**: The API Gateway, running on port 8443, also registers itself with the Discovery Server. It serves as a central entry point for all incoming requests and employs load balancing, utilizing the Round Robin technique to distribute requests across multiple service instances.

### 3. Config Server

- **Description**: The Config Server, found on port 8085, registers itself with the Discovery Server. It provides centralized configuration management for all microservices. Common configuration settings are stored in a GitHub repository.

### 4. Product Service

- **Description**: The Product Service can run on multiple instances and registers with the Discovery Server. It is configured as a Config Client to retrieve common configuration from the Config Server. This service offers several functionalities, including fetching products by ID and adding products to the database.
  
### 5. CartItem Service

- **Description**: The CartItem Service can also run on multiple instances and registers with the Discovery Server. Like the Product Service, it utilizes the Config Server for common configuration. This service handles cart item operations, such as adding cart items and deleting them based on cart item or shopping cart IDs.
  
### 6. ShoppingCart Service

- **Description**: The ShoppingCart Service, designed for multiple instances, registers itself with the Discovery Server. It, too, relies on the Config Server for common configuration. This service is responsible for managing shopping carts and their contents. It can add cart items to shopping carts, remove specific cart items from a cart, or delete an entire shopping cart.


<br><br><br>


## Quick Start Guide

Welcome to the Ecommerce App! This guide will help you get started quickly with setting up and using the application.

## Step 1: Clone the Ecommerce App Repository

To get started, clone the Ecommerce App from the GitHub repository:

```shell
git clone https://github.com/ankitpathak530/Ecommerce_App.git
```


## Step 2: Configure the Database and Centralized Configuration

- Create a database named "ecom" for the application to use.


- Centralized Configuration: You can use the provided GitHub repository URL [https://github.com/ankitpathak530/EcommerceConfigServer.git](https://github.com/ankitpathak530/EcommerceConfigServer.git) as a starting point to create your custom repository and configuration files based on your requirements.

  - Create your own repository and customize the configuration files to match your environment and preferences.
  - Ensure that you update the properties file in your custom repository as needed.

After customizing the configuration files in your own repository, configure the Config Server with your custom repository URL:

```yaml
spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/your-username/your-custom-config-repo
```

With these updates, your configuration will be properly centralized from your custom GitHub repository.


## Step 3: Start the Microservices

Follow these steps to start the Ecommerce App microservices:

1. **Discovery Server**:
   - Start the Discovery Server.
   ```shell
   java -jar discovery-server.jar
   ```

2. **Config Server**:
   - Start the Config Server.
   ```shell
   java -jar config-server.jar
   ```

3. **Other Microservices**:
   - Start the remaining four microservices, including Product Service, CartItem Service, ShoppingCart Service, and the API Gateway.


## Step 4: Access Sample Product Data

- When the Product Service starts, it will automatically persist some sample product data in your database.
- You can view the sample data by accessing the following URL: [http://localhost:8443/product/](http://localhost:8443/product/)

Sample Product Data (JSON Array):
```json
[
    {
        "productId": 101,
        "name": "Samsung Phone",
        "description": "Special Phone with 4GB RAM",
        "price": 10999.0,
        "sku": ""
    },
    {
        "productId": 102,
        "name": "Lenovo Phone",
        "description": "Special Phone with 5 cameras",
        "price": 11999.0,
        "sku": ""
    },
    {
        "productId": 103,
        "name": "Micromax Phone",
        "description": "Special Phone with Almond display",
        "price": 8999.0,
        "sku": ""
    },
    {
        "productId": 104,
        "name": "Apple Phone",
        "description": "Special Phone with earphone",
        "price": 15999.0,
        "sku": ""
    }
]
```

## Step 5: Create Your Shopping Cart

- To create your own shopping cart, make a POST request to the following URL, and provide the ShoppingCart object in JSON format:

    ```http
    POST http://localhost:8443/shoppingCart/add-shoppingCart
    ```

    Sample JSON for creating a shopping cart:

    ```json
    {
        "userId" : 211099,
        "shoppingCartName": "My Top Smartphones"
    }
    ```

## Step 6: Add Cart Items to Your Shopping Cart

- After creating your shopping cart, you can add multiple cart items to it.
- To add a cart item, send a POST request to the following URL with the CartItem object in JSON format:

    ```http
    POST http://localhost:8443/shoppingCart/add-cartItem
    ```

    Sample JSON for adding a cart item:

    ```json
    {
        "shoppingCartId": 1,
        "productId": 10,
        "quantity": 4
    }
    ```

## Step 7: Manage Your Cart Items

- You can manage your cart items by using the provided API endpoints.
- To delete a specific cart item, use a DELETE request to the following URL:

    ```http
    DELETE http://localhost:8443/shoppingCart/delete-item/{shoppingCartId}/{cartItemId}
    ```

    Example URL: `http://localhost:8443/shoppingCart/delete-item/1/1`

- To delete your entire shopping cart along with all associated cart items, make a DELETE request to the following URL:

    ```http
    DELETE http://localhost:8443/shoppingCart/delete-item/shoppingCartId/{shoppingCartId}
    ```

    Example URL: `http://localhost:8443/shoppingCart/delete-item/shoppingCartId/1`

Enjoy using the Ecommerce App to manage your shopping carts and cart items seamlessly!

---

For any questions or support, please contact [ankitpathak530@gmail.com](mailto:your.email@example.com)
