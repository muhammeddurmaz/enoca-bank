# enoca-bank

## Uygulamayı Docker ile Ayağa Kaldırmak için;
- Uygulamanın bulunduğu dizine gel
- projenin jar dosyasını oluştur bunun için aşağıdaki komutu çalıştır
     - mvn clean install
- Projede bulunan Dockerfile dosyası ile uygulamayı docker image haline getir.
     - docker build -t enoca-bank:0.1 .
- Diğer bağımlıklıklarla birlikte bir container oluşturmak için
     - cd integration
     - docker-compose up
## Uygulamayı mvn ile ayağa kaldırmak için
- Projenin ana dizininde terminali açınız.
- Terminalde mvn spring-boot:run komutunu çalıştırarak projeyi başlatınız.

## EnocaBank API Dökümantasyonu

Sunucu Bilgileri
Servis URL: http://localhost:8080/api

### Account İşlemleri
- Hesap oluşturmak için POST /account endpoint'i kullanılır.
 Request : {
  "accountName": "enoca",
  "customerId": 2
}
- Hesap güncellemek için PUT /account/{id} endpoint'i kullanılır.
 Request : {
  "id": 1,
  "accountName": "enoca",
  "customerId": 2
}  
- Bir müşterinin hesaplarını sorgulamak için GET /accounts/{id} endpoint'i kullanılır.
- Bir hesabın detaylarını sorgulamak için GET /account/{id} endpoint'i kullanılır.
- Hesap silmek için DELETE /account/{id} endpoint'i kullanılır.
- Hesaptan para çekmek için PUT /account/withdraw endpoint'i kullanılır.
 Request : {
  "accountId": "2",
  "amount": 234
} 
- Hesaba para eklemek için PUT /account/add-money endpoint'i kullanılır.
 Request : {
  "accountId": "2",
  "amount": 234
} 

### Müşteri İşlemleri
- Müşteri oluşturmak için POST /customer endpoint'i kullanılır.
Request : {
  "name": "enoca"
}    
- Müşteri güncellemek için PUT /customer/{id} endpoint'i kullanılır.
 Request : {
  "id": 2,
  "name": "updatedname"
} 
- Tüm müşterileri sorgulamak için GET /customers endpoint'i kullanılır.
- Bir müşterinin detaylarını sorgulamak için GET /customer/{id} endpoint'i kullanılır.
- Müşteri silmek için DELETE /customer/{id} endpoint'i kullanılır.
