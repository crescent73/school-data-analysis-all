package com.crescent.sparksql.part

import org.apache.spark.sql.{SaveMode, SparkSession}

object SocialPractice {

  /**
   * 社会实践分析
   * 1）社会实践类型&个人比例
   * 2）设计实践具体项目
   * @param spark
   */
  def analysisSocialPractice(spark:SparkSession):Unit = {
    spark.read.format("csv").option("header","true").load("in/社会实践.csv").createOrReplaceTempView("social_practice")
    // 社会实践类型&个人比例
//    analysisSocialPracticeType(spark)
    // 设计实践具体项目
    analysisProjectDetail(spark)
  }

  private def analysisSocialPracticeType(spark:SparkSession):Unit = {

    spark.sql(
      """
        |select social_practice_level,award_type,count(*) count,Round(count(*)/sum(count(*)) over(partition by social_practice_level),2) percentage
        |from social_practice
        |group by social_practice_level,award_type
        |""".stripMargin)
      .write.format("jdbc").mode(SaveMode.Overwrite)
      .option("url", "jdbc:mysql://localhost:3306/school_data")
      .option("dbtable", "social_practice_type_analysis")
      .option("user", "root").option("password", "root").save()

  }

  private def analysisProjectDetail(spark:SparkSession):Unit= {
    spark.sql(
      """
        |select social_practice_level,award_name,count(*) count
        |from social_practice
        |group by social_practice_level,award_name
        |having social_practice_level in ('校级','省级','国家级')
        |""".stripMargin)
      .write.format("jdbc").mode(SaveMode.Overwrite)
      .option("url", "jdbc:mysql://localhost:3306/school_data")
      .option("dbtable", "social_practice_project_analysis")
      .option("user", "root").option("password", "root").save()
  }

}
