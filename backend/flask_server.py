from flask import Flask
from flask import request, jsonify

app = Flask(__name__)

people_list = []
address_list = []

@app.route("/")
def hello_world():
    return "<p>Hello, World!</p>"

@app.route("/ajoutPersonne", methods = ['POST'])
def ajout_personne():
    data = request.form.to_dict()
    print(str(data))
    if "name" in data and "surname" in data:
        people_list.append(data)
        return jsonify({"message": "Person added successfully", "person": data}), 201
    return jsonify({"error": "Invalid person data"}), 400

@app.route("/ajoutAdresse", methods = ['POST'])
def ajout_adresse():
    data = request.form.to_dict()
    print(str(data))
    if "street" in data and "city" in data:
        address_list.append(data)
        return jsonify({"message": "Address added successfully", "address": data}), 201
    return jsonify({"error": "Invalid address data"}), 400

@app.route("/list", methods = ["GET"])
def list():
    return jsonify({"people": people_list, "addresses": address_list})
