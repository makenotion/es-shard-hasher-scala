import org.elasticsearch.Version
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.cluster.routing.OperationRouting
import org.elasticsearch.cluster.routing.Murmur3HashFunction
import scala.io.Source

import java.io._

object Hello {
  def settings(version: Version): Settings.Builder = {
    val builder =
      Settings.builder().put(IndexMetaData.SETTING_VERSION_CREATED, version);
    return builder;
  }

  def main(args: Array[String]) = {
    val metaData = IndexMetaData
      .builder("tesfdfdsfsdst")
      .settings(settings(Version.V_7_1_1))
      .numberOfShards(512)
      .numberOfReplicas(1)
      .setRoutingNumShards(1024)
      .build();

    val pw = new PrintWriter(new File("hello.txt"))

    val filename = "workspaceids.csv"
    for (line <- Source.fromFile(filename).getLines) {
      val shardId =
        OperationRouting.generateShardId(metaData, "1sfsdf234", line);
      if (shardId == 467) {
        println(line)
        pw.write(line)
        pw.write("\n")
      }

    }
    pw.close
  }
}
