import sqlite3
from aifc import Error


class CheltuieliService:

    def createCheltuieli(self, id, intretinere, mancare,  user_id):
        conn = None
        try:
            conn = sqlite3.connect('test.db')
        except Error as e:
            print(e)

        sql = "INSERT INTO cheltuieli (id, intretinere , mancare, user_id) VALUES(" + str(id) + "," + str(
            intretinere) + "," + str(mancare) + "," + str(user_id) + ");"

        conn.execute(sql)
        conn.commit()
        conn.close()

    def updateCheltuieli(self, id, intretinere, mancare, distractie, scoala, personale, user_id):
        conn = None
        try:
            conn = sqlite3.connect('test.db')
        except Error as e:
            print(e)

        cautat = "SELECT * FROM cheltuieli WHERE id=" + str(id) + ";"

        rezultat = conn.execute(cautat)
        conn.commit()
        rv = rezultat.fetchall()

        if (rv[0] is None):
            return False

        sql = "UPDATE cheltuieli set intretinere=" + str(intretinere) + "," + "mancare=" + str(
            mancare) + "," + "distractie=" + str(distractie) + "," + "scoala=" + str(scoala) + "," + "personale=" + str(
            personale) + "," + "user_id=" + str(user_id) + " where id=" + str(id) + ";"

        conn.execute(sql)
        conn.commit()
        conn.close()

    def getCheltuieli(self):
        conn = None
        try:
            conn = sqlite3.connect('test.db')
        except Error as e:
            print(e)

        sql = "select prenume as nume, sum(intretinere+mancare+distractie+scoala+personale) as total  from users inner join cheltuieli on users.id=cheltuieli.user_id;"
        rezultat = conn.execute(sql).fetchall()
        conn.commit()
        conn.close()
        return rezultat


    def getTotal(self):
        conn = None
        try:
            conn = sqlite3.connect('test.db')
        except Error as e:
            print(e)

        sql = "select sum(intretinere+mancare+distractie+scoala+personale) from cheltuieli;"
        rezultat = conn.execute(sql).fetchall()
        conn.commit()
        conn.close()
        return rezultat

    def deleteCheltuieli(self, id):
        conn = None
        try:
            conn = sqlite3.connect('test.db')
        except Error as e:
            print(e)

        cautat = "SELECT * FROM cheltuieli WHERE id=" + str(id) + ";"

        rezultat = conn.execute(cautat)
        conn.commit()
        rv = rezultat.fetchall()

        if (rv[0] is None):
            return False

        sql = "DELETE  from cheltuieli where id=" + str(id) + ";"
        conn.execute(sql)
        conn.commit()
        conn.close()
        return True
