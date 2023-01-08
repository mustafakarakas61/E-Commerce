# _Bring to Me_ _Online Alışveriş_

Spring Boot ve React.js kullanılarak geliştirilen e-ticaret projesidir.

#### Admin girişi:

Login: admin@gmail.com <br>
Password: admin

## Kullanılan Teknolojiler:

* Back-end: Spring (Boot, Data, Security), JPA / Hibernate, PostgreSQL,
* Front-end: TypeScript, React.js, Redux Toolkit, Ant Design, Jest
* Security: JWT, OAuth2 Google, Facebook, Github
* REST API, GraphQL API
* Server Build: Maven
* Client Build: npm, yarn, webpack

## Özellikler

* JWT ve E-posta doğrulama ile kimlik doğrulama.
* Google, Facebook veya Github ile kimlik doğrulama
* Müşteriler belirtilen kriterlere göre ürün araması yapabilirler.
* Müşteriler, alışveriş sepetinden ürün ekleyebilir ve silebilir.
* Müşteriler, alışveriş sepetindeki ürünleri sipariş edebilirler.
* Müşteriler şifrelerini değiştirebilir ve siparişlerini görüntüleyebilir.
* Yönetici ürün ekleyebilir veya değiştirebilir.
* Yönetici herhangi bir kullanıcının verilerini değiştirebilir.
* Yönetici, tüm kullanıcıların siparişlerini görüntüleyebilir.

## Kurulum

1. Projeyi Intellij idea'da açın.
2. Postgresql'i ve pgAdmin'i bilgisayarınıza kurun.
3. pgAdmin'i açın ve "bringtome" adında bir veritabanı oluşturun.
4. postgresql için application.yml ve application-dev.yml'de ayarlarınızı yapın.
5. node.js ve npm'i bilgisayarınıza kurun
6. Projeyi açtıktan sonra terminale `cd .\frontend\` yazıp frontend dosyaların bulunduğu alt klasöre inin. 
6. Terminale `npm install` komutunu çalıştırarak gerekli bağımlılıkları yükleyin.
7. Spring boot uygulamasını (port 8080) ayağa kaldırın ve `npm start` komutunu çalıştırarak frontend'i çalıştırın ve http://localhost:3000 adresine gidin.
8. Proje artık çalışır durumda.

## Swagger Documentation
http://localhost:8080/swagger-ui.html

## Screenshots
Menu page  |  Product page
:------------------------:|:-------------------------:
![Menu page](https://i.ibb.co/VT4RzYj/1menu.jpg)  |  ![Product page](https://i.ibb.co/HtnKp0W/2-Product-page.jpg)

Cart  |  Ordering
:------------------------:|:-------------------------:
![Email template](https://i.ibb.co/8Y8bfSG/3-Cart.jpg)  |  ![List of users](https://i.ibb.co/tLmY8y2/4-Ordering.jpg)

Email template  |  List of orders
:------------------------:|:-------------------------:
![Email template](https://i.ibb.co/bmKTLPJ/email-template.jpg)  |  ![List of users](https://i.ibb.co/pLTyF25/6-List-of-orders.jpg)

User profile page  |  Add product page
:------------------------:|:-------------------------:
![User profile page](https://i.ibb.co/qx1Csc8/7-User-profile-page.jpg)  |  ![Add product page](https://i.ibb.co/XbsJPQH/8-Add-perfume-page.jpg)

Edit product list  |  Edit product page
:------------------------:|:-------------------------:
![Edit product list](https://i.ibb.co/HFb9wfR/9-Edit-perfume-list.jpg)  |  ![Edit product page](https://i.ibb.co/jH8R8xL/10-Edit-perfume-page.jpg)
