![](https://i.ibb.co/71WSSZh/main-Logo-fw.png)
# Bring to Me Incorporated Online Alışveriş

Spring Boot ve React.js kullanılarak geliştirilen e-ticaret projesidir.

#### Admin girişi:

Login: mail.m.karakas@gmail.com <br>
Password: ****** (Şifre Özel)

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
Anasayfa  |  Ürünler
:------------------------:|:-------------------------:
![](https://i.ibb.co/jgdgPXf/menu.png)  |  ![](https://i.ibb.co/Y7v6756/urunler.png)

Sepet  |  Boş Sepet
:------------------------:|:-------------------------:
![](https://i.ibb.co/Jn99fr2/sepet.png)  |  ![](https://i.ibb.co/Jn8CBJ7/sepetbo.png)

Ürünler Listesi  |  Kullanıcılar Listesi
:------------------------:|:-------------------------:
![]()  |  ![]()

Admin ekranı  |  Kullanıcı ekranı
:------------------------:|:-------------------------:
![]()  |  ![]()

Ürün ekleme  |  Ürün listeleme
:------------------------:|:-------------------------:
![]()  |  ![]()
