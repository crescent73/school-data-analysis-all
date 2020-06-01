import java.util

import com.hankcs.hanlp.HanLP
import com.hankcs.hanlp.model.crf.CRFSegmenter
import com.hankcs.hanlp.seg.common
import com.hankcs.hanlp.tokenizer.StandardTokenizer
import com.huaban.analysis.jieba.JiebaSegmenter
import org.ansj.recognition.impl.StopRecognition
import org.ansj.splitWord.analysis.ToAnalysis
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.ml.feature._
import org.apache.spark.ml.linalg.SparseVector
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * TF_IDF 简单的文本特征提取算法
 * TF（Term Frequency）词频，某个关键词在文本中出现的次数
 *  计算方法：TF=某个词在文章中出现的次数/文章所有单词出现的总次数
 * IDF（Inverse Doucument Frequency）逆文档频率
 *  计算方法：IDF=log（查找的文章总数/（包含该词的文章数+1））
 * TF-IDF = TF（词频）* IDF（逆文档频率）
 */
object LoadTerm {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("analysis")
    val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
    import spark.implicits._
    val sc: SparkContext = spark.sparkContext
    val dataRdd: RDD[String] = sc.textFile("in/大创题目.csv").map(x => {
      val str = x.replaceAll("[^\\u4e00-\\u9fa5]", "")
      //      println("x:"+x+"\nstr:"+str)
      val strings: util.List[String] = new JiebaSegmenter().sentenceProcess(str)
      strings.toString
    })
    dataRdd.toDF("words").show(false)
  }

}
