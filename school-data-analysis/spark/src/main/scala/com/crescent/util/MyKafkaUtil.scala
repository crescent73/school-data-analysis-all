package com.crescent.util

import java.util.Properties

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}

object MyKafkaUtil {
  // kafka的节点
  val brokers="hadoop101:9092"

  // kafka参数
  val kafkaParams = Map[String, String](
    "bootstrap.servers" -> brokers,
    "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
    "key.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
    "group.id" -> "bigdata")

  /**
   * 设置kafka消费者
   * @param ssc spark StreamingContext
   * @param topics 主题
   * @return DStream
   */
  def getKafkaStream(ssc:StreamingContext, topics:String) = {
    // Create direct kafka stream with brokers and topics
    val topicsSet = topics.split(",").toSet
    val kafkaDStream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](topicsSet, kafkaParams))
    kafkaDStream
  }

  /**
   * 创建kafka生产者
   * @return KafkaProducer
   */
  def createKafkaProducer:KafkaProducer[String,String] = {
    val props:Properties = new Properties()
    // kafka服务端的主机号和端口号
    props.put("bootstrap.servers","hadoop101:9092")
    // key 序列化
    props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer")
    // value 序列化
    props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer")
    new KafkaProducer[String,String](props)
  }

}
