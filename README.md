# canal 
l'application comporte 2 endpoints: 1 pour reserver une salle et un au autre pour lister les reservation

AP1 de type get exemple:localhost:8080/api/reserveration
Api2 de type poste:localhost:8080/api/reserver
  exemple body: {
    "reservedBy": "Arame",
    "startHour": 8,
    "meetingType": "RC",
    "size": 10,
    "day":"VENDREDI"

}

Contraintes: Impossible de revertion une heure avant ou après une reservation existante
NB: Les équipements additionnels sont disponible dès la fin de la reservation
