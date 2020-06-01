package com.crescent.sparksql.part

import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

object Main {
  /**
   * 概览数据分析
   * 1）学生的地区分布（省份）
   * 2）男女比例
   * 3）少数民族比例
   * 4）各年级维度占比
   */
  def analysisMain(spark: SparkSession):Unit = {
    // 读取全校学生表
    spark.read.format("csv").option("header","true").load("in/全校学生基本信息表.csv").createOrReplaceTempView("all_student")
    // 分析各年级男女比例
    analysisGender(spark)

    // 分析学生的地区分布
    analysisBrithplace(spark)

//     少数民族比例
    analysisNation(spark)

//    各年级维度占比
    analysisGradeLevel(spark)

  }

  /**
   * 各年级维度占比
   * @param spark SparkSession
   */
  private def analysisGradeLevel(spark: SparkSession):Unit = {
    spark.read.format("csv").option("header","true").load("in/奖学金评选与审核（资助中心）.csv").createOrReplaceTempView("scholarship")
    spark.sql(
      """
        |select
        |substr(class,INSTR(class,'1'),2) as grade,
        |Round(avg(research_innovation_score),2) as research_innovation_avg,
        |Round(avg(professional_skill_score),2) as professional_skill_avg,
        |Round(avg(stylistic_specialties_score),2) as stylistic_specialties_avg,
        |Round(avg(group_work_score),2) as group_work_avg,
        |Round(avg(social_practice_score),2) as social_practice_avg,
        |from scholarship
        |group by grade
        |order by grade
        |""".stripMargin)
      .write.format("jdbc")
      .mode(SaveMode.Overwrite)
      .option("url", "jdbc:mysql://localhost:3306/school_data")
      .option("dbtable", "level_analysis")
      .option("user", "root")
      .option("password", "root")
      .save()

  }

  /**
   * 分析少数民族比例
   * @param spark
   */
  private def analysisNation(spark: SparkSession):Unit = {
    spark.sql(
      """
        |select nation,count(*) as count,count(*)/ sum(count(*)) over() as percentage
        |from all_student
        |group by nation
        |having nation !='汉族'
        |order by percentage desc
        |""".stripMargin)
      .write.format("jdbc")
      .mode(SaveMode.Overwrite)
      .option("url", "jdbc:mysql://localhost:3306/school_data")
      .option("dbtable", "nation_analysis")
      .option("user", "root")
      .option("password", "root")
      .save()

  }

  /**
   * 分析学生的地区分布
   * @param spark SparkSession
   */
  private def analysisBrithplace(spark: SparkSession):Unit = {
    spark.sql("select birthplace as name,count(*) as value from all_student group by birthplace")
      .write.format("jdbc")
      .mode(SaveMode.Overwrite)
      .option("url", "jdbc:mysql://localhost:3306/school_data")
      .option("dbtable", "birthplace_analysis")
      .option("user", "root")
      .option("password", "root")
      .save()
  }

  /**
   * 分析各年级男女比例
   * @param spark SparkSession
   */
  private def analysisGender(spark: SparkSession):Unit = {
    spark.sql(
      """
        |select grade, gender, count(*) as count
        |from all_student
        |group by gender, grade
        |union
        |select 'all' as grade,gender, count(*) as count
        |from all_student
        |group by gender
        |""".stripMargin).createOrReplaceTempView("gender_grade")
    val genderAnalysisDF: DataFrame = spark.sql(
      """
        |select grade,gender,count,
        |Round(count/sum(count) over (partition by grade),2) as percentage
        |from gender_grade
        |""".stripMargin)
    genderAnalysisDF.show()
    // 将男女分析数据写出到mysql
    genderAnalysisDF.write.format("jdbc")
      .mode(SaveMode.Overwrite)
      .option("url", "jdbc:mysql://localhost:3306/school_data")
      .option("dbtable", "gender_analysis")
      .option("user", "root")
      .option("password", "root")
      .save()


  }

}
