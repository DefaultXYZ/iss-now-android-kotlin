package com.defaultxyz.issnow.data.model.response

data class AstrosResponse(val message: String,
                          val people: List<AstroResponse>,
                          val number: Int)

data class AstroResponse(val craft: String,
                         val name: String)