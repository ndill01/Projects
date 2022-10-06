//StreamLogParser.scala

/** Program shows top URL's visited over a 5 minute window,
 * from a stream of Apache access logs on port 9999.
 */

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.storage.StorageLevel

import java.util.regex.Pattern
import java.util.regex.Matcher

import Utilities._

object LogParser {

  def main(args: Array[String]) {

    // Create the context with a 3 second batch size
    val ssc = new StreamingContext("local[*]", "LogParser", Seconds(3))

    setupLogging()

    // Construct a regular expression (regex)
    // to extract fields from raw Apache log lines
    val pattern = apacheLogPattern()

    // Create a socket stream to read log data
    // published via netcat on port 9999 locally
    val lines =  ssc.socketTextStream("127.0.0.1", 9999, StorageLevel.MEMORY_AND_DISK_SER)

    // Extract the request field from each log line
    // if the record is invalid, discard

    // This is sample request field
    // HTTP command, URL and protocol
    // POST /wp-login.php HTTP/1.1
    val requests = lines.map(x =>
    {
      val matcher: Matcher = pattern.matcher(x)
      if (matcher.matches()) matcher.group(5)
    })

    // Extract the URL from the request
    val urls = requests.map(x =>
    {
      val arr = x.toString.split(" ")
      if(arr.size == 3) arr(1)
      else "[error]"
    })

    // Calculate URL request count over a 5-minute window sliding every three second
    val urlCounts = urls.map(x => (x,1))
      .reduceByKeyAndWindow(_+_, _-_, Seconds(300), Seconds(3))

    // Sort by url counts and print the results
    val sortedResults = urlCounts.transform(rdd =>
      rdd.sortBy(x => x._2, false))
    sortedResults.print()


    // Set a checkpoint directory, and kick it off
    ssc.checkpoint("/home/dill/checkpoint/")
    ssc.start()
    ssc.awaitTermination()

  }

}
