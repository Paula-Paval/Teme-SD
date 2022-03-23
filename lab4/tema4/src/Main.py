
import sqlite3
from aifc import Error

import flask
from flask import request, Response
from werkzeug.exceptions import abort

from services.CheltuieliService import CheltuieliService
from  services.UserService import UserService

app = flask.Flask(__name__)
app.config["DEBUG"] = True


@app.route('/', methods=['GET'])
def home():
     return "<h1>Hello</p>"


@app.route('/cheltuieli', methods=['POST'])
def create_cheltuieli():
    if not request.json or not 'intretinere' in request.json:
        abort(400)

    id= request.json['id']
    intretinere= request.json['intretinere']
    mancare=request.json.get('mancare')
    user_id=request.json.get('user_id')

    CheltuieliService().createCheltuieli(id,intretinere, mancare, user_id)

    response = Response("Cheltuieli inserate", status=200)

    return response

@app.route('/cheltuieli/<int:id>', methods=['PUT'])
def update_cheltuieli(id):

    intretinere = request.json['intretinere']
    mancare = request.json.get('mancare')
    distractie = request.json.get('distractie')
    scoala = request.json.get('scoala')
    personale = request.json.get('personale')
    user_id = request.json.get('user_id')

    if CheltuieliService().updateCheltuieli(id, intretinere, mancare, distractie, scoala, personale, user_id) is False:
        return Response(status=404)
    else:
        return Response(status=200)

@app.route('/cheltuieli', methods=['GET'])
def getCheltuieliPerUser():

    rezultat=CheltuieliService().getCheltuieli()
    return Response(str(rezultat),status=200)

@app.route('/cheltuielitotal', methods=['GET'])
def getTotal():

    rezultat=CheltuieliService().getTotal()
    return Response(str(rezultat),status=200)


@app.route('/cheltuieli/<int:id>', methods=['DELETE'])
def deleteCheltuieli(id):

    if CheltuieliService().getTotal() is False:
        return Response(status=404)
    else:
        return Response(status=200)

@app.route('/users', methods=['POST'])
def creareCont():
    conn = None
    try:
        conn = sqlite3.connect('test.db')
    except Error as e:
        print(e)

    id= request.json['id']
    nume= request.json['nume']
    prenume=request.json.get('prenume')
    statut=request.json.get('statut')
    parola=request.json.get('parola')

    UserService().addUser(id, nume, prenume, statut, parola)
    response = Response("User introdus", status=200)
    return response

@app.route('/users', methods=['GET'])
def getUsers():
    return Response(str(UserService().getUser()), status=200)

app.run()