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

Account API Kullanım Kılavuzu
Bu kılavuz, Account API'sini kullanarak hesap işlemleri gerçekleştirmek için adım adım rehber sunmaktadır.

1. Hesap Oluşturma
Endpoint: POST /api/account

Yeni bir hesap oluşturmak için bu endpoint'i kullanın. Hesap bilgilerini göndererek hesabınızı oluşturabilirsiniz.

2. Hesap Güncelleme
Endpoint: PUT /api/account/{id}

Varolan bir hesabı güncellemek için bu endpoint'i kullanın. Hesap kimliği ile belirtilen hesabın bilgilerini güncelleyebilirsiniz.

3. Hesapları Müşteri Kimliğine Göre Getirme
Endpoint: GET /api/accounts/{id}

Belirli bir müşteri kimliğine ait tüm hesapları listeleyebilirsiniz. Müşteri kimliği ile hesapları getirin.

4. Tek Hesabı Kimliğe Göre Getirme
Endpoint: GET /api/account/{id}

Belirli bir hesabı, hesap kimliği ile getirebilirsiniz. Hesap kimliğini kullanarak tek bir hesap bilgisini alabilirsiniz.

5. Hesap Silme
Endpoint: DELETE /api/account/{id}

Belirli bir hesabı silmek için bu endpoint'i kullanın. Hesap kimliği ile hesabı silebilirsiniz.

6. Para Çekme
Endpoint: PUT /api/account/withdraw/{id}/{amount}

Hesaptan para çekmek için bu endpoint'i kullanın. Hesap kimliği ve çekilecek miktarı belirterek para çekebilirsiniz.

7. Para Ekleme
Endpoint: PUT /account/add/{id}/{amount}

Hesaba para eklemek için bu endpoint'i kullanın. Hesap kimliği ve eklenecek miktarı belirterek para ekleyebilirsiniz.
