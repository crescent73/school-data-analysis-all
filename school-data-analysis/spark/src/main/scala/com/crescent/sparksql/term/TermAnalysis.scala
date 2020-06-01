package com.crescent.sparksql.term

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.ml.feature.{CountVectorizer, CountVectorizerModel, HashingTF, IDF, IDFModel, Tokenizer}
import org.apache.spark.ml.linalg.SparseVector
import org.apache.spark.rdd.RDD

import scala.collection.mutable.ArrayBuffer

/**
 * TF_IDF 简单的文本特征提取算法
 * TF（Term Frequency）词频，某个关键词在文本中出现的次数
 *  计算方法：TF=某个词在文章中出现的次数/文章所有单词出现的总次数
 * IDF（Inverse Doucument Frequency）逆文档频率
 *  计算方法：IDF=log（查找的文章总数/（包含该词的文章数+1））
 * TF-IDF = TF（词频）* IDF（逆文档频率）
 */
object TermAnalysis {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("analysis")
    val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
    // 将文章内容转化为dataFrame
    val sentenceData = spark.createDataFrame(Seq(
      (0.0, "Hi I heard about Spark i like spark"),
      (2.0, "I wish Java could use case classes yes"),
      (1.0, "Logistic regression models are neat i think so")
    )).toDF("label", "sentence")
    val labelRdd: RDD[String] = sentenceData.select("label").rdd.map(row => {
      val str: Double = row.getAs[Double]("label")
      (str.toString)
    })
    // 分词
    val tokenizer:Tokenizer = new Tokenizer().setInputCol("sentence").setOutputCol("words")
    val wordsData:DataFrame = tokenizer.transform(sentenceData)

    // 将每个词向量化并统计长度，这里用CountVectorizer，IDF，但是IDF不可逆，获取不到原词
    val cvModel: CountVectorizerModel = new CountVectorizer()
      .setInputCol("words").setOutputCol("feature").fit(wordsData)
    val cvDf:DataFrame = cvModel.transform(wordsData)
//    val hashingTF = new HashingTF().setInputCol("words").setOutputCol("rawFeatures").setNumFeatures(20)

    // IDF 逆向文件频率
    val idf:IDF = new IDF().setInputCol("feature").setOutputCol("features")
    val idfModel: IDFModel = idf.fit(cvDf)
    val idfDf = idfModel.transform(cvDf)
    idfDf.show(false)

    // 将结果排序，取出结果
    val keyWords: RDD[Array[(String, Double)]] = idfDf.select("features").rdd.map(x => {
      val vector: SparseVector = x.getAs[SparseVector](0) // idf结果以稀疏矩阵保存
      val tuples: Array[(Int, Double)] = vector.indices.zip(vector.values) // 将向量转化为（index,value）数组
      tuples.sortWith((x, y) => x._2 > y._2).take(10) // 从大到小排序并取前10
        .map(x => { // 通过cvModel.vocabulary(index) 可以拿到之前的单词
          (cvModel.vocabulary(x._1), x._2) // 转化为（word,value）
        })
    })
    val labelKeyRdd: RDD[(String, Array[(String, Double)])] = labelRdd.zip(keyWords)
    labelKeyRdd.collect().foreach(x=>{
      println(x._1)
      x._2.foreach(x=>println(x))
    })

  }

}
