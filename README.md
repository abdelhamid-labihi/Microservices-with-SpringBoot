# Microservices-Practical-2


### [Config server Readme](config-server/Readme.md)


# Microservices Practical 2

Welcome to the **Microservices Practical 2** project! This guide will walk you through setting up a microservice with **Consul** for service discovery and configuration management, as well as **Vault** for secure secret storage.

## Table of Contents
1. [Creating a Microservice](#creating-a-microservice)
2. [Configuring Consul](#configuring-consul)
3. [Using Consul for Configuration](#using-consul-for-configuration)
4. [Testing Configuration with a REST Controller](#testing-configuration-with-a-rest-controller)
5. [Introduction to Vault](#introduction-to-vault)
6. [Starting Vault](#starting-vault)
7. [Using Vault CLI](#using-vault-cli)
8. [Accessing the Vault UI](#accessing-the-vault-ui)
9. [Configuring the Microservice for Vault](#configuring-the-microservice-for-vault)
10. [Multiple Configuration Sources](#multiple-configuration-sources)
11. [Best Practices](#best-practices)

## [Config Server Setup](config-server/Readme.md)

## 1. Creating a Microservice

- **Name**: `billing-service`
- **Dependencies**:
    - Spring Web
    - Consul Discovery (or Spring Cloud Config)
    - Consul Config

## 2. Configuring Consul

- Consul provides a key-value store for service-specific configurations.
- Create a folder structure for your service configuration:
    - Example: `config/billing-service/`
- Add key-value pairs like `user.username` and `token` in Consul.

## 3. Using Consul for Configuration

In the `application.properties` of your microservice, set the following properties:

```properties
spring.application.name=billing-service
server.port=8084
spring.config.import=optional:consul:
```

This tells Spring Boot to fetch configuration from Consul.

## 4. Testing Configuration with a REST Controller

- Create a REST controller in your microservice to retrieve configuration values.
- Use the `@Value` annotation to inject and return these configuration properties.

## 5. Introduction to Vault

**Vault** is a tool for securely storing secrets such as passwords and API keys.

- Download Vault and run it in **dev mode** for testing purposes.

## 6. Starting Vault

Start Vault using the following command:

```bash
vault server -dev
```

Take note of the **root token** provided when Vault starts.

## 7. Using Vault CLI

To interact with Vault using the command line:

1. Set the Vault address environment variable:
   ```bash
   export VAULT_ADDR=http://localhost:8200
   ```

2. Add secrets using the `vault kv put` command:
   ```bash
   vault kv put secret/billing-service/ user.username=Mohamed user.password=verysecret
   ```

## 8. Accessing the Vault UI

- Vault provides a web interface where you can view and manage your secrets.
- Access it using the URL and root token provided when Vault started.

## 9. Configuring the Microservice for Vault

To enable Vault in your microservice, add the following to your `application.properties`:

```properties
spring.cloud.vault.token=<root-token>
spring.cloud.vault.scheme=http
spring.cloud.vault.kv.enabled=true
spring.config.import=optional:consul:,vault://
```

## 10. Multiple Configuration Sources

You can use multiple configuration sources in your application. For example:

```properties
spring.config.import=optional:consul:,vault://
```

This allows you to fetch configuration from both Consul and Vault.

## 11. Best Practices

- Use **Consul** for general configuration management.
- Use **Vault** to store sensitive information such as passwords and tokens.
- In production environments, always use **HTTPS** for securing communication with Vault.

---

By following this guide, you will have set up a microservice using **Consul** for configuration and **Vault** for secret management, ensuring both convenience and security in your system.