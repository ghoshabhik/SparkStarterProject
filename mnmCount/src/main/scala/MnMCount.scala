package com.github

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object MnMCount {

  def main(args: Array[String]): Unit ={

    val spark = SparkSession.builder.appName("MnMCountApplication").getOrCreate()

    if(args.length < 1){
      print("Usage: Need input file")
      System.exit(1)
    }

    val mnmFile = args(0)
    val df = spark.read.format("csv")
      .option("inferSchema", "true").option("header","true")
      .load(mnmFile)

    val countMnM = df.groupBy("State","Color").sum("Count")
      .orderBy(desc("sum(Count)"))

    countMnM.show(60, false)
    print(s"Total Rows: ${countMnM.count()}" )

    val caCountMnmDf = df.select("State", "Color", "Count").where(col("State") === "CA")
      .groupBy("State","Color").sum("Count").orderBy(desc("sum(Count)"))

    caCountMnmDf.show(10, false)

    spark.stop()
  }

}
