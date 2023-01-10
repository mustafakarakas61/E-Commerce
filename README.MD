![](https://i.ibb.co/71WSSZh/main-Logo-fw.png)
# Bring to Me Incorporated Online Alışveriş

Spring Boot ve React.js kullanılarak geliştirilen e-ticaret projesidir.

#### Admin girişi:

Login: mail.m.karakas@gmail.com <br>
Password: ****** (Şifre Özel)

## Kullanılan Teknolojiler:

* Back-end: Spring (Boot, Data, Security), JPA / Hibernate, PostgreSQL,
* Front-end: TypeScript, React.js, Redux Toolkit, Ant Design, Jest
* Security: JWT
* REST API, GraphQL API
* Server Build: Maven
* Client Build: npm, yarn, webpack

## Özellikler

* JWT ve E-posta doğrulama ile kimlik doğrulama.
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
Anasayfa | Ürünler 
:------------------------:|:------------------------:
<img src="https://i.ibb.co/jgdgPXf/menu.png" style="width: 693px ; float:left"> | <img src="https://i.ibb.co/Y7v6756/urunler.png" style="width: 693px ; float:right"> 

Sepet  |  Boş Sepet
:------------------------:|:------------------------:
<img src="https://i.ibb.co/Jn99fr2/sepet.png" style="width: 693px ;float:left">  |  <img src="https://i.ibb.co/Jn8CBJ7/sepetbo.png" style="width: 693px ;float:right">

Ürünler Listesi  |  Kullanıcılar Listesi
:------------------------:|:-------------------------:
<img src="https://i.ibb.co/Sxd5QZ9/resim-2023-01-11-003915756.png" style="width: 693px ;float:left">  | <img src="https://i.ibb.co/YN0s9kn/resim-2023-01-11-002605722.png" style="width: 693px ;float:right">

Admin ekranı  | Kullanıcı ekranı
:------------------------:|:-------------------------:
<img src="https://i.ibb.co/svjVScV/resim-2023-01-11-001622560.png" style="width: 693px ; float:left">  |  <img src="https://i.ibb.co/xzmZXyN/resim-2023-01-11-002020986.png" style="width: 693px ;float:right">

Ürün ekleme  | Ürün listeleme
:------------------------:|:-------------------------:
<img src="https://i.ibb.co/GMs90cv/resim-2023-01-11-002304346.png" style="width: 693px ;float:left"> | <img src="https://i.ibb.co/LZ7DNj1/resim-2023-01-11-002358581.png" style="width: 693px ;float:right">