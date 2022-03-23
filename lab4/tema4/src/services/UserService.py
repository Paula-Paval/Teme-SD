import sqlite3
from aifc import Error

from Crypto.Cipher import AES
import hashlib

class Securitate:

        def criptare(self, string):
            key = b'Sixteen byte key'
            cipher = AES.new(key, AES.MODE_EAX)
            nonce = cipher.nonce
            ciphertext, tag = cipher.encrypt_and_digest(bytes(string, 'utf-8'))
            return ciphertext
        def decriptare(self, criptat):
            key = b'Sixteen byte key'
            cipher1 = AES.new(key, AES.MODE_EAX)
            return  cipher1.decrypt(bytes(str(criptat), 'utf-8'))

class UserService:


    def getUser(self):
        conn = None
        try:
            conn = sqlite3.connect('test.db')
        except Error as e:
            print(e)

        sql = "select  nume, prenume, statut from users"
        users = conn.execute(sql).fetchall()
        conn.commit()
        conn.close()
        rezultat = []

        for user in users:
            nume=Securitate().decriptare(user[1])
            prenume = Securitate().decriptare(user[2])
            rezultat.append((nume, prenume))

        return rezultat

    def addUser(self, id, nume, prenume,statut, parola):

        conn = None
        try:
            conn = sqlite3.connect('test.db')
        except Error as e:
            print(e)


        ciphernume=Securitate().criptare(nume)
        cipherprenume=Securitate().criptare(prenume)

        m = hashlib.sha256()
        m.update(bytes(parola, 'utf-8'))

        sql = "INSERT INTO users(id, nume, prenume, parola, statut) VALUES(?,?,?,?,?);"

        user = (id, ciphernume, cipherprenume, m.digest(), statut)
        conn.execute(sql, user)
        conn.commit()
        conn.close()

