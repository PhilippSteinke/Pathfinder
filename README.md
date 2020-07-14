# Pathfinder

## Endpoint Dokumentation

### Login
* Login
   * URL: https://vps723941.ovh.net:9090/login
      * POST
   * JSON Body
      * username: string
      * password: string
   * Rückgabe
      * Status 200 OK
         * Header
            * Authorization (“Bearer eyJ0…..”)
      * Status 403 Forbidden
         * Wenn falsche Login Daten


### User
* Registrieren
   * URL: https://vps723941.ovh.net:9090/user/sign-up
      * POST
   * JSON Body
      * username: string
      * password: string
   * Rückgabe
      * Status 200 OK
      * Status 409 Conflict
         * wenn Username vorhanden


* Token validieren
   * URL: https://vps723941.ovh.net:9090/user/validate
      * GET
   * Header
      * Authorization
         * der zu validierende Token
   * Rückgabe
      * Status 200 OK
         * boolean: true wenn valider Token
      * Status 403 Forbidden
         * Wenn der Token ein falsches Format hat


* Alle User löschen
   * URL: https://vps723941.ovh.net:9090/user/delete/all?password=brilliant
      * DELETE
   * Rückgabe
      * Status 200 OK
      * Status 403 Forbidden
         * Wenn Passwort falsch


* Einen User löschen
   * URL: https://vps723941.ovh.net:9090/user/delete/{username}?password=brilliant
      * DELETE
         * username: string
   * Rückgabe
      * Status 200 OK
      * Status 403 Forbidden
         * Wenn Passwort falsch oder User nicht vorhanden


* POI abschließen (Achievement hinzufügen)
   * URL: https://vps723941.ovh.net:9090/user/pointsofinterest/{id}/complete/{value}
      * POST
         * id: PointOfInterestId string
         * value: int
   * Header
      * Authorization (Type Bearer Token)
         * Token (komplett mit Prefix “Bearer eyJ0….”) angeben
   * Rückgabe
      * Status 200 OK
         * Liste von PointOfInterest mit folgenden Feldern:
            * id: string
            * lat: BigDecimal
            * lng: BigDecimal
            * active: boolean
      * Status 403 Forbidden
         * Wenn Token ungültig
      * Status 404 Not Found
         * Wenn PointOfInterest mit der Id nicht vorhanden ist
      * Status 405 Method Not Allowed
         * Wenn PointOfInterest mit der Id bereits abgeschlossen wurde


* Achievement Liste abrufen
   * URL: https://vps723941.ovh.net:9090/user/achievements
      * GET
   * Header
      * Authorization (Type Bearer Token)
   * Rückgabe
      * Status 200 OK
         * Liste von Achievements
            * pointOfInterestId: string
            * value: int
      * Status 403 Forbidden
         * Wenn Token ungültig


* User Highscore abrufen
   * URL: https://vps723941.ovh.net:9090/user/highscore
      * GET
   * Header
      * Authorization (Type Bearer Token)
   * Rückgabe
      * Status 200 OK
         * Highscore
            * position: int
            * username: string
            * score: int
      * Status 403 Forbidden
         * Wenn Token ungültig


* User POI Liste abrufen
   * Remarks
      * bei Registrierung werden automatisch alle von uns in der Datenbank hinterlegten PointsOfInterest in den User kopiert.
   * URL: https://vps723941.ovh.net:9090/user/pointsofinterest
      * GET
   * Header
      * Authorization (Type Bearer Token)
   * Rückgabe
      * Status 200 OK
         * Liste von PointOfInterest mit folgenden Feldern:
            * id: string
            * lat: BigDecimal
            * lng: BigDecimal
            * active: boolean
      * Status 403 Forbidden
         * Wenn Token ungültig


* Nächsten aktiven POI finden
   * URL: https://vps723941.ovh.net:9090/user/pointsofinterest/closest?lat=xx.xxxxxx&lng=yy.yyyyyy
      * GET
   * Header
      * Authorization (Type Bearer Token)
   * Rückgabe
      * Status 200 OK
         * PointOfInterestDistance
            * pointOfInterestId: string
            * distance: double
      * Status 403 Forbidden
         * Wenn Token ungültig


### PointsOfInterest
* Vorlagen POI Liste abrufen
   * URL: https://vps723941.ovh.net:9090/pointsofinterest/all
      * GET
   * Rückgabe
      * Status 200 OK
         * Liste von PointOfInterest


* POI als Vorlage hinzufügen
   * URL: https://vps723941.ovh.net:9090/pointsofinterest/add?password=brilliant
      * POST
      * JSON Body
         * Liste von PointOfInterest mit folgenden Feldern:
            * id: string (optional)
            * lat: BigDecimal
            * lng: BigDecimal
            * active: boolean
   * Rückgabe
      * Status 200 OK
      * Status 403 Forbidden
         * Wenn Passwort falsch


* Alle POI entfernen
   * URL: https://vps723941.ovh.net:9090/pointsofinterest/delete/all?password=brilliant
      * DELETE
   * Rückgabe
      * Status 200 OK
      * Status 403 Forbidden
         * Wenn Passwort falsch


* Einen POI entfernen
   * URL: https://vps723941.ovh.net:9090/pointsofinterest/delete/{id}?password=brilliant
      * DELETE
         * id: string
   * Rückgabe
      * Status 200 OK
         * auch wenn POI nicht existiert
      * Status 403 Forbidden
         * Wenn Passwort falsch


Highscore
* Highscore Liste abrufen
   * URL: https://vps723941.ovh.net:9090/highscore/{amount}
      * GET
         * amount: int
   * Header
      * Authorization (Type Bearer Token)
   * Rückgabe
      * Status 200 OK
         * Liste mit amount Einträgen von Highscores sortiert nach Position mit folgenden Feldern:
            * position: int
            * username: string
            * score: int
      * Status 403 Forbidden
         * Wenn Token ungültig
