package com.crescent.sparksql.part

import org.apache.spark.sql.{SaveMode, SparkSession}

object Patent {

  /**
   *  分析专利数据
   *  1）专利类型
   *  2）专利名称（热点话题）
   *  3）专利与年级学院的关系
   * @param spark
   */
  def analysisPatent(spark:SparkSession):Unit = {
    spark.read.format("csv").option("header","true").load("in/发明专利.csv").createOrReplaceTempView("patent")
    // 专利类型
    analysisPatentType(spark)
    // 专利名称

    // 专利与年级学院的关系
    analysisPatentAndAcademy(spark)
  }

  private def analysisPatentType(spark:SparkSession) : Unit = {
    spark.sql(
      """
        |select patent_type,count(*) count
        |from patent
        |group by patent_type
        |""".stripMargin)
      .write.format("jdbc").mode(SaveMode.Overwrite)
      .option("url", "jdbc:mysql://localhost:3306/school_data")
      .option("dbtable", "patent_type_analysis")
      .option("user", "root").option("password", "root").save()

  }

  private def analysisPatentAndAcademy(spark:SparkSession):Unit = {
    spark.sql(
      """
        |select academy,count(*) count
        |from patent
        |group by academy
        |union
        |select 'all' academy,count(*) count
        |from patent
        |order by count desc
        |""".stripMargin)
      .write.format("jdbc").mode(SaveMode.Overwrite)
      .option("url", "jdbc:mysql://localhost:3306/school_data")
      .option("dbtable", "patent_academy_analysis")
      .option("user", "root").option("password", "root").save()
  }


}
