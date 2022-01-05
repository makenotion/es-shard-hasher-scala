import org.elasticsearch.Version
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.cluster.routing.OperationRouting
import org.elasticsearch.cluster.routing.OperationRouting2
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
      if (shardId == 142) {
        println(line)
        pw.write(line)
        pw.write("\n")
      }

    }
    pw.close

    // println(
    //   "Hello, world",
    //   metaData.getRoutingFactor(),
    //   metaData.isRoutingPartitionedIndex(),
    //   metaData.getRoutingNumShards(),
    //   metaData.getRoutingPartitionSize()
    // )

    // val workspaceId = "cfef20fc-56fd-4f46-a1c5-3d550c3daa30"
    // val shardId =
    //   OperationRouting2.generateShardId(metaData, "1sfsdf234", workspaceId);

    // val hash = Murmur3HashFunction.hash(workspaceId)
    // println(hash)

    // // we don't use IMD#getNumberOfShards since the index might have been shrunk such that we need to use the size
    // // of original index to hash documents
    // val shardId2 = Math.floorMod(
    //   hash,
    //   512
    // )

    // println(workspaceId, shardId, shardId2)

  }
}
