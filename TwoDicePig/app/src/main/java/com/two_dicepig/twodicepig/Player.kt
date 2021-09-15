package com.two_dicepig.twodicepig

class Player {
    private var total : Int = 0
    private var name : String = ""

    fun Player(name : String){
        setPlayerName(name)
    }

    fun setScore (score: Int) {
        total += score
    }

    fun snakeEyes () {
        total = 0
    }

    fun setPlayerName (name : String) {
        this.name = name
    }

    fun getScore () : Int {
        return total
    }

    fun getPlayerName () : String {
        return name
    }

    fun changeScore (score: Int) {
        total = score
    }
}