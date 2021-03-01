package signature
import java.io.File

fun getFontArray(inputWords: String, file: File): Array<String>{
    val listOfLines = file.readLines()
    val fontSize = listOfLines[0].substringBefore(' ').toInt()
    val spaceWidth = listOfLines[1].substringAfter(' ').toInt()
    //val noOfChars = listOfLines[0].substringAfter(' ').toInt()
    val fontArray = Array(fontSize) { arrIndex ->
        var resultString = ""
        for (char in inputWords){
            if (char == ' ') {
                resultString += " ".repeat(spaceWidth)
            } else {
                val index = if (char.isLowerCase()) {
                    char.toInt() - 97 //fontSize + 1 for each char def line
                } else {
                    char.toInt() - 65 + 26
                }
                val fileIndex = index * (fontSize + 1) + 1
                resultString += listOfLines[fileIndex + 1 + arrIndex]
            }
        }
        resultString
    }
    return fontArray
}

fun main() {
    val romanPath = "C:/Users/Sai Kumar/Documents/ASCII Text Signature/ASCII Text Signature/task/src/signature/roman.txt"
    val mediumPath = "C:/Users/Sai Kumar/Documents/ASCII Text Signature/ASCII Text Signature/task/src/signature/medium.txt"
    val romanFile = File(romanPath)
    val mediumFile = File(mediumPath)
    print("Enter name and surname: ")
    val name = readLine()!!
    print("Enter person's status: ")
    val status = readLine()!!

    val nameArray = getFontArray( name, romanFile)

    val statusArray = getFontArray( status, mediumFile)

    val nameLength = nameArray[0].length
    val statusLength = statusArray[0].length

    //calculate border length
    val borderLength = if (statusLength > nameLength) {
        statusLength + 8
    } else {
        nameLength + 8
    }

    //print first border
    printBorder(borderLength)
    println()

    //print name
    printContent(nameArray, statusLength)

    //print status
    printContent(statusArray, nameLength)

    //print final border
    printBorder(borderLength)
}

fun printContent(fontArray: Array<String>, otherLength: Int) {
    val diff = otherLength - fontArray[0].length
    for (row in fontArray){
        print("88  ")
        printSpaces(diff, false)
        print(row)
        printSpaces(diff, true)
        println("  88")
    }
}

//prints leading or trailing spaces
fun printSpaces(diff: Int, trail: Boolean) {
    if (diff > 0){
        if (!trail) {
            repeat(diff / 2) {
                print(" ")
            }
        } else {
            repeat(diff - (diff / 2)) {
                print(" ")
            }
        }
    }
}

fun printBorder(borderLength: Int) {
    repeat(borderLength) {
        print("8")
    }
}
