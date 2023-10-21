import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    val triangle = Triangle(-2, 1, 3, 2, 1, 5)

    triangle.setVerticeA(1, 1) //параметр, позволяющий задавать вершины вручную
    triangle.print() //метод print() выводящий описание треугольника на экран
    println("Периметр: " + triangle.getPerimeter()) //метод для вычисления периметра треугольника
    println("Площадь: " + triangle.getArea()) //метод для вычисления площади треугольника
}

class Vertice(private var x: Int, private var y: Int) {
    fun getX(): Int {
        return x
    }

    fun getY(): Int {
        return y
    }
}

class Triangle(aX: Int, aY: Int, bX: Int, bY: Int, cX: Int, cY: Int) {
    private var verticeA = Vertice(aX, aY)
    private var verticeB = Vertice(bX, bY)
    private var verticeC = Vertice(cX, cY)

    init {
        if (areOnOneLine(verticeA, verticeB, verticeC)) throw Exception("Три вершины лежат на одной прямой")
    }

    fun setVerticeA(x: Int, y: Int) {
        val verticeTemp = Vertice(x, y)
        if (areOnOneLine(verticeTemp, verticeB, verticeC)) throw Exception("Три вершины лежат на одной прямой")
        verticeA = verticeTemp
    }

    fun setVerticeB(x: Int, y: Int) {
        val verticeTemp = Vertice(x, y)
        if (areOnOneLine(verticeA, verticeTemp, verticeC)) throw Exception("Три вершины лежат на одной прямой")
        verticeB = verticeTemp
    }

    fun setVerticeC(x: Int, y: Int) {
        val verticeTemp = Vertice(x, y)
        if (areOnOneLine(verticeA, verticeB, verticeTemp)) throw Exception("Три вершины лежат на одной прямой")
        verticeC = verticeTemp
    }

    fun print() {
        println("Координаты A: (${verticeA.getX()}, ${verticeA.getY()})")
        println("Координаты B: (${verticeB.getX()}, ${verticeB.getY()})")
        println("Координаты C: (${verticeC.getX()}, ${verticeC.getY()})")
    }

    private fun getSideLength(verticeFirst: Vertice, verticeSecond: Vertice): Double {
        return sqrt(
            (verticeFirst.getX() - verticeSecond.getX()).toDouble()
                .pow(2) + (verticeFirst.getY() - verticeSecond.getY()).toDouble().pow(2)
        )
    }

    fun getPerimeter(): Double {
        val abLength = getSideLength(verticeA, verticeB)
        val bcLength = getSideLength(verticeB, verticeC)
        val caLength = getSideLength(verticeC, verticeA)
        return abLength + bcLength + caLength;
    }

    fun getArea(): Double {
        val abLength = getSideLength(verticeA, verticeB)
        val bcLength = getSideLength(verticeB, verticeC)
        val caLength = getSideLength(verticeC, verticeA)
        val halfP = (abLength + bcLength + caLength) / 2
        return sqrt(halfP * (halfP - abLength) * (halfP - bcLength) * (halfP - caLength))
    }
}

fun areOnOneLine(verticeA: Vertice, verticeB: Vertice, verticeC: Vertice): Boolean {
    return (verticeA.getX() - verticeC.getX()) * (verticeB.getY() - verticeC.getY()) == (verticeB.getX() - verticeC.getX()) * (verticeA.getY() - verticeC.getY())
}
