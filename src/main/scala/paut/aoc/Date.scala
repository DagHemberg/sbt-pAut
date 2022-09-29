package paut.aoc
import java.util.GregorianCalendar
import java.util.TimeZone
import java.time.LocalDate

/** A trait that can be extended by Actions in order to provide easy access to the current date and year */
trait Date {
  val date: LocalDate
  val day = date.getDayOfMonth
  val formattedDay = f"${day}%02d"
  val year = date.getYear
}

/** Companion object for Date trait. Contains useful functions for getting the current dates and available years and dates within a year. 
 */
object Date {  
  def from(day: Int, year: Option[Int] = None) = 
    LocalDate.of(year.getOrElse(availableYears.max), 12, day)
  
  def today = {
    new GregorianCalendar(TimeZone.getTimeZone("EST"))
      .toZonedDateTime()
      .toLocalDate()
  }

  def availableYears = {
    if (today isBefore LocalDate.of(today.getYear, 12, 1))
      2015 until today.getYear
    else 
      2015 to today.getYear
  }

  def availableDays = 
    1 to (if (today.getMonthValue != 12) 25 else today.getDayOfMonth)
}