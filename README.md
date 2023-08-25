# enoca-bank
A repository for the backend challenge
Account API Kullanım Kılavuzu
Bu kılavuz, Account API'sini kullanarak hesap işlemleri gerçekleştirmek için gereken adımları içermektedir.

Hesap Oluşturma
Endpoint: POST /account

Bu endpoint ile yeni bir hesap oluşturabilirsiniz. Hesap bilgileri (bakiye, şehir, para birimi, müşteri kimliği) girilir ve yeni bir hesap oluşturulur.

Hesap Güncelleme
Endpoint: PUT /account/{id}

Bu endpoint ile mevcut bir hesabı güncelleyebilirsiniz. Hesap kimliği belirtilir ve güncellenecek hesap bilgileri (bakiye, şehir, para birimi, müşteri kimliği) girilerek hesap güncellenir.

Hesapları Müşteri Kimliğine Göre Getirme
Endpoint: GET /accounts/{id}

Bu endpoint ile belirli bir müşteri kimliğine ait tüm hesapları listeleyebilirsiniz. Müşteri kimliği belirtilir ve o müşteriye ait hesaplar listelenir.

Tek Hesabı Kimliğe Göre Getirme
Endpoint: GET /account/{id}

Bu endpoint ile belirli bir hesabı, hesap kimliği belirtilerek getirebilirsiniz. İstenilen hesap kimliği girilir ve o hesap bilgileri getirilir.

Hesap Silme
Endpoint: DELETE /account/{id}

Bu endpoint ile belirli bir hesabı, hesap kimliği belirtilerek silebilirsiniz. İstenilen hesap kimliği girilir ve o hesap silinir.

Para Çekme
Endpoint: PUT /account/withdraw/{id}/{amount}

Bu endpoint ile belirli bir hesaptan para çekebilirsiniz. Hesap kimliği ve çekilmek istenen miktar girilir, ilgili hesaptan belirtilen miktar çekilir.

Para Ekleme
Endpoint: PUT /account/add/{id}/{amount}

Bu endpoint ile belirli bir hesaba para ekleyebilirsiniz. Hesap kimliği ve eklemek istenen miktar girilir, ilgili hesaba belirtilen miktar eklenir.
