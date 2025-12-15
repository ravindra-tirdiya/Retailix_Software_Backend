# 🏬 Retailix Billing Software (Spring Boot)

A **modern billing & POS backend application** built using **Spring Boot** for malls and retail shops. This system allows shop owners to manage categories, items, billing, payments (Cash / UPI / Card), receipts, and view daily income & order history through a dashboard.

---

## 🚀 Features

### 👤 Shop / Mall Owner

* Add & manage **Categories**
* Add **Items** under specific categories
* View **Item List** & manage pricing

### 🧾 Billing System

* Add multiple items during billing
* Auto calculate **total amount**
* Supports multiple payment modes:

  * 💵 Cash
  * 📱 UPI (QR Code)
  * 💳 Card

### 💰 Payments

* UPI payment QR generation
* Card & Cash payment support
* Payment status tracking

### 🖨️ Receipt

* Auto-generated **receipt after payment**
* Printable bill format

### 📊 Dashboard

* Daily total income
* Total orders count
* Order history listing

### 📦 Order Management

* Store complete order history
* View previous bills
* Filter orders by date

---

## 🛠️ Tech Stack

| Technology            | Usage                          |
| --------------------- | ------------------------------ |
| Java 21               | Programming Language           |
| Spring Boot           | Backend Framework              |
| Spring Security + JWT | Authentication & Authorization |
| Spring Data JPA       | Database Access                |
| MySQL                 | Database                       |
| Maven                 | Build Tool                     |
| Cloudinary            | Image Storage                  |
| Razorpay              | Online Payments                |

---

## 📂 Project Structure

```
com.ecommers.billingsoftware
│
├── controller    # REST APIs
├── service       # Business logic
├── repository    # JPA repositories
├── entity        # Database entities
├── io            # Request & Response DTOs
├── security      # JWT & Security config
└── util          # Utility classes
```

---

## 🔐 Authentication

* JWT based authentication
* Role based access:

  * ROLE_ADMIN
  * ROLE_USER

---

## ⚙️ Application Configuration

Create a file: `src/main/resources/application.properties`

```properties
spring.datasource.url=YOUR_DATABASE_URL
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

server.servlet.context-path=/api/v1.0

cloudinary.cloud-name=YOUR_CLOUDINARY_NAME
cloudinary.api-key=YOUR_CLOUDINARY_KEY
cloudinary.api-secret=YOUR_CLOUDINARY_SECRET

jwt.secret.key=YOUR_JWT_SECRET_KEY

razorpay.api-key=YOUR_RAZORPAY_KEY
razorpay.secret-key=YOUR_RAZORPAY_SECRET
```

---

## 🔐 Frontend URL Configuration

Replace your frontend URL inside SecurityConfig (CORS configuration).

Example:
```
.allowedOrigins("http://localhost:5173")

```

## ▶️ Run the Application

```bash
# Clone repository
git clone n  https://github.com/ravindra-tirdiya/Retailix_Software_Backend.git

# Run application
./mvnw spring-boot:run
```

Application will start at:

```
http://localhost:8080/api/v1.0
```

---

## 📌 Sample API Endpoints

| Method | Endpoint        | Description   |
| ------ | --------------- | ------------- |
| POST   | /admin/register | Register user |
| GET    | /admin/users    | Get all users |
| POST   | /categories     | Add category  |
| POST   | /items          | Add item      |
| POST   | /billing        | Create bill   |
| GET    | /orders         | Order history |

---

## 🧪 Testing

* Use **Postman** or **Swagger** to test APIs
* JWT token required for secured endpoints

---

## 🌍 Deployment

This backend can be deployed on:

* Railway
* Render
* AWS EC2
* Docker

Make sure environment variables are properly set in production.

---

## 📸 Future Enhancements

* PDF receipt download
* GST & tax calculation
* Multi-shop support
* Frontend (React)

---

## 👨‍💻 Developer

**Ravindra Tirdiya**
Software Developer
📍 Jaipur, India

---

## ⭐ Support

If you like this project, please ⭐ star the repository and share feedback.

---

**Happy Coding 🚀**
