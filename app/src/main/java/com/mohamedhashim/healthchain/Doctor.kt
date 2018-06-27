package com.mohamedhashim.healthchain

/**
 * Created by Mohamed Hashim on 6/27/2018.
 */
class Doctor {
    var title: String? = null
    var genre: String? = null
    var year: String? = null

    constructor() {}

    constructor(title: String, genre: String, year: String) {
        this.title = title
        this.genre = genre
        this.year = year
    }
}