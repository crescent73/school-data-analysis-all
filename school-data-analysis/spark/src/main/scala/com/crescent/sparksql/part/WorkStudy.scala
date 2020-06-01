package com.crescent.sparksql.part

import org.apache.spark.sql.{SaveMode, SparkSession}

object WorkStudy {

  /**
   * hle)b562>Dxa
   * 勤工助学分析
   * 1）勤工助学各年级参与比例
   * 2）勤工助学与年级的关系
   * 3）勤工助学的工作部门
   * @param spark SparkSession
   */
  def analysisWorkStudy(spark:SparkSession):Unit={
    spark.read.format("csv").option("header","true").load("in/勤工助学上岗详情全.csv").createOrReplaceTempView("work_study")
    spark.read.format("csv").option("header","true").load("in/薪酬发放数据.csv").createOrReplaceTempView("work_study_salary")
    // 勤工助学的比例
    analysisWorkStudyProportion(spark)
    // 勤工助学的工作部门
    analysisWorkStudyJob(spark)
  }

  private def analysisWorkStudyProportion(spark:SparkSession):Unit = {
    spark.read.format("csv").option("header","true").load("in/全校学生基本信息表.csv").createOrReplaceTempView("all_student")

    // 勤工助学的各年级参与比例
     spark.sql(
      """
        |select a.grade,count,count_all,Round(count/count_all,4) percentage
        |from
        |(select grade,count(*) count_all
        |from all_student
        |group by grade) as a
        |inner join
        |(select grade,count(distinct all.number) count
        |from work_study inner join all on work_study.number = all.number
        |group by grade,school_year
        |having work_study.school_year in ('2019-2020学年')) as b
        |on a.grade = b.grade
        |""".stripMargin)
      .write.format("jdbc").mode(SaveMode.Overwrite)
      .option("url", "jdbc:mysql://localhost:3306/school_data")
      .option("dbtable", "work_study_participation_analysis")
      .option("user", "root").option("password", "root").save()



  }

  private def analysisWorkStudyJob(spark:SparkSession):Unit = {
    spark.sql(
      """
        |select job_name,count(*) count
        |from work_study
        |group by job_name
        |order by count desc
        |limit 10
        |""".stripMargin)
      .write.format("jdbc").mode(SaveMode.Overwrite)
      .option("url", "jdbc:mysql://localhost:3306/school_data")
      .option("dbtable", "work_study_job_analysis")
      .option("user", "root").option("password", "root").save()
  }

}
