const val SHOW_SEATS = 1
const val BUY_TICKET = 2
const val STATISTICS = 3
const val EXIT = 0

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seatsPerRow = readln().toInt()
    val totalSeats = rows * seatsPerRow

    val cinema = MutableList(rows) { MutableList(seatsPerRow) { 'S' } }
    var ticketPrice: Int = 0
    
    var currentIncome = ticketPrice
    var totalIncome: Int = 0
        
    fun showSeats() {
        println("Cinema:")
        print("  ")
        for (i in 1..seatsPerRow) {
            print("$i ")
        }
        println()
        for (i in 0 until rows) {
            print("${i + 1} ")
            for (j in 0 until seatsPerRow) {
                print("${cinema[i][j]} ")
            }
            println()
        }
    }
      
    var choice: Int
    var purchasedTickets = 0
    
    do {
        println("\n1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        choice = readln().toInt()

        if (choice == SHOW_SEATS) {
            showSeats() 
        }

        if (choice == BUY_TICKET) {
            var row = 0
            var seat = 0

            fun selectSeat() {
                println("Enter a row number:")
                row = readln().toInt()
                println("Enter a seat number in that row:")
                seat = readln().toInt()
            }

            selectSeat()

            while (row > 9 || seat > 9 || row <= 0 || seat <= 0) {
                println("Wrong input!")
                selectSeat()
             }
                
            if (cinema[row - 1][seat - 1] == 'B') {
                println("That ticket has already been purchased!")
                selectSeat()
            } 
                
            if (totalSeats <= 60) {
                ticketPrice = 10
            } else if (row <= rows / 2) {
                ticketPrice = 10
            } else {
                ticketPrice = 8
            }

            cinema[row - 1][seat - 1] = 'B'
            println("Ticket price: $$ticketPrice")
            purchasedTickets++
            currentIncome += ticketPrice 
        }

        if (choice == STATISTICS) {
            if (totalSeats <= 60) {
                totalIncome = totalSeats * 10
            } else {
                var firstHalf = (rows / 2) * seatsPerRow
                var secondHalf = totalSeats - firstHalf
                totalIncome = firstHalf * 10 + secondHalf * 8
            }

            var percentage: Double = purchasedTickets.toDouble() / totalSeats * 100
            val formatPercentage = "%.2f".format(percentage)
            
            println("Number of purchased tickets: $purchasedTickets")            
            println("Percentage: $formatPercentage%")
            println("Current income: $$currentIncome")
            println("Total income: $$totalIncome")
        }
        
        if (choice == EXIT) {
            break
        }
    } while (true)
}
