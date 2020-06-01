package com.crescent.sparksql.part

import org.apache.spark.sql.{SaveMode, SparkSession}

object VolunteerService {

  /**
   * 志愿服务分析
   * 1）志愿服务与年级的关系
   * 2）志愿服务比例
   * 3) 学院参与度
   * @param spark SparkSession
   */
  def analysisVolunteerService(spark:SparkSession):Unit = {
    spark.read.format("csv").option("header","true").load("in/志愿服务.csv").createOrReplaceTempView("volunteer")
    // 分析志愿服务比例
//    analysisVolunteerProportion(spark)
    // 分析志愿服务与年级的关系
//    analysisVolunteerAndGrade(spark)
    // 分析志愿服务的学院参与度与年级参与度
    analysisVolunteerAcademyParticipation(spark)
  }

  private def analysisVolunteerProportion(spark:SparkSession):Unit = {
    spark.sql(
      """
        |select volunteer_level,count(*) count
        |from volunteer
        |group by volunteer_level
        |""".stripMargin)
      .write.format("jdbc").mode(SaveMode.Overwrite)
      .option("url", "jdbc:mysql://localhost:3306/school_data")
      .option("dbtable", "volunteer_level_analysis")
      .option("user", "root").option("password", "root").save()
  }

  private def analysisVolunteerAndGrade(spark:SparkSession):Unit = {
    spark.sql(
      """
        |select substr(class,INSTR(class,'1'),2) as grade,count(*) count
        |from volunteer
        |group by grade
        |""".stripMargin)
      .write.format("jdbc").mode(SaveMode.Overwrite)
      .option("url", "jdbc:mysql://localhost:3306/school_data")
      .option("dbtable", "volunteer_grade_analysis")
      .option("user", "root").option("password", "root").save()
  }

  private def analysisVolunteerAcademyParticipation(spark:SparkSession):Unit = {
    spark.read.format("csv").option("header","true").load("in/全校学生基本信息表.csv").createOrReplaceTempView("all")
    spark.sql(
      """
        |select a.academy,count,count_all,Round(count/count_all,4) percentage
        |from
        |(select academy,count(distinct number) count from volunteer group by academy) as a
        |inner join
        |(select academy,count(*) count_all from all group by academy) as b
        |on a.academy = b.academy
        |order by percentage desc
        |""".stripMargin)
      .write.format("jdbc").mode(SaveMode.Overwrite)
      .option("url", "jdbc:mysql://localhost:3306/school_data")
      .option("dbtable", "volunteer_academy_participation_analysis")
      .option("user", "root").option("password", "root").save()

    spark.sql(
      """
        |select a.grade,count,count_all,Round(count/count_all,4) percentage
        |from
        |(select substr(class,INSTR(class,'1'),2) as grade,count(distinct number) count
        |from volunteer group by grade) as a
        |INNER JOIN
        |(select substr(grade,INSTR(grade,'1'),2) as grade,count(*) count_all
        |from all group by grade) as b
        |on a.grade = b.grade
        |""".stripMargin)
      .write.format("jdbc").mode(SaveMode.Overwrite)
      .option("url", "jdbc:mysql://localhost:3306/school_data")
      .option("dbtable", "volunteer_grade_participation_analysis")
      .option("user", "root").option("password", "root").save()
  }

}
