package com.defaultxyz.issnow.data.model.response

import com.defaultxyz.issnow.data.model.IssAstro

data class AstrosResponse(val message: String,
                          val people: List<AstroResponse>,
                          val number: Int) {
    fun toAstroList(): List<IssAstro> {
        return people.map { IssAstro(it.name) }
    }

    companion object {
        val TAG = AstrosResponse::class.java.simpleName
    }
}

data class AstroResponse(val craft: String,
                         val name: String)