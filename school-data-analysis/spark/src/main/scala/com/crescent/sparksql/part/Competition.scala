package com.crescent.sparksql.part

import org.apache.spark.sql.{SaveMode, SparkSession}

object Competition {
  /**
   * 竞赛数据分析
   * 竞赛类型有：学术竞赛 academic，技能竞赛 skill ，文艺赛事 literary，体育赛事 sport，文化知识竞赛等其它赛事 other
   * 1）各比赛占比
   * 2）比赛与年级和学院的关系
   * 3）比赛团队与个人占比
   * 4）各时间段比赛时间图
   */
  def analysisCompetition(spark: SparkSession):Unit = {
    // 读取数据,创建临时表
    spark.read.format("csv").option("header","true").load("in/学术竞赛.csv").createOrReplaceTempView("academic_competition")
    spark.read.format("csv").option("header","true").load("in/技能竞赛.csv").createOrReplaceTempView("skill_competition")
    spark.read.format("csv").option("header","true").load("in/文艺赛事.csv").createOrReplaceTempView("literary_competition")
    spark.read.format("csv").option("header","true").load("in/体育赛事.csv").createOrReplaceTempView("sport_competition")
    spark.read.format("csv").option("header","true").load("in/文化知识竞赛等其它赛事.csv").createOrReplaceTempView("other_competition")
    // 数据预处理，整合数据，筛选有用字段
    spark.sql(
      """
        |select '学术竞赛' as type,substr(class,INSTR(class,'1'),2) as grade,academy,gender,competition_level,award_name,award_type,award_date
        |from academic_competition
        |union
        |select '技能竞赛' as type,substr(class,INSTR(class,'1'),2) as grade,academy,gender,competition_level,award_name,award_type,award_date
        |from skill_competition
        |union
        |select '文艺赛事' as type,substr(class,INSTR(class,'1'),2) as grade,academy,gender,competition_level,award_name,award_type,award_date
        |from literary_competition
        |union
        |select '体育赛事' as type,substr(class,INSTR(class,'1'),2) as grade,academy,gender,competition_level,award_name,award_type,award_date
        |from sport_competition
        |union
        |select '文化知识竞赛等其它赛事' as type,substr(class,INSTR(class,'1'),2) as grade,academy,gender,competition_level,award_name,award_type,award_date
        |from other_competition
        |""".stripMargin).createOrReplaceTempView("competition_data")

    // 各等级竞赛比例
//    analysisCompetitionAndLevel(spark)

    // 各比赛占比与学院的关系
    analysisCompetitionAndAcademy(spark)

    // 各类热门竞赛排名
    analysisCompetitionRanking(spark)

    // 各时间段比赛时间图

  }

  /**
   *各类热门竞赛排名
   */
  private def analysisCompetitionRanking(spark: SparkSession):Unit = {
    spark.sql(
      """
        |select type,award_name,competition_level,count(*) count
        |from competition_data
        |GROUP BY competition_level,type,award_name
        |order by type,count(*) desc
        |""".stripMargin)
      .write.format("jdbc")
      .mode(SaveMode.Overwrite)
      .option("url", "jdbc:mysql://localhost:3306/school_data")
      .option("dbtable", "competition_ranking")
      .option("user", "root")
      .option("password", "root")
      .save()

  }

  /**
   * 各等级竞赛的比例
   * @param spark SparkSession
   */
  private def analysisCompetitionAndLevel(spark:SparkSession):Unit = {
    spark.sql(
      """
        |select
        |type,
        |SUBSTR(competition_level,1,INSTR(competition_level,'级')) as c_level,
        |count(*) count
        |from competition_data
        |GROUP BY c_level,type
        |ORDER BY type,count desc
        |""".stripMargin)
      .write.format("jdbc")
      .mode(SaveMode.Overwrite)
      .option("url", "jdbc:mysql://localhost:3306/school_data")
      .option("dbtable", "competition_level_analysis")
      .option("user", "root")
      .option("password", "root")
      .save()
  }

  /**
   * 各比赛占比与学院的关系
   * @param spark SparkSession
   */
  private def analysisCompetitionAndAcademy(spark: SparkSession):Unit = {
    spark.sql(
      """
        |select type,academy,count(*) count,Round(count(*)/sum(count(*)) over(partition by academy),4) as percentage
        |from competition_data
        |group by type,academy
        |union
        |select type, 'all' as academy,count(*) count,Round(count(*)/sum(count(*)) over(),4) as percentage
        |from competition_data
        |group by type
        |order by academy
        |""".stripMargin)
      .write.format("jdbc")
      .mode(SaveMode.Overwrite)
      .option("url", "jdbc:mysql://localhost:3306/school_data")
      .option("dbtable", "competition_analysis")
      .option("user", "root")
      .option("password", "root")
      .save()
  }

}
