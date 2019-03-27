package com.example.api_project;

/**
 * Classe <b>NotFoundId</b>, classe d'exception qui hérite de la classe <b>Throwable</b>
 * Invoquée dans la classe <b>Detailed_ItemActivity</b> par la méthode <b>getIdByExtra</b>
 *
 * @see java.lang.Throwable
 * @see Detailed_ItemActivity
 * @see Detailed_ItemActivity#getIdByExtra(int)
 */
class NotFoundId extends Throwable {
    /**
     * <b>Constructeur</b> de l'exception
     *
     * @param message - une <b>String</b>
     * @see String
     */
    NotFoundId(String message) {
        super(message);
    }
}
