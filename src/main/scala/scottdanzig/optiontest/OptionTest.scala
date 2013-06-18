package scottdanzig.optiontest

object OptionTest {
  def main(args: Array[String]): Unit = {
    Console.println("Getting an Option that happens to hold something...")
    talkAboutValue(getSomeString)
    Console.println("\nGetting an Option that happens to hold nothing...")
    talkAboutValue(getNoString)
    
    Console.println("\nRetrieving last name of harry...")
    talkAboutValue(retrieveLastNameFromMap("harry"))
    Console.println("\nRetrieving last name of ben...")
    talkAboutValue(retrieveLastNameFromMap("ben"))
    Console.println("\n")
    
    Console.println("Last name of harry is: "+
        firstToLastNameMap.get("harry").getOrElse("not found"))
    Console.println("Last name of harry is: "+
        firstToLastNameMap.get("sam").getOrElse("not found"))
    Console.println("\n")
    listOfOptions.foreach(talkAboutValue(_)) //map instead of foreach if you need a list of return values 
    Console.println("\n")
    Console.println("Flattened list is "+listOfOptions.flatten)
    listOfOptions.flatten.foreach(Console.println(_))
    /*
     * flatten's base functionality is to change a list of lists into one
     * big combined list.  It can "flatten" a list of Options because
     * Options are automatically (implicitly) converted to lists:
     * 
     * implicit def option2Iterable[A](xo: Option[A]): Iterable[A] = xo.toList
     */
	    Console.println("\nLast names for list of first names:")
	    Console.println(List("beth","sam","harry").flatMap(firstToLastNameMap.get(_)).map("["+_+"]").mkString(","))
  }

  def talkAboutString(str: String) {
    Console.println("value is the String \""+str+"\", length of "+str.length)
  }
  
  def talkAboutValue(value: Option[String]) {
    value match {
      case Some(str) => talkAboutString(str)
      case None => Console.println("value is None") // A compile-time warning will appear if "None" is not handled.
    }
  }
  
  def getSomeString : Option[String] = {
    Some("test")
  }

  def getNoString : Option[String] = {
    None
  }

  
  def firstToLastNameMap : Map[String,String] = Map(
    "beth"->"peterson",
    "harry"->"smith",
    "john"->"doe"
  )
  
  def retrieveLastNameFromMap(firstName: String) = {
    firstToLastNameMap.get(firstName)
  }
  
  def listOfOptions : List[Option[String]] =
    List(Some("first"), None, Some("third"), None, Some("fifth"))
}