package com.crescent.loaddata

import java.net.URI

import com.crescent.util.MyKafkaUtil
import io.netty.handler.codec.spdy.DefaultSpdyDataFrame
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * 加载本地数据
 * 将加载后的数据输出到HDFS和Kafka
 */
object LoadData {

  /**
   *  将数据传到kafka
   * @param sc SparkContext，读取文件
   * @param topic kafka主题
   * @param path 文件路径
   */
  def loadDataToKafka(sc:SparkContext, topic:String, path:String): Unit ={
    val file: RDD[String] = sc.textFile(path)
    file.foreachPartition(iterator=>{
      val producer: KafkaProducer[String, String] = MyKafkaUtil.createKafkaProducer
      iterator.foreach(row=>{
        println(row)
        producer.send(new ProducerRecord(topic,row))
      })
    })
  }

  /**
   * 将文件传入hdfs
   */
  def loadDataToHdfs():Unit = {
    val fs = FileSystem.get(URI.create("hdfs://hadoop101:9000"), new Configuration, "crescent")
    // 如果目标文件夹，则删除文件夹
    if(fs.exists(new Path("/in")))
      fs.delete(new Path("/in"),true)
    fs.copyFromLocalFile(new Path("in"),new Path("/"))
    fs.close()
  }


  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("RealtimeApp").setMaster("local[*]")
    // 事实数据分析的环境对象，seconds指定采集时间
    val ssc = new StreamingContext(conf,Seconds(5))
    val sc = ssc.sparkContext

//    val path = "in/csv/体育赛事.csv"
//    // 将数据传到kafka中
//    loadDataToKafka(sc, "test", path)
//    // 读取kafka数据
//    val inputStream: InputDStream[ConsumerRecord[String, String]] = MyKafkaUtil.getKafkaStream(ssc,"test")
//    // 打印数据
//    inputStream.print()

    // 将文件上传到HDFS
    loadDataToHdfs()
    // 启动采集器
    ssc.start()
    // driver等待采集器的执行
    ssc.awaitTermination()


  }
}
