package com.example.schoolsguide

class ModelComment {

    //variables, should be with same speeling and type as we added in firebase

    var id = ""
    var schoolId = ""
    var timestamp = ""
    var comment = ""
    var uid = ""

    //empety construcror, required by firebase
    constructor()

    //param construcor

    constructor(id: String, schoolId: String, timestamp: String, comment: String, uid: String) {
        this.id = id
        this.schoolId = schoolId
        this.timestamp = timestamp
        this.comment = comment
        this.uid = uid
    }


}