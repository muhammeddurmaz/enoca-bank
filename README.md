# enoca-bank

Account API Kullanım Kılavuzu
Bu kılavuz, Account API'sini kullanarak hesap işlemleri gerçekleştirmek için adım adım rehber sunmaktadır.

1. Hesap Oluşturma
Endpoint: POST /account

Yeni bir hesap oluşturmak için bu endpoint'i kullanın. Hesap bilgilerini göndererek hesabınızı oluşturabilirsiniz.

2. Hesap Güncelleme
Endpoint: PUT /account/{id}

Varolan bir hesabı güncellemek için bu endpoint'i kullanın. Hesap kimliği ile belirtilen hesabın bilgilerini güncelleyebilirsiniz.

3. Hesapları Müşteri Kimliğine Göre Getirme
Endpoint: GET /accounts/{id}

Belirli bir müşteri kimliğine ait tüm hesapları listeleyebilirsiniz. Müşteri kimliği ile hesapları getirin.

4. Tek Hesabı Kimliğe Göre Getirme
Endpoint: GET /account/{id}

Belirli bir hesabı, hesap kimliği ile getirebilirsiniz. Hesap kimliğini kullanarak tek bir hesap bilgisini alabilirsiniz.

5. Hesap Silme
Endpoint: DELETE /account/{id}

Belirli bir hesabı silmek için bu endpoint'i kullanın. Hesap kimliği ile hesabı silebilirsiniz.

6. Para Çekme
Endpoint: PUT /account/withdraw/{id}/{amount}

Hesaptan para çekmek için bu endpoint'i kullanın. Hesap kimliği ve çekilecek miktarı belirterek para çekebilirsiniz.

7. Para Ekleme
Endpoint: PUT /account/add/{id}/{amount}

Hesaba para eklemek için bu endpoint'i kullanın. Hesap kimliği ve eklenecek miktarı belirterek para ekleyebilirsiniz.
