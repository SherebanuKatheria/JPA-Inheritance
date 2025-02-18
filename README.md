# Authentication Provider Inheritance Design

This document provides an overview of the inheritance-based design used to handle different authentication providers (e.g., email-based, OAuth-based) in the application. The design leverages polymorphism and JPA's inheritance strategies to avoid redundant data and provide a clean, extensible structure.

---

## **Overview**

The goal of this design is to:
1. **Avoid redundant data**: Only store authentication data relevant to the specific provider (e.g., `password` for email-based users, `oauthProviderId` for OAuth users).
2. **Support multiple authentication providers**: Easily add new authentication methods (e.g., SMS-based authentication) without modifying the core structure.
3. **Simplify the database schema**: Use JPA's inheritance strategies to manage polymorphic relationships in the database.

---

## **Key Components**

### 1. **Base Class: `BaseAuthDetails`**
This abstract class serves as the base for all authentication provider implementations. It uses JPA's `@Inheritance` strategy with a discriminator column (`auth_type`) to differentiate between provider types.

---

### 2. **Concrete Implementations**
Each authentication provider has its own concrete class that extends `BaseAuthDetails`.

#### **LocalAuthDetails**
Stores data for email-based authentication (e.g., `password`).

#### **GoogleAuthDetails**
Stores data for OAuth-based authentication.

---

### 3. **`UserAuth` Entity**
This entity represents the authentication data for a user. It uses a polymorphic relationship with `BaseAuthDetails` to store provider-specific data.

---

## **Database Schema**

The `auth_details` table uses a **single-table inheritance** strategy with a discriminator column (`auth_type`) to differentiate between authentication providers.

---

## **Benefits of This Design**

1. **No Redundant Data**: Only the necessary authentication data is stored for each user.
2. **Extensibility**: Adding new authentication providers is straightforward and does not require changes to the core structure.
3. **Polymorphism**: JPA handles the polymorphism using a discriminator column, making it easy to query and manage different authentication types.
4. **Cleaner Code**: The `UserAuth` entity is simpler and only contains a single `authDetails` field, which can represent any authentication type.

---

## **Conclusion**

This inheritance-based design provides a flexible and scalable solution for handling multiple authentication providers in the application. It avoids redundant data, simplifies the database schema, and makes it easy to add new authentication methods in the future.
