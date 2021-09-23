package main.kotlin.ru.sber.oop

open class Room(val name: String, val size: Int) {

    protected open val dangerLevel = 5

    constructor(name: String) : this(name, 100)

    val goblin: Monster = Goblin(
        "Goblin",
        "Skate flyer",
        "Bomber",
        2345)

    fun description() = "Room: $name"

    open fun load() {
        goblin.getSalutation()
        println("Greeeting")
    }

    fun Monster.getSalutation(): String {
        return "$name is fun"
    }

}

class TownSquare(): Room("Town Square", 1000)
{
    final override fun load() {
        println("Loading smth...")
    }
    override val dangerLevel = super.dangerLevel - 3
}
